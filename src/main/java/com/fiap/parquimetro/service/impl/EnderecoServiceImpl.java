package com.fiap.parquimetro.service.impl;

import com.fiap.parquimetro.dto.EnderecoDTO;
import com.fiap.parquimetro.model.Endereco;
import org.springframework.stereotype.Service;


@Service
public class EnderecoServiceImpl {
    public EnderecoDTO toDTO(Endereco endereco) {
        return new EnderecoDTO(
                endereco.getRua(),
                endereco.getNumero(),
                endereco.getBairro(),
                endereco.getCidade(),
                endereco.getEstado(),
                endereco.getPais(),
                endereco.getCep()
        );
    }

    public Endereco toEntity(EnderecoDTO enderecoDTO) {
        Endereco endereco = new Endereco();
        endereco.setRua(enderecoDTO.rua());
        endereco.setNumero(enderecoDTO.numero());
        endereco.setBairro(enderecoDTO.bairro());
        endereco.setCidade(enderecoDTO.cidade());
        endereco.setEstado(enderecoDTO.estado());
        endereco.setPais(enderecoDTO.pais());
        endereco.setCep(enderecoDTO.cep());
        return endereco;
    }

}