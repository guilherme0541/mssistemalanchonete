package com.fiap.mssistemalanchonete.cucumber.client;

import com.fiap.mssistemalanchonete.core.model.Produto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "client", url = "http://localhost:8099/sistema-lanchonete-produto/api/v1/produtos",  configuration = ClientConfiguration.class)
public interface ProdutoClient {

  @RequestMapping(method = RequestMethod.POST)
  ResponseEntity<Produto> adicionarProduto(@RequestBody Produto produto);

  @RequestMapping(method = RequestMethod.DELETE, value = "/{codigo}")
  ResponseEntity<Void> deletarProduto(@PathVariable(value = "codigo") String codigo);

  @RequestMapping(method = RequestMethod.PATCH, value = "/{codigo}")
  ResponseEntity<Produto> atualizarProduto(@RequestBody Produto produto, @PathVariable(value = "codigo") String codigo);

  @RequestMapping(method = RequestMethod.GET, value = "/{categoria}")
  ResponseEntity<List<Produto>> buscaPorCategoria( @PathVariable(value = "categoria")String categoria);

  @RequestMapping(method = RequestMethod.GET)
  ResponseEntity<List<Produto>> buscaTodos();
}
