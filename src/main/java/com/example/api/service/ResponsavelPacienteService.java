package com.example.api.service;

import com.example.api.dto.atualizar.AtualizarResponsavelPacienteDto;
import com.example.api.dto.criar.CriarResponsavelPacienteDto;
import com.example.api.exception.CpfException;
import com.example.api.exception.ResourceNotFound;
import com.example.api.model.Paciente;
import com.example.api.model.ResponsavelPaciente;
import com.example.api.repository.ResponsavelPacienteRepository;
import org.aspectj.weaver.IClassFileProvider;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ResponsavelPacienteService {

    final ResponsavelPacienteRepository responsavelPacienteRepository;

    public ResponsavelPacienteService(ResponsavelPacienteRepository responsavelPacienteRepository) {
        this.responsavelPacienteRepository = responsavelPacienteRepository;
    }

    public ResponsavelPaciente criarResponsavel(CriarResponsavelPacienteDto responsavelPacienteDto) {
        ResponsavelPaciente responsavel = new ResponsavelPaciente();
        if (responsavelPacienteRepository.existsByCpf(responsavelPacienteDto.cpf())){
            throw new CpfException("Cpf já registrado no sistema");
        }
        BeanUtils.copyProperties(responsavelPacienteDto, responsavel);
        return responsavelPacienteRepository.save(responsavel);
    }

    public Page<ResponsavelPaciente> listarResponsavelPacientes(Pageable pageable) {
        return responsavelPacienteRepository.findAll(pageable);
    }

    public ResponsavelPaciente listarUmResponsavelPaciente(UUID id) {
        return responsavelPacienteRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Responsavel não localizado"));
    }

    public void atualizarResponsavelPaciente(UUID id, AtualizarResponsavelPacienteDto responsavelPacienteDto) {
        var responsavelPaciente = responsavelPacienteRepository.findById(id);
        if (responsavelPaciente.isPresent()) {
            ResponsavelPaciente responsavelPacienteAtualizar = responsavelPaciente.get();

            if (responsavelPacienteDto.nome() != null){
                responsavelPacienteAtualizar.setNome(responsavelPacienteDto.nome());
            }

            if (responsavelPacienteDto.parentesco() != null){
                responsavelPacienteAtualizar.setParentesco(responsavelPacienteDto.parentesco());
            }

            if (responsavelPacienteDto.contato() != null){
                responsavelPacienteAtualizar.setContato(responsavelPacienteDto.contato());
            }

            if (responsavelPacienteDto.endereco() != null){
                responsavelPacienteAtualizar.setEndereco(responsavelPacienteDto.endereco());
            }

            responsavelPacienteRepository.save(responsavelPacienteAtualizar);
        } else {
            throw new ResourceNotFound("Responsavel não localizado");
        }
    }


    public void deletarResponsavelPaciente(UUID id) {
        var responsavelPaciente =responsavelPacienteRepository.findById(id);
        if (responsavelPaciente.isEmpty()) {
            throw new ResourceNotFound("Responsavel não localizado");
        }
        responsavelPacienteRepository.delete(responsavelPaciente.get());
    }


}
