package vendas.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vendas.enums.StatusPedido;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pedido")
public class Pedido {

	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO) //usado somente quando usamos o bd em memoria, retirado no mysql
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;

	@Column(name = "data_pedido")
	private LocalDate data_pedido;

	@Column(name = "total", precision = 20, scale = 2)
	private BigDecimal total;

	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private StatusPedido status;

	@OneToMany(mappedBy = "pedido")
	private List<ItemPedido> itens;

//	public List<ItemPedido> getItens() {
//		return itens;
//	}
//
//	public void setItens(List<ItemPedido> itens) {
//		this.itens = itens;
//	}
//
//	public Integer getId() {
//		return id;
//	}
//
//	public void setId(Integer id) {
//		this.id = id;
//	}
//
//	public Cliente getCliente() {
//		return cliente;
//	}
//
//	public void setCliente(Cliente cliente) {
//		this.cliente = cliente;
//	}
//
//	public LocalDate getData_pedido() {
//		return data_pedido;
//	}
//
//	public void setData_pedido(LocalDate data_pedido) {
//		this.data_pedido = data_pedido;
//	}
//
//	public BigDecimal getTotal() {
//		return total;
//	}
//
//	public void setTotal(BigDecimal total) {
//		this.total = total;
//	}

	@Override
	public String toString() {
		return "Pedido [id=" + id + ", data_pedido=" + data_pedido + ", total=" + total + "]";
	}

}
