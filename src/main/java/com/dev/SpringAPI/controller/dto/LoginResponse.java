package com.dev.SpringAPI.controller.dto;

public record LoginResponse(String acessToken, Long expiresIn) {
}
