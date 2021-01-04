package br.com.compasso.produto.service;


import br.com.compasso.produto.model.Produto;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public interface ProdutoService {
    public Produto save(Produto cliente) throws SQLException;

    public void deleteById(Long id) throws SQLException;

    public Optional<Produto> findById(Long id) throws SQLException;

    public List<Produto> list() throws SQLException;
}
