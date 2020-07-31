package com.example.testunit.demo.socket;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * socket连接接客户端，先启动客户端：java SocketSver
 */
public class SocketServer {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(11222);
        Socket s = serverSocket.accept();
        System.out.println("ip: "+s.getInetAddress().getHostAddress());
        InputStream is = s.getInputStream();
        byte[] bytes = new byte[1024];
        int read = is.read(bytes);
        System.out.println("server received: "+new String(bytes,0,read));
    }
}
