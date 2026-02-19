package com.JUL;

import lombok.extern.java.Log;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

@Log(topic = "打工是不可能打工的")
public class LoggerTest {
    public static void main(String[] args) throws IOException {
        //获取日志管理器
        LogManager manager = LogManager.getLogManager();
        //读取我们自己的配置文件
        manager.readConfiguration(new FileInputStream("logging.properties"));
        //再获取日志打印器
        Logger logger = Logger.getLogger(LoggerTest.class.getName());
        //通过自定义配置文件，我们发现默认级别不再是INFO了
        logger.config("我是一条日志信息");

        System.out.println("自动生成的Logger名称："+log.getName());
        log.info("我是日志信息");



    }


}
