import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.rmi.server.ExportException;

public class TCPClients {
    public static void main(String argv[]) throws Exception {

       byte[] message = new byte []{(byte) 0xda,0x7a,0x0,0x0,0x0,0x5,0x1,0x1,0x2,0x3,0x4};
//       byte[] message = new byte []{(byte) 0xe1,0x10,0x0,0x0,0x0,0x5,0x1,0x1,0x2,0x3,0x4};
//       byte[] message = new byte []{(byte) 0x0b,0x1e,0x0,0x0,0x0,0x5,0x1,0x1,0x2,0x3,0x4};
       Socket clientSocket = new Socket("localhost", 6000);
       DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
       while (true)
       {
           try{
               outToServer.write( message);
               Thread.sleep(10000);
           }catch (ExportException e)
           {
               e.printStackTrace();
               break;
           }
       }
        clientSocket.close();
    }
}
