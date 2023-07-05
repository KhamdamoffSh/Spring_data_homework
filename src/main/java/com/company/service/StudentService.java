package com.company.service;


import com.company.dto.StudentDTO;
import com.company.entity.StudentEntity;
import com.company.exaption.AppBadRequestException;
import com.company.exaption.ItemNotFoundException;
import com.company.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;


    public StudentDTO add(StudentDTO dto) {
        check(dto);

        StudentEntity entity = new StudentEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setLevel(dto.getLevel());
        entity.setAge(dto.getAge());
        entity.setGender(dto.getGender());
        entity.setCreatedDate(LocalDateTime.now());
        studentRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }

    public List<StudentDTO> getAll() {
        Iterable<StudentEntity> iterable = studentRepository.findAll();
        List<StudentDTO> list = new LinkedList<>();
        iterable.forEach(i -> {
            list.add(toDTO(i));
        });
        return list;
    }

    public StudentDTO getById(Integer id) {
        Optional<StudentEntity> optional = studentRepository.findById(id);
        if (optional.isEmpty()) {
            try {
                throw new ItemNotFoundException("Student not exists");
            } catch (ItemNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        StudentEntity entity = optional.get();
        return toDTO(entity);
    }

    public Boolean update(Integer id, StudentDTO student) {
        check(student);

        Optional<StudentEntity> optional = studentRepository.findById(id);
        if (optional.isEmpty()) {
            throw new ItemNotFoundException("Student not found");
        }

        List<StudentDTO> list = getAll();
        list.forEach(i -> {
            if (i.getId().equals(id)) {
                i.setId(student.getId());
                i.setName(student.getName());
                i.setSurname(student.getSurname());
                i.setLevel(student.getLevel());
                i.setAge(student.getAge());
                i.setGender(student.getGender());
                i.setCreatedDate(student.getCreatedDate());
                return;
            }
        });
        return false;
    }

    public Boolean deleteById(Integer id) {
        Optional<StudentEntity> optional = studentRepository.getById(id);
        if (optional.isEmpty()) {
            throw new ItemNotFoundException("Student not faund");
        }

        optional.ifPresent(studentRepository::delete);

        return true;
    }

    /*public StudentDTO getByDate(LocalDateTime date) {
        Optional<StudentEntity> optional = studentRepository.getByDate(date);
        if (optional.isEmpty()) {
            try {
                throw new ItemNotFoundException("Student not exists");
            } catch (ItemNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        StudentEntity entity = optional.get();
        return toDTO(entity);
    }*/


    private void check(StudentDTO student) throws AppBadRequestException {
        if (student.getName() == null || student.getName().isBlank()) {
            throw new AppBadRequestException("Name qani?");
        }
        if (student.getSurname() == null || student.getSurname().isBlank()) {
            throw new AppBadRequestException("Surname qani?");
        }
    }

    public StudentDTO toDTO(StudentEntity entity) {
        StudentDTO dto = new StudentDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setLevel(entity.getLevel());
        dto.setAge(entity.getAge());
        dto.setGender(entity.getGender());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }

}
