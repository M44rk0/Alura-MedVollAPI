package com.m44rk0.med_voll_api.domain.consulta;

import com.m44rk0.med_voll_api.domain.consulta.validacoes.ValidacaoAgendamentoDeConsulta;
import com.m44rk0.med_voll_api.domain.exceptions.ValidacaoException;
import com.m44rk0.med_voll_api.domain.medico.Medico;
import com.m44rk0.med_voll_api.domain.medico.MedicoRepository;
import com.m44rk0.med_voll_api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaDeConsultas {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private List<ValidacaoAgendamentoDeConsulta> validadores;

    public DadosDetalhamentoConsulta agendar(DadosAgendamentoConsulta dadosConsulta){

        if(!pacienteRepository.existsById(dadosConsulta.idPaciente())){
            throw new ValidacaoException("Id do paciente informado não existe!");
        }

        if(dadosConsulta.idMedico() !=null && !medicoRepository.existsById(dadosConsulta.idMedico())){
            throw new ValidacaoException("Id do médico não existe!");
        }

        validadores.forEach(v -> v.validar(dadosConsulta));

        var paciente = pacienteRepository.getReferenceById(dadosConsulta.idPaciente());
        var medico = escolherMedico(dadosConsulta);
        var consulta = new Consulta(null, medico, paciente, dadosConsulta.dataDaConsulta(), null);

        consultaRepository.save(consulta);

        return new DadosDetalhamentoConsulta(consulta);

    }

    public void cancelar(DadosCancelamentoConsulta cancelamentoConsulta){
        if(!consultaRepository.existsById(cancelamentoConsulta.idConsulta())){
            throw new ValidacaoException("Id da consulta informado não existe no banco de dados");
        }

        var consulta = consultaRepository.getReferenceById(cancelamentoConsulta.idConsulta());
        consulta.cancelar(cancelamentoConsulta.motivoCancelamento());
    }

    private Medico escolherMedico(DadosAgendamentoConsulta dadosConsulta) {

        if(dadosConsulta.idMedico() != null){
            return medicoRepository.getReferenceById(dadosConsulta.idMedico());
        }

        if(dadosConsulta.especialidade() == null){
            throw  new ValidacaoException("Especialidade é obrigatória quando o médico não foi escolhido");
        }

        return medicoRepository.escolherMedicoAleatorioLivreNaData(dadosConsulta.especialidade(), dadosConsulta.dataDaConsulta());
    }



}
