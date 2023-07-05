package com.company.service;

import com.company.dto.CourseDTO;
import com.company.dto.StudentDTO;
import com.company.entity.CourseEntity;
import com.company.entity.StudentEntity;
import com.company.exaption.AppBadRequestException;
import com.company.exaption.ItemNotFoundException;
import com.company.repository.CourseRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;


    public CourseDTO add(CourseDTO dto) {
        check(dto);

        CourseEntity entity = new CourseEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setPrice(dto.getPrice());
        entity.setDuration(dto.getDuration());
        entity.setCreatedDate(LocalDateTime.now());
        courseRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }

    public CourseDTO getById(Integer id){
        Optional<CourseEntity> optional = courseRepository.findById(id);
        if (optional.isEmpty()) {
            try {
                throw new ItemNotFoundException("course not exists");
            } catch (ItemNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        CourseEntity entity = optional.get();
        return toDTO(entity);
    }

    public List<CourseDTO> getAll() {
        Iterable<CourseEntity> iterable = courseRepository.findAll();
        List<CourseDTO> list = new LinkedList<>();
        iterable.forEach(i -> {
            list.add(toDTO(i));
        });
        return list;
    }

    public Boolean update(Integer id, CourseDTO course) {
        check(course);

        Optional<CourseEntity> optional = courseRepository.findById(id);
        if (optional.isEmpty()) {
            throw new ItemNotFoundException("course not found");
        }

        List<CourseDTO> list = getAll();
        list.forEach(i -> {
            if (i.getId().equals(id)) {
                i.setId(course.getId());
                i.setName(course.getName());
                i.setPrice(course.getPrice());
                i.setDuration(course.getDuration());
                i.setCreatedDate(course.getCreatedDate());
                return;
            }
        });
        return false;
    }





    private void check(CourseDTO courseDTO) throws AppBadRequestException {
        if (courseDTO.getName() == null || courseDTO.getName().isBlank()) {
            throw new AppBadRequestException("Name qani?");
        }
    }

    public CourseDTO toDTO(CourseEntity entity) {
        CourseDTO dto = new CourseDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setPrice(entity.getPrice());
        dto.setDuration(entity.getDuration());
        dto.setCreatedDate(LocalDateTime.now());
        return dto;
    }
}
