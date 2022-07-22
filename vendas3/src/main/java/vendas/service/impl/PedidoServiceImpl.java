package vendas.service.impl;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vendas.entity.Cliente;
import vendas.entity.ItemPedido;
import vendas.entity.Pedido;
import vendas.entity.Produto;
import vendas.enums.StatusPedido;
import vendas.exception.PedidoNaoEncontradoException;
import vendas.exception.RegraNegocioException;
import vendas.repository.Clientes;
import vendas.repository.ItemsPedido;
import vendas.repository.Pedidos;
import vendas.repository.Produtos;
import vendas.rest.controller.dto.ItemPedidoDTO;
import vendas.rest.controller.dto.PedidoDTO;
import vendas.service.PedidoService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final Pedidos repository;
    private final Clientes clientesRepository;
    private final Produtos produtosRepository;
    private final ItemsPedido itemsPedidoRepository;

//    public PedidoServiceImpl(Pedidos repository, Clientes clientes, Produtos produtos) {
//        this.repository = repository;
//        this.clientesRepository = clientes;
//        this.produtosRepository = produtos;
//    }

    @Override
    @Transactional
    public Pedido salvar(PedidoDTO dto) {
        Integer idCliente = dto.getCliente();
        Cliente cliente = clientesRepository
                .findById(idCliente)
                .orElseThrow(() -> new RegraNegocioException("Código de cliente inválido."));

        Pedido pedido = new Pedido();
        pedido.setTotal(dto.getTotal());
        pedido.setData_pedido(LocalDate.now());
        pedido.setCliente(cliente);
        pedido.setStatus(StatusPedido.REALIZADO);

        List<ItemPedido> itemsPedido = converterItems(pedido, dto.getItems());
        repository.save(pedido);
        itemsPedidoRepository.saveAll( itemsPedido );
        pedido.setItens(itemsPedido);

        return pedido;
    }

    @Override
    public Optional<Pedido> obterPedidoCompleto(Integer id) {
        return repository.findByIdFetchItens(id);
    }

    @Override
    @Transactional
    public void atualizaStatus(Integer id, StatusPedido statusPedido) {
        repository
                .findById(id)
                .map( pedido -> {
                    pedido.setStatus((statusPedido));
                    return  repository.save(pedido);
                }).orElseThrow(()-> new PedidoNaoEncontradoException() );
    }

    private List<ItemPedido> converterItems(Pedido pedido, List<ItemPedidoDTO> items){
        if(items.isEmpty()){
            throw new RegraNegocioException("Não é possível realizar um pedido sem items.");
        }

        return items
                .stream()
                .map( dto -> {
                    Integer idProduto = dto.getProduto();
                    Produto produto = produtosRepository
                            .findById((idProduto))
                            .orElseThrow(
                                    ()-> new RegraNegocioException("Código de produto inválido: " + idProduto
                                    ));

                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setQuantidade(dto.getQuantidade());
                    itemPedido.setPedido(pedido);
                    itemPedido.setProduto(produto);
                    return itemPedido;

                }).collect(Collectors.toList());
    }
}
