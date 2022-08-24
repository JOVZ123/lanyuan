package com.lanyuan.springboot.pojo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
@Component
@ConfigurationProperties(prefix = "role") //匹配 application.properties文件中自定义的属性
@PropertySource(value = "classpath:role.properties",encoding = "UTF-8") //读取指定的配置文件(role.properties)
public class Role implements Serializable {
    private Integer id;

    private String reloname;

    private String status;

    private Date createtime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReloname() {
        return reloname;
    }

    public void setReloname(String reloname) {
        this.reloname = reloname == null ? null : reloname.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", reloname=").append(reloname);
        sb.append(", status=").append(status);
        sb.append(", createtime=").append(createtime);
        sb.append("]");
        return sb.toString();
    }
}