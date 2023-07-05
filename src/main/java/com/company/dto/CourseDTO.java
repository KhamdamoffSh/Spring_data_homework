package com.company.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class CourseDTO {
    private Integer id;
    private String name;
    private String price;
    private String duration;
    private LocalDateTime createdDate = LocalDateTime.now();
}
