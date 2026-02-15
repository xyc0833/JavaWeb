package com.network;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
//了解socket技术 ：它是计算机之间进行通信的一种约定或一种方式
//而Java就对socket底层支持进行了一套完整的封装，我们可以通过Java来实现Socket通信。
//要实现Socket通信，我们必须创建一个数据发送者和一个数据接收者，也就是客户端和服务端，
//我们需要提前启动服务端，来等待客户端的连接，而客户端只需要随时启动去连接服务端即可！

//模拟服务端
public class Main_A {
    public static void main(String[] args) {
        //将服务端创建在端口8080上
        try(ServerSocket server = new ServerSocket(8080)){
            System.out.println("正在等待客户连接。。。");
            //当没有客户端连接时，线程会阻塞，直到有客户端连接为止
            Socket socket = server.accept();
            System.out.println("客户端已经连接" + socket.getInetAddress().getHostAddress());
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("接收到客户端信息");
            System.out.println(reader.readLine());
            //模拟服务端给客户端发送数据
            OutputStreamWriter writer = new OutputStreamWriter(socket.getOutputStream());
            writer.write("已收到！ ");
            writer.flush();

            socket.close();//和服务端TCP连接完成之后，记得关闭socket
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}