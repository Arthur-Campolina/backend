package com.delta.school.services;

import com.delta.school.dto.RoleDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IRoleService {

    Page<RoleDTO> findAll(Pageable pageable);
    RoleDTO findById(Integer id);
    RoleDTO update(Integer id, RoleDTO roleDTO);
    RoleDTO save(RoleDTO roleDTO);
    void delete(Integer id);
}
