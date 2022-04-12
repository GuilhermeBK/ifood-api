package com.br.api.model.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

public class ProdutoInput {

	@ApiModelProperty(example = "Espetinho de Cupim", required = true)
	@NotBlank
	private String nome;

	@ApiModelProperty(example = "Acompanha farinha, mandioca e vinagrete", required = true)
	@NotBlank
	private String descricao;

	@ApiModelProperty(example = "12.50", required = true)
	@NotNull
	@PositiveOrZero
	private BigDecimal preco;

	@ApiModelProperty(example = "true", required = true)
	@NotNull
	private Boolean ativo;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	
	

}
