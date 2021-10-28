package org.serratec.backend.exercicio02.controller;

import java.util.ArrayList;
import java.util.List;

import org.serratec.backend.exercicio02.domain.Veiculo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

	private List<Veiculo> listaVeiculos = new ArrayList<Veiculo>();
	
	
	//METODO LISTAR
	@GetMapping
	public List<Veiculo> listar(){
		listaVeiculos.add(new Veiculo(1l, "Mercedes", "Sedan"));
		listaVeiculos.add(new Veiculo(2l, "Ferrari", "812 GTS"));
		listaVeiculos.add(new Veiculo(3l, "AstonMartin", "DB9"));
		listaVeiculos.add(new Veiculo(4l, "Mercedes", "GLC 250 4MATIC Sport"));
		
		return listaVeiculos;
	}
	
	//METODO BUSCAR VEICULOS A PARTIR DA MARCA
	@GetMapping("{marca}")
	public Veiculo buscar(@PathVariable String marca) {
		for(Veiculo vetor : listaVeiculos) {
			if(vetor.getMarca().equals(marca)) {
				return vetor;
			}
		}
		return null;
	}
	
	//METODO ADICIONAR/INSERIR
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Veiculo inserir(@RequestBody Veiculo veiculo) {
		listaVeiculos.add(veiculo);
		return veiculo;
	}
	
	//METODO REMOVER
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void deletar(@PathVariable Long id) {
		for(Veiculo ve : listaVeiculos) {
			if(ve.getId().equals(id)) {
				listaVeiculos.remove(ve);
			}
		}
	}
	
	//METODO ATUALIZAR
	@PutMapping("{id}")
	public Veiculo atualizar(@RequestBody Veiculo veiculo, @PathVariable Long id) {
		for(int i= 0; i < listaVeiculos.size(); i++) {
			if(listaVeiculos.get(i).getId().equals(id)) {
				
				Veiculo car = new Veiculo(id, veiculo.getMarca(), veiculo.getModelo());
				listaVeiculos.set(i, car);
				return car;
			}
		}
		return null;
	}
	
}
