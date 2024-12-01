package com.fiap.mssistemalanchonete.core.usecase.produto;

import com.fiap.mssistemalanchonete.core.exception.exception.ProdutoNotFoundException;
import com.fiap.mssistemalanchonete.core.model.Produto;
import com.fiap.mssistemalanchonete.core.port.ProdutoRepositoryPort;
import com.fiap.mssistemalanchonete.core.usecase.AtualizarProdutoUseCaseFacade;
import org.springframework.util.ObjectUtils;

public class AtualizarProdutoUseCase implements AtualizarProdutoUseCaseFacade {

    private final ProdutoRepositoryPort produtoRepositoryPort;

    public AtualizarProdutoUseCase(ProdutoRepositoryPort produtoRepositoryPort){
        this.produtoRepositoryPort = produtoRepositoryPort;
    }

    @Override
    public Produto atualizarProduto(Produto produto, String codigo) {

        Produto produtoAntigo = produtoRepositoryPort.consultarProdutoPorCodigo(codigo);

        if (ObjectUtils.isEmpty(produtoAntigo))
            throw new ProdutoNotFoundException(codigo);

        return produtoRepositoryPort.atualizarProduto(produtoAntigo, produto);
    }
}
