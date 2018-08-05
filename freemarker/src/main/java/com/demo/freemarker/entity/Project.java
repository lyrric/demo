package com.demo.freemarker.entity;

import lombok.Data;

@Data
public class Project {
    private Integer id;
    private Integer userId;
    private String name;
    private String description;
    private String startDate;
    private String endDate;
}
