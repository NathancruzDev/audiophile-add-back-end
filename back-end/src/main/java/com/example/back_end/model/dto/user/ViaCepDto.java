package com.example.back_end.model.dto.user;

public record ViaCepDto(
        String cep,
        String logradouro,
        String complemento,
        String bairro,
        String localidade,
        String uf,
        Boolean erro
) {
}
