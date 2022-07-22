package vendas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import vendas.entity.Cliente;


public interface Clientes extends JpaRepository<Cliente, Integer> {

//	@Query(value = " select c from Cliente c where c.name like :nome ") //hql
	@Query(value = " select * from cliente where nome like '%:nome%' ", nativeQuery = true)
	List<Cliente> encontrarPorNome(@Param("nome") String nome);
	
//	@Query(value = "delete from Cliente c where c.name = :nome ")
//	@Modifying
//	void deleteByNome(String nome);
	
//	List<Cliente> findByNameLike(String nome);	//query methods
	
//	List<Cliente> findOneClientes(String nome); // query methods é um metodo que retorna uma query
	
//	List<Cliente> findByNomeOrIdOrderById(String nome, Integer id); //query method
	
	@Query("select c from Cliente c left join fetch c.pedidos where c.id = :id ")
	Cliente findClienteFetchPedidos(@Param("id") Integer id );
	
}
