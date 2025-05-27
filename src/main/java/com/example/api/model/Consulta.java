package com.example.api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table(name = "tb_consulta ")
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataConsulta;

    @Column(nullable = false)
    @JsonFormat(pattern = "HH:mm")
    private LocalTime horaConsulta;

    @ManyToOne
    @JoinColumn(name = "paciente_id")
    @JsonBackReference("paciente-consultas")
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "medico_id")
    @JsonBackReference("medico-consultas")
    private Medico medico;


    @Column(nullable = false)
    private String status;

    public Consulta() {
    }

    public Consulta(UUID id, LocalDate dataConsulta, LocalTime horaConsulta, Paciente paciente, Medico medico, String status) {
        this.id = id;
        this.dataConsulta = dataConsulta;
        this.horaConsulta = horaConsulta;
        this.paciente = paciente;
        this.medico = medico;
        this.status = status;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDate getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(LocalDate dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public LocalTime getHoraConsulta() {
        return horaConsulta;
    }

    public void setHoraConsulta(LocalTime horaConsulta) {
        this.horaConsulta = horaConsulta;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
