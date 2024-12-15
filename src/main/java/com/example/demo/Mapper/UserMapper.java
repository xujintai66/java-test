package com.example.demo.Mapper;

import com.example.demo.entity.Form;
import com.example.demo.entity.User;
import com.example.demo.entity.isChecked;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    //用于用户登录
    @Select("select * from test11 where username = #{username} and password = #{password}")
    List<User> find(String username ,String password);

    //用于查找已经提交的表格
    @Select("select * from info_table")
    List<Form> findForm();

    //用于提交表格
    @Insert("INSERT into info_table values (#{laboratory},#{name},#{stuId},#{major},#{phone},#{startTime},#{endTime},#{device},#{teacher},#{project},#{result})")
    int InsertUser(Form form);

    //如果表格已经存在，则改为更新表格
    @Update("UPDATE info_table SET laboratory = #{laboratory}, name = #{name}, major = #{major}, phone = #{phone}, startTime = #{startTime}, endTime = #{endTime}, device = #{device}, teacher = #{teacher}, project = #{project}, result = #{result} WHERE stuId = #{stuId}")
    int updateUser(Form form);

    //用于查找指定学生的提交表格
    @Select("SELECT * FROM info_table WHERE stuId = #{stuId}")
    Form findUserByStuId(String stuId);

    //用于在完成流程后删除该学生提交的表格
    @Delete("delete from info_table where stuId = #{stuId}")
    int deleteUserByStuId(String stuId);

    //用于查询审核状态
    @Select("select * from ischeck_table where stuId = #{stuId}")
    isChecked isCheckByStuId(String stuId);

    //提交的时候把审核状态也提交
    @Insert("insert into ischeck_table (stuId,isCheck) values (#{stuId},0)")
    int insertCheck(String stuId);

    //如果已经存在则改为更新表单
    @Update("update ischeck_table set isCheck = #{isCheck},reason = #{reason} where stuId = #{stuId}")
    int updateCheck(String stuId,int isCheck,String reason);

    //返回已经批改过的学生号
    @Select("select stuId from ischeck_table where isCheck >=1")
    List<String> findIsCheck();
}
