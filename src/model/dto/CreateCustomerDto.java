package model.dto;

import lombok.Builder;

import java.sql.Date;

@Builder
public record CreateCustomerDto(
        String name,
        String email,
        String password,
        Boolean is_deleted,
        Date created_at
) {
}