package annoTest;

import cn.ssm.mapper.EmployeeMapper;
import cn.ssm.model.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @user Administrator
 * @date 2021/4/16
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-mybatis.xml"})
public class EmployeeAnnoTest {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Test
    public void testAllEmployees() {
//        List<Employee> all = employeeMapper.findAll();
//        all.forEach(System.out::println);

        Employee employee = employeeMapper.findById(2);
        System.out.println(employee);
    }

    @Test
    public void testAddEmployees() {
        int i = employeeMapper.addEmployee(
                new Employee(null, "孙膑", "sb@qq.com", 0, 26));
        System.out.println(i);
    }

}
