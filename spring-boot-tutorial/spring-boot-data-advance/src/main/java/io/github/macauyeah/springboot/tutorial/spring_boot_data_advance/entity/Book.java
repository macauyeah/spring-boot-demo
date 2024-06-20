package io.github.macauyeah.springboot.tutorial.spring_boot_data_advance.entity;

import java.util.Date;
import java.util.UUID;

import io.micrometer.common.util.StringUtils;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;

@Entity
public class Book {
    @Id
    private String uuid;
    private String name;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    

    @PrePersist
    void genUuid(){
        if (StringUtils.isEmpty(this.getUuid())){
            Date now = new Date();
            this.setUuid(now.getTime() + UUID.randomUUID().toString());
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
