package com.example.api.service;

import com.example.api.dto.CustomPageResponseDto;
import com.example.api.dto.atualizar.AtualizarPacienteDto;
import com.example.api.dto.criar.CriarPacienteDto;
import com.example.api.exception.CpfException;
import com.example.api.exception.ResourceNotFound;
import com.example.api.model.Paciente;
import com.example.api.repository.PacienteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.UUID;

@Service
public class PacienteService {

    final PacienteRepository pacienteRepository;

    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public Paciente criarPaciente(CriarPacienteDto pacienteDto) {
        Paciente paciente = new Paciente();
        if (pacienteRepository.existsByCpf(pacienteDto.cpf())){
            throw new CpfException("Cpf já registrado no sistema");
        }

        BeanUtils.copyProperties(pacienteDto, paciente);

        return pacienteRepository.save(paciente);
    }

    public CustomPageResponseDto<Paciente> listarPaciente(Pageable pageable) {
        Page<Paciente> pacientes = pacienteRepository.findAll(pageable);
        return new CustomPageResponseDto<>(pacientes.getContent(), pacientes.getNumber(), pacientes.getSize(), pacientes.getTotalElements(), pacientes.getTotalPages(), pacientes.isFirst(), pacientes.isLast());


    }


    public Paciente listarUmPaciente(UUID id) {
        return pacienteRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Paciente não localizado"));
    }



    public void atualizarPaciente(UUID id, AtualizarPacienteDto pacienteDto) {
        var paciente = pacienteRepository.findById(id);
        if (paciente.isPresent()) {
            Paciente pacienteAtualizar = paciente.get();

            if (pacienteDto.nome() != null){
                pacienteAtualizar.setNome(pacienteDto.nome());
            }

            if (pacienteDto.idade() != null){
                pacienteAtualizar.setIdade(pacienteDto.idade());
            }

            if (pacienteDto.contato() != null){
                pacienteAtualizar.setContato(pacienteDto.contato());
            }

            if (pacienteDto.endereco() != null){
                pacienteAtualizar.setEndereco(pacienteDto.endereco());
            }

            pacienteRepository.save(pacienteAtualizar);
        } else {
            throw new ResourceNotFound("Paciente não localizado");
        }

    }

    public void deletarPaciente(UUID id) {
        var paciente =pacienteRepository.findById(id);
        if (paciente.isEmpty()) {
            throw new ResourceNotFound("Paciente não localizado");
        }
        pacienteRepository.delete(paciente.get());
    }
}
