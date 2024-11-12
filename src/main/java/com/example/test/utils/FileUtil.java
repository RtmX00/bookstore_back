package com.example.test.utils;


import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileUtil {
    //private String path = "src/main/resources";
    private final String folderStatic = "static";
    private final String folderMedia = "media";
    private final String fileType = "jpg";

    public void saveImage(String image,String imageName, String folder) {
        try {
            String filename = imageName + "." + fileType;
            String imagesPath = folderStatic + "/" + folderMedia + "/" + folder;
            byte[] imageBytes = Base64.decodeBase64(image);
            Files.createDirectories(Paths.get(imagesPath));
            File file = new File(imagesPath, filename);
            try (FileOutputStream fos = new FileOutputStream(file)) {
                fos.write(imageBytes);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getImage(String image, String folder) {
        return  folderMedia + "/" + folder + "/" + image + "." + fileType;
    }
}