package com.delta.school.services.Impl;

import com.delta.school.dto.RoleDTO;
import com.delta.school.entities.Role;
import com.delta.school.exceptions.DataBaseException;
import com.delta.school.exceptions.NotFoundException;
import com.delta.school.repositories.IRoleRepository;
import com.delta.school.services.IRoleService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class RoleServiceImpl implements IRoleService {

    private final IRoleRepository repository;
    private static final org.apache.log4j.Logger logger = Logger.getLogger(RoleServiceImpl.class);

    @Autowired
    public RoleServiceImpl(IRoleRepository repository) {
        this.repository = repository;
    }

    public Page<RoleDTO> findAll(Pageable pageable) {
        Page<Role> roles = repository.findAll(pageable);
        logger.info("Lista de roles encontrada com sucesso.");
        return roles.map(RoleDTO::new);
    }


    public RoleDTO findById(Integer id) {
        Role role = repository.findById(id).orElseThrow(() -> new NotFoundException("Role not found"));
        logger.info("Role encontrado para o ID: " + id);
        return new RoleDTO(role);
    }

    public RoleDTO update(Integer id, RoleDTO dto) {
        Role role = repository.findById(id).orElseThrow(() -> new NotFoundException("Role not found"));
        copyDtoToEntity(dto, role);
        role = repository.save(role);
        logger.info("Role atualizado com sucesso: " + dto);
        return new RoleDTO(role);
    }

    public RoleDTO save(RoleDTO dto) {
        Role role = new Role();
        copyDtoToEntity(dto, role);
        role = repository.save(role);
        logger.info("Role salvo com sucesso: " + dto);
        return new RoleDTO(role);
    }

    public void delete(Integer id) {
        try {
            repository.deleteById(id);
            logger.info("Role deletado com sucesso: " + id);
        } catch (EmptyResultDataAccessException e) {
            logger.warn("Erro ao buscar o role pelo ID: " + id);
            throw new NotFoundException("Id não encontrado: " + id);
        } catch (DataIntegrityViolationException e) {
            logger.warn("Violação de integridade.");
            throw new DataBaseException("Violação de integridade.");
        }
    }

    private void copyDtoToEntity(RoleDTO dto, Role role) {
        role.setAuthority(dto.getAuthority());
        role.setDescription(dto.getDescription());
    }
}
