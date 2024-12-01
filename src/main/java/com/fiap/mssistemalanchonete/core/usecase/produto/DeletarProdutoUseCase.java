package com.fiap.mssistemalanchonete.core.usecase.produto;

import com.fiap.mssistemalanchonete.core.exception.exception.ProdutoNotFoundException;
import com.fiap.mssistemalanchonete.core.model.Produto;
import com.fiap.mssistemalanchonete.core.port.ProdutoRepositoryPort;
import com.fiap.mssistemalanchonete.core.usecase.DeletarProdutoUseCaseFacade;
import org.springframework.util.ObjectUtils;


public class DeletarProdutoUseCase implements DeletarProdutoUseCaseFacade {

    private final ProdutoRepositoryPort produtoRepositoryPort;

    public DeletarProdutoUseCase(ProdutoRepositoryPort produtoRepositoryPort){
        this.produtoRepositoryPort = produtoRepositoryPort;
    }

    @Override
    public void deletarProduto(String codigo) {

        Produto produto = produtoRepositoryPort.consultarProdutoPorCodigo(codigo);

        if (ObjectUtils.isEmpty(produto))
            throw new ProdutoNotFoundException(codigo);

        produtoRepositoryPort.deletarProduto(produto);
    }
}
