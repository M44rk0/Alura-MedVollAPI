package com.m44rk0.med_voll_api.domain.consulta.validacoes;

import com.m44rk0.med_voll_api.domain.consulta.DadosAgendamentoConsulta;
import com.m44rk0.med_voll_api.domain.exceptions.ValidacaoException;
import com.m44rk0.med_voll_api.domain.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoMedicoAtivo implements ValidacaoAgendamentoDeConsulta{

    @Autowired
    private MedicoRepository medicoRepository;

    public void validar(DadosAgendamentoConsulta dadosAgendamentoConsulta){
        if(dadosAgendamentoConsulta.idMedico() == null){
            return;
        }

        var medicoAtivo = medicoRepository.findAtivoById(dadosAgendamentoConsulta.idMedico());
        if(!medicoAtivo){
            throw new ValidacaoException("Médico não está ativo");
        }
    }

}
