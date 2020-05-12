import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


/**
 * @author Administrator
 */
@Slf4j
public class Server {


    public static void main(String[] args) throws IOException {
        DatagramSocket ds = new DatagramSocket();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        InetAddress address = InetAddress.getByName("127.0.0.1");
        int port = 55555;
        String msg;
        while ((msg = reader.readLine()) != null) {
            byte[] buf = msg.getBytes();
            DatagramPacket packet = new DatagramPacket(buf, buf.length,address,port);
            ds.send(packet);
            if("close".equals(msg)){
                break;
            }
        }
        ds.close();

    }
}
