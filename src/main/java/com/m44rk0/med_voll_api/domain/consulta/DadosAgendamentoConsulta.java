package com.m44rk0.med_voll_api.domain.consulta;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.m44rk0.med_voll_api.domain.medico.Especialidade;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosAgendamentoConsulta(
        Long idMedico,
        @NotNull
        Long idPaciente,
        @NotNull @Future
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime dataDaConsulta,

        Especialidade especialidade
) {
}
