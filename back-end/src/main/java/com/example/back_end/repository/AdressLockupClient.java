package com.example.back_end.repository;

import com.example.back_end.model.dto.user.ViaCepDto;

import java.util.Optional;

public interface AdressLockupClient {
    Optional<ViaCepDto> findAdressByCep(String cep);
}
