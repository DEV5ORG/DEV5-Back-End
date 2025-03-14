package com.dev5.backenddev5.Controller;

import com.dev5.backenddev5.Model.AuthenticationResponse;
import com.dev5.backenddev5.Model.Usuario;
import com.dev5.backenddev5.Service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    private final AuthenticationService authService;

    public AuthenticationController(AuthenticationService authService) {
        this.authService = authService;
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
}
