package com.br.api.model.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;


public class SenhaInput {

	@ApiModelProperty(example = "123", required = true)
	@NotBlank
	private String senhaAtual;

	@ApiModelProperty(example = "123", required = true)
	@NotBlank
	private String novaSenha;

	public String getSenhaAtual() {
		return senhaAtual;
	}

	public void setSenhaAtual(String senhaAtual) {
		this.senhaAtual = senhaAtual;
	}

	public String getNovaSenha() {
		return novaSenha;
	}

	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}
	
	
}
