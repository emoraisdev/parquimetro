package com.fiap.parquimetro.service.impl;

import com.fiap.parquimetro.dto.LocalVagaDTO;
import com.fiap.parquimetro.model.LocalVaga;
import com.fiap.parquimetro.model.Permanencia;
import com.fiap.parquimetro.repository.LocalVagaRepository;
import com.fiap.parquimetro.service.LocalVagaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LocalVagaServiceImpl implements LocalVagaService {

    private final LocalVagaRepository localVagaRepository;

    @Override
    public LocalVagaDTO buscarLocalVaga(String id) {
        Optional<LocalVaga> localVaga = localVagaRepository.findById(id);
        if (localVaga.isPresent()) {
            return toDTO(localVaga.get());
        } else {
            throw new RuntimeException("Não existe informações cadastradas no estacionamento com esse ID");
        }
    }

    @Override
    public BigDecimal calcularValorEstacionamento(LocalVagaDTO localVagaDTO, Permanencia permanencia) {
        if (localVagaDTO.valorHoraFixa() != null) {
            return calcularValorHoraFixa(localVagaDTO, permanencia);
        } else if (localVagaDTO.valorHoraVariavel() != null) {
            return calcularValorHoraVariavel(localVagaDTO, permanencia);
        } else {
            throw new IllegalArgumentException("Os valores de hora fixa e variável não estão definidos.");
        }
    }

    private BigDecimal calcularValorHoraFixa(LocalVagaDTO localVagaDTO, Permanencia permanencia) {
        BigDecimal valorFixoPorHora = new BigDecimal("10.0");
        return valorFixoPorHora.multiply(BigDecimal.valueOf(calcularPeriodo(permanencia)));
    }

    private BigDecimal calcularValorHoraVariavel(LocalVagaDTO localVagaDTO, Permanencia permanencia) {

        BigDecimal valorVariavelPorHora = localVagaDTO.valorHoraVariavel();

        if (permanenciaOcorreDuranteANoite(permanencia)) {
            BigDecimal taxaNoturna = valorVariavelPorHora.multiply(new BigDecimal("0.5"));
            valorVariavelPorHora = valorVariavelPorHora.add(taxaNoturna);
        }
        return valorVariavelPorHora.multiply(BigDecimal.valueOf(calcularPeriodo(permanencia)));
    }

    private boolean permanenciaOcorreDuranteANoite(Permanencia permanencia) {
        LocalDateTime horaEntrada = permanencia.getEntrada();
        LocalDateTime horaSaida = permanencia.getSaida();
        LocalTime inicioNoite = LocalTime.of(22, 0);
        LocalTime fimNoite = LocalTime.of(6, 0);
        return horaEntrada.toLocalTime().isBefore(fimNoite) && horaSaida.toLocalTime().isAfter(inicioNoite);
    }


    private float calcularPeriodo(Permanencia permanencia) {
        LocalDateTime horaEntrada = permanencia.getEntrada();
        LocalDateTime horaSaida = permanencia.getSaida();

        long minutes = ChronoUnit.MINUTES.between(horaEntrada, horaSaida);

        return minutes / 60.0f;
    }

    private LocalVagaDTO toDTO(LocalVaga localVaga) {
        return new LocalVagaDTO(
                localVaga.getId(),
                localVaga.getValorHoraVariavel(),
                localVaga.getValorHoraFixa(),
                localVaga.getStatus()
        );
    }

    private LocalVaga toEntity(LocalVagaDTO localVagaDTO) {
        LocalVaga localVaga = new LocalVaga();
        localVaga.setId(localVagaDTO.id());
        localVaga.setValorHoraVariavel(localVagaDTO.valorHoraVariavel());
        localVaga.setValorHoraFixa(localVagaDTO.valorHoraVariavel());
        localVaga.setStatus(localVagaDTO.status());
        return localVaga;
    }
}