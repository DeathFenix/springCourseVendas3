package vendas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vendas.entity.ItemPedido;

public interface ItemsPedido extends JpaRepository<ItemPedido, Integer>{

}
