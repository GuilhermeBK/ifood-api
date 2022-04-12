package com.br.api.model.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import com.br.core.validation.FileContentType;
import com.br.core.validation.FileSize;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


public class FotoProdutoInput {

	@ApiModelProperty(hidden = true)
	@NotNull
	@FileSize(max = "500KB")
	@FileContentType(allowed = { MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE })
	private MultipartFile arquivo;

	@ApiModelProperty(value = "Descrição da foto do produto", required = true)
	@NotBlank
	private String descricao;

	public MultipartFile getArquivo() {
		return arquivo;
	}

	public void setArquivo(MultipartFile arquivo) {
		this.arquivo = arquivo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	

}
