package com.t3h.entity;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public Integer getId() {return id;}
    public void setId(Integer id) {
        this.id = id;
    }
}
