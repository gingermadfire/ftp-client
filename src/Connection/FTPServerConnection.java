package Connection;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class FTPServerConnection implements ServerConnection{

     URLConnection connection;

     @Override
     public void connect(String login, String password, String IPAddress,String endPoint) {

          String path = String.format("ftp://%s:%s@%s/%s;type=i", login, password, IPAddress, endPoint);

          try {
               connection = new URL(path).openConnection();

          } catch (IOException e) {
               System.out.println(e.getMessage());
          }
     }

     public InputStream findInputStream() throws IOException {
          return connection.getInputStream();
     }

     public OutputStream findOutputStream() throws IOException {
          return connection.getOutputStream();
     }
}
