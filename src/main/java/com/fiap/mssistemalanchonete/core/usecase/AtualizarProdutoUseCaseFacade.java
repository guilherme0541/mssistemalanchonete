package com.fiap.mssistemalanchonete.core.usecase;

import com.fiap.mssistemalanchonete.core.model.Produto;

public interface AtualizarProdutoUseCaseFacade {
  Produto atualizarProduto(Produto produto, String codigo);
}
