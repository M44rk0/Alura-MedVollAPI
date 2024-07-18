package com.m44rk0.med_voll_api.domain.consulta.validacoes;

import com.m44rk0.med_voll_api.domain.consulta.DadosAgendamentoConsulta;
import com.m44rk0.med_voll_api.domain.exceptions.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidacaoHorarioFuncionamentoClinica implements ValidacaoAgendamentoDeConsulta{

    public void validar(DadosAgendamentoConsulta dadosAgendamentoConsulta){

        var dataConsulta = dadosAgendamentoConsulta.dataDaConsulta();

        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antesDaAberturaDaClinica = dataConsulta.getHour() < 7;
        var depoisDoEncerramento = dataConsulta.getHour() >18;

        if(domingo || antesDaAberturaDaClinica || depoisDoEncerramento){
            throw new ValidacaoException("Consulta fora do horário de funcionamento da clínica");
        }
    }

}
