package com.oredoo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sys_user")
public class User {

    @Id
    @Column(name = "id")
    @NotBlank(message = "User's id is required")
    private String id;

    @Column(name = "first_name", columnDefinition = "varchar(255)")
    private String firstName;

    @Column(name = "middle_name", columnDefinition = "varchar(255)")
    private String middleName;

    @Column(name = "last_name", columnDefinition = "varchar(255)")
    private String lastName;

    @Min(value = 1)
    @Max(value = 3)
    @Column(name = "type")
    private Integer type;

    @Column(name = "username", unique = true, columnDefinition = "varchar(255)")
    private String username;

    @Column(name = "password", columnDefinition = "varchar(255)")
    private String password;

    @Column(name = "birthday")
    private Date birthday;

    @Column(name = "phone", columnDefinition = "varchar(11)")
    private String phone;

    @Column(name = "avatar", columnDefinition = "varchar(255)")
    private String avatar;

    @Column(name = "email", columnDefinition = "varchar(255)")
    @Pattern(regexp = "^[\\w._%+-]+@[a-zA-Z]+\\.[a-zA-Z]{2,6}$", message = "Invalid email address")
    private String email;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @PrePersist
    void preCondition() {
        this.isActive = true;
        this.createdDate = LocalDateTime.now();
    }

    public User(String username, String email, String password, int type) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.type = type;
    }
}
