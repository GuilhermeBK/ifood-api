package com.br.api.openapi.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.hateoas.Links;

import com.br.api.model.UsuarioModel;

import java.util.List;

@ApiModel("UsuariosModel")
@Data
public class UsuariosModelOpenApi {

	private UsuariosEmbeddedModelOpenApi _embedded;
	private Links _links;

	@ApiModel("UsuariosEmbeddedModel")
	@Data
	public class UsuariosEmbeddedModelOpenApi {

		private List<UsuarioModel> usuarios;

	}

}
