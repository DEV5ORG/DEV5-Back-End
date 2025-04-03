package com.dev5.backenddev5.Service;

import com.dev5.backenddev5.Exception.PasswordChangeException;
import com.dev5.backenddev5.Model.AuthenticationResponse;
import com.dev5.backenddev5.Model.PwdToken;
import com.dev5.backenddev5.Model.Usuario;
import com.dev5.backenddev5.Repository.PwdTokenRepository;
import com.dev5.backenddev5.Repository.UsuarioRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Optional;

@Service
public class AuthenticationService {
    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PwdTokenRepository pwdTokenRepository;

    public AuthenticationService(UsuarioRepository repository,
                                 PasswordEncoder passwordEncoder,
                                 JwtService jwtService,
                                 AuthenticationManager authenticationManager,
                                 PwdTokenRepository pwdTokenRepository) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.pwdTokenRepository = pwdTokenRepository;
    }

    public AuthenticationResponse register(Usuario request) {
        Usuario user = new Usuario();
        user.setNombre(request.getNombre());
        user.setCorreoElectronico(request.getCorreoElectronico());
        user.setContraseña(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());

        user = repository.save(user);
        String token = jwtService.generateToken(user);

        return new AuthenticationResponse(token);
    }

    public AuthenticationResponse authenticate(Usuario request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        Usuario user = repository.findByCorreoElectronico(request.getCorreoElectronico()).orElseThrow();
        String token = jwtService.generateToken(user);

        return new AuthenticationResponse(token);
    }

    public String generateRandomToken(int length) {
        SecureRandom secureRandom = new SecureRandom();
        byte[] randomBytes = new byte[length];
        secureRandom.nextBytes(randomBytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(randomBytes);
    }

    public String passwordRecovery(Usuario request) {
        String email = request.getCorreoElectronico();
        // Validar si el usuario existe
        Optional<Usuario> usuario = repository.findByCorreoElectronico(email);
        if (usuario.isEmpty())
            return "";

        // Generar y almacenar token
        String token = generateRandomToken(15);
        pwdTokenRepository.save(new PwdToken(email, token, LocalDateTime.now().plusHours(24)));

        // Generación de token correcta
        return token;
    }

    public boolean changePassword(String token, Usuario request) {

        String newPassword = request.getContraseña();

        // Validar si el token existe y es válido
        PwdToken pwdToken = pwdTokenRepository.findByToken(token)
                .orElseThrow(() -> new PasswordChangeException(
                        "El token es invalido, por favor vuelva a crear uno nuevo en recuperación de contraseña."));

        // Validar si el token ha expirado
        if (pwdToken.getFecha().isBefore(LocalDateTime.now())) {
            throw new PasswordChangeException(
                    "El token ha expirado, por favor vuelva a crear uno nuevo en recuperación de contraseña.");
        }

        // Obtener el usuario
        Usuario usuario = repository.findByCorreoElectronico(pwdToken.getEmail())
                .orElseThrow(() -> new PasswordChangeException("No se encontró el usuario asociado al token."));

        // Validar que la contraseña sea diferente a la actual
        if (passwordEncoder.matches(newPassword, usuario.getPassword())) {
            throw new PasswordChangeException("La contraseña debe ser diferente a la contraseña actual.");
        }

        // Cambio de contraseña
        String contrasenaEncriptada = passwordEncoder.encode(newPassword);
        usuario.setContraseña(contrasenaEncriptada);
        repository.save(usuario);
        return true;
    }
}
