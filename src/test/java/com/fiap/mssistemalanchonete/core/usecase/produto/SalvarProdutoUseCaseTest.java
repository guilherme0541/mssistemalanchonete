package com.fiap.mssistemalanchonete.core.usecase.produto;

import com.fiap.mssistemalanchonete.core.exception.exception.ProdutoAlreadyExistsException;
import com.fiap.mssistemalanchonete.core.model.Produto;
import com.fiap.mssistemalanchonete.core.port.ProdutoRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class SalvarProdutoUseCaseTest {
  public static final String CODIGO = "codigo";
  private ProdutoRepositoryPort repositoryPort;
  private SalvarProdutoUseCase useCase;

  @BeforeEach
  void setUp() {
    repositoryPort = mock(ProdutoRepositoryPort.class);
    useCase = new SalvarProdutoUseCase(repositoryPort);
  }

  @Test
  void testSalvarProdutoComSucesso() {
    Produto produto =  Produto.builder().codigo(CODIGO).build();

    when(repositoryPort.consultarProdutoPorCodigo(CODIGO)).thenReturn(null);
    when(repositoryPort.salvarProduto(produto)).thenReturn(produto);

    assertEquals(produto, useCase.salvarProduto(produto), "Should return a Produto");

  }

  @Test
  void testSalvarProdutoDeveLancarProdutoAlredyExistsExceptionSeOProdutoJaExiste() {
    Produto produto =  Produto.builder().codigo(CODIGO).build();

    when(repositoryPort.consultarProdutoPorCodigo(CODIGO)).thenReturn(produto);

    assertThrows(ProdutoAlreadyExistsException.class,
      ()-> useCase.salvarProduto(produto), "Should throw an exception");

    verify(repositoryPort,never()).salvarProduto(any());
  }
}