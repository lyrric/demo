package com.demo.freemarker.entity;

import lombok.Data;

@Data
public class Job {
    private Integer id;
    private Integer userId;
    private String startDate;
    private String endDate;
    private String company;
    private String position;
    private String description;
}
