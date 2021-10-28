package org.serratec.backend.exercicio01.controller;

import java.util.ArrayList;
import java.util.List;

import org.serratec.backend.exercicio01.domain.Aluno;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

	private static List<Aluno> lista = new ArrayList<Aluno>();
	
	static {
		lista.add(new Aluno("Damian Wayne", 15l, "21 98757-9665"));
		lista.add(new Aluno("Micael Mayers", 16l, "21 98347-9235"));
		lista.add(new Aluno("Elon Musk jr", 17l, "21 99470-6665"));
		lista.add(new Aluno("Claudin Carburador", 18l, "21 99720-8765"));
	}
	
	//BUSCA IRÁ TRAZER TODOS OS ALUNOS
	@GetMapping
	public List<Aluno> listar(){
		return lista;
	}
	
	//PERCORRER PARA ACHAR 1 ALUNO COM A MATRICULA COMPATIVEL
	@GetMapping("{matricula}")
	public Aluno buscar(@PathVariable Long matricula) {
		for(Aluno alu : lista) {
			if(alu.getMatricula().equals(matricula)) {
				return alu;
			}
		}
		return null;
	}
	
	//ADICIONAR ALUNO
	@PostMapping
	public Aluno inserir(@RequestBody Aluno aluno) {
		lista.add(aluno);
		return aluno;
	}
	
	//REMOVER ALUNO POR MATRICULA
	@DeleteMapping("{matricula}")
	public void delete(@PathVariable Long matricula) {
		for(int i = 0; i < lista.size(); i++) {
			if(lista.get(i).getMatricula().equals(matricula)) {
				lista.remove(i);
			}
		}
	}
}
