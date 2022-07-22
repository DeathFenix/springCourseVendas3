package vendas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vendas.entity.Produto;

public interface Produtos  extends JpaRepository<Produto, Integer>{

}
