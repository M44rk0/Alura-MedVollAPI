package com.m44rk0.med_voll_api.domain.endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosEndereco(

        @NotBlank
        String logradouro,

        @NotBlank
        String bairro,

        @NotBlank @Pattern(regexp = "\\d{8}")
        String cep,

        @NotBlank
        String uf,

        @NotBlank
        String cidade,

        String complemento,

        String numero

) {
}
