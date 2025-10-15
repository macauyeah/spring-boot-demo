package io.github.macauyeah.springboot.tutorial.spring_boot_web_api_async.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.springframework.stereotype.Service;

@Service
public class ReportService {
    public void genAndSaveReport(String uuid) {
        String content = "This is the text content to be saved in the file.";
        String dirPath = "/tmp/SpringAsyncApi";
        String filePathString = "/tmp/SpringAsyncApi/Report-" + uuid + ".txt";

        try {
            Path dirPathObj = Paths.get(dirPath);
            if (!Files.exists(dirPathObj)) {
                Files.createDirectories(dirPathObj);
            }
            // Create a Path object from the string path
            Path filePath = Paths.get(filePathString);

            // Write the content to the file.
            // CREATE_NEW: Creates a new file, throws exception if file exists.
            // WRITE: Opens for writing.
            // StandardOpenOption.TRUNCATE_EXISTING: If file exists, truncate it to 0 bytes.
            // If you want to append, use StandardOpenOption.APPEND instead of
            // TRUNCATE_EXISTING.
            Files.write(filePath, content.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.WRITE,
                    StandardOpenOption.TRUNCATE_EXISTING);

            System.out.println("Text saved to file successfully at: " + filePath);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public InputStream getReport(String uuid) throws FileNotFoundException{
        String filePathString = "/tmp/SpringAsyncApi/Report-" + uuid + ".txt";
        return new FileInputStream(filePathString);
    }
}
