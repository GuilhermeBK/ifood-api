package com.br.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.br.api.assembler.RestauranteApenasNomeModelAssembler;
import com.br.api.assembler.RestauranteBasicoModelAssembler;
import com.br.api.assembler.RestauranteInputDisassembler;
import com.br.api.assembler.RestauranteModelAssembler;
import com.br.api.model.RestauranteApenasNomeModel;
import com.br.api.model.RestauranteBasicoModel;
import com.br.api.model.RestauranteModel;
import com.br.api.model.input.RestauranteInput;
import com.br.api.openapi.controller.RestauranteControllerOpenApi;
import com.br.domain.exception.CidadeNaoEncontradaException;
import com.br.domain.exception.CozinhaNaoEncontradaException;
import com.br.domain.exception.NegocioException;
import com.br.domain.exception.RestauranteNaoEncontradoException;
import com.br.domain.model.Restaurante;
import com.br.domain.repository.RestauranteRepository;
import com.br.domain.service.CadastroRestauranteService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/restaurantes")
public class RestauranteController implements RestauranteControllerOpenApi {

	@Autowired
	private RestauranteRepository restauranteRepository;

	@Autowired
	private CadastroRestauranteService cadastroRestaurante;

	@Autowired
	private RestauranteModelAssembler restauranteModelAssembler;

	@Autowired
	private RestauranteBasicoModelAssembler restauranteBasicoModelAssembler;

	@Autowired
	private RestauranteApenasNomeModelAssembler restauranteApenasNomeModelAssembler;

	@Autowired
	private RestauranteInputDisassembler restauranteInputDisassembler;

	@Override
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public CollectionModel<RestauranteBasicoModel> listar() {
		return restauranteBasicoModelAssembler
				.toCollectionModel(restauranteRepository.findAll());
	}

	@Override
	@GetMapping(params = "projecao=apenas-nome", produces = MediaType.APPLICATION_JSON_VALUE)
	public CollectionModel<RestauranteApenasNomeModel> listarApenasNomes() {
		return restauranteApenasNomeModelAssembler
				.toCollectionModel(restauranteRepository.findAll());
	}

	@Override
	@GetMapping(value = "/{restauranteId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public RestauranteModel buscar(@PathVariable Long restauranteId) {
		Restaurante restaurante = cadastroRestaurante.buscarOuFalhar(restauranteId);

		return restauranteModelAssembler.toModel(restaurante);
	}

	@Override
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public RestauranteModel adicionar(@RequestBody @Valid RestauranteInput restauranteInput) {
		try {
			Restaurante restaurante = restauranteInputDisassembler.toDomainObject(restauranteInput);

			return restauranteModelAssembler.toModel(cadastroRestaurante.salvar(restaurante));
		} catch (CozinhaNaoEncontradaException | CidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}

	@Override
	@PutMapping(value = "/{restauranteId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public RestauranteModel atualizar(@PathVariable Long restauranteId,
	                                  @RequestBody @Valid RestauranteInput restauranteInput) {
		try {
			Restaurante restauranteAtual = cadastroRestaurante.buscarOuFalhar(restauranteId);

			restauranteInputDisassembler.copyToDomainObject(restauranteInput, restauranteAtual);

			return restauranteModelAssembler.toModel(cadastroRestaurante.salvar(restauranteAtual));
		} catch (CozinhaNaoEncontradaException | CidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}

	@Override
	@PutMapping("/{restauranteId}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> ativar(@PathVariable Long restauranteId) {
		cadastroRestaurante.ativar(restauranteId);

		return ResponseEntity.noContent().build();
	}

	@Override
	@DeleteMapping("/{restauranteId}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> inativar(@PathVariable Long restauranteId) {
		cadastroRestaurante.inativar(restauranteId);

		return ResponseEntity.noContent().build();
	}

	@Override
	@PutMapping("/ativacoes")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void ativarMultiplos(@RequestBody List<Long> restauranteIds) {
		try {
			cadastroRestaurante.ativar(restauranteIds);
		} catch (RestauranteNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}

	@Override
	@DeleteMapping("/ativacoes")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void inativarMultiplos(@RequestBody List<Long> restauranteIds) {
		try {
			cadastroRestaurante.inativar(restauranteIds);
		} catch (RestauranteNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}

	@Override
	@PutMapping("/{restauranteId}/abertura")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> abrir(@PathVariable Long restauranteId) {
		cadastroRestaurante.abrir(restauranteId);

		return ResponseEntity.noContent().build();
	}

	@Override
	@PutMapping("/{restauranteId}/fechamento")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> fechar(@PathVariable Long restauranteId) {
		cadastroRestaurante.fechar(restauranteId);

		return ResponseEntity.noContent().build();
	}

}
