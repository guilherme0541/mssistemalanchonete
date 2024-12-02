package com.fiap.mssistemalanchonete.cucumber;

import com.fiap.mssistemalanchonete.core.model.Produto;
import com.fiap.mssistemalanchonete.cucumber.client.ProdutoClient;
import feign.FeignException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@EnableFeignClients
public class StepDefinition {

  public static final String CATEGORIA = "Categoria";
  public static final String DESCRICAO = "descricao";
  public static final String NOME = "nome";
  public static final String CODIGO = "codigo";
  public static final String OUTR_NOME = "abobora";
  public static final String OUTRO_CODIGO = "outroCodigo";
  public static final String OUTRA_CATAGORIA = "outraCatagoria";

  @Autowired
  private ProdutoClient client;
  private ResponseEntity<?> response;

  @When("chamada a criação do produto")
  public void chamadoCriacaoDoProduto() {
    try {
    response = client.adicionarProduto(getProdutoDefault());

    } catch (FeignException e) {
      response = ResponseEntity.status(e.status()).body(e.responseBody());
    }
    resetProduto();
  }

  private Produto getProdutoDefault() {
    return Produto.builder()
      .codigo(CODIGO)
      .nome(NOME)
      .descricao(DESCRICAO)
      .categoria(CATEGORIA)
      .preco(BigDecimal.TEN)
      .build();
  }

  @Then("o produto é criado")
  public void produtoCriado() {
    assertEquals(HttpStatus.CREATED, response.getStatusCode(), "Shoulde be return creates status.");
    Produto responseBody = (Produto) response.getBody();
    assertEquals(CODIGO, responseBody.getCodigo(), "Should be codigo produto");
    assertEquals(NOME, responseBody.getNome(), "Should be nome produto");
    assertEquals(DESCRICAO, responseBody.getDescricao(), "Should be descricao produto");
    assertEquals(BigDecimal.TEN, responseBody.getPreco(), "Should be preco produto");
    assertEquals(CATEGORIA, responseBody.getCategoria(), "Should be categoria produto");
  }

  @Given("que o produto já existe")
  public void produtoJaExiste() {
    client.adicionarProduto(getProdutoDefault());
  }

  @Then("retorna bad request")
  public void retornaBadRequest() {
    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode(), "Shoulde be return bad request status.");
  }

  @When("chamada a atualização do produto")
  public void atualizaProduto() {
    try {
      Produto produto = getProdutoDefault();
      produto.setNome(OUTR_NOME);
      response = client.atualizarProduto(produto, CODIGO);

    } catch (FeignException e) {
      response = ResponseEntity.status(e.status()).body(e.responseBody());
    }
    resetProduto();
  }

  @Then("atualiza o produto")
  public void verificaAtualizacaoProduto() {
    assertEquals(HttpStatus.OK, response.getStatusCode(), "Shoulde be return OK status.");

    Produto responseBody = (Produto) response.getBody();
    assertEquals(CODIGO, responseBody.getCodigo(), "Should be codigo produto");
    assertEquals(OUTR_NOME, responseBody.getNome(), "Should be nome produto");
    assertEquals(DESCRICAO, responseBody.getDescricao(), "Should be descricao produto");
    assertEquals(BigDecimal.TEN, responseBody.getPreco(), "Should be preco produto");
    assertEquals(CATEGORIA, responseBody.getCategoria(), "Should be categoria produto");
  }

  @Given("que o produto não existe")
  public void resetProduto() {
    try {
      client.deletarProduto(CODIGO);
    } catch (FeignException e) {
      if (e.status() != HttpStatus.NOT_FOUND.value()) {
        throw new RuntimeException(e);
      }
      Logger.getAnonymousLogger().info("Produto não existe!");
    }
  }

  @Then("retorna not found")
  public void retorna_not_found() {
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode(), "Shoulde be return not found status.");
  }

  @When("chamada a deleção do produto")
  public void delecaoProduto() {
    try {
      response = client.deletarProduto(CODIGO);
    } catch (FeignException e) {
      response = ResponseEntity.status(e.status()).body(e.responseBody());
    }
  }

  @Then("deleta o produto")
  public void deleta_o_produto() {
    assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode(), "Should be return no content status.");
    assertDoesNotThrow(() -> client.adicionarProduto(getProdutoDefault()));
    resetProduto();
  }

  @Given("que os produtos já existem")
  public void produtosJaExistem() {
      client.adicionarProduto(getProdutoDefault());
      var outroProduto = getProdutoDefault();
      outroProduto.setNome(OUTR_NOME);
      outroProduto.setCodigo(OUTRO_CODIGO);
      outroProduto.setCategoria(OUTRA_CATAGORIA);
      client.adicionarProduto(outroProduto);
  }

  @When("chamada a busca dos produtos por categoria")
  public void buscaProdutosPorCategoria() {
    try {
      response = client.buscaPorCategoria(CATEGORIA);
    } catch (FeignException e) {
      response = ResponseEntity.status(e.status()).body(e.responseBody());
    }
    resetProdutos();
  }

  private void resetProdutos() {
    try {
      client.deletarProduto(CODIGO);
      client.deletarProduto(OUTRO_CODIGO);
    } catch (FeignException e) {
      if (e.status() != HttpStatus.NOT_FOUND.value()) {
        throw new RuntimeException(e);
      }
      Logger.getAnonymousLogger().info("Produto não existe!");
    }
  }

  @Then("retorna os produtos da categoria")
  public void retorna_os_produtos_da_categoria() {
    assertEquals(HttpStatus.OK, response.getStatusCode(), "Should be return OK status.");

    var responseBody = (List<Produto>) response.getBody();
    assertEquals(1, responseBody.size(), "Should be 1 produto");
    assertEquals(CATEGORIA, responseBody.get(0).getCategoria(), "Should be categoria produto");
  }

  @When("chamada a busca dos produtos")
  public void chamada_a_busca_dos_produtos() {
    try {
      response = client.buscaTodos();
    } catch (FeignException e) {
      response = ResponseEntity.status(e.status()).body(e.responseBody());
    }
    resetProdutos();
  }

  @Then("retorna os produtos")
  public void retorna_os_produtos() {
    assertEquals(HttpStatus.OK, response.getStatusCode(), "Should be return OK status.");

    var responseBody = (List<Produto>) response.getBody();
    assertEquals(2, responseBody.size(), "Should be 1 produto");
  }
}
