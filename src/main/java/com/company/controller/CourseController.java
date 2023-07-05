package com.company.controller;

import com.company.dto.CourseDTO;
import com.company.dto.StudentDTO;
import com.company.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @PostMapping(value = "/create")
    public ResponseEntity<?> create(@RequestBody CourseDTO course) {
        return new ResponseEntity<>(courseService.add(course), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id){
        return ResponseEntity.ok(courseService.getById(id));
    }

    @GetMapping("/all")
    public List<CourseDTO> all(){
        return courseService.getAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id,
                                    @RequestBody CourseDTO courseDTO){
        courseService.update(id,courseDTO);
        return ResponseEntity.ok(true);
    }






}
