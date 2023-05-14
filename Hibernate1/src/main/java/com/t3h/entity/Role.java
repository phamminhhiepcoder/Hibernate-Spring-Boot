package com.t3h.entity;

import javax.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "role")
@Entity
public class Role extends BaseEntity{

    private String name;
}
