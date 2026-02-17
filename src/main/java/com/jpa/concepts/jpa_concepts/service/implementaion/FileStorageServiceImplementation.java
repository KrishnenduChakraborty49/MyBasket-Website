package com.jpa.concepts.jpa_concepts.service.implementaion;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import com.jpa.concepts.jpa_concepts.Entity.FileMetaData;
import com.jpa.concepts.jpa_concepts.Repository.FileMetaDataRepo;
import com.jpa.concepts.jpa_concepts.service.FileStorageService;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class FileStorageServiceImplementation implements FileStorageService {
//    @Value("${app.files.upload.folder.products}")
//    String productUploadFolder;
//    private final FileMetaDataRepo fileMetaDataRepo;

//    @Override
//    public FileMetaData uploadFile(MultipartFile file) {
//        try {
//            String storeName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
//
//            Path uploadPath = Files.createDirectories(Paths.get(productUploadFolder));
//            Path filePath = uploadPath.resolve(storeName);
//
//            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
//
//            FileMetaData fileMetadata = new FileMetaData();
//            fileMetadata.setFileSize(file.getSize());
//            fileMetadata.setOriginalName(file.getOriginalFilename());
//            fileMetadata.setStoredName(storeName);
//            fileMetadata.setFileType(file.getContentType());
//            fileMetadata.setFileUrl(filePath.toString());
//            fileMetadata.setStoregeType("local");
//
//            return fileMetaDataRepo.save(fileMetadata);
//
//        } catch (Exception e) {
//            throw new RuntimeException("File upload failed", e);
//        }
//    }
    private final Cloudinary cloudinary;
    private final FileMetaDataRepo fileMetaDataRepo;

    @Override
    public FileMetaData uploadFile(MultipartFile file) {
        try {
            Map uploadResult = cloudinary.uploader().upload(
                    file.getBytes(),
                    ObjectUtils.asMap(
                            "folder", "mybasket/products"
                    )
            );

            FileMetaData metaData = new FileMetaData();
            metaData.setOriginalName(file.getOriginalFilename());
            metaData.setFileSize(file.getSize());
            metaData.setFileType(file.getContentType());
            metaData.setFileUrl(uploadResult.get("secure_url").toString());

            metaData.setStoregeType("cloudinary");

            return fileMetaDataRepo.save(metaData);

        } catch (Exception e) {
            throw new RuntimeException("Cloudinary upload failed", e);
        }
    }

//    @Override
//    public Resource loadFile(FileMetaData fileMetaData) throws MalformedURLException, BadRequestException {
//        Path path=Paths.get(productUploadFolder).resolve(fileMetaData.getStoredName()).normalize();
//        Resource resource=new UrlResource((path.toUri()));
//        if(!resource.exists()){
//            throw new BadRequestException("File not found!");
//        }
//        return resource;
//    }

}
