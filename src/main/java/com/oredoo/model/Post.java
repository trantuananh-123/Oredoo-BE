package com.oredoo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    @NotBlank(message = "Post's title is required")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "content")
    @NotBlank(message = "Post's content is required")
    private String content;

    @Column(name = "user_id")
    @NotBlank(message = "Post's user is required")
    private String userId;


    @Column(name = "author_name")
    @NotBlank(message = "Post's author is required")
    private String authorName;

    @Column(name = "category_id")
    private int categoryId;

    @Column(name = "image")
    private String image;

    @Column(name = "rate")
    private float rate;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "created_date")
    @NotNull(message = "Tag's created date is required")
    private Date createdDate;

    @Column(name = "updated_date")
    private Date updatedDate;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "post_tag",
        joinColumns = @JoinColumn(name = "post_id"),
        inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<Tag> tags;

    @PrePersist
    void preCondition() {
        this.isActive = true;
        this.createdDate = new Date();
    }
}
