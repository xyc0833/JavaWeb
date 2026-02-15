package com.network;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

//客户端
public class File_Clinet {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 8080)){
            FileInputStream fileInputStream = new FileInputStream("README.md");
            OutputStream stream = socket.getOutputStream();
            byte[] bytes = new byte[1024];
            int i;
            while ((i = fileInputStream.read(bytes)) != -1) {
                stream.write(bytes, 0, i);
            }
            fileInputStream.close();
            stream.flush();
        } catch (IOException e) {
            System.out.println("服务端连接失败！");
            e.printStackTrace();
        }
    }
}
