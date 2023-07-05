package com.company.repository;


import com.company.entity.StudentEntity;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface StudentRepository extends CrudRepository<StudentEntity, Integer> {

    Optional<StudentEntity> getById(Integer id);

//    Optional<StudentEntity> getByDate(LocalDateTime date);

}
