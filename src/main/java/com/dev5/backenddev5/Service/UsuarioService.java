package com.dev5.backenddev5.Service;

import com.dev5.backenddev5.Model.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UsuarioService {

    private List<String> UsuarioStringData;

    public UsuarioService() {}

    public Usuario adduser(Usuario user){
        user.setId(1L);
        user.setNombre("Daniel");
        user.setApellido1("Apellido1");
        user.setApellido2("Apellido2");
        user.setCorreoElectronico("prueba.asd@gmail.com");
        this.UsuarioStringData.add(String.valueOf(user));
        return user;
    }
}
