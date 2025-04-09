package com.example.gerenciador_pedidos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class GerenciadorPedidosApplication implements CommandLineRunner {
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private FornecedorRepository fornecedorRepository;

	public static void main(String[] args) {
		SpringApplication.run(GerenciadorPedidosApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {

		//Categorias
		Categoria categoriaEletronicos = new Categoria(1L, "Eletrônicos");
		Categoria categoriaLivros = new Categoria(2L, "Livros");
		categoriaRepository.saveAll(List.of(categoriaEletronicos, categoriaLivros));

		//Fornecedores
		Fornecedor fornecedorTech = new Fornecedor("Tech Supplier");
		Fornecedor fornecedorLivros = new Fornecedor("Livraria Global");
		fornecedorRepository.saveAll(List.of(fornecedorTech, fornecedorLivros));

		//Produtos
		Produto produto1 = new Produto("Notebook", 3500.0, categoriaEletronicos, fornecedorTech);
		Produto produto2 = new Produto("Smartphone", 2500.0, categoriaEletronicos, fornecedorTech);
		Produto produto3 = new Produto("Livro de Java", 100.0, categoriaLivros, fornecedorLivros);
		Produto produto4 = new Produto("Livro de Spring Boot", 150.0, categoriaLivros, fornecedorLivros);
//		produto1.setFornecedor(fornecedorTech);
//		produto2.setFornecedor(fornecedorTech);
//		produto3.setFornecedor(fornecedorLivros);
//		produto4.setFornecedor(fornecedorLivros);
		produtoRepository.saveAll(List.of(produto1, produto2, produto3, produto4));

		//Pedidos
		Pedido pedido1 = new Pedido(1L, LocalDate.now());
		pedido1.setProdutos(List.of(produto1, produto3));
		Pedido pedido2 = new Pedido(2L, LocalDate.now().minusDays(1));
		pedido2.setProdutos(List.of(produto2, produto4));
		pedidoRepository.saveAll(List.of(pedido1, pedido2));

		//categoriaRepository.save(categoriaEletronicos);
		//pedidoRepository.save(pedido);

		// Testando a persistência e o relacionamento
//		System.out.println("Categorias e seus produtos:");
//		categoriaRepository.findAll().forEach(categoria -> {
//			System.out.println("Categoria: " + categoria.getNome());
//			categoria.getProdutos().forEach(produto ->
//					System.out.println(" - Produto: " + produto.getNome())
//			);
//		});

		// Testando consultas e verificando os relacionamentos
		System.out.println("Produtos na categoria Eletrônicos:");
		categoriaRepository.findById(1L).ifPresent(categoria ->
				categoria.getProdutos().forEach(produto ->
						System.out.println(" - " + produto.getNome())
				)
		);

		System.out.println("\nPedidos e seus produtos:");
		pedidoRepository.findAll().forEach(pedido -> {
			System.out.println("Pedido " + pedido.getId() + ":");
			pedido.getProdutos().forEach(produto ->
					System.out.println(" - " + produto.getNome())
			);
		});

		System.out.println("\nProdutos e seus fornecedores:");
		produtoRepository.findAll().forEach(produto ->
				System.out.println("Produto: " + produto.getNome() +
						", Fornecedor: " + produto.getFornecedor().getNome())
		);

	}
}
