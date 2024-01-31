package com.exoticlanka.backend.dto.request;

import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class RequestUserDto {
    private long id;
    private String fullName;
    private String number;
    private String email;
    private String password;
}
