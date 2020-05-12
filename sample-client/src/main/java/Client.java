import lombok.extern.slf4j.Slf4j;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


/**
 * @author zhengquan
 */
@Slf4j
public class Client {

    public static void main(String[] args) {
        try {
            //建立接收端
            DatagramSocket ds = new DatagramSocket(55555);
            while (true) {
                //建立数据包
                byte[] buf = new byte[1024];
                DatagramPacket dp = new DatagramPacket(buf, buf.length);
                //调用Socket的接收方法
                ds.receive(dp);
                //解析数据
                InetAddress address = dp.getAddress();
                String send_ip = address.getHostAddress();
                int send_port = dp.getPort();
                byte[] data_buf = dp.getData();
                int length = dp.getLength();

                //将字节数据,组装为字符串
                String data = new String(data_buf, 0, length);
                if ("close".equals(data)) {
                    break;
                }
                System.out.println("收到来自" + send_ip + ",端口号为" + send_port + "程序的数据:" + data);
            }
            //关闭Socket
            ds.close();
        } catch (Exception e) {

        }
    }
}
