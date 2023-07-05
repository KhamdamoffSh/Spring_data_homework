package com.company.controller;


import com.company.dto.StudentDTO;
import com.company.entity.StudentEntity;
import com.company.service.StudentService;
import jakarta.persistence.PostUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping(value = "/create")
    public ResponseEntity<?> create(@RequestBody StudentDTO student) {
        return new ResponseEntity<>(studentService.add(student), HttpStatus.OK);
    }

    @GetMapping("/all")
    public List<StudentDTO> all(){
      return studentService.getAll();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(studentService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id,
                                   @RequestBody StudentDTO studentDTO){
        studentService.update(id,studentDTO);
        return ResponseEntity.ok(true);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Integer id) {
        Boolean response = studentService.deleteById(id);
        if (response) {
            return ResponseEntity.ok("Student deleted");
        }
        return ResponseEntity.badRequest().body("Student Not Found");
    }

    /*@GetMapping("/{date}")
    private ResponseEntity<?> getByDate(@PathVariable("date") LocalDateTime date){
        return ResponseEntity.ok(studentService.getByDate(date));
    }*/

}

