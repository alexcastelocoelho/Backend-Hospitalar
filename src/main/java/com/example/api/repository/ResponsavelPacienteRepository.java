package com.example.api.repository;

import com.example.api.model.ResponsavelPaciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ResponsavelPacienteRepository extends JpaRepository<ResponsavelPaciente, UUID> {
    boolean existsByCpf(String cpf);
}
