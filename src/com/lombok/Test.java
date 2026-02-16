package com.lombok;

import com.jdbc.Student;
import lombok.Cleanup;
import lombok.SneakyThrows;

import java.io.FileInputStream;
import java.io.InputStream;

public class Test {
    //使用@SneakyThrows来自动生成try-catch代码块
    @SneakyThrows
    public static void main(String[] args) {
        Student s1 = new Student(15,"xyc","nan");
        //使用@Cleanup作用与局部变量，在最后自动调用其close()方法（可以自由更换）
        @Cleanup
        InputStream inputStream = new FileInputStream("lombok.jar");
        inputStream.close();
    }
}
