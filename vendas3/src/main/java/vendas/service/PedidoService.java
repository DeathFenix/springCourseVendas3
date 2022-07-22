package vendas.service;

import vendas.entity.Pedido;
import vendas.enums.StatusPedido;
import vendas.rest.controller.dto.PedidoDTO;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface PedidoService {
    Pedido salvar( PedidoDTO dto );

    Optional<Pedido> obterPedidoCompleto(Integer id);

    void atualizaStatus(Integer id, StatusPedido statusPedido);
}
