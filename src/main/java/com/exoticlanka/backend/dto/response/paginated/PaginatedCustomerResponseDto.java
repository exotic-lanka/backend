package com.exoticlanka.backend.dto.response.paginated;

import com.exoticlanka.backend.dto.response.ResponseCustomerDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaginatedCustomerResponseDto {
    private long count;
    private List<ResponseCustomerDto> dataList;
}
