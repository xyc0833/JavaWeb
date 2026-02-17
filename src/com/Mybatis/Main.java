package com.Mybatis;

import com.jdbc.Student;
import lombok.SneakyThrows;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

public class Main {
    @SneakyThrows // 因为用到了文件io流 使用lombok注解自动生成try-catch代码块
    public static void main(String[] args) throws FileNotFoundException {
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
            }
        //}


    }
}
