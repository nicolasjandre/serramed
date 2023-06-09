package br.com.serratec.serramed.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.serramed.domain.service.LoginService;
import br.com.serratec.serramed.dto.login.LoginCreateRequestDto;
import br.com.serratec.serramed.dto.login.LoginCreateResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/login")
@Tag(name = "Login", description = "Todos os endpoints relacionados a entidade de Login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/create")
    @Operation(summary = "Cria um novo login atrelado a um médico ou a um funcionário")
    public ResponseEntity<LoginCreateResponseDto> create(@RequestBody @Valid LoginCreateRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(loginService.create(dto));
    }

    @GetMapping
    @Operation(summary = "Retorna todos os logins cadastrados")
    public ResponseEntity<List<LoginCreateResponseDto>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(loginService.findAll());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza o login do usuário")
    public ResponseEntity<LoginCreateResponseDto> update(@Valid @RequestBody LoginCreateRequestDto dto,
            @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(loginService.update(dto, id));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retorna o login baseado no id digitado")
    public ResponseEntity<LoginCreateResponseDto> findById(
            @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(loginService.findById(id));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta o login do ID digitado")
    public ResponseEntity<Void> delete(
            @PathVariable Long id) {
        loginService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}