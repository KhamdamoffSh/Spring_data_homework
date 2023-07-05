package com.company.repository;

import com.company.entity.CourseEntity;
import com.company.entity.StudentEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CourseRepository extends CrudRepository<CourseEntity, Integer> {

    Optional<CourseEntity> getById(Integer id);
}
