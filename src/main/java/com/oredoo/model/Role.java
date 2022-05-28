package com.oredoo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sys_role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name", columnDefinition = "varchar(255)")
    @NotBlank(message = "Role's name is required")
    @Pattern(regexp = "^ROLE_")
    private String name;

    @Column(name = "description", columnDefinition = "varchar(255)")
    private String description;

    @NotBlank(message = "Role's is_active is required")
    @Column(name = "is_active")
    private Boolean isActive;

    @NotBlank(message = "Role's is_delete is required")
    @Column(name = "is_delete")
    private Boolean isDelete;

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
