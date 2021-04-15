package cn.ssm.model;

import java.io.Serializable;

/**
 * @user Administrator
 * @date 2021/4/14
 */
public class TpmTestLog implements Serializable {

    /**
     * @备注: 主键
     * @字段:LOG_UUID bigint
     */
    private Long logUuid;


    /**
     * @备注:
     * @字段:TESTMAIN_UUID CHAR(32)
     */
    private String testmainUuid;


    /**
     * @备注:
     * @字段:OPERATION VARCHAR(20)
     */
    private String operation;


    /**
     * @备注:
     * @字段:SYSCREATEDATE VARCHAR(25)
     */
    private String syscreatedate;

    public TpmTestLog() {
    }

    public Long getLogUuid() {
        return logUuid;
    }

    public void setLogUuid(Long logUuid) {
        this.logUuid = logUuid;
    }

    public String getTestmainUuid() {
        return testmainUuid;
    }

    public void setTestmainUuid(String testmainUuid) {
        this.testmainUuid = testmainUuid;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getSyscreatedate() {
        return syscreatedate;
    }

    public void setSyscreatedate(String syscreatedate) {
        this.syscreatedate = syscreatedate;
    }

    @Override
    public String toString() {
        return "TpmTestLog{" +
                "logUuid=" + logUuid +
                ", testmainUuid='" + testmainUuid + '\'' +
                ", operation='" + operation + '\'' +
                ", syscreatedate='" + syscreatedate + '\'' +
                '}';
    }
}
