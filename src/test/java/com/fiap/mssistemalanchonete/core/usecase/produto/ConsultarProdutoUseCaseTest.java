package com.fiap.mssistemalanchonete.core.usecase.produto;

import com.fiap.mssistemalanchonete.core.model.Produto;
import com.fiap.mssistemalanchonete.core.port.ProdutoRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ConsultarProdutoUseCaseTest {

  private ProdutoRepositoryPort repositoryPort;
  private ConsultarProdutoUseCase useCase;

  @BeforeEach
  void setUp() {
    repositoryPort = mock(ProdutoRepositoryPort.class);
    useCase = new ConsultarProdutoUseCase(repositoryPort);
  }

  @Test
  void testConsultarProdutoPorCategoriaComSucesso() {
    String categoria = "C";
    List<Produto> produtos = List.of(new Produto());

    when(repositoryPort.consultarProdutoPorCategoria(categoria)).thenReturn(produtos);

    var response = useCase.consultarProdutoPorCategoria(categoria);

    assertEquals(produtos, response, "Should return a produtos list.");
  }

  @Test
  void testConsultarTodosOsProdutoComSucesso() {
    List<Produto> produtos = List.of(new Produto());

    when(repositoryPort.consultarTodosProdutos()).thenReturn(produtos);

    var response = useCase.consultarTodosProdutos();

    assertEquals(produtos, response, "Should return a produtos list.");
  }

}