package com.example.ex6_6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket ss;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    public Server() {
        new ServerThread().start();
    }

    class ServerThread extends Thread {
        public void run() {
            try {
                ss = new ServerSocket(8888);
                System.out.println("服务器启动了");
                while (true) {
                    socket = ss.accept();
                    System.out.println("有客户端连接到服务器");
                    in = new DataInputStream(socket.getInputStream());
                    out = new DataOutputStream(socket.getOutputStream());
                    String msg = "";
                    if ((msg = in.readUTF()) != null) {
                        System.out.println(msg);
                    }
                    out.writeUTF("恭喜你，连接服务器成功！  \n");
                    sleep(500);
                    if ((msg = in.readUTF()) != null) {
                        System.out.println(msg);
                    }
                    out.writeUTF("你发来的数据服务器收到了。^_^");
                    out.flush();
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            } finally {
                try {
                    in.close();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new Server();
    }

}
