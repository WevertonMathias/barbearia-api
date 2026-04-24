package com.barbearia.api.service.validador;

import com.barbearia.api.infra.VerificarSeEmailExisteException;
import com.barbearia.api.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ClienteServiceValidador {

    private final ClienteRepository clienteRepository;

    public void verificarSeEmailExiste(String email){
        if (clienteRepository.existsByEmail(email)) {
            throw new VerificarSeEmailExisteException();
        };
    }
}
