package com.hoop.court.dto.response;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
@Builder
public class ErrorResponse {
    private final String message;
    private final String code;
}
