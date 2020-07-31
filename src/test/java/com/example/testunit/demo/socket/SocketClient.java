package com.example.testunit.demo.socket;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * socket连接用户端，后启动用户端：java SocketTest
 */
class SocketClient {
    public static void main(String[] args) throws IOException {
        String msg = "I'll come back!";
        Socket socket = new Socket("192.168.31.248", 11222);
        OutputStream os = socket.getOutputStream();
        os.write(msg.getBytes());
        System.out.println("client send: " + msg);
        socket.close();
    }
}