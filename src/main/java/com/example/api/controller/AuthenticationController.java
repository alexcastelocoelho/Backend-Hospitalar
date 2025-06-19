package com.example.api.controller;

import com.example.api.config.TokenService;
import com.example.api.model.usuario.RealizarLoginDto;
import com.example.api.model.usuario.LoginRespostaDTO;
import com.example.api.model.usuario.CadastrarUsuarioDto;
import com.example.api.model.usuario.Usuario;
import com.example.api.repository.UsuarioRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/auth")
@Tag(name = "Autenticacao", description = "operações/endpoints para autenticacao no sistema")
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
    @Operation(summary = "login no sistema", description = "endpoint para realizar login no sistema")
    public ResponseEntity<LoginRespostaDTO> login(@RequestBody @Valid RealizarLoginDto loginDto) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(loginDto.email(), loginDto.senha());
        var auth = authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((Usuario) auth.getPrincipal());


        return ResponseEntity.ok(new LoginRespostaDTO(token));
    }

    @PostMapping("/registro")
    @Operation(summary = "cadastrar novo usuario", description = "endpoint para cadastrar um novo usuario no sistema")
    public ResponseEntity<Void> register(@RequestBody @Valid CadastrarUsuarioDto usuarioCadastroDto) {

        if (usuarioRepository.findByEmail(usuarioCadastroDto.email()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(usuarioCadastroDto.senha());
        Usuario usuario = new Usuario(usuarioCadastroDto.email(), encryptedPassword, usuarioCadastroDto.role());

        usuarioRepository.save(usuario);

        return ResponseEntity.ok().build();

    }
}












