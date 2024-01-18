package mapper;

import com.fiap.parquimetro.dto.FormaPagamentoDTO;
import com.fiap.parquimetro.model.FormaPagamento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper
public interface FormaPagamentoMapper {

    FormaPagamentoDTO toDTO(FormaPagamento formaPagamento);
    FormaPagamento toEntity(FormaPagamentoDTO dto);
}