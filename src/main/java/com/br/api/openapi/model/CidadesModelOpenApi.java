package com.br.api.openapi.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.hateoas.Links;

import com.br.api.model.CidadeModel;

import java.util.List;

@ApiModel("CidadesModel")
@Data
public class CidadesModelOpenApi {

	private CidadesEmbeddedModelOpenApi _embedded;
	private Links _links;

	@ApiModel("CidadesEmbeddedModel")
	@Data
	public class CidadesEmbeddedModelOpenApi {

		private List<CidadeModel> cidades;

	}

}
