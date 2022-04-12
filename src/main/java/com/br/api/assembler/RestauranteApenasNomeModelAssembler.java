package com.br.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.br.api.AlgaLinks;
import com.br.api.controller.RestauranteController;
import com.br.api.model.RestauranteApenasNomeModel;
import com.br.domain.model.Restaurante;

@Component
public class RestauranteApenasNomeModelAssembler
		extends RepresentationModelAssemblerSupport<Restaurante, RestauranteApenasNomeModel> {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private AlgaLinks algaLinks;

	public RestauranteApenasNomeModelAssembler() {
		super(RestauranteController.class, RestauranteApenasNomeModel.class);
	}

	@Override
	public RestauranteApenasNomeModel toModel(Restaurante restaurante) {
		RestauranteApenasNomeModel restauranteModel = createModelWithId(
				restaurante.getId(), restaurante);

		modelMapper.map(restaurante, restauranteModel);

		restauranteModel.add(algaLinks.linkToRestaurantes("restaurantes"));

		return restauranteModel;
	}

	@Override
	public CollectionModel<RestauranteApenasNomeModel> toCollectionModel(Iterable<? extends Restaurante> entities) {
		return super.toCollectionModel(entities)
				.add(algaLinks.linkToRestaurantes());
	}

}
