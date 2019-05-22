package com.hotel_admin.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileService {
    @Value("${application.images.directory}")
    private String IMAGE_DIR;
    private BASE64Decoder base64Decoder;

    @PostConstruct
    public void init() {
        new File(IMAGE_DIR).mkdir();
        base64Decoder = new BASE64Decoder();
    }
    public String saveFromBase64(String base64) {
        if (base64 == null || base64.isEmpty())
            return null;
        try {
            String[] base64data = base64.split(",");
            String info = base64data[0];
            String suffix = info.split("/")[1];
            suffix = '.' + suffix.substring(0, suffix.length() - ";base64".length());
            String fileName = UUID.randomUUID().toString() + suffix;
            Path filePath = Paths.get(IMAGE_DIR, fileName);

            byte[] imageBytes = base64Decoder.decodeBuffer(base64data[1]);
            Files.write(filePath, imageBytes);
            return '/' + filePath.toString();
        } catch (Exception e) {
            return null;
        }
    }

    public String saveImage(MultipartFile image) {
        if (image == null)
            return null;
        String suffix;
        try {
            suffix = '.' + image.getContentType().split("/")[1];
        } catch (Exception ignored) {
            return null;
        }
        String fileName = UUID.randomUUID().toString() + suffix;
        Path filePath = Paths.get(IMAGE_DIR, fileName);
        try {
            Files.write(filePath, image.getBytes());
            return '/' + filePath.toString();
        } catch (IOException e) {
            return null;
        }
    }
}
