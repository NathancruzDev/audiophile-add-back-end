package com.example.back_end.service;

import com.example.back_end.model.dto.user.ViaCepDto;
import com.example.back_end.repository.AdressLockupClient;
import org.springframework.web.client.RestClient;

import java.util.Optional;

public class ViaCepLockupService implements AdressLockupClient {
    private final RestClient restClient;
    private String url="https://viacep.com.br/ws/{cep}/json/";
    public ViaCepLockupService(RestClient.Builder restClientBuilder){
        this.restClient = restClientBuilder.build();
    }
    @Override
    public Optional<ViaCepDto> findAdressByCep(String cep) {
        return Optional.empty();
    }

}
