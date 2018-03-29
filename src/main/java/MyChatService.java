import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;

import util.RandomString;
import util.LockWrapper;
import util.Database;
import util.Database.Connection;
import chat.*;
import chat.ClientSession;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.Timestamp;

public class MyChatService implements Chat.Iface {
    //BigInteger lastMessageId;
    LockWrapper lock = new LockWrapper(new ReentrantLock());
    
    RandomString randomString = new RandomString();
    Database db;
    String salt = "257";

    public static class ServerSession{
        public long id;
        public String session;
        public String certificate;
        public long lastPing;

        private static Map<String, ServerSession> sessions = new HashMap<String, ServerSession>();
        private static Map<Long, ServerSession> sessionsById = new HashMap<Long, ServerSession>();
        private static RandomString generator = new RandomString();

        public static boolean check(String session){
            return true;
        }

        public ServerSession(long id, String certificate){
            session = generator.nextString();
            while(sessions.containsKey(session)){
                session = generator.nextString();
            }
            this.id = id;
            this.certificate = certificate;
            ping();
            map();
        }

        public ClientSession getClientSession(){
            return new ClientSession(id, session, certificate);
        }

        public void map(){
            sessions.put(session, this);

            ServerSession old = sessionsById.get(id);
            if (old != null && sessions.containsKey(old.session)){
                sessions.remove(old.session);
            }

            sessionsById.put(id, this);
            //sessionsByCert.put(certificate, this);
        }

        public void ping(){
            this.lastPing = Instant.now().getEpochSecond();
        }
    }



    private Connection getConnection() throws Exception{
        return db.getConnection();
    }
    
    public void DBInit() throws Exception{
        db = new Database(System.getenv("JDBC_DATABASE_URL"));
        try (Connection conn = db.getConnection()){
            Statement stmt = conn.createStatement();
            stmt.execute("CREATE TABLE IF NOT EXISTS Users(id SERIAL PRIMARY KEY, username TEXT UNIQUE, hashkey TEXT, certificate TEXT, revision BIGINT DEFAULT 0)");
            stmt.execute("CREATE TABLE IF NOT EXISTS PersonalChats (first BIGINT, second BIGINT, lastId BIGINT DEFAULT 0, CONSTRAINT personalChat UNIQUE (first, second))");
            stmt.execute("CREATE TABLE IF NOT EXISTS Rooms(id SERIAL PRIMARY KEY, revision BIGINT DEFAULT 0, lastId BIGINT DEFAULT 0)");
            stmt.execute("CREATE TABLE IF NOT EXISTS RoomMemberIds(id BIGINT, userId BIGINT, CONSTRAINT roomMember UNIQUE (id, userId))");
            stmt.execute("CREATE TABLE IF NOT EXISTS Groups(id SERIAL PRIMARY KEY, name TEXT, revision BIGINT DEFAULT 0, lastId BIGINT DEFAULT 0)");
            stmt.execute("CREATE TABLE IF NOT EXISTS GroupMemberIds(id BIGINT, userId BIGINT,  CONSTRAINT groupMember UNIQUE (id, userId))");
            stmt.execute("CREATE TABLE IF NOT EXISTS Messages(id BIGINT, senderId BIGINT, chatroomType INTEGER, chatroomId BIGINT, timestamp BIGINT, contentType INTEGER, content TEXT, CONSTRAINT message UNIQUE (id, senderId, chatroomType, chatroomId))");
            stmt.execute("CREATE TABLE IF NOT EXISTS NewMessageNotifications(userId BIGINT, chatroomType INTEGER, chatroomId BIGINT,  CONSTRAINT messageNotification UNIQUE (userId, chatroomType, chatroomId))");
            conn.commit();
        }
    }
    
    public boolean checkSession(String session){
        return true;
    }

    public String md5(String s){
        try{
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.reset();
            m.update(s.getBytes("UTF-8"));
            byte[] digest = m.digest();
            BigInteger bigInt = new BigInteger(1, digest);
            return bigInt.toString(16);
        }catch (Exception ex){
            ex.printStackTrace();
            return s;
        }
    }
    
    @Override
    public ClientSession signup(String username, String password) throws ChatException {
        try{
            try (Connection conn = db.getConnection()){
                if (username.isEmpty()){
                    throw new ChatException(ErrorCode.usernameEmpty);
                }
                if (password.isEmpty()){
                    throw new ChatException(ErrorCode.passwordEmpty);
                }
                PreparedStatement stmt = conn.prepareStatement("SELECT id FROM Users WHERE username=?");
                stmt.setString(1, username);
                if (stmt.executeQuery().next()){
                    throw new ChatException(ErrorCode.usernameExists);
                }
                String hashkey = md5(username+salt+password);
                String cert = randomString.nextString();
                stmt = conn.prepareStatement("INSERT INTO Users(username, hashkey, certificate) VALUES(?, ?, ?) RETURNING id");
                stmt.setString(1, username);
                stmt.setString(2, hashkey);
                stmt.setString(3, cert);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()){
                    long id = rs.getLong("id");
                    Statement stmt2 = conn.createStatement();
                    stmt2.execute("INSERT INTO GroupMemberIds(id, userId) VALUES(0, " + id + ")");
                    conn.commit();
                    ClientSession ret = (new ServerSession(id, cert)).getClientSession();
                    return ret;
                }else{
                    throw new ChatException(ErrorCode.signupError);
                }
            }
        }catch (InterruptedException|SQLException ex){
            ex.printStackTrace();
            throw new ChatException(ErrorCode.unknownError);
        }
    }
    @Override
    public ClientSession login(String username, String password) throws ChatException {
        try{
            try (Connection conn = db.getConnection()){
                if (username.isEmpty()){
                    throw new ChatException(ErrorCode.usernameEmpty);
                }
                if (password.isEmpty()){
                    throw new ChatException(ErrorCode.passwordEmpty);
                }
                String cert = randomString.nextString();
                String hashkey = md5(username+salt+password);
                PreparedStatement stmt = conn.prepareStatement("UPDATE Users SET certificate=? WHERE username=? AND hashkey=? RETURNING id");
                stmt.setString(1, cert);
                stmt.setString(2, username);
                stmt.setString(3, hashkey);
                stmt.execute();
                ResultSet rs = stmt.getResultSet();
                if (rs.next()){
                    long id = rs.getLong("id");
                    conn.commit();
                    return (new ServerSession(id, cert)).getClientSession();
                }else{
                    throw new ChatException(ErrorCode.loginError);
                }
            }
        }catch (InterruptedException|SQLException ex){
            ex.printStackTrace();
            throw new ChatException(ErrorCode.unknownError);
        }
    }
    @Override
    public ClientSession getSession(long id, String certificate) throws ChatException{
        try{
            try (Connection conn = db.getConnection()){
                String newCert = randomString.nextString();
                PreparedStatement stmt = conn.prepareStatement("UPDATE Users SET certificate=? WHERE id=? AND certificate=?");
                stmt.setString(1, newCert);
                stmt.setLong(2, id);
                stmt.setString(3, certificate);
                if (stmt.executeUpdate () > 0){
                    conn.commit();
                    return (new ServerSession(id, newCert)).getClientSession();
                }else{
                    throw new ChatException(ErrorCode.invalidCertificate);
                }
            }
        }catch (InterruptedException|SQLException ex){
            ex.printStackTrace();
            throw new ChatException(ErrorCode.unknownError);
        }
    }
    @Override
    public User getUserById(String session, long id) throws ChatException{
        try{
            String username = "Anonymous";
            try (Connection conn = db.getConnection()){
                PreparedStatement stmt = conn.prepareStatement("SELECT username, revision FROM Users WHERE id=?");
                stmt.setLong(1, id);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()){
                    return new User(id, rs.getLong("revision"), rs.getString("username"));
                }else{
                    throw new ChatException(ErrorCode.invalidUserId);
                }
            }
        }catch (InterruptedException|SQLException ex){
            ex.printStackTrace();
            throw new ChatException(ErrorCode.unknownError);
        }
    }
    @Override
    public List<User> getUsersByIds(String session, List<Long> ids) throws ChatException{
        List<User> ret = new LinkedList<User>();
        for (Long id : ids){
            ret.add(getUserById(session, id));
        }
        return ret;
    }
    @Override
    public MessageBox getOldMessages(String session, ChatroomType chatroomType, long chatroomId, long revision, int count) throws ChatException {
        try{
            long lastMessageId = revision;
            try (Connection conn = db.getConnection()){
                PreparedStatement stmt = conn.prepareStatement("SELECT id, senderId, timestamp, contentType, content FROM Messages WHERE chatroomType = ? AND chatroomId = ? AND id < ? ORDER BY timestamp DESC LIMIT ?");
                stmt.setInt(1, chatroomType.getValue());
                stmt.setLong(2, chatroomId);
                stmt.setLong(3, revision);
                stmt.setInt(4, count);
                ResultSet rs = stmt.executeQuery();
                List<Message> list = new LinkedList<Message>();
                while(rs.next()){
                    list.add(new Message(
                        rs.getLong("id"),
                        rs.getLong("senderId"),
                        rs.getLong("timestamp"),
                        MessageContentType.findByValue(rs.getInt("contentType")),
                        rs.getString("content")
                    ));
                }
                if (list.size() > 0){
                    return new MessageBox(chatroomType, chatroomId, list, list.get(list.size()-1).id);
                }else{
                    return new MessageBox(chatroomType, chatroomId, list, 0);
                }
            }

        }catch (InterruptedException|SQLException ex){
            ex.printStackTrace();
            throw new ChatException(ErrorCode.unknownError);
        }
    }
    @Override
    public MessageBox getLastMessages(String session, ChatroomType chatroomType, long chatroomId, int count, int offset) throws ChatException {
        try{
            try (Connection conn = db.getConnection()){
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT id, senderId, timestamp, contentType, content FROM Messages WHERE chatroomType = " + chatroomType.getValue() + " AND chatroomId = " + chatroomId + " ORDER BY timestamp DESC LIMIT " + count + " OFFSET " + offset);
                List<Message> list = new LinkedList<Message>();
                while(rs.next()){
                    list.add(new Message(
                        rs.getLong("id"),
                        rs.getLong("senderId"),
                        rs.getLong("timestamp"),
                        MessageContentType.findByValue(rs.getInt("contentType")),
                        rs.getString("content")
                    ));
                }
                if (list.size() > 0){
                    return new MessageBox(chatroomType, chatroomId, list, list.get(0).id);
                }else{
                    return new MessageBox(chatroomType, chatroomId, list, 0);
                }
            }
        }catch (InterruptedException|SQLException ex){
            ex.printStackTrace();
            throw new ChatException(ErrorCode.unknownError);
        }
    }
    @Override
    public MessageBox getNewMessages(String session, ChatroomType chatroomType, long chatroomId, long revision, int count) throws ChatException {
        try{
            long lastMessageId = revision;
            try (Connection conn = db.getConnection()){
                PreparedStatement stmt = conn.prepareStatement("SELECT id, senderId, timestamp, contentType, content FROM Messages WHERE chatroomType = ? AND chatroomId = ? AND id > ? ORDER BY timestamp DESC LIMIT ?");
                stmt.setInt(1, chatroomType.getValue());
                stmt.setLong(2, chatroomId);
                stmt.setLong(3, revision);
                stmt.setInt(4, count);
                ResultSet rs = stmt.executeQuery();
                List<Message> list = new LinkedList<Message>();
                while(rs.next()){
                    list.add(new Message(
                        rs.getLong("id"),
                        rs.getLong("senderId"),
                        rs.getLong("timestamp"),
                        MessageContentType.findByValue(rs.getInt("contentType")),
                        rs.getString("content")
                    ));
                }
                if (list.size() > 0){
                    return new MessageBox(chatroomType, chatroomId, list, list.get(0).id);
                }else{
                    return new MessageBox(chatroomType, chatroomId, list, 0);
                }
            }

        }catch (InterruptedException|SQLException ex){
            ex.printStackTrace();
            throw new ChatException(ErrorCode.unknownError);
        }
    }
    @Override
    public CompleteMessage sendMessage(String session, CompleteMessage message) throws ChatException {
        try{
            try (LockWrapper lock = this.lock.lock(10)){
                if (message.message.content.length() > 1000){
                    throw new ChatException(ErrorCode.messageTooLong);
                }
                message.message.senderId = ServerSession.sessions.get(session).id;
                message.message.timestamp = Instant.now().getEpochSecond();
                
                try (Connection conn = db.getConnection()){
                    Statement stmt0 = conn.createStatement();
                    ResultSet rs0 = stmt0.executeQuery("UPDATE Groups SET lastId=lastId+1 WHERE id=0 RETURNING lastId");
                    if (rs0.next()){
                        message.message.id = rs0.getLong("lastId");
                        conn.commit();
                    }else{
                        throw new ChatException(ErrorCode.unknownError);
                    };

                    PreparedStatement stmt = conn.prepareStatement("INSERT INTO Messages(id, senderId, chatroomType, chatroomId, timestamp, contentType, content) VALUES(?,?,?,?,?,?,?) ON CONFLICT DO NOTHING");// RETURNING id");
                    stmt.setLong(1, message.message.id);
                    stmt.setLong(2, message.message.senderId);
                    stmt.setInt(3, message.chatroomType.getValue());
                    stmt.setLong(4, message.chatroomId);
                    stmt.setLong(5, message.message.timestamp);
                    stmt.setInt(6, message.message.contentType.getValue());
                    stmt.setString(7, message.message.content);
                    
                    if(stmt.executeUpdate() > 0){

                        //message.id = rs.getLong("id");
                        Statement stmt2 = conn.createStatement();
                        ResultSet rs = stmt2.executeQuery("SELECT userId FROM GroupMemberIds WHERE id=" + message.chatroomId);
                        int a = message.chatroomType.getValue();
                        long b = message.chatroomId;
                        stmt = conn.prepareStatement("INSERT INTO NewMessageNotifications(userId, chatroomType, chatroomId) VALUES(?, " + a + ", " + b + ") ON CONFLICT DO NOTHING");
                        while(rs.next()){
                            stmt.setLong(1, rs.getLong(1));
                            stmt.execute();
                        }
                        conn.commit();
                        return message;
                    }else{
                        throw new ChatException(ErrorCode.sendMessageError);
                    }
                }
            }
        } catch (InterruptedException|SQLException ex){
            ex.printStackTrace();
            throw new ChatException(ErrorCode.unknownError);
        }
    }
    @Override
    public List<Operation> fetchOperations(String session) throws ChatException {
        try{
            try (LockWrapper lock = this.lock.lock(10)){
                List<Operation> ret =  new LinkedList<Operation>();
                long userId = ServerSession.sessions.get(session).id;
                try (Connection conn = db.getConnection()){
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery("DELETE FROM NewMessageNotifications WHERE userId=" + userId + " RETURNING chatroomType, chatroomId");
                    while(rs.next()){
                        ret.add(new Operation(OperationType.notifyMessages, rs.getInt("chatroomType"), rs.getLong("chatroomId"), 0, 0));
                    }
                    conn.commit();
                }
                return ret;
            }
        } catch (InterruptedException|SQLException ex){
            ex.printStackTrace();
            throw new ChatException(ErrorCode.unknownError);
        }
    }
}
