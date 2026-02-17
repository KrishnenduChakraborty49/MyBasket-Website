package com.jpa.concepts.jpa_concepts.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Table(name="my-basket-file-meta-data")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FileMetaData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String originalName;
    private String storedName;
    private String fileType;
    private Long fileSize;
    private String storegeType;

    private String fileUrl;
}
