package com.delta.school.dto;

import com.delta.school.entities.Student;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class StudentDTO {

    private Integer id;
    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    private int number;
    private String street;
    private String neighbourhood;
    private String zipCode;

    @JsonProperty("blob")
    private String image;
    @JsonIgnore
    @Setter(AccessLevel.NONE)
    @OneToMany(fetch = FetchType.EAGER)
    private Set<String> roles = new HashSet<>();
    private Boolean isActive;
    private String password;

    public StudentDTO(Student entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.surname = entity.getSurname();
        this.email = entity.getEmail();
        this.phoneNumber = entity.getPhoneNumber();
        this.number = entity.getNumber();
        this.street = entity.getStreet();
        this.neighbourhood = entity.getNeighbourhood();
        this.zipCode = entity.getZipCode();
        this.image = entity.getImage();
        this.isActive = entity.getIsActive();
        this.roles.clear();
        entity.getRoles().forEach(role -> this.addRole(role.getAuthority()));
        this.password = entity.getPassword();
    }

    public void addRole(String role) {
        this.roles.add(role);
    }

    public void removeRole(String role) {
        this.roles.remove(role);
    }

}
