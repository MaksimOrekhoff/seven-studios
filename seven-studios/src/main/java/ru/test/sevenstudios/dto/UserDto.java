package ru.test.sevenstudios.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class UserDto {
    private Long id;
    @Column(name = "email")
    private String email;
    @NotBlank(message = "Некорректный формат имени")
    private String firstName;
    @NotBlank(message = "Некорректный формат фамилии")
    private String lastName;
    private String middleName;
    @NotBlank(message = "Некорректный формат номера")
    private String phoneNumber;
}
