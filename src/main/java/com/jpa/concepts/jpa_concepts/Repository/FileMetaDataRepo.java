package com.jpa.concepts.jpa_concepts.Repository;

import com.jpa.concepts.jpa_concepts.Entity.FileMetaData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileMetaDataRepo extends JpaRepository<FileMetaData,Long> {
}
