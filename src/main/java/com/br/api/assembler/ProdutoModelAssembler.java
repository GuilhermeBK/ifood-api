package com.br.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.br.api.AlgaLinks;
import com.br.api.controller.RestauranteProdutoController;
import com.br.api.model.ProdutoModel;
import com.br.domain.model.Produto;

@Component
public class ProdutoModelAssembler
		extends RepresentationModelAssemblerSupport<Produto, ProdutoModel> {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private AlgaLinks algaLinks;

	public ProdutoModelAssembler() {
		super(RestauranteProdutoController.class, ProdutoModel.class);
	}

	@Override
	public ProdutoModel toModel(Produto produto) {
		ProdutoModel produtoModel = createModelWithId(
				produto.getId(), produto, produto.getRestaurante().getId());

		modelMapper.map(produto, produtoModel);

		produtoModel.add(algaLinks.linkToProdutos(produto.getRestaurante().getId(), "produtos"));

		produtoModel.add(algaLinks.linkToFotoProduto(
				produto.getRestaurante().getId(), produto.getId(), "foto"));

		return produtoModel;
	}

}
