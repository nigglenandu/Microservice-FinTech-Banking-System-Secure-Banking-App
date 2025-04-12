package Niggle.Nandu.Jwt.Security.JwtSecurity.payload;

import jakarta.validation.constraints.NotNull;

public class LogoutRequest {

    @NotNull(message = "Token is required")
    private String token;
}


