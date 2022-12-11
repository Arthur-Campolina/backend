package com.delta.school.services;

import com.delta.school.dto.StudentDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IStudentService {

    Page<StudentDTO> findAll(Pageable pageable);
    StudentDTO findById(Integer id);
    StudentDTO save(StudentDTO dto);
    StudentDTO update(Integer id, StudentDTO dto);
    void delete(Integer id);
}
