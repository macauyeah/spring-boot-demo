package io.github.macauyeah.springboot.tutorial.spring_boot_data_advance.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class FolderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;
    private String remark;
    @OneToMany(mappedBy = "folder")
    private List<FileEntity> files = new ArrayList<>();

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<FileEntity> getFiles() {
        return files;
    }

    public void setFiles(List<FileEntity> files) {
        this.files = files;
    }

    public void addFile(FileEntity file) {
        this.getFiles().add(file);
        file.setFolder(this);
    }

    public void removeFile(FileEntity file) {
        this.getFiles().remove(file);
        file.setFolder(this);
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
}
