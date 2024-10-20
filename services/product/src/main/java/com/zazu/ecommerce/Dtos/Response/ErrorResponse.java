package com.zazu.ecommerce.Dtos.Response;

import java.util.Map;

public record ErrorResponse(
        Map<String,String> errors

) {
}
