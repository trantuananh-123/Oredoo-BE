package com.oredoo.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.oredoo.model.Role;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

@Data
public class UserRequestDTO {
    private String id;
    private String firstName;
    private String middleName;
    private String lastName;
    private Integer type;
    private String username;
    private String password;
    private Date birthday;
    private String phone;
    private String avatar;
    private String email;
    private Boolean isActive;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    @JsonFormat(timezone = "GMT+07:00")
    private Date startDate;
    @JsonFormat(timezone = "GMT+07:00")
    private Date endDate;
    private Integer roles;

}
