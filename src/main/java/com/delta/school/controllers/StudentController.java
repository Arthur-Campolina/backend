package com.delta.school.controllers;

import com.delta.school.dto.StudentDTO;
import com.delta.school.services.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
@RestController
@RequestMapping("/students")
public class StudentController {
    private final IStudentService service;

    @Autowired
    public StudentController(IStudentService service) {this.service = service;}

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping
    public ResponseEntity<Page<StudentDTO>> findAll(Pageable pageable) {
        return ResponseEntity.ok(service.findAll(pageable));
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping
    public ResponseEntity<StudentDTO> novo(@RequestBody StudentDTO dto) {
        StudentDTO address = service.save(dto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(address.getId()).toUri();
        return ResponseEntity.created(uri).body(address);
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> update(@PathVariable Integer id, @RequestBody StudentDTO StudentDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(service.update(id, StudentDTO));
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
