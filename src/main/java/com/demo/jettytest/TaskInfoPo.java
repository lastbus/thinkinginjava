package com.demo.jettytest;

import java.util.Date;

/**
 * @author make
 * @creare 25/09/2018
 */
public class TaskInfoPo {

    private int id;
    private int taskId;
    private Date createTime;
    private int appId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getAppId() {
        return appId;
    }

    public void setAppId(int appId) {
        this.appId = appId;
    }
}
