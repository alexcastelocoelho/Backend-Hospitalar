package com.example.api.model;

import com.example.api.utils.Cpf.CpfSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "tb_medico")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String numeroConselho;

    @Column(nullable = false, unique = true, length = 11)
    @JsonSerialize(using = CpfSerializer.class)
    private String cpf;

    @Column(nullable = false)
    private String contato;

    @Column(nullable = false)
    private String endereco;

    @Column(nullable = false)
    private String email;

    public Medico() {
    }

    public Medico(UUID id, String nome, String numeroConselho, String cpf, String contato, String endereco, String email) {
        this.id = id;
        this.nome = nome;
        this.numeroConselho = numeroConselho;
        this.cpf = cpf;
        this.contato = contato;
        this.endereco = endereco;
        this.email = email;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNumeroConselho() {
        return numeroConselho;
    }

    public void setNumeroConselho(String numeroConselho) {
        this.numeroConselho = numeroConselho;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
