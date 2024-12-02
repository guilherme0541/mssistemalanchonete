package com.fiap.mssistemalanchonete.core.usecase.produto;

import com.fiap.mssistemalanchonete.core.model.Produto;
import com.fiap.mssistemalanchonete.core.port.ProdutoRepositoryPort;
import com.fiap.mssistemalanchonete.core.usecase.ConsultarProdutoUseCaseFacade;

import java.util.List;

public class ConsultarProdutoUseCase implements ConsultarProdutoUseCaseFacade {

    private final ProdutoRepositoryPort produtoRepositoryPort;

    public ConsultarProdutoUseCase(ProdutoRepositoryPort produtoRepositoryPort){
        this.produtoRepositoryPort = produtoRepositoryPort;
    }

    @Override
    public List<Produto> consultarProdutoPorCategoria(String categoria) {
        return produtoRepositoryPort.consultarProdutoPorCategoria(categoria);
    }

    @Override
    public List<Produto> consultarTodosProdutos() {
        return produtoRepositoryPort.consultarTodosProdutos();
    }
}
