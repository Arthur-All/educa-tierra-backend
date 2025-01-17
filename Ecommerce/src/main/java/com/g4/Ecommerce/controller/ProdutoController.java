package com.g4.Ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.g4.Ecommerce.model.Produto;
import com.g4.Ecommerce.repository.ProdutoRepository;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class ProdutoController {

	@Autowired
	private ProdutoRepository repository;

	// FindAll
	@GetMapping
	public ResponseEntity<List<Produto>> FindAll() {
		return ResponseEntity.ok(repository.findAll());
	}

	// FindById
	@GetMapping("/{id}")
	public ResponseEntity<Produto> FindById(@PathVariable long id) {
		return repository.findById(id).map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.notFound().build());
	}

	// MetodoEspecifico
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Produto>> FindByName(@PathVariable String nome) {
		return ResponseEntity.ok(repository.findAllByNomeContainingIgnoreCase(nome));
	}

	@GetMapping("/descricao/{descricao}")
	public ResponseEntity<List<Produto>> FindByDescricao(@PathVariable String descricao) {
		return ResponseEntity.ok(repository.findAllByDescricaoContainingIgnoreCase(descricao));
	}

}
