package com.m44rk0.med_voll_api.controller;

import com.m44rk0.med_voll_api.domain.consulta.AgendaDeConsultas;
import com.m44rk0.med_voll_api.domain.consulta.DadosAgendamentoConsulta;
import com.m44rk0.med_voll_api.domain.consulta.DadosCancelamentoConsulta;
import com.m44rk0.med_voll_api.domain.consulta.DadosDetalhamentoConsulta;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("consultas")
@SecurityRequirement(name = "bearer-key")
public class ConsultaController {

    @Autowired
    private AgendaDeConsultas agendaDeConsultas;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoConsulta> agendarConsulta(@RequestBody @Valid DadosAgendamentoConsulta dadosConsulta){

        var consulta = agendaDeConsultas.agendar(dadosConsulta);

        return ResponseEntity.ok(consulta);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity<Void> cancelarConsulta(@RequestBody @Valid DadosCancelamentoConsulta cancelamentoConsulta){
        agendaDeConsultas.cancelar(cancelamentoConsulta);

        return ResponseEntity.noContent().build();
    }

}
