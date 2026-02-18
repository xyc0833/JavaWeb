package com.Mybatis;

import com.jdbc.Student;
import lombok.SneakyThrows;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Proxy;
import java.util.List;

public class Main {
    @SneakyThrows // 因为用到了文件io流 使用lombok注解自动生成try-catch代码块
    public static void main(String[] args) throws FileNotFoundException {
        Student student001;
        //通过文件io流的方式读取
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(new FileInputStream("mybatis-config.xml"));
        //try (SqlSession sqlSession = sqlSessionFactory.openSession(true)){
            try (SqlSession sqlSession = MybatisUtil.getSession(true)){
                //暂时还没有业务
                List<Student> students =  sqlSession.selectList("selectStudent");
                //双冒号： 方法引用
                students.forEach(System.out::println);
                //System.out::println 在这里引用的是 java.io.PrintStream 类中的 public void println(Object x) 方法
                // （System.out 是 PrintStream 类型的对象）。

                System.out.println(" ================================= ");
                System.out.println();
                //通过mapper绑定接口的方式 访问
                TestMapper testMapper = sqlSession.getMapper(TestMapper.class);
                testMapper.selectStudent().forEach(System.out::println);

                System.out.println(testMapper.getClass());

                //TestMapper testMapper02 = sqlSession.getMapper(TestMapper.class);
                System.out.println(testMapper.getStudentBySid(1003));

                //添加操作
                //System.out.println(testMapper.addStudent(new Student().setSid(8008).setName("xyccc").setSex("男")));

                //删除操作
                //System.out.println(testMapper.deleteStudent(1005));


                //复杂查询操作 ：一个老师可以教授多个学生，一次性将老师的学生全部映射给此老师的对象
                System.out.println(testMapper.getTeacherByTid(101));


                //两次得到的是同一个Student对象，也就是说我们第二次查询并没有重新去构造对象，而是直接得到之前创建好的对象。
                Student s1 = testMapper.getStudentBySid(1);
                Student s2 = testMapper.getStudentBySid(1);//相当于开了一级缓存
                //如果一级缓存中有这个数据的话 不用再走数据库了
                System.out.println(s1 == s2); // true
                student001 = testMapper.getStudentBySid(2);
                //如果中间有 其他的插入操作 前面的s1 == s2就是false了
                //一级缓存，在进行DML操作后，会使得缓存失效




            }
        //}

        //在xml中开启二级缓存 不同的sqlsession对话之间也能得到缓存的结果
        try(SqlSession sqlSession2 = MybatisUtil.getSession(true) ){
            TestMapper testMapper2 = sqlSession2.getMapper(TestMapper.class);
            Student s02 = testMapper2.getStudentBySid(1);
            System.out.println(student001 == s02); //true


            //使用注解的方式 添加数据
            //System.out.println(testMapper2.addStudent(new Student().setSid(5001).setName("yyh").setSex("男")));

            System.out.println(testMapper2.getTeacherByTid02(101));
        }


        //代理的方式 卖西瓜
        //静态代理，也就是说我们需要提前知道接口的定义并进行实现才可以完成代理
        //Shopper shopper = new ShopperProxy(new ShopperImpl());

//        shopper.saleWatermelon("小强");

        //动态代理
        Shopper impl = new ShopperImpl();
        Shopper shopper = (Shopper) Proxy.newProxyInstance(impl.getClass().getClassLoader(),
                impl.getClass().getInterfaces(), new ShopperProxy(impl));
        shopper.saleWatermelon("小强");
        System.out.println(shopper.getClass());
    }
}
