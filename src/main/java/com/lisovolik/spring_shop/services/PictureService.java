package com.lisovolik.spring_shop.services;

import com.lisovolik.spring_shop.entity.ProductPicture;
import com.lisovolik.spring_shop.repositories.ProductPictureRepository;
import com.lisovolik.spring_shop.utils.Utils;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

/**
 * Created by Alexandr Lisovolik on  17.09.2024
 */

@Service
@AllArgsConstructor
public class PictureService {
    private final ProductPictureRepository pictureRepository;

    public ResponseEntity<String> savePicture(String path, Long id, MultipartFile file){
        String folder = path + "/" + id + "/";
        creteDirIfNeeded(folder);

        String fileName = UUID. randomUUID()  + "." + Utils.getExtension(file.getOriginalFilename());
        Path fileNameAndPath = Paths.get(folder, fileName);
        try {
            Files.write(fileNameAndPath, file.getBytes());

            ProductPicture picture = new ProductPicture();
            picture.setName(fileName);
            picture.setUrl(fileNameAndPath.toString());
            picture.setIdProduct(id);
            pictureRepository.save(picture);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return ResponseEntity.ok(fileName);
    }

    public ResponseEntity<Resource> getPicture(String dir, Long id, String fileName)  {
        String fullPath = dir + "/" + id + "/" + fileName;
        try {
            Path path = Path.of(fullPath);
            Resource resource = new UrlResource(path.toUri());

            if (resource.exists() || resource.isReadable()) {
                String content_type = Files.probeContentType(path);
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .contentType(MediaType.valueOf(content_type))
                        .body(resource);
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }


    private void creteDirIfNeeded(String folder) {
        File directory = new File(folder);
        if (! directory.exists()){
            directory.mkdirs();
        }
    }

    public ResponseEntity<String> deletePicture(String path, Long id, String fileName){
        String folder = path + "/" + id + "/";
        Path fileNameAndPath = Paths.get(folder, fileName);

        try {
           Files.deleteIfExists(fileNameAndPath);

           Optional<ProductPicture> dbPicture = pictureRepository.findByName(fileName);
           if (dbPicture.isPresent()){
               pictureRepository.delete(dbPicture.get());
           }

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return ResponseEntity.ok(fileName);
    }
}
