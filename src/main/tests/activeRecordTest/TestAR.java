package activeRecordTest;

import cn.ssm.model.User;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Active Record(活动记录)，是一种领域模型模式，特点是一个模型类对应关系型数据库中的一个表，而模型类的一个实例对应表中的一行记录。
 * ActiveRecord 一直广受动态语言（ PHP 、 Ruby 等）的喜爱，而 Java 作为准静态语言，对于 ActiveRecord 往往只能感叹其优雅，
 * 所以 MP 也在 AR 道路上进行了一定的探索，仅仅需要让实体类继承 Model 类且实现主键指定方法，即可开启 AR 之旅。
 *
 * @user 郑超
 * @date 2021/4/16
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-mybatis.xml"})
public class TestAR {

    @Ignore
    @Test
    public void testArInsert() {
        User user = new User(null, "林青霞", 22, 1);
        boolean insert = user.insert();
        System.out.println(insert);
    }

    @Ignore
    @Test
    public void testArUpdate() {
        User user = new User();
        user.setId(1);
        user.setName("刘亦菲");
        boolean id = user.updateById();
        System.out.println(id);
    }

    @Ignore
    @Test
    public void testArSelect() {
        User user = new User();
        // 根据id查询
//        user = user.selectById(1);
        // 或者这样用
//        user.setId(1);
//        user.selectById();
//        System.out.println(user);
        // 查询所有
//        List<User> users = user.selectAll();
        // 根据条件查询
//        List<User> users = user.selectList(new EntityWrapper<User>().like("name", "刘"));
//        users.forEach(System.out::println);
        // 查询符合条件的总数
        int count = user.selectCount(new EntityWrapper<User>().eq("gender", 1));
        System.out.println(count);
    }

    @Ignore
    @Test
    public void testArDelete() {
        User user = new User();
        // 删除数据库中不存在的数据也是返回true
        // 根据id删除数据
//        boolean result = user.deleteById(1);
        // 或者这样写
//        user.setId(1);
//        boolean result = user.deleteById();

        // 根据条件删除
        boolean result = user.delete(new EntityWrapper<User>().like("name", "玲"));
        System.out.println(result);
    }

    @Test
    public void testArPage() {
        User user = new User();
        Page<User> page =
                user.selectPage(new Page<>(1, 4),
                        new EntityWrapper<User>().like("name", "刘"));
        List<User> users = page.getRecords();
        System.out.println(users);
    }
}
