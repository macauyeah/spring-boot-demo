package io.github.macauyeah.springboot.tutorial.spring_boot_data_advance.entity;

import java.util.Date;
import java.util.UUID;

import io.micrometer.common.util.StringUtils;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;

@Entity
public class BookSearch {
    @Id
    private String uuid;
    private String batchSearchNum;
    private String searchBookUuid;

    @PrePersist
    void genUuid(){
        if (StringUtils.isEmpty(this.getUuid())){
            Date now = new Date();
            this.setUuid(now.getTime() + UUID.randomUUID().toString());
        }
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getBatchSearchNum() {
        return batchSearchNum;
    }

    public void setBatchSearchNum(String batchSearchNum) {
        this.batchSearchNum = batchSearchNum;
    }

    public String getSearchBookUuid() {
        return searchBookUuid;
    }

    public void setSearchBookUuid(String searchBookUuid) {
        this.searchBookUuid = searchBookUuid;
    }



}
