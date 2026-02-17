package com.Mybatis;

import com.jdbc.Student;
import com.jdbc.Teacher;

import java.util.List;

public interface TestMapper {
    List<Student> selectStudent();
    //如何才能让 mybatis知道 我们的接口 和 xml文件是关联的？
    Student getStudentBySid(int sid);

    //插入一条数据 返回值为int 表示生效多少行
    int addStudent(Student student);

    //删除数据
    int deleteStudent(int sid);

    Teacher getTeacherByTid(int tid);
}
