package vendas.rest.controller;

import java.util.List;
import java.util.Optional;

import io.swagger.annotations.*;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.server.ResponseStatusException;
import vendas.entity.Cliente;
import vendas.repository.Clientes;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/clientes")
//@Controller
//@RequestMapping("/api/clientes")
@Api("Api Clientes")
public class ClienteController {
	
//	@RequestMapping(value = "/hello/{nome}", method = RequestMethod.GET)
//	@ResponseBody
//	public String helloCliente(@PathVariable("nome") String nomeCliente ) {
//		return String.format("Hello %s.", nomeCliente);
//	}
	
	private Clientes clientes;

	public ClienteController(Clientes clientes) {
		this.clientes = clientes;
	}

	@GetMapping("/{id}")
	@ApiOperation("Obter detalhes de um cliente")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Cliente encontrado."),
			@ApiResponse(code = 404, message = "Cliente não encontrado para o ID informado.")
	})
	public Cliente getClienteById(
			@PathVariable
			@ApiParam("Id do cliente") Integer id ) {
		return clientes
				.findById(id)
				.orElseThrow(() ->
					new ResponseStatusException( HttpStatus.NOT_FOUND,
							"Cliente  não encontrado"));
	}
	//Refactoring aula 42
	//@ResponseBody
//	public ResponseEntity getClienteById(@PathVariable Integer id ) {
//		Optional<Cliente> cliente = clientes.findById(id);
//
//		if(cliente.isPresent()) {
//			return ResponseEntity.ok(cliente.get());
//		}
//
//		return ResponseEntity.notFound().build();
//	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation("Salva um novo cliente")
	@ApiResponses({
			@ApiResponse(code = 201, message = "Cliente salvo com sucesso."),
			@ApiResponse(code = 400, message = "Erro de validação.")
	})
	public Cliente save(@RequestBody @Valid Cliente cliente ){
		return clientes.save(cliente);
	}
	//Refactoring aula 42
	//@ResponseBody
//	public ResponseEntity save(@RequestBody Cliente cliente ){
//		Cliente clienteSalvo = clientes.save( cliente );
//		return ResponseEntity.ok(clienteSalvo);
//	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Integer id ) {
		clientes.findById(id)
				.map( cliente -> {
					clientes.delete( cliente );
					return cliente;
				})
				.orElseThrow( () ->
						new ResponseStatusException( HttpStatus.NOT_FOUND,
								"Cliente  não encontrado"));
	}
	//@ResponseBody
//	public ResponseEntity delete(@PathVariable Integer id ) {
//		Optional<Cliente> cliente = clientes.findById(id);
//
//		if(cliente.isPresent()) {
//			clientes.delete(cliente.get());
//			return ResponseEntity.noContent().build();
//		}
//
//		return ResponseEntity.notFound().build();
//	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@PathVariable Integer id,
					   @RequestBody @Valid Cliente cliente ){
		clientes.findById(id)
				.map( clienteExistente -> {
					cliente.setId(clienteExistente.getId());
					clientes.save(cliente);
					return clienteExistente;
		}).orElseThrow( () ->
						new ResponseStatusException( HttpStatus.NOT_FOUND,
								"Cliente  não encontrado"));
	}
	//refactoring aula 42
	//@ResponseBody
//	public ResponseEntity update(@PathVariable Integer id,
//								 @RequestBody Cliente cliente ){
//
//		return clientes.findById(id).map( clienteExistente -> {
//			cliente.setId(clienteExistente.getId());
//			clientes.save(cliente);
//			return ResponseEntity.noContent().build();
//		}).orElseGet( () -> ResponseEntity.notFound().build() );
//	}

 	@GetMapping
	public List<Cliente> find( Cliente filtro ){
		ExampleMatcher matcher = ExampleMatcher
				.matching()
				.withIgnoreCase()
				.withStringMatcher( ExampleMatcher.StringMatcher.CONTAINING);

		Example example = Example.of(filtro, matcher);
		return clientes.findAll(example);
	}
	//refactoring aula42
//	public ResponseEntity find( Cliente filtro ){
//		ExampleMatcher matcher = ExampleMatcher
//										.matching()
//										.withIgnoreCase()
//										.withStringMatcher( ExampleMatcher.StringMatcher.CONTAINING);
//
//		Example example = Example.of(filtro, matcher);
//		List<Cliente> lista = clientes.findAll(example);
//		return ResponseEntity.ok(lista);
//	}

}
