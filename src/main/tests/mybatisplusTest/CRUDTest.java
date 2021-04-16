package mybatisplusTest;

import cn.ssm.mapper.EmployeeMapper;
import cn.ssm.model.Employee;
import com.baomidou.mybatisplus.enums.SqlLike;
import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
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

    @Ignore
    @Test
    public void testQueryPage() {
        // 这个分页其实并不是物理分页，而是内存分页
        // 也就是说，查询的时候并没有limit语句
        // 等配置了分页插件后才可以实现真正的分页
        List<Employee> employees = employeeMapper.selectPage(new Page<>(2, 2), null);
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

    @Ignore
    @Test
    public void testEntityWrapper() {
//        List<Employee> employees = employeeMapper.selectPage(new Page<Employee>(0, 3),
//                new EntityWrapper<Employee>()
//                        .between("age", 18, 50)
//                        .eq("gender", 0)
//                        .like("last_name", "李", SqlLike.RIGHT)
//        );
//        employees.forEach(System.out::println);

//        List<Employee> employees = employeeMapper.selectList(new EntityWrapper<Employee>()
//                .eq("gender", "0")
//                .like("last_name", "武")
//                .orNew()// .or()和.orNew() 有点区别
//                .like("email", "163")
//        );
//        employees.forEach(System.out::println);

        List<Employee> employees = employeeMapper.selectList(
                new EntityWrapper<Employee>()
                        .eq("gender", 0)
                        .orderBy("age")// orderBy()是升序，asc；orderByDesc()是降序
                        .last("desc limit 1,3")// 在sql语句后面拼接last里面的内容(改为降序，同时分页)
        );
        employees.forEach(System.out::println);

//        List<Employee> selectPage = employeeMapper.selectPage(
//                new Page(1, 2),
//                Condition.create().between("age", 18, 50)
//                        .eq("gender", "0")
//        );
    }

    @Ignore
    @Test
    public void testEntityWrapperUpdate() {
        Employee employee = new Employee();
        employee.setLastName("苍老师");
        employee.setEmail("cjk@sina.com");
        employee.setGender(0);
        Integer id = employeeMapper.update(employee,
                new EntityWrapper<Employee>()
                        .eq("id", "2")
        );
        System.out.println(id);

        Integer delete = employeeMapper.delete(
                new EntityWrapper<Employee>()
                        .eq("last_name", "tom")
                        .eq("age", 16)
        );
    }

    @Test
    public void testPagePlugin() {
        // 配置了分页插件后，还是和以前一样的使用selectPage方法，
        // 但是现在就是真正的物理分页了，sql语句中有limit了
        Page<Employee> page = new Page<>(0, 4);
        List<Employee> employeeList =
                employeeMapper.selectPage(page, null);
        System.out.println(employeeList);
        System.out.println("================= 相关的分页信息 ==================");
        System.out.println("总条数:" + page.getTotal());
        System.out.println("当前页码:" + page.getCurrent());
        System.out.println("总页数:" + page.getPages());
        System.out.println("每页显示条数:" + page.getSize());
        System.out.println("是否有上一页:" + page.hasPrevious());
        System.out.println("是否有下一页:" + page.hasNext());
        //还可以将查询到的结果set进page对象中
        page.setRecords(employeeList);
        employeeList.forEach(System.out::println);
    }
}
