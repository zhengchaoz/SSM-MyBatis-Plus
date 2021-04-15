package cn.ssm.service.impl;

import cn.ssm.mapper.TpmTestLogMapper;
import cn.ssm.model.TpmTestLog;
import cn.ssm.service.TpmTestLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @user Administrator
 * @date 2021/4/14
 */
@Service
@Transactional
public class TpmTestLogServiceImpl implements TpmTestLogService {

    private final TpmTestLogMapper tpmTestLogMapper;

    public TpmTestLogServiceImpl(TpmTestLogMapper tpmTestLogMapper) {
        this.tpmTestLogMapper = tpmTestLogMapper;
    }

    @Override
    public Long ceshi(TpmTestLog tpmTestLog) {
        return tpmTestLogMapper.insert(tpmTestLog);
    }
}
