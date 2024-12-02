package com.fiap.mssistemalanchonete.core.usecase.produto;

import com.fiap.mssistemalanchonete.core.exception.exception.ProdutoNotFoundException;
import com.fiap.mssistemalanchonete.core.model.Produto;
import com.fiap.mssistemalanchonete.core.port.ProdutoRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class DeletarProdutoUseCaseTest {

  private ProdutoRepositoryPort repositoryPort;
  private DeletarProdutoUseCase useCase;

  @BeforeEach
  void setUp() {
    repositoryPort = mock(ProdutoRepositoryPort.class);
    useCase = new DeletarProdutoUseCase(repositoryPort);
  }

  @Test
  void testDeletarProdutoComSucesso() {
    String codigo = "1";
    Produto produto = new Produto();

    when(repositoryPort.consultarProdutoPorCodigo(codigo)).thenReturn(produto);

    useCase.deletarProduto(codigo);

    verify(repositoryPort, times(1)).deletarProduto(produto);
  }

  @Test
  void testDeletarProdutoDeveLancarProdutoNotFoundExceptionQuandoOProdutoNaoExiste() {
    String codigo = "codigo";

    when(repositoryPort.consultarProdutoPorCodigo(codigo)).thenReturn(null);

    assertThrows(ProdutoNotFoundException.class, () -> {
      useCase.deletarProduto(codigo);
    }, "Should throw ProdutoNotFoundException.");

    verify(repositoryPort, never()).deletarProduto(any());
  }
}