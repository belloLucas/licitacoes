# Licitações

Este projeto tem como intuito fazer o scraping da página `http://comprasnet.gov.br/ConsultaLicitacoes/ConsLicitacaoDia.asp` e, com os dados retirados da página, exibir os mesmos no front end desenvolvido.

No momento da criação do projeto, optei por mockar os dados e fazer um "scraping" de um `.html`, mas com mais tempo hábil seria possível implementar o scraping com base no site original.

A estrutura do scraping é baseada na estrutura do `.html` do `comprasnet.gov.br`.

O projeto utiliza o banco de dados em memória H2 do Java.

## Rodando o projeto

#### 1. Faça o clone do projeto

```bash
git clone https://github.com/belloLucas/licitacoes.git
```

#### 2. Após clonar, entre no diretório e instale as dependências

```bash
1. cd licitacoes
2. cd frontend
3. npm i
```

#### 3. Depois, entre no diretório backend e instale as dependências

```bash

```

#### 4. Volte ao diretório raiz `licitacoes` e execute os containers

```bash
docker-compose up -d
```

#### 5. Acesse o projeto.

O front end fica acessível na url

```bash
http://localhost:5173
```

E o back end estará disponível na porta 8080

```bash
http://localhost:8080
```

## Estrutura da API

A API até o momento tem uma estrutura simples, com 3 endpoints:

```bash
GET http://localhost:8080/licitacoes
```

```bash
GET http://localhost:8080/licitacoes/uasg/{codigo}
```

```bash
GET http://localhost:8080//licitacoes/pregao?numeroPregao={2/2024}
```

Mais detalhes dos endpoints podem ser vistos via swagger:

```bash
http://localhost:8080/swagger-ui/index.html
```
