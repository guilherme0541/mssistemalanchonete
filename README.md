# ms-sistema-lanchonete-produto

Microsserviço responsável pela criação e autenticação dos clientes do sistema-lanchonete (FIAP - Pós Tech - Fase 4)

## Contrato
  Com aplicação rodando, acesse o endpoint `/sistema-lanchonete/api/v1/swagger-ui/index.html` e você terá o detalhamento dos endpoints expostos na aplicação.

## A aplicação
  Conforme escrito anteriormente, podemos gerenciar os produtos do sistema.

### Produtos
  Os produtos possuem código, nome, descrição, preço e categoria (lanche, acompanhamento, bebida e sobremesa).

### Serviços e Ferramentas AWS:
  - Amazon EC2: Para hospedar os microsserviços.
  - Amazon RDS: Banco de dados relacional.
  - Amazon EKS: Serviço gerenciado do Kubernetes.
  - Amazon ECR: registro de container.
  - Amazon Api Gateway: serviço gerenciado de gateway.
  - Amazon VPC: rede virtual.
  - Application Load Balancer: Distribuição de tráfego para instâncias EC2.
  - Docker: Containerização dos microsserviços.


### Desenho de Arquitetura da Infraestrutura da solução completa: 
![Diagrama - Arquitetura](/assets/arch.jpeg)

### Banco de Dados: 

Para a aplicação, foi utilizado como banco de dados uma instância do banco relacional **PostgreSQL** através do serviço **Amazon RDS**. A escolha desse serviço ocorreu principalmente pela praticidade na configuração e gerenciamento do banco de dados por parte da AWS, e por suas capacidades de alta disponibilidade e escalonamento, que são muito importantes em ambiente produtivo. A escolha do PostgreSQL como SGBD se deu por conta de sua característica estruturada (SQL), visto que os bancos de dados relacionais garantem consistência e integridade dos dados (ACID), que são características importantes nesse tipo de aplicação.

## CI/CD
Alguns passos de ci/cd foram implementados utilizando terraform e github-actions.   
O cluster EKS e a infra necessária para ele são disponibilizadas utilizando [esse respositório](https://github.com/guilherme0541/mslanchonete-infra-eks).   
O instaância RDS é provisionada através [desse](https://github.com/guilherme0541/mslanchonete-db-secreteKubernetes).   
Já a lambda utilizada para autenticação é provisionada [aqui](https://github.com/Guimaj/lambda-auth-mslanchonete).   
Toda essa infra precisa ser preparada previamente para que a automação desse repositório possa ser executada.   
As actions desse repositório verificam a qualidade do código, compilam, buildam e publicam a aplicação em um repositório privado no ECR e fazem deploy no cluster EKS.

### Variaveis e Secrets
Para executar os scripts diretamente do github, é necessário criar a variable `AWS_REGION` que é o código da região AWS e  as secrets `TSECRET, AWS_ACCESS_KEY_ID e AWS_SECRET_ACCESS_KEY`, respectivamente a secret usada no JWT, o ID e chave de acesso de um usuário AWS com permissões suficientes para criar e alterar os recursos citados acima.  

### Execução
As automações rodam a partir de pull-requests para a `main`: na abertura verifica a qualidade do código, publica a nova versão da aplicação e valida as alterações necessárias no ambiente, no merge ela aplica as alterações. Também é possivel acionar a automação manualmente no menu action do github.
Para fazer o desprovisionamento da infra também existe uma action nesse repositório: **Deploy terraform**. Ela precisa ser acionada manualmente e escolhendo "Yes_sure" mo menu suspenso o processo é iniciado.

### Consumindo a API
Após a conclusão da action de deploy você precisará pegar o endereço do host no api gateway na sua conta AWS.

## Quality
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=mssistemalanchonete_mssistemalanchonete-produto&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=mssistemalanchonete_mssistemalanchonete-produto)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=mssistemalanchonete_mssistemalanchonete-produto&metric=coverage)](https://sonarcloud.io/summary/new_code?id=mssistemalanchonete_mssistemalanchonete-produto)