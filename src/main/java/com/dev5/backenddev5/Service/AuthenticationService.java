package com.dev5.backenddev5.Service;

import com.dev5.backenddev5.Model.AuthenticationResponse;
import com.dev5.backenddev5.Model.Usuario;
import com.dev5.backenddev5.Repository.UsuarioRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UsuarioRepository repository,
                                 PasswordEncoder passwordEncoder,
                                 JwtService jwtService,
                                 AuthenticationManager authenticationManager) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResponse register(Usuario request) {
        Usuario user = new Usuario();
        user.setNombre(request.getNombre());
        user.setApellido1(request.getApellido1());
        user.setApellido2(request.getApellido2());
        user.setCorreoElectronico(request.getCorreoElectronico());
        user.setContraseña(passwordEncoder.encode(request.getPassword()));
        user.setTipoUsuario(request.getTipoUsuario());
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

}
