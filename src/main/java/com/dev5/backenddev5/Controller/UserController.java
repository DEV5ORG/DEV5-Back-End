package com.dev5.backenddev5.Controller;

import com.dev5.backenddev5.Model.Usuario;
import com.dev5.backenddev5.Repository.UsuarioRepository;
import com.dev5.backenddev5.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin("*")
public class UserController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/api/add-user")
    public Usuario submitUser(@RequestBody Usuario newUser){
        Usuario user = usuarioService.adduser(newUser);
        return user;
    }
    @GetMapping("/get-user-by-email")
    public ResponseEntity<Map<String, Object>> getUserByEmail(@RequestParam String email) {
        return usuarioService.getUserByEmail(email)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyMap()));
    }
}
