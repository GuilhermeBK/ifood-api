package com.br.api.openapi.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.hateoas.Links;

import com.br.api.model.PermissaoModel;

import java.util.List;

@ApiModel("PermissoesModel")
@Data
public class PermissoesModelOpenApi {

	private PermissoesEmbeddedModelOpenApi _embedded;
	private Links _links;

	@ApiModel("PermissoesEmbeddedModel")
	@Data
	public class PermissoesEmbeddedModelOpenApi {

		private List<PermissaoModel> permissoes;

	}

}
