package com.m44rk0.med_voll_api.domain.consulta.validacoes;

import com.m44rk0.med_voll_api.domain.consulta.DadosAgendamentoConsulta;
import com.m44rk0.med_voll_api.domain.exceptions.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidacaoHorarioAntecedencia implements ValidacaoAgendamentoDeConsulta{

    public void validar(DadosAgendamentoConsulta dadosAgendamentoConsulta) {
        var dataConsulta = dadosAgendamentoConsulta.dataDaConsulta();
        var agora = LocalDateTime.now();

        var diferenca = Duration.between(agora, dataConsulta).toMinutes();

        if(diferenca < 30){
            throw new ValidacaoException("Consulta deve ser agendada com antecedência mínima de 30 minutos");
        }

    }
}
