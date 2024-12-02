Feature: Testa as funcionalidades da aplicação

  Scenario: Criar produto
    When chamada a criação do produto
    Then o produto é criado

  Scenario: Não criar se o produto já existe
    Given que o produto já existe
    When chamada a criação do produto
    Then retorna bad request

  Scenario: Atualizar produto
    Given que o produto já existe
    When chamada a atualização do produto
    Then atualiza o produto

  Scenario: Não atualizar se o produto não existe
    Given que o produto não existe
    When chamada a atualização do produto
    Then retorna not found

  Scenario: Deletar produto
    Given que o produto já existe
    When chamada a deleção do produto
    Then deleta o produto

  Scenario: Não deleta se o produto não existe
    Given que o produto não existe
    When chamada a deleção do produto
    Then retorna not found

  Scenario: Buscar produtos por categoria
    Given que os produtos já existem
    When chamada a busca dos produtos por categoria
    Then retorna os produtos da categoria

  Scenario: Buscar todos os produtos
    Given que os produtos já existem
    When chamada a busca dos produtos
    Then retorna os produtos