package io.github.macauyeah.springboot.tutorial.spring_boot_data_advance;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import io.github.macauyeah.springboot.tutorial.spring_boot_data_advance.entity.FileEntity;
import io.github.macauyeah.springboot.tutorial.spring_boot_data_advance.entity.FolderEntity;
import io.github.macauyeah.springboot.tutorial.spring_boot_data_advance.repo.FileRepo;
import io.github.macauyeah.springboot.tutorial.spring_boot_data_advance.repo.FolderRepo;

@SpringBootTest
public class OneToManyRefreshStatusTests {
    @Autowired
    FileRepo fileRepo;
    @Autowired
    FolderRepo folderRepo;

    @BeforeEach
    void setUp() {
        fileRepo.deleteAll();
        folderRepo.deleteAll();
    }

    @Test
    @Transactional
    void withTransaction() {
        FolderEntity folder = new FolderEntity();
        folderRepo.saveAndFlush(folder);
        assertEquals(0, folder.getFiles().size());

        FileEntity file = new FileEntity();
        file.setFolder(folder);
        fileRepo.saveAndFlush(file);

        UUID folderUuid = folder.getUuid();
        FolderEntity folderById;
        folderById = folderRepo.findById(folderUuid).orElseThrow();
        assertEquals(0, folderById.getFiles().size()); // hibernate not query database again, using existing folder value
        
        folder.addFile(file);
        folderById = folderRepo.findById(folderUuid).orElseThrow();
        assertEquals(1, folderById.getFiles().size()); // hibernate not query database again, using existing folder value
    }

    @Test
    void withoutTransaction() {
        FolderEntity folder = new FolderEntity();
        folderRepo.saveAndFlush(folder);
        assertEquals(0, folder.getFiles().size());

        FileEntity file = new FileEntity();
        file.setFolder(folder);
        fileRepo.saveAndFlush(file);

        UUID folderUuid = folder.getUuid();
        FolderEntity folderById = folderRepo.findById(folderUuid).orElseThrow();
        assertEquals(1, folderById.getFiles().size()); // without transaction, hibernate directly query database again
    }
}
