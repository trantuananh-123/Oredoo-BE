package com.oredoo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "post")
public class Post {

    @Id
    @Column(name = "id")
    private String id;

    @NotBlank(message = "Post's title is required")
    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @NotBlank(message = "Post's content is required")
    @Column(name = "content")
    private String content;

    @NotBlank(message = "Post's user is required")
    @Column(name = "user_id")
    private String userId;

    @Column(name = "image")
    private String image;

    @Column(name = "rate")
    private float rate;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    @PrePersist
    void preCondition() {
        this.isActive = true;
        this.createdDate = LocalDateTime.now();
    }
}
