package cn.ssm.model;

import com.baomidou.mybatisplus.activerecord.Model;
import lombok.*;

import java.io.Serializable;

/**
 * @user 郑超
 * @date 2021/4/16
 */
@Data
@NoArgsConstructor// 无参构造函数
@AllArgsConstructor// 全参构造函数
@ToString
@EqualsAndHashCode(callSuper = false)
public class User extends Model<User> {

    private Integer id;
    private String name;
    private Integer age;
    private Integer gender;

    //重写这个方法，return当前类的主键
    @Override
    protected Serializable pkVal() {
        return id;
    }
}
