package com.delta.school.repositories;

import com.delta.school.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepository extends JpaRepository<Role, Integer> {

    Role findByAuthority (String authority);
}
