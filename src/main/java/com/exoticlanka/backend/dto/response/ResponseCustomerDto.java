package com.exoticlanka.backend.dto.response;

import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResponseCustomerDto {
    private long id;
    private String name;
    private String address;
    private String contact;
}
