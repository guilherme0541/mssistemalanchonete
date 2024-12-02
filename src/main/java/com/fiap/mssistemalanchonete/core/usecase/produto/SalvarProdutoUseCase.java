package com.fiap.mssistemalanchonete.core.usecase.produto;

import com.fiap.mssistemalanchonete.core.exception.exception.ProdutoAlreadyExistsException;
import com.fiap.mssistemalanchonete.core.model.Produto;
import com.fiap.mssistemalanchonete.core.port.ProdutoRepositoryPort;
import com.fiap.mssistemalanchonete.core.usecase.SalvarProdutoUseCaseFacade;
import org.springframework.util.ObjectUtils;

public class SalvarProdutoUseCase implements SalvarProdutoUseCaseFacade {

    private final ProdutoRepositoryPort produtoRepositoryPort;

    public SalvarProdutoUseCase(ProdutoRepositoryPort produtoRepositoryPort){
        this.produtoRepositoryPort = produtoRepositoryPort;
    }

    @Override
    public Produto salvarProduto(Produto produto) {

        String codigo = produto.getCodigo();

        if (!ObjectUtils.isEmpty(produtoRepositoryPort.consultarProdutoPorCodigo(codigo)))
            throw new ProdutoAlreadyExistsException(codigo);

        return produtoRepositoryPort.salvarProduto(produto);
    }
}
