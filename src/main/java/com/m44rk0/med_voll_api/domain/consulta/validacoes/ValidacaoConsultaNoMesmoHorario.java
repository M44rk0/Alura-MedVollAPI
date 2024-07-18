package com.m44rk0.med_voll_api.domain.consulta.validacoes;

import com.m44rk0.med_voll_api.domain.consulta.ConsultaRepository;
import com.m44rk0.med_voll_api.domain.consulta.DadosAgendamentoConsulta;
import com.m44rk0.med_voll_api.domain.exceptions.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoConsultaNoMesmoHorario implements ValidacaoAgendamentoDeConsulta{

    @Autowired
    private ConsultaRepository consultaRepository;

    public void validar(DadosAgendamentoConsulta dadosAgendamentoConsulta){
        var medicoPossuiOutroConsultaNoMesmoHorario = consultaRepository.existsByMedicoIdAndData(dadosAgendamentoConsulta.idMedico(),
                dadosAgendamentoConsulta.dataDaConsulta());

        if(medicoPossuiOutroConsultaNoMesmoHorario){
            throw new ValidacaoException("Médico já possui uma consulta agendada pra esse hórario");
        }
    }

}
