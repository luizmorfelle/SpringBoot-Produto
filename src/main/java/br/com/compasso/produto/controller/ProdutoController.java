package br.com.compasso.produto.controller;

import br.com.compasso.produto.controller.dto.ProdutoDto;
import br.com.compasso.produto.model.Produto;
import br.com.compasso.produto.repository.ProdutoRepository;
import br.com.compasso.produto.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    private final static Logger LOGGER = LoggerFactory.getLogger(ProdutoController.class);

//    @Autowired
//    private ProdutoService produtoService;

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping
    private List<ProdutoDto> findAll(){
        var produto = produtoRepository.findAll();
        return produto
                .stream()
                .map(ProdutoDto::converter)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    private ProdutoDto findById(@PathVariable("id") Long id){
        return ProdutoDto.converter(produtoRepository.getOne(id));
    }

    @PostMapping
    private ResponseEntity<ProdutoDto> create (@Validated @RequestBody Produto produto){
        try {
            Produto novoProduto = produtoRepository.save(produto);
            return ResponseEntity.created(new URI("/produtos/" + novoProduto.getId())).build();
        }catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Ocorreu um erro ao tentar salvar esse produto", e);
        }
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<?> delete(@PathVariable("id") Long id) {
        if (produtoRepository.existsById(id)) {
            produtoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable("id") long id,
                                 @RequestBody Produto produto) {
        return produtoRepository.findById(id)
                .map(record -> {
                    record.setId(produto.getId());
                    record.setSku(produto.getSku());
                    record.setCategoria(produto.getCategoria());
                    record.setDescricao(produto.getDescricao());
                    record.setPreco(produto.getPreco());
                    record.setNome(produto.getNome());
                    Produto updated = produtoRepository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

}
