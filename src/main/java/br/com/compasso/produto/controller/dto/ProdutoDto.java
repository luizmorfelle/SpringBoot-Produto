package br.com.compasso.produto.controller.dto;

import br.com.compasso.produto.model.Produto;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class ProdutoDto {

    private Long id;
    private String sku;
    private String nome;
    private String descricao;
    private String categoria;
    private BigDecimal preco;

    public ProdutoDto() {
    }

    public ProdutoDto(Produto produto) {
        this.id = produto.getId();
        this.sku = produto.getSku();
        this.nome = produto.getNome();
        this.descricao = produto.getDescricao();
        this.categoria = produto.getCategoria();
        this.preco = produto.getPreco();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public static ProdutoDto converter(Produto produto){
        ProdutoDto novoProduto = new ProdutoDto();

        novoProduto.setCategoria(produto.getCategoria());
        novoProduto.setPreco(produto.getPreco());
        novoProduto.setDescricao(produto.getDescricao());
        novoProduto.setId(produto.getId());
        novoProduto.setSku(produto.getSku());
        novoProduto.setNome(produto.getNome());

        return novoProduto;
    }
}
