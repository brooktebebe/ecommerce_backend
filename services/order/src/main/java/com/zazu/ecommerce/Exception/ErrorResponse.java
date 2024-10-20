package com.zazu.ecommerce.Exception;

import java.util.Map;

public record ErrorResponse(
        Map<String,String> errors

) {
}
