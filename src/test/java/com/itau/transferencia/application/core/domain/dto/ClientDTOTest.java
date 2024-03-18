package com.itau.transferencia.application.core.domain.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClientDTOTest {
    @Test
    public void testGetterSetter() {
        ClientDTO clientDTO = new ClientDTO();

        String cpfEsperado = "12345678900";
        String nomeEsperado = "Mateus Macedo";
        String emailEsperado = "mateusmacedo@live.com";
        String telefoneEsperado = "123456789";
        String numeroContaEsperado = "78901234";
        int statusContaEsperado = 1;

        clientDTO.setCpf(cpfEsperado);
        clientDTO.setNome(nomeEsperado);
        clientDTO.setEmail(emailEsperado);
        clientDTO.setTelefone(telefoneEsperado);
        clientDTO.setNumeroContaCorrente(numeroContaEsperado);
        clientDTO.setStatusConta(statusContaEsperado);

        assertEquals(cpfEsperado, clientDTO.getCpf());
        assertEquals(nomeEsperado, clientDTO.getNome());
        assertEquals(emailEsperado, clientDTO.getEmail());
        assertEquals(telefoneEsperado, clientDTO.getTelefone());
        assertEquals(numeroContaEsperado, clientDTO.getNumeroContaCorrente());
        assertEquals(statusContaEsperado, clientDTO.getStatusConta());
    }
}
