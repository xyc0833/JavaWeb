package com.network;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Http_xyc {
    public static void main(String[] args) {
        try(ServerSocket server = new ServerSocket(8080)){
            System.out.println("正在等待客户连接。。。");
            //当没有客户端连接时，线程会阻塞，直到有客户端连接为止
            Socket socket = server.accept();
            System.out.println("客户端已经连接：" + socket.getInetAddress().getHostAddress());
            //InputStream in = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("接收到客户端数据：");
//            while(true){
//                int i = in.read();
//                if(i == -1)break;
//                System.out.print((char) i);
//            }
            //ready是判断当前流中是否还有可读内容
            while(reader.ready()){
                System.out.println(reader.readLine());
            }
            //按照HTTP协议的规则，返回一个规范的响应文本
            OutputStreamWriter writer = new OutputStreamWriter(socket.getOutputStream());
            //200是响应码，Http协议规定200为接受请求，400为错误的请求，404为找不到此资源（不止这些，还有很多）
            writer.write("HTTP/1.1 200 Accepted\r\n");
            //在请求头写完之后还要进行一次换行，然后写入我们的响应实体（会在浏览器上展示的内容）
            writer.write("\r\n");
            writer.write("hello world");
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
