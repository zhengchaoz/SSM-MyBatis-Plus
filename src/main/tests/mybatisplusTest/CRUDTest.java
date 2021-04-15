package mybatisplusTest;

import cn.ssm.mapper.EmployeeMapper;
import cn.ssm.model.Employee;
import com.baomidou.mybatisplus.plugins.Page;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @user 郑超
 * @date 2021/4/15
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-mybatis.xml"})
public class CRUDTest {

    @Autowired
    private DataSource dataSource;

    @Ignore
    @Test
    public void test() throws SQLException {
        System.out.println("dataSource*****" + dataSource.getConnection());
    }

    @Autowired
    private EmployeeMapper employeeMapper;

    @Ignore
    @Test
    public void testAdd() {
        Employee employee = new Employee(null, "东方不败", "dfbb@163.com", 1, 20);
        employeeMapper.insert(employee);
        // mybatisPlus会自动把当前插入对象在数据库中的id写回到该实体中
        System.out.println(employee.getId());
    }

    @Ignore
    @Test
    public void testModify() {
        Employee employee = new Employee();
        employee.setId(2);
        employee.setLastName("武则天");
        employeeMapper.updateById(employee);// 根据id进行更新，没有传值的属性就不会更新
//        employeeMapper.updateAllColumnById(employee);// 根据id进行更新，没传值的属性就更新为null
    }

    @Ignore
    @Test
    public void testQuery() {
        Employee employee = new Employee(1, "东方不败");
        // 若是数据库中符合传入的条件的记录有多条，那就不能用这个方法，会报错
        Employee one = employeeMapper.selectOne(employee);
        System.out.println(one);
    }

    @Ignore
    @Test
    public void testQueryList() {
        HashMap<String, Object> employee = new HashMap<>();
        employee.put("gender", 1);// 写表中的列名
        // 查询条件用map集合封装，columnMap，写的是数据表中的列名，而非实体类的属性名
        List<Employee> list = employeeMapper.selectByMap(employee);
        list.forEach(System.out::println);
    }

    //    @Ignore
    @Test
    public void testQueryPage() {
        // 这个分页其实并不是物理分页，而是内存分页
        // 也就是说，查询的时候并没有limit语句
        // 等配置了分页插件后才可以实现真正的分页
        List<Employee> employees = employeeMapper.selectPage(new Page<>(1, 2), null);
        System.out.println(employees);
    }

    @Ignore
    @Test
    public void testDelete() {
        // 根据主键删除
        employeeMapper.deleteById(1);
        // 根据条件删除
        Map<String, Object> columnMap = new HashMap<>();
        columnMap.put("gender", 0);
        columnMap.put("age", 18);
        employeeMapper.deleteByMap(columnMap);

        List<Integer> idList = new ArrayList<>();
        idList.add(1);
        idList.add(2);
        employeeMapper.deleteBatchIds(idList);
    }
}
