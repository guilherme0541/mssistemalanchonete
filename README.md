# mssistemalanchonete

  Bem-vindo ao projeto **mssistemalanchonete**!  
  Esta aplicação é o backend de um sistema de controle de pedidos e auto-atendimento para uma lanchonete. Nela é possível cadastrar clientes, produtos e pedidos bem como gerenciar seu ciclo de vida.  
  Ela foi contruída usando arquitetura hexagonal, java 17 e spring-boot 3.

## Pré-Requisitos
  Para rodar essa aplicação você precisa de um banco de dados postgres, de java 17 e do maven.  
  Entretanto, recomendamos fortemente que você utilize o docker e o docker compose para facilitar esse processo (nós criamos um docker-compose.yml :wink: ). 
  Caso ainda não os tenha instalado, visite a [documentação oficial do Docker](https://docs.docker.com/get-docker/) para obter instruções de instalação.

## Executando através do Docker Compose:
  Garanta que você está na branch `develop`.  
  Olhe para a pasta `./docker-compose` você encontrará tudo que é necessário para subir a aplicação utilizando docker compose (um yml e as enviroments referenciadas nele).  
  Basta, a partir de `./docker-compose`, no seu linux, executar :  
  ```
  docker compose up
  ```

## Contrato
  Com aplicação rodando, acesse `http://localhost:8080/sistema-lanchonete/api/v1/swagger-ui/index.html` e você terá o detalhamento dos endpoints expostos na aplicação.

## A aplicação
  Conforme escrito anteriormente, podemos gerenciar clientes, produtos e pedidos.

### Clientes
  Os clientes (pessoas que compram da lanchonete) podem ter cpf, e-mail e nome.  
  Um cliente pode se cadastar utilizando apenas o cpf ou utilizando nome e e-mail.  
  O cadastro de um cliente possui um código que é utilizado para referencia-lo. 

### Produtos
  Os produtos possuem código, nome, descrição, preço e categoria (lanche, acompanhamento, bebida e sobremesa).

### Pedidos
  Os pedidos são compostos por combos, que são uma coleção de produtos e quantidades a eles associadas. Caso o cliente se identifique ao fazer o pedido, o pedido também contém a referencia ao cliente.  
  Existem endpoints específicos para adicionar e remover produtos e adicionar e remover combos(vazios ou com produtos relacionados).  
  Após a escolha de produtos ser finalizada, é necessário fazer o checkout para que o pedido seja encaminhado para a cozinha.
  Após o checkout é possível acompanhar o status do pedido e o tempo de espera.

### Desenho de Arquitetura dos Requisitos do Negócio:

Requisitos do Negócio

Interface de Seleção de Produtos
  Identificação do Cliente: Opções para identificação via CPF, cadastro com nome e e-mail, ou anonimato.
  Montagem de Combo: Clientes podem montar combos de Lanche, Acompanhamento, Bebida.
  Exibição de Produtos: Nome, descrição e preço de cada produto exibidos em cada etapa.
  
Pagamento
  Integração com Mercado Pago utilizando QRCode.

Acompanhamento de Pedido
  Monitor para clientes acompanharem o progresso do pedido com etapas: Recebido, Em preparação, Pronto, Finalizado.

Entrega
  Notificação ao cliente quando o pedido estiver pronto para retirada.
  Atualização do status do pedido para "Finalizado" quando retirado pelo cliente.

Acesso Administrativo
  Gerenciamento de Clientes: Coletar dados dos clientes identificados para campanhas promocionais.
  Gerenciamento de Produtos e Categorias: Gerenciar produtos com categorias fixas: Lanche, Acompanhamento, Bebida, Sobremesa.
  Acompanhamento de Pedidos: Monitorar pedidos em andamento e tempo de espera.

Componentes Principais:
    Backend: Microsserviços para gerenciar autenticação, produtos, pedidos, pagamentos e notificações.
    Banco de Dados: Armazenamento de dados de clientes, produtos, pedidos e histórico de transações.
    Integração de Pagamentos: Serviço de integração com Mercado Pago.
    Serviço de Notificações: Para notificar clientes sobre o status do pedido.

Serviços e Ferramentas AWS:
    Amazon EC2: Para hospedar os microsserviços.
    Amazon RDS: Banco de dados relacional.
    Amazon SQS: Gerenciamento de filas para comunicação assíncrona.
    Amazon SNS: Serviço de notificação.
    Amazon S3: Armazenamento de imagens de produtos.

### Desenho de Arquitetura da Infraestrutura: 
![Diagrama - Arquitetura](https://github.com/user-attachments/assets/47b1b124-e7fe-47d2-bbe0-81a7937b3d91)

