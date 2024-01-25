package com.fiap.parquimetro.service;

import com.fiap.parquimetro.dto.CondutorDTO;
import com.fiap.parquimetro.dto.NotificacaoDTO;
import com.fiap.parquimetro.model.Notificacao;

import java.util.List;

public interface NotificacaoService {

    public NotificacaoDTO criar(NotificacaoDTO dto);

    public NotificacaoDTO get(String id);

    public void salvarNotificacao(Notificacao notificacao);

}
