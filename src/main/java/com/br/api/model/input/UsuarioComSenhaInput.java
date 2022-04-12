package com.br.api.model.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;


public class UsuarioComSenhaInput extends UsuarioInput {

	@ApiModelProperty(example = "123", required = true)
	@NotBlank
	private String senha;

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	

}
