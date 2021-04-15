package cn.ssm.controller;

import cn.ssm.model.TpmTestLog;
import cn.ssm.service.TpmTestLogService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @user Administrator
 * @date 2021/4/14
 */
@RestController
@RequestMapping("/api")
public class TestController {

    private final TpmTestLogService tpmTestLogService;

    public TestController(TpmTestLogService tpmTestLogService) {
        this.tpmTestLogService = tpmTestLogService;
    }

    @GetMapping("/test")
    public JSONObject test() {
        JSONObject jsonObject = new JSONObject();

        TpmTestLog tpmTestLog = new TpmTestLog();
        tpmTestLog.setSyscreatedate("asfasgags");
        tpmTestLog.setTestmainUuid("fdasgassfsarew");
        tpmTestLog.setOperation("新增");
        tpmTestLogService.ceshi(tpmTestLog);

        jsonObject.put("title", "测试成功" + tpmTestLog.getLogUuid());
        return jsonObject;
    }

}
