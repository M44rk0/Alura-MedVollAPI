package com.m44rk0.med_voll_api.domain.consulta.validacoes;

import com.m44rk0.med_voll_api.domain.consulta.ConsultaRepository;
import com.m44rk0.med_voll_api.domain.consulta.DadosAgendamentoConsulta;
import com.m44rk0.med_voll_api.domain.exceptions.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoPacienteSemOutraConsultaNoDia implements ValidacaoAgendamentoDeConsulta{

    @Autowired
    private ConsultaRepository consultaRepository;

    public void validar(DadosAgendamentoConsulta dadosAgendamentoConsulta){

        var primeiroHorario = dadosAgendamentoConsulta.dataDaConsulta().withHour(7);
        var ultimoHorario = dadosAgendamentoConsulta.dataDaConsulta().withHour(18);

        var pacientePossuiOutraConsulta = consultaRepository.existsByPacienteIdAndDataBetween(dadosAgendamentoConsulta.idPaciente(),
                primeiroHorario, ultimoHorario);

        if(pacientePossuiOutraConsulta){
            throw new ValidacaoException("Paciente já possui uma consulta agendada para esse dia");
        }

    }

}
