package com.Mybatis;

import com.jdbc.Student;
import com.jdbc.Teacher;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.cache.decorators.FifoCache;

import java.util.List;
//@CacheNamespace(readWrite = false,eviction = FifoCache.class)
public interface TestMapper {
    List<Student> selectStudent();
    //如何才能让 mybatis知道 我们的接口 和 xml文件是关联的？
    Student getStudentBySid(int sid);

    //插入一条数据 返回值为int 表示生效多少行
    //int addStudent(Student student);

    //删除数据
    int deleteStudent(int sid);

    Teacher getTeacherByTid(int tid);

    //可以通过注解的方式不写xml
    //简化xml的配置

    @Insert("insert into student(sid,name,sex) values(#{sid},#{name}, #{sex})")
    int addStudent(Student student);


    //使用注解实现复杂查询
    //getTeacherByid对应的例子
    @Results({
            @Result(id = true,column = "tid",property = "tid"),
            @Result(column = "name",property = "name"),
            @Result(column = "tid",property = "studentList",many = @Many(select = "getStudentByTid02"))
    })
    @Select("select * from teacher where tid = #{tid}")
    Teacher getTeacherByTid02(int tid);

    @Select("select * from student inner join teach on student.sid = teach.sid where tid = #{tid}")
    List<Student> getStudentByTid02(int tid);
}
