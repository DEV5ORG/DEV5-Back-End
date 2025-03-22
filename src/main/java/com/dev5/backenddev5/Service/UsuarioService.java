package com.dev5.backenddev5.Service;

import com.dev5.backenddev5.Model.Usuario;
import com.dev5.backenddev5.Repository.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
public class UsuarioService implements UserDetailsService {

    private final UsuarioRepository repository;

    private List<String> UsuarioStringData;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public Usuario adduser(Usuario user){
        user.setId(1);
        user.setNombre("Daniel");
        user.setCorreoElectronico("prueba.asd@gmail.com");
        this.UsuarioStringData.add(String.valueOf(user));
        return user;
    }

    //LoadByEmail
    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        return repository.findByCorreoElectronico(correo)
                .orElseThrow(()-> new UsernameNotFoundException("User not found"));
    }

    public Optional<Map<String, Object>> getUserByEmail(String correo) {
        return repository.findByCorreoElectronico(correo)
                .map(usuario -> {
                    Map<String, Object> userData = new HashMap<>();
                    userData.put("idUsuario", usuario.getId());
                    userData.put("nombre", usuario.getNombre());
                    userData.put("tipoUsuario", usuario.getRole().name());
                    return userData;
                });
    }
}
