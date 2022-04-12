package com.br.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.br.api.assembler.PedidoInputDisassembler;
import com.br.api.assembler.PedidoModelAssembler;
import com.br.api.assembler.PedidoResumoModelAssembler;
import com.br.api.model.PedidoModel;
import com.br.api.model.PedidoResumoModel;
import com.br.api.model.input.PedidoInput;
import com.br.api.openapi.controller.PedidoControllerOpenApi;
import com.br.core.data.PageWrapper;
import com.br.core.data.PageableTranslator;
import com.br.domain.exception.EntidadeNaoEncontradaException;
import com.br.domain.exception.NegocioException;
import com.br.domain.filter.PedidoFilter;
import com.br.domain.model.Pedido;
import com.br.domain.model.Usuario;
import com.br.domain.repository.PedidoRepository;
import com.br.domain.service.EmissaoPedidoService;
import com.br.infrastructure.repository.spec.PedidoSpecs;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoController implements PedidoControllerOpenApi{

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private EmissaoPedidoService emissaoPedido;
	
	@Autowired
	private PedidoModelAssembler pedidoModelAssembler;
	
	@Autowired
	private PedidoResumoModelAssembler pedidoResumoModelAssembler;
	
	@Autowired
	private PedidoInputDisassembler pedidoInputDisassembler;

	@Autowired
	private PagedResourcesAssembler<Pedido> pagedResourcesAssembler;

	@Override
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public PagedModel<PedidoResumoModel> pesquisar(PedidoFilter filtro,
	                                               @PageableDefault(size = 10) Pageable pageable) {
		Pageable pageableTraduzido = traduzirPageable(pageable);

		Page<Pedido> pedidosPage = pedidoRepository.findAll(
				PedidoSpecs.usandoFiltro(filtro), pageableTraduzido);

		pedidosPage = new PageWrapper<>(pedidosPage, pageable);

		return pagedResourcesAssembler.toModel(pedidosPage, pedidoResumoModelAssembler);
	}

	@Override
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public PedidoModel adicionar(@Valid @RequestBody PedidoInput pedidoInput) {
		try {
			Pedido novoPedido = pedidoInputDisassembler.toDomainObject(pedidoInput);

			// TODO pegar usuário autenticado
			novoPedido.setCliente(new Usuario());
			novoPedido.getCliente().setId(1L);

			novoPedido = emissaoPedido.emitir(novoPedido);

			return pedidoModelAssembler.toModel(novoPedido);
		} catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}

	@Override
	@GetMapping(value = "/{codigoPedido}", produces = MediaType.APPLICATION_JSON_VALUE)
	public PedidoModel buscar(@PathVariable String codigoPedido) {
		Pedido pedido = emissaoPedido.buscarOuFalhar(codigoPedido);
		
		return pedidoModelAssembler.toModel(pedido);
	}
	
	private Pageable traduzirPageable(Pageable apiPageable) {
		var mapeamento = Map.of(
				"codigo", "codigo",
				"subtotal", "subtotal",
				"taxaFrete", "taxaFrete",
				"valorTotal", "valorTotal",
				"dataCriacao", "dataCriacao",
				"restaurante.nome", "restaurante.nome",
				"restaurante.id", "restaurante.id",
				"cliente.id", "cliente.id",
				"cliente.nome", "cliente.nome"
			);
		
		return PageableTranslator.translate(apiPageable, mapeamento);
	}
	
}
