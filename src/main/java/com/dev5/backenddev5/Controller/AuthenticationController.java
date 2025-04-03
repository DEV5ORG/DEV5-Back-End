package com.dev5.backenddev5.Controller;

import com.dev5.backenddev5.Exception.PasswordChangeException;
import com.dev5.backenddev5.Model.AuthenticationResponse;
import com.dev5.backenddev5.Model.Usuario;
import com.dev5.backenddev5.Service.AuthenticationService;
import com.dev5.backenddev5.Service.EmailService;
import com.dev5.backenddev5.Service.TokenBlacklistService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthenticationController {
    private final AuthenticationService authService;
    private final TokenBlacklistService tokenBlacklistService;
    private final EmailService emailService;

    public AuthenticationController(AuthenticationService authService, TokenBlacklistService tokenBlacklistService,
                                    EmailService emailService) {
        this.authService = authService;
        this.tokenBlacklistService = tokenBlacklistService;
        this.emailService = emailService;
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

    @PostMapping("/api/pwd-recovery")
    public ResponseEntity<?> pwdrecovery(@RequestBody Usuario request) {
        String token = authService.passwordRecovery(request);

        if(!token.isEmpty()){
            emailService.sendEmail(request.getCorreoElectronico(),
                    "Recuperación de contraseña | LinkoApp",
                    "Solicitaste una recuperación de contraseña, ingresa el siguiente código para realizar la modificación: "
                            + token + " (este código expirará dentro de 24 horas).");
            return ResponseEntity.ok("Password recovery successful");
        } else {
            return ResponseEntity.badRequest().body("Invalid email");
        }
    }

    @PutMapping("/api/pwd-change/{token}")
    public ResponseEntity<?> cambiarpwd(@PathVariable(value = "token") String token, @RequestBody Usuario request) {
        try {
            authService.changePassword(token, request);
            return ResponseEntity.ok().build();
        } catch (PasswordChangeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
