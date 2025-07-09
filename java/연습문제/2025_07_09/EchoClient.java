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
