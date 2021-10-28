package org.serratec.backend.projeto01;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class HelloWorldController {
	
	@GetMapping("/hello")
	public String hello() {
		return "Hello World!";
	}
	
	@GetMapping("/ola")
	public String ola() {
		return "ola";
	}
	
	@GetMapping("/maiuscula")
	public String gerarMaiuscula(@RequestParam String valor) {
		return valor.toUpperCase();
	}
}
