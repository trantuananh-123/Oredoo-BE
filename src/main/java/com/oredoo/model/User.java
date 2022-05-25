package com.oredoo.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "sys_user")
public class User {

    @Id
    @Column(name = "id")
    @NotBlank(message = "User's id is required")
    private String id;

    @Column(name = "first_name", columnDefinition = "varchar(255)")
    private String firstName;

    @Column(name = "last_name", columnDefinition = "varchar(255)")
    private String lastName;

    @Min(value = 1)
    @Max(value = 3)
    @Column(name = "type")
    private int type;

    @Column(name = "username", unique = true, columnDefinition = "varchar(255)")
    @NotBlank(message = "User's username is required")
    private String username;

    @Column(name = "password", columnDefinition = "varchar(255)")
    @NotBlank(message = "User's password is required")
    private String password;

    @Column(name = "birthday")
    private Date birthday;

    @Column(name = "phone", columnDefinition = "varchar(11)")
    private String phone;

    @Column(name = "avatar", columnDefinition = "varchar(255)")
    private String avatar;

    @Column(name = "email", columnDefinition = "varchar(255)")
    private String email;

    @NotBlank(message = "User's is_active is required")
    @Column(name = "is_active")
    private boolean isActive;

    @NotBlank(message = "User's is_delete is required")
    @Column(name = "is_delete")
    private boolean isDelete;

    @NotBlank(message = "User's created_date is required")
    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    @PrePersist
    void preCondition() {
        this.isActive = false;
        this.isDelete = false;
        this.createdDate = LocalDateTime.now();
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

}
