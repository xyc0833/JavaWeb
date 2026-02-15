package com.network;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

//服务端
public class File_Server {
    public static void main(String[] args) {
        //建立连接
        try(ServerSocket server  = new ServerSocket(8080)){
            Socket socket = server.accept();
            InputStream stream = socket.getInputStream();
            FileOutputStream fileOutputStream = new FileOutputStream("net/xyc.md");
            byte[] bytes = new byte[1024];
            int i;
            while((i=stream.read(bytes)) != -1){
                // 把bytes里「0开始的i个字节」写入Socket输出流
                fileOutputStream.write(bytes,0,i);
            }
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


//        try(ServerSocket server = new ServerSocket(8080)){
//            System.out.println("正在等待客户连接。。。");
//            //当没有客户端连接时，线程会阻塞，直到有客户端连接为止
//            Socket socket = server.accept();
//            System.out.println("客户端已经连接：" + socket.getInetAddress().getHostAddress());
//            InputStream in = socket.getInputStream();
//            System.out.println("接收到客户端数据：");
//            while(true){
//                int i = in.read();
//                if(i == -1)break;
//                System.out.print((char) i);
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }
}
