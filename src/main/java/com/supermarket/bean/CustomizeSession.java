package com.supermarket.bean;

/**
 * @author ghy
 */
public class CustomizeSession {
    private long id;
    private String kfc;
    private long createTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getKfc() {
        return kfc;
    }

    public void setKfc(String kfc) {
        this.kfc = kfc;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }
}
