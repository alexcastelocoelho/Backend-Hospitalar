package com.example.api.controller;

import com.example.api.config.TokenService;
import com.example.api.model.usuario.RealizarLoginDto;
import com.example.api.model.usuario.LoginRespostaDTO;
import com.example.api.model.usuario.CadastrarUsuarioDto;
import com.example.api.model.usuario.Usuario;
import com.example.api.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    final AuthenticationManager authenticationManager;
    final UsuarioRepository usuarioRepository;
    final TokenService tokenService;

    public AuthenticationController(AuthenticationManager authenticationManager, UsuarioRepository usuarioRepository, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.usuarioRepository = usuarioRepository;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginRespostaDTO> login(@RequestBody @Valid RealizarLoginDto loginDto) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(loginDto.login(), loginDto.password());
        var auth = authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((Usuario) auth.getPrincipal());


        return ResponseEntity.ok(new LoginRespostaDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody @Valid CadastrarUsuarioDto usuarioCadastroDto) {

        if (usuarioRepository.findByLogin(usuarioCadastroDto.login()).isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(usuarioCadastroDto.password());
        Usuario usuario = new Usuario(usuarioCadastroDto.login(), encryptedPassword, usuarioCadastroDto.role());

        usuarioRepository.save(usuario);

        return ResponseEntity.ok().build();

    }
}












