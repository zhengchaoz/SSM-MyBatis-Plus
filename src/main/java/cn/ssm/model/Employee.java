package cn.ssm.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.*;

/**
 * @user Administrator
 * @date 2021/4/15
 */
@Data// get set 方法
@NoArgsConstructor// 无参构造函数
@AllArgsConstructor// 全参构造函数
@ToString
@EqualsAndHashCode
//@TableName(value = "tb_employee")// 指定表名
public class Employee {

    // value与数据库主键列名一致，若实体类属性名与表主键列名一致可省略value
//    @TableId(value = "id", type = IdType.AUTO)// 指定自增策略
    private Integer id;
    // 若没有开启驼峰命名，或者表中列名不符合驼峰规则，可通过该注解指定数据库表中的列名
//    @TableField(value = "last_name", exist = true)// exist标明数据表中有没有对应列
    private String lastName;
    private String email;
    private Integer gender;
    private Integer age;

    public Employee(Integer id) {
        this.id = id;
    }

    public Employee(Integer id, String lastName) {
        this.id = id;
        this.lastName = lastName;
    }
}
