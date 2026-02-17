package com.jpa.concepts.jpa_concepts.service;

import com.jpa.concepts.jpa_concepts.Entity.FileMetaData;
import org.apache.coyote.BadRequestException;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;

public interface FileStorageService {
    FileMetaData uploadFile(MultipartFile file);

    //Resource loadFile(FileMetaData fileMetaData) throws MalformedURLException, BadRequestException;
}
