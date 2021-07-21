package com.cloud.web.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.LinkedList;

/**
 * @author: heyongliu
 * @date: 2021/7/9 9:34
 * @description:
 */
public class BIOSocket {

    private static LinkedList<SocketChannel> clients = new LinkedList<>();

    private static void startClientChannelHandleThread(){
        new Thread(() -> {
            while (true){
                ByteBuffer buffer = ByteBuffer.allocateDirect(4096);

                //处理客户端连接
                for (SocketChannel c : clients) {
                    // 非阻塞, >0 表示读取到的字节数量, 0或-1表示未读取到或读取异常
                    int num = 0;
                    try {
                        num = c.read(buffer);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    if (num > 0) {
                        buffer.flip();
                        byte[] clientBytes = new byte[buffer.limit()];
                        //从缓冲区 读取到内存中
                        buffer.get(clientBytes);

                        System.out.println(c.socket().getPort() + ":" + new String(clientBytes));

                        //清空缓冲区
                        buffer.clear();
                    }
                }
            }
        }).start();
    }

    public static void main(String[] args) throws IOException {
        //new socket,开启监听
        ServerSocketChannel socketChannel = ServerSocketChannel.open();
        socketChannel.bind(new InetSocketAddress(9090));
        //设置阻塞接受客户端连接
        socketChannel.configureBlocking(true);

        //开始client处理线程
        startClientChannelHandleThread();

        while (true) {
            //接受客户端连接; 非阻塞，无客户端返回null(操作系统返回-1)
            SocketChannel client = socketChannel.accept();

            if (client == null) {
                //System.out.println("no client");
            } else {
                //设置读非阻塞
                client.configureBlocking(false);

                int port = client.socket().getPort();
                System.out.println("client port :" + port);

                clients.add(client);
            }
        }
    }

    //public static void main(String[] args) throws IOException {
    //    ServerSocket serverSocket = new ServerSocket(8090);
    //    System.out.println("step1: new ServerSocket ");
    //    while (true) {
    //        Socket client = serverSocket.accept();
    //        System.out.println("step2: client\t" + client.getPort());
    //        new Thread(() -> {
    //            try {
    //                InputStream in = client.getInputStream();
    //                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
    //                while (true) {
    //                    System.out.println(reader.readLine());
    //                }
    //            } catch (IOException e) {
    //                e.printStackTrace();
    //            }
    //        }).start();
    //    }
    //}
}
