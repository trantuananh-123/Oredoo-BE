package com.oredoo.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "sys_role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "name", columnDefinition = "varchar(255)")
    @NotBlank(message = "Role's name is required")
    @Pattern(regexp = "^ROLE_")
    private String name;

    @Column(name = "description", columnDefinition = "varchar(255)")
    private String description;

    @NotBlank(message = "Role's is_active is required")
    @Column(name = "is_active")
    private boolean isActive;

    @NotBlank(message = "Role's is_delete is required")
    @Column(name = "is_delete")
    private boolean isDelete;

    @Column(name = "created_date")
    @NotBlank(message = "Role's created_date is required")
    private LocalDateTime createdDate;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    @PrePersist
    void preCondition() {
        this.isActive = true;
        this.isDelete = false;
        this.createdDate = LocalDateTime.now();
    }
}
