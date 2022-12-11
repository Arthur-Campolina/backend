package com.delta.school.services.Impl;

import com.delta.school.dto.StudentDTO;
import com.delta.school.entities.Student;
import com.delta.school.exceptions.DataBaseException;
import com.delta.school.exceptions.NotFoundException;
import com.delta.school.repositories.IStudentRepository;
import com.delta.school.services.IStudentService;
import org.apache.log4j.Logger;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class StudentServiceImpl implements IStudentService {
    private final IStudentRepository repository;
    private static org.apache.log4j.Logger logger = Logger.getLogger(StudentServiceImpl.class);

    public StudentServiceImpl(IStudentRepository repository) {
        this.repository = repository;
    }

    @Override
    public Page<StudentDTO> findAll(Pageable pageable) {
        Page<Student> page = repository.findAll(pageable);
        logger.info("Lista de alunos encontrada com sucesso.");
        return page.map(StudentDTO::new);
    }

    @Override
    public StudentDTO findById(Integer id) {
        Student student = repository.findById(id).orElseThrow(() -> new NotFoundException("Alunos não encontrados"));
        logger.info("Aluno encontrado para o ID: " + id);
        return new StudentDTO(student);
    }

    @Override
    public StudentDTO save(StudentDTO dto) {
        Student student = new Student();
        copyDtoToEntity(dto, student);
        student.setPassword(dto.getPassword());
        student = repository.save(student);
        logger.info("Aluno salvo com sucesso: " + dto);
        return new StudentDTO(student);
    }

    @Override
    public StudentDTO update(Integer id, StudentDTO dto) {
        Student student = repository.findById(id).orElseThrow(() -> new NotFoundException("Aluno não encontrado"));
        copyDtoToEntity(dto, student);
        student.setPassword(dto.getPassword());
        student = repository.save(student);
        logger.info("Aluno atualizado com sucesso: " + dto);
        return new StudentDTO(student);
    }

    @Override
    public void delete(Integer id) {
        try {
            repository.deleteById(id);
            logger.info("Aluno deletado com sucesso: " + id);
        } catch (EmptyResultDataAccessException e) {
            logger.warn("Erro ao buscar o aluno pelo ID: " + id);
            throw new NotFoundException("Id não encontrado: " + id);
        } catch (DataIntegrityViolationException e) {
            logger.warn("Violação de integridade.");
            throw new DataBaseException("Violação de integridade.");
        }
    }

    private void copyDtoToEntity(StudentDTO dto, Student entity) {
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setEmail(dto.getEmail());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setNumber(dto.getNumber());
        entity.setStreet(dto.getStreet());
        entity.setNeighbourhood(dto.getNeighbourhood());
        entity.setZipCode(dto.getZipCode());
        entity.setImage(dto.getImage());
        entity.setIsActive(dto.getIsActive());
        entity.setIsActive(dto.getIsActive());
        entity.setPassword(dto.getPassword());
    }

}
