package com.t3h.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "department")
public class Department extends BaseEntity{
    private String code;
    private String name;
    private String address;
}
