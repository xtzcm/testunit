package com.example.testunit.demo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * socket连接用户端，后启动用户端：java SocketTest
 */
class SocketClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("192.168.31.248", 11222);
        OutputStream os = socket.getOutputStream();
        os.write("I'll come back!".getBytes());
        System.out.println("client: message send success!");
        socket.close();
    }
}

/**
 * socket连接接客户端，先启动客户端：java SocketSver
 */
class SocketSver{
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(11222);
        Socket s = serverSocket.accept();
        System.out.println("ip:"+s.getInetAddress().getHostAddress());
        InputStream is = s.getInputStream();
        byte[] bytes = new byte[1024];
        int read = is.read(bytes);
        System.out.println("server received: "+new String(bytes,0,read));
    }
}
