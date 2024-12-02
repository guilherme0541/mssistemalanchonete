package com.fiap.mssistemalanchonete.core.usecase.produto;

import com.fiap.mssistemalanchonete.core.exception.exception.ProdutoNotFoundException;
import com.fiap.mssistemalanchonete.core.model.Produto;
import com.fiap.mssistemalanchonete.core.port.ProdutoRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AtualizarProdutoUseCaseTest {

  private ProdutoRepositoryPort repository;
  private AtualizarProdutoUseCase atualizarProdutoUseCase;

  @BeforeEach
  void setUp() {
    repository = Mockito.mock(ProdutoRepositoryPort.class);
    atualizarProdutoUseCase = new AtualizarProdutoUseCase(repository);
  }

  @Test
  void testAtualizarProdutoComSucesso() {
    String codigo = "codigo";
    Produto produtoNovo = Produto.builder().codigo(codigo).descricao("desc").build();
    Produto produtoAntigo = Produto.builder().codigo(codigo).descricao("desc2").build();

    when(repository.consultarProdutoPorCodigo(codigo)).thenReturn(produtoAntigo);
    when(repository.atualizarProduto(produtoAntigo, produtoNovo)).thenReturn(produtoNovo);

    var response = atualizarProdutoUseCase.atualizarProduto(produtoNovo, codigo);

    assertEquals(produtoNovo, response, "Should return the produto");
  }

  @Test
  void testAtualizarProdutoDeveLancarProdutoNotFoundExceptionQuandoOProdutoNaoExiste() {
    String codigo = "codigo";
    Produto produtoNovo = Produto.builder().codigo(codigo).descricao("desc").build();

    when(repository.consultarProdutoPorCodigo(codigo)).thenReturn(null);
    assertThrows(ProdutoNotFoundException.class, () -> {
      atualizarProdutoUseCase.atualizarProduto(produtoNovo, codigo);
    }, "Should throw ProdutoNotFoundException.");

    verify(repository, never()).atualizarProduto(any(), any());
  }

}