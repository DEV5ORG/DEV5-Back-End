package com.dev5.backenddev5.Controller;

import com.dev5.backenddev5.Model.Usuario;
import com.dev5.backenddev5.Repository.UsuarioRepository;
import com.dev5.backenddev5.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}
