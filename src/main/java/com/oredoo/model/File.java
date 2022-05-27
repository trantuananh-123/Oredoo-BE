package com.oredoo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "file")
public class File {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @JsonIgnore
    @Lob
    @Column(name = "data")
    private byte[] data;

    @Column(name = "imageUrl")
    private String imageUrl;

    @Column(name = "created_date")
    private LocalDateTime uploadDate;

    @PrePersist
    void preCondition() {
        this.uploadDate = LocalDateTime.now();
    }

    public File(String id, String name, String type, byte[] data, String imageUrl) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.data = data;
        this.imageUrl = imageUrl;
    }
}
