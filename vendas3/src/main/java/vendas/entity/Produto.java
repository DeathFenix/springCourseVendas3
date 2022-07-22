package vendas.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "produto")
public class Produto {
	
	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO) //usado somente quando usamos o bd em memoria, retirado no mysql
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "descricao", length = 100)
	@NotEmpty(message = "{campo.descricao.obrigatorio}")
	private String descricao;
	
	@Column(name = "preco_unitario")
	@NotNull(message = "{campo.preco.obrigatorio}")
	private BigDecimal preco_unitario;
	
//	public Integer getId() {
//		return id;
//	}
//
//	public void setId(Integer id) {
//		this.id = id;
//	}
//
//	public String getDescricao() {
//		return descricao;
//	}
//
//	public void setDescricao(String descricao) {
//		this.descricao = descricao;
//	}
//
//	public BigDecimal getPreco_unitario() {
//		return preco_unitario;
//	}
//
//	public void setPreco_unitario(BigDecimal preco_unitario) {
//		this.preco_unitario = preco_unitario;
//	}

}
