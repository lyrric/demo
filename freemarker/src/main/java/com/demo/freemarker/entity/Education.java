package com.demo.freemarker.entity;

import lombok.Data;

@Data
public class Education {
    private Integer id;
    private Integer userId;
    private String startDate;
    private String endDate;
    private String college;
    private String specialty;
    private String type;
    private String courses;
}
