package org.serratec.java2backend.exercicio02.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.serratec.java2backend.exercicio02.domain.Cliente;
import org.serratec.java2backend.exercicio02.repository.ClienteRepository;
import org.serratec.java2backend.exercicio02.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	//USA A INTERFACE COMO INJEÇÃO PARA USAR OS METODOS .FindAll()/notFound()
	/* A anotação @ Autowired fornece controle sobre onde e como a ligação entre
	 * os beans deve ser realizada. Pode ser usado para em métodos setter, no construtor,
	 * em uma propriedade ou métodos com nomes arbitrários e / ou vários argumentos.
	 */
	@Autowired
	private ClienteRepository clienteRepository;
	
	//RETORNAR TODOS OS CLIENTES
	@GetMapping
	public ResponseEntity<List<Cliente>> listar(){
		List<Cliente> listaClientes = clienteRepository.findAll();
		return ResponseEntity.ok(listaClientes);	
	}
	
	//RETORNAR UM CLIENTE POR ID
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> buscar(@PathVariable Long id){
		
		Optional<Cliente> cliente = clienteRepository.findById(id);
		if(cliente.isPresent()) {
			return ResponseEntity.ok(cliente.get());
		}
		
		return ResponseEntity.notFound().build();
	}
	
	//INSERIR UM NOVO CLIENTE
	@PostMapping
	public Cliente inserir(@Valid @RequestBody Cliente cliente){
		return clienteRepository.save(cliente);
	}
	
	//ATUALIZAR CLIENTE POR ID
	//SEMPRE QUE FOR ALTERAR/ATUALIZAR ALGO É NECESSARIO O ID IR JUNTO (.setId)
	@PutMapping("{id}")
	public ResponseEntity<Cliente> atualizar (@Valid @RequestBody Cliente cliente,@PathVariable Long id/*ESSE ID É UITLIZADO COMO VÁRIAVEL APENAS*/){
		//SE CLIENTE EXISTIR ELE IRÁ ATUALIZAAR
		if(clienteRepository.existsById(id)) {
			/*O .setId(id) SETA O ID E É UTILIZADO PARA NÃO
			 *INSERIR ALGUM CLIENTE COM ID VAZIO,
			 *PARA O BANCO NÃO ENTENDER COMO NOVO CLIENTE
			 *
			 *O ID PEGO SERÁ O PASSADO COMO PARAMETRO E RECEBIDO LÁ NA VARIAVEL
			 */
			cliente.setId(id);
			//AQUI O RESTO DAS INFORMAÇÕES SERÁ ATUALIZADA
			cliente = clienteRepository.save(cliente);
			//CASO FOR TUDO CERTO SERÁ RETORNAR UM OK 200;
			return ResponseEntity.ok(cliente);
		}
		//CASO NÃO ENCONTRE RETORNAR UM NOT FOUND 404;
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Void> deletar (@PathVariable Long id){
		if(clienteRepository.existsById(id)) {
			clienteRepository.deleteById(id);
			
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
	
}
