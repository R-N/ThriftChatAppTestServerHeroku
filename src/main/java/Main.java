import com.linecorp.armeria.server.Server;
import com.linecorp.armeria.server.ServerBuilder;
import static com.linecorp.armeria.common.SessionProtocol.HTTP;
import com.linecorp.armeria.common.HttpRequest;
import com.linecorp.armeria.common.HttpResponseWriter;
import com.linecorp.armeria.common.HttpStatus;
import com.linecorp.armeria.common.MediaType;

import com.linecorp.armeria.server.ServiceRequestContext;
import com.linecorp.armeria.server.AbstractHttpService;

import com.linecorp.armeria.server.thrift.THttpService;
import com.linecorp.armeria.common.thrift.ThriftSerializationFormats;

import java.util.*;
import java.util.concurrent.*;


import org.apache.thrift.TException;
import org.apache.thrift.transport.TSSLTransportFactory;
import org.apache.thrift.transport.THttpClient;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TSSLTransportFactory.TSSLTransportParameters;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;



public class Main {
  public static Thread singleton = null;
  public static int port = 0;


  public static void StartServer() throws Exception{
    try{
      ServerBuilder sb = new ServerBuilder();
      sb.port(Main.port, HTTP);
      sb.idleTimeoutMillis(10000);
      
      // Add a simple 'Hello, world!' service.
      sb.service("/", new AbstractHttpService() {
          @Override
          protected void doGet(ServiceRequestContext ctx,
                               HttpRequest req, HttpResponseWriter res) {
              res.respond(HttpStatus.OK, MediaType.PLAIN_TEXT_UTF_8, "Hello, world!");
          }
      });
      MyChatService srv = new MyChatService();
      srv.DBInit();
      sb.service("/chat", THttpService.of(srv,
      ThriftSerializationFormats.BINARY));
      
      Server server = sb.build();
      CompletableFuture<Void> future = server.start();
      future.join();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(String... args) throws Exception {
    Main.port = Integer.valueOf(args[0]);
    if (Main.singleton == null){
      Runnable startServer = new Runnable() {
        public void run() {
          try{
            StartServer();
          } catch(Exception ex){
            ex.printStackTrace();
          }
        }
      };
      Main.singleton = new Thread(startServer);
      Main.singleton.start();
    }
  }
}