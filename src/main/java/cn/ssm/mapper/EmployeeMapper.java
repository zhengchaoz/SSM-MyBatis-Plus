package cn.ssm.mapper;

import cn.ssm.model.Employee;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author zhu
 * @since 2021-04-16
 */
public interface EmployeeMapper extends BaseMapper<Employee> {

    @Select("select * from tb_employee;")
    @Results(id = "emp", value = {
            @Result(column = "id", property = "id", id = true),
            @Result(column = "last_name", property = "lastName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "email", property = "email"),
            @Result(column = "gender", property = "gender"),
            @Result(column = "age", property = "age")
    })
    List<Employee> findAll();

    @Select("select * from tb_employee where id = #{id}")
    @ResultMap("emp")
    Employee findById(Integer id);

    @Insert("insert into tb_employee(id, last_name, email, gender, age) " +
            "VALUES(null, #{lastName}, #{email}, #{gender}, #{age})")
    int addEmployee(Employee e);

    @Update("update tb_employee " +
            "set last_name = #{lastName}, email = #{email}, gender = #{gender}, age = #{age} where id = #{id}")
    int updateEmployee(Employee e);

    @Delete("delete from tb_employee where id = #{id}")
    int deleteEmployee(Employee e);

}
