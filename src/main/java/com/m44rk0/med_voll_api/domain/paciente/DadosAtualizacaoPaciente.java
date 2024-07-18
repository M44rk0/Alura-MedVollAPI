package com.m44rk0.med_voll_api.domain.paciente;

import com.m44rk0.med_voll_api.domain.endereco.DadosEndereco;
import jakarta.validation.Valid;

public record DadosAtualizacaoPaciente(
        Long id,
        String nome,
        String telefone,
        @Valid DadosEndereco endereco
) {
}
