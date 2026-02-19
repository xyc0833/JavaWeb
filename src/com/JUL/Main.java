package com.JUL;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.logging.*;

public class Main {
    public static void main(String[] args) throws IOException {
        //首先获取日志打印器
        Logger logger = Logger.getLogger(Main.class.getName());
        // 调用info来输出一个普通的信息，直接填写字符串即可
        logger.info("我是一个普通的日志");

        logger.log(Level.SEVERE, "严重的错误", new IOException("我就是错误"));
        logger.log(Level.WARNING, "警告的内容");
        logger.log(Level.INFO, "普通的信息");
        logger.log(Level.CONFIG, "级别低于普通信息");

        //修改日志级别
        logger.setLevel(Level.CONFIG);
        //不使用父日志处理器
        logger.setUseParentHandlers(false);
        //使用自定义日志处理器
        //为了让颜色变回普通的颜色，通过代码块在初始化时将输出流设定为System.out
        //写法1：双层大括号（匿名内部类+初始化块）
        ConsoleHandler handler =  new ConsoleHandler(){
            {
                setOutputStream(System.out);
            }
        };
        //上面的写法等价于
        // 写法2：常规写法（创建对象+单独调用方法）
//        ConsoleHandler handler2 = new ConsoleHandler();
//        handler2.setOutputStream(System.out); // 单独初始化

        //addHandler() 是 Logger 类提供的方法 作用是给日志记录器绑定一个处理器
        logger.addHandler(handler);
        //创建匿名内部类实现自定义的格式
        handler.setFormatter(new Formatter() {
            @Override
            public String format(LogRecord record) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
                String time = format.format(new Date(record.getMillis()));  //格式化日志时间
                String level = record.getLevel().getName();  // 获取日志级别名称
                // String level = record.getLevel().getLocalizedName();   // 获取本地化名称（语言跟随系统）
                String thread = String.format("%10s", Thread.currentThread().getName());  //线程名称（做了格式化处理，留出10格空间）
                long threadID = record.getThreadID();   //线程ID
                String className = String.format("%-20s", record.getSourceClassName());  //发送日志的类名
                String msg = record.getMessage();   //日志消息

                //\033[33m作为颜色代码，30~37都有对应的颜色，38是没有颜色，IDEA能显示，但是某些地方可能不支持
                return "\033[38m" + time + "  \033[33m" + level + " \033[35m" + threadID
                        + "\033[38m --- [" + thread + "] \033[36m" + className + "\033[38m : " + msg + "\n";
            }
        });
        //自定义过滤规则
        //lambda表达式
        logger.setFilter(record -> !record.getMessage().contains("普通"));

        /***等价于
         *         logger.setFilter(new Filter() {
         *             @Override
         *             public boolean isLoggable(LogRecord record) {
         *                 // 核心逻辑和 Lambda 完全一致：过滤掉包含"普通"的日志记录
         *                 return !record.getMessage().contains("普通");
         *             }
         *         });
         */



        //添加输出到本地文件
        FileHandler fileHandler = new FileHandler("test.log",true);
        //设置了waring级别 waring以下的级别是不会被写入进去的
        fileHandler.setLevel(Level.WARNING);
        //设置日志的格式
        fileHandler.setFormatter(new SimpleFormatter());
        logger.addHandler(fileHandler);



        logger.log(Level.SEVERE, "严重的错误", new IOException("我就是错误"));
        logger.log(Level.WARNING, "警告的内容");
        logger.log(Level.INFO, "普通的信息");
        logger.log(Level.CONFIG, "级别低于普通信息");

        System.out.println(logger.getParent().getClass());

        //Properties配置文件 是Java的一种配置文件 格式为配置项=配置值
        //继承自HashTable 类似map对象
        Properties properties = new Properties();
        // properties.setProperty("test", "lbwnb");  //和put效果一样
        properties.put("test", "lbwnb");
        properties.store(System.out, "????");
        //properties.storeToXML(System.out, "????");  保存为XML格式
        //通过System.getProperties()获取系统的参数
        //System.getProperties().store(System.out, "系统信息：");
    }
}
