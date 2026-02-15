package com.network;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

//客户端
public class Main_B {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 8080);
             Scanner scanner = new Scanner(System.in)){
            //使用socket进行数据传输
            System.out.println("已连接到服务端！");
            OutputStream stream = socket.getOutputStream();
            //通过转换流来帮助我们快速写入内容
            OutputStreamWriter writer = new OutputStreamWriter(stream);
            System.out.println("请输入要发送给服务端的内容：");
            String text = scanner.nextLine();
            writer.write(text+'\n');   //因为对方是readLine()这里加个换行符
            writer.flush();//刷新： 强制清空缓冲区，把数据推出去
            System.out.println("数据已发送："+text);
            //同理，既然服务端可以读取客户端的内容，客户端也可以在发送后等待服务端给予响应：
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("收到服务器返回："+reader.readLine());
        }catch (IOException e){
            System.out.println("服务端连接失败！");
            e.printStackTrace();
        }finally {
            System.out.println("客户端断开连接！");
        }
    }
}
