package com.example.api.service;

import com.example.api.dto.CustomPageResponseDto;
import com.example.api.dto.atualizar.AtualizarConsultaDto;
import com.example.api.dto.criar.CriarConsultaDto;
import com.example.api.exception.ResourceNotFound;
import com.example.api.model.Consulta;
import com.example.api.repository.ConsultaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ConsultaService {

    final ConsultaRepository consultaRepository;

    public ConsultaService(ConsultaRepository consultaRepository) {
        this.consultaRepository = consultaRepository;
    }

    public Consulta criarConsulta(CriarConsultaDto consultaDto) {
        Consulta consulta = new Consulta();

        BeanUtils.copyProperties(consultaDto, consulta);
        return consultaRepository.save(consulta);
    }

    public CustomPageResponseDto<Consulta> listarConsultas(Pageable pageable) {
        Page<Consulta> consultas  = consultaRepository.findAll(pageable);
        return new CustomPageResponseDto<>(consultas.getContent(), consultas.getNumber(), consultas.getSize(), consultas.getTotalElements(), consultas.getTotalPages(), consultas.isFirst(), consultas.isLast());

    }


    public Consulta listarUmaConsulta(UUID id) {
        return consultaRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Consulta não localizado"));

    }

    public void deletarConsulta(UUID id) {
        var consulta = consultaRepository.findById(id);
        if (consulta.isEmpty()) {
            throw new ResourceNotFound("Consulta não localizada");
        }
        consultaRepository.delete(consulta.get());
    }

    public void atualizarConsulta(UUID id, AtualizarConsultaDto consultaDto) {
        var consulta = consultaRepository.findById(id);
        if (consulta.isPresent()) {
            Consulta consultaAtualizar = consulta.get();

            if (consultaDto.dataConsulta() != null) {
                consultaAtualizar.setDataConsulta(consultaDto.dataConsulta());
            }

            if (consultaDto.horaConsulta() != null) {
                consultaAtualizar.setHoraConsulta(consultaDto.horaConsulta());
            }

            if (consultaDto.medico() != null) {
                consultaAtualizar.setMedico(consultaDto.medico());
            }

            if (consultaDto.status() != null) {
                consultaAtualizar.setStatus(consultaDto.status());
            }

            consultaRepository.save(consultaAtualizar);
        } else {
            throw new ResourceNotFound("Consulta não localizada");
        }
    }
}
