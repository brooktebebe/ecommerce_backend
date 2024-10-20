package com.zazu.ecommerce.Dto.Response;

import java.util.Map;

public record ErrorResponse (
        Map<String,String> errors

) {
}
