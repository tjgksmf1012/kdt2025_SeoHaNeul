package network;

import java.io.*;
import java.net.*;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(5000);
        System.out.println("연결 대기 중.....");

        Socket connection = server.accept();
        System.out.println("메시지를 기다리는 중.....");

        InputStream in = connection.getInputStream();
        OutputStream out = connection.getOutputStream();

        byte[] buf = new byte[1024];

        while (true) {
            int len = in.read(buf);
            if (len == -1) break;

            String msg = new String(buf, 0, len, "UTF-8");
            if (msg.contains("quit")) break;
            System.out.print(new String(msg.getBytes("UTF-8"), "UTF-8"));

            out.write(msg.getBytes("UTF-8"));
            out.flush();
        }

        System.out.println("서버 종료");
        in.close();
        out.close();
        connection.close();
        server.close();
    }
}
