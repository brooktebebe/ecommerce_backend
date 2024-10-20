package com.zazu.ecommerce.Dto.Response;

import com.zazu.ecommerce.Entity.Address;

public record CustomerResponse(
        String id,
        String firstName,
        String lastName,
        String email,
        Address address
) {

}
