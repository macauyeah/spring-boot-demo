package io.github.macauyeah.springboot.tutorial.springbootdatabasic;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Transient;

@Entity
public class Apple {
    @Id
    String uuid;
    Double weight;
    @Transient
    Double gravity;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Double getGravity() {
        return gravity;
    }

    public void setGravity(Double gravity) {
        this.gravity = gravity;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    @PrePersist
    @PreUpdate
    public void updateWeight() {
        // this.getGravity() is always null because it is Transient;
        // when object enter per-presist or pre-update lifecycle,
        // transient field would be cleared
        // Following code block is never executed;
        if (this.getGravity() != null && this.getWeight() == null) {
            this.setWeight(this.getGravity() / 10);
        }
    }
}
