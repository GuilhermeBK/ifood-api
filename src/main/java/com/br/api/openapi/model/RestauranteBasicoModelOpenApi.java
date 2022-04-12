package com.br.api.openapi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

import com.br.api.model.CozinhaModel;

@ApiModel("RestauranteBasicoModel")
@Setter
@Getter
public class RestauranteBasicoModelOpenApi {

	@ApiModelProperty(example = "1")
	private Long id;

	@ApiModelProperty(example = "Thai Gourmet")
	private String nome;

	@ApiModelProperty(example = "12.00")
	private BigDecimal taxaFrete;

	private CozinhaModel cozinha;

}
