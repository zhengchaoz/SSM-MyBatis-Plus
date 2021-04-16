package cn.ssm.service.impl;

import cn.ssm.model.Employee;
import cn.ssm.mapper.EmployeeMapper;
import cn.ssm.service.EmployeeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhu
 * @since 2021-04-16
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

}
