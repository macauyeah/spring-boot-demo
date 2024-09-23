package io.github.macauyeah.springboot.tutorial.spring_boot_data_advance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
    void simpleCreate() {
        FolderEntity folder = new FolderEntity();
        folderRepo.saveAndFlush(folder); // same as save()
        assertEquals(0, folder.getFiles().size());

        FileEntity file = new FileEntity();
        file.setFolder(folder);
        fileRepo.saveAndFlush(file); // same as save()
        assertNotNull(file.getFolder());
        assertEquals(0, folder.getFiles().size());

        FolderEntity folderById = folderRepo.findById(folder.getUuid()).orElseThrow();
        assertEquals(0, folderById.getFiles().size()); // why no update
        assertEquals(0, folder.getFiles().size());

        folder.addFile(file);
        fileRepo.save(file);
        folderById = folderRepo.findById(folder.getUuid()).orElseThrow();
        assertEquals(1, folderById.getFiles().size()); // even no saving event on folder object, folderById still
                                                       // updated
        assertEquals(1, folder.getFiles().size());
    }

    @Transactional
    private UUID createAndEndTrasaction() {
        FolderEntity folder = new FolderEntity();
        folderRepo.saveAndFlush(folder);
        assertEquals(0, folder.getFiles().size());

        FileEntity file = new FileEntity();
        file.setFolder(folder);
        fileRepo.saveAndFlush(file);
        return folder.getUuid();
    }

    @Test
    @Transactional
    void createInTransactionAndReadInOtherTransaction() {
        UUID folderUuid = this.createAndEndTrasaction();
        FolderEntity folderById = folderRepo.findById(folderUuid).orElseThrow();
        assertEquals(0, folderById.getFiles().size()); // why no update, same transaction?
    }
}
