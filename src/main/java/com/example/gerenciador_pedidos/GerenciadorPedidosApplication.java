package com.example.gerenciador_pedidos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class GerenciadorPedidosApplication implements CommandLineRunner {
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private CategoriaRepository categoriaRepository;

	public static void main(String[] args) {
		SpringApplication.run(GerenciadorPedidosApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		Produto produto = new Produto("Notebook", 3500.0);
		Categoria categoria = new Categoria(1L, "Eletrônicos");
		Pedido pedido = new Pedido(1L, LocalDate.now());

		produtoRepository.save(produto);
		pedidoRepository.save(pedido);
		categoriaRepository.save(categoria);

	}
}
