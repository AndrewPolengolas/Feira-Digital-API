package com.pi.feiradigital.controller.security;

import com.pi.feiradigital.model.Role;
import com.pi.feiradigital.model.Usuario;
import com.pi.feiradigital.records.DadosAuth;
import com.pi.feiradigital.records.UserRecord;
import com.pi.feiradigital.repository.RoleRepository;
import com.pi.feiradigital.repository.UsuarioRepository;
import com.pi.feiradigital.service.jwt.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder encoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid DadosAuth dados) {

        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.password());

        var authentication = manager.authenticate(authenticationToken);

        if (authentication.isAuthenticated()) {
            var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());

            HashMap<String, String> token = new HashMap<>();

            token.put("token", tokenJWT);

            return new ResponseEntity<>(token, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @PostMapping("/cadastrar-user")
    public ResponseEntity adicionarSuser(@RequestBody UserRecord user) {

        Optional<Role> userRole = roleRepository.findByName(user.roleName());
        List<Role> roles = new ArrayList<>();
        roles.add(userRole.get());

        Usuario usuario = new Usuario();

        usuario.setRoles(roles);
        usuario.setPassword(encoder.encode(user.password()));

        usuarioRepository.save(usuario);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/cadastrar-role")
    public ResponseEntity<?> cadastrarRole(@RequestBody Role role) {

        Optional<Role> userRole = roleRepository.findByName(role.getName());

        if (userRole.isPresent()){
            throw new RuntimeException("Role já exixtente!");
        }

        Role retorno = roleRepository.save(role);

        return new ResponseEntity<>(retorno, HttpStatus.OK);
    }

}
