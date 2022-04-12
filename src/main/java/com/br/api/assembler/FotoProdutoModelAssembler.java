package com.br.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.br.api.AlgaLinks;
import com.br.api.controller.RestauranteProdutoFotoController;
import com.br.api.model.FotoProdutoModel;
import com.br.domain.model.FotoProduto;

@Component
public class FotoProdutoModelAssembler
		extends RepresentationModelAssemblerSupport<FotoProduto, FotoProdutoModel> {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private AlgaLinks algaLinks;

	public FotoProdutoModelAssembler() {
		super(RestauranteProdutoFotoController.class, FotoProdutoModel.class);
	}

	@Override
	public FotoProdutoModel toModel(FotoProduto foto) {
		FotoProdutoModel fotoProdutoModel = modelMapper.map(foto, FotoProdutoModel.class);

		fotoProdutoModel.add(algaLinks.linkToFotoProduto(
				foto.getRestauranteId(), foto.getProduto().getId()));

		fotoProdutoModel.add(algaLinks.linkToProduto(
				foto.getRestauranteId(), foto.getProduto().getId(), "produto"));

		return fotoProdutoModel;
	}

}
