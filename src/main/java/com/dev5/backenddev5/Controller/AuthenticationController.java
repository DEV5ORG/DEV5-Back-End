package com.dev5.backenddev5.Controller;

import com.dev5.backenddev5.Model.AuthenticationResponse;
import com.dev5.backenddev5.Model.Usuario;
import com.dev5.backenddev5.Service.AuthenticationService;
import com.dev5.backenddev5.Service.TokenBlacklistService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    private final AuthenticationService authService;
    private final TokenBlacklistService tokenBlacklistService;

    public AuthenticationController(AuthenticationService authService, TokenBlacklistService tokenBlacklistService) {
        this.authService = authService;
        this.tokenBlacklistService = tokenBlacklistService;
    }

    @PostMapping("/api/register")
    public ResponseEntity<AuthenticationResponse> register (
            @Valid @RequestBody Usuario request
    ) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/api/login")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody Usuario request
    ) {
        return ResponseEntity.ok(authService.authenticate(request));
    }

    @PostMapping("/api/logout")
    public ResponseEntity<String> logout(@RequestHeader("Authorization") String authHeader) {
        // Extract token from Authorization header
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            tokenBlacklistService.blacklistToken(token);
            return ResponseEntity.ok("Logged out successfully");
        }
        return ResponseEntity.badRequest().body("Invalid token");
    }
}
