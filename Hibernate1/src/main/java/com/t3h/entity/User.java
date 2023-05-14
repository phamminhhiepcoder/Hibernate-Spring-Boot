package com.t3h.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "user_master")
@Entity
public class User extends BaseEntity{

    @Column(name = "full_name")
    private String fullName;
    private String address;
    private Integer age;
    private String username;
    private String password;
    @Column(name = "role_id")
    private Integer roleId;
}
