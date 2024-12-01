package com.fiap.mssistemalanchonete.config;

import com.fiap.mssistemalanchonete.core.port.ProdutoRepositoryPort;
import com.fiap.mssistemalanchonete.core.usecase.produto.AtualizarProdutoUseCase;
import com.fiap.mssistemalanchonete.core.usecase.produto.ConsultarProdutoUseCase;
import com.fiap.mssistemalanchonete.core.usecase.produto.DeletarProdutoUseCase;
import com.fiap.mssistemalanchonete.core.usecase.produto.SalvarProdutoUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainBeansConfig {

  @Bean
  public SalvarProdutoUseCase salvarProdutoUseCase (ProdutoRepositoryPort produtoRepositoryPort){
    return new SalvarProdutoUseCase(produtoRepositoryPort);
  }

  @Bean
  public AtualizarProdutoUseCase atualizarProdutoUseCase(ProdutoRepositoryPort produtoRepositoryPort){
    return new AtualizarProdutoUseCase(produtoRepositoryPort);
  }

  @Bean
  public DeletarProdutoUseCase deletarProdutoUseCase(ProdutoRepositoryPort produtoRepositoryPort){
    return new DeletarProdutoUseCase(produtoRepositoryPort);
  }

  @Bean
  public ConsultarProdutoUseCase consultarProdutoUseCase(ProdutoRepositoryPort produtoRepositoryPort){
    return new ConsultarProdutoUseCase(produtoRepositoryPort);
  }
}
