package com.delta.school.dto;

import com.delta.school.entities.Role;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class RoleDTO {

    private Integer id;
    private String authority;
    private String description;

    public RoleDTO(Role entity) {

        this.id = entity.getId();
        this.authority = entity.getAuthority();
        this.description = entity.getDescription();
    }
}

