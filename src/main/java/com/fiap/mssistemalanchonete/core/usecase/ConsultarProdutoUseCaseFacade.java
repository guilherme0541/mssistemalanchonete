package com.fiap.mssistemalanchonete.core.usecase;

import com.fiap.mssistemalanchonete.core.model.Produto;

import java.util.List;

public interface ConsultarProdutoUseCaseFacade {

    List<Produto> consultarTodosProdutos();

    List<Produto> consultarProdutoPorCategoria(String categoria);

}
