package util;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;
import java.sql.*;

public class Database{
    public String url;
    LockWrapper lock = new LockWrapper(new ReentrantLock());
    public class Connection implements AutoCloseable{
        Database db;
        java.sql.Connection conn;
        public Connection(Database db) throws SQLException{
            this.db = db;
            conn = DriverManager.getConnection(db.url);
            setAutoCommit(false);
        }

        public void setAutoCommit(boolean autoCommit) throws SQLException{
            conn.setAutoCommit(autoCommit);
        }

        public Statement createStatement() throws SQLException{
            return conn.createStatement();
        }

        public PreparedStatement prepareStatement(String query) throws SQLException{
            return conn.prepareStatement(query);
        }

        public void close() throws InterruptedException{
            db.returnConnection(this);
        }

        public void commit() throws SQLException{
            conn.commit();
        }
    }
    public Queue<Connection> pool = new LinkedList<Connection>();
    int poolSize = 0;
    public Database(String url){
        this.url = url;
    }

    public void returnConnection(Connection conn) throws InterruptedException{
		try (LockWrapper lock = this.lock.lock(10)){
            pool.add(conn);
            ++poolSize;
		} 
    }

    public Connection createConnection() throws SQLException{
        return new Connection(this);
    }

    public Connection getConnection() throws InterruptedException, SQLException{
        Connection conn = null;
		try (LockWrapper lock = this.lock.lock(10)){
            if (poolSize > 0){
                conn = pool.remove();
                --poolSize;
            }else{
                conn = createConnection();
            }
        }
        return conn;
    }
    public Connection getConnection(boolean autoCommit) throws InterruptedException, SQLException{
        Connection conn = getConnection();
        conn.setAutoCommit(autoCommit);
        return conn;
    }
}