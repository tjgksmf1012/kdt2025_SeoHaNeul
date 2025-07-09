서버
```
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
```
클라이언트
```
package network;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class EchoClient {
    public static void main(String[] args) throws IOException {
        Socket client = null;
        OutputStream out = null;

        try {
            client = new Socket();
            System.out.println("에코 서버와 연결 시도.....");
            client.connect(new InetSocketAddress("localhost", 5000), 3000);
            System.out.println("에코 서버와 연결 성공.....");
        } catch (Exception e) {
            System.out.println("연결 실패: " + e.getMessage());
            return;
        }

        out = client.getOutputStream();

        Scanner s = new Scanner(System.in, "UTF-8");
        String msg;

        System.out.print("보낼 메시지가 있나요?");
        while((msg = s.nextLine()) != null){
            if(msg.contains("quit")) break;
            // 문자열을 UTF-8 바이트로 변환해서 전송, 마지막에 개행 추가
            byte[] data = (msg + System.lineSeparator()).getBytes("UTF-8");
            out.write(data);
            out.flush();
            System.out.print("보낼 메시지가 더 있나요? ");
        }

        System.out.println("클라이언트 종료");
        out.close();
        s.close();
        client.close();
    }
}
```
