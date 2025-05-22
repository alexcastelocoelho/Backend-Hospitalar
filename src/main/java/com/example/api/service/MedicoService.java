package com.example.api.service;

import com.example.api.dto.atualizar.AtualizarMedicoDto;
import com.example.api.dto.criar.CriarMedicoDto;
import com.example.api.exception.CpfException;
import com.example.api.exception.ResourceNotFound;
import com.example.api.model.Medico;
import com.example.api.repository.MedicoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MedicoService {

    final MedicoRepository medicoRepository;

    public MedicoService(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    public Medico criarMedico(CriarMedicoDto medicoDto) {
        Medico medico = new Medico();
        if (medicoRepository.existsByCpf(medicoDto.cpf())){
            throw new CpfException("Cpf já registrado no sistema");
        }

        BeanUtils.copyProperties(medicoDto, medico);

        return medicoRepository.save(medico);
    }

    public Page<Medico> listarMedicos(Pageable pageable) {
        return medicoRepository.findAll(pageable);
    }

    public Medico listarUmMedico(UUID id) {
        return medicoRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Medico não localizado"));
    }

    public void atualizarMedico(UUID id, AtualizarMedicoDto medicoDto) {
        var medico = medicoRepository.findById(id);
        if (medico.isPresent()){
            Medico medicoAtualizar = medico.get();

            if (medicoDto.nome() != null){
                medicoAtualizar.setNome(medicoDto.nome());
            }

            if (medicoDto.contato() != null){
                medicoAtualizar.setContato(medicoDto.contato());
            }

            if (medicoDto.endereco() != null){
                medicoAtualizar.setEndereco(medicoDto.endereco());
            }

            if (medicoDto.email() != null){
                medicoAtualizar.setEmail(medicoDto.email());
            }

            medicoRepository.save(medicoAtualizar);
        } else {
            throw new ResourceNotFound("Medico não localizado");
        }
    }


    public void deletarMedico(UUID id) {
        var medico =medicoRepository.findById(id);
        if (medico.isEmpty()) {
            throw new ResourceNotFound("Medico não localizado");
        }
        medicoRepository.delete(medico.get());
    }


}
