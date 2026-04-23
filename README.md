# Checkpoint 2 – API ReST de Pedidos

API ReST desenvolvida com Spring Boot e MongoDB para cadastro e gerenciamento de pedidos de clientes.

## Integrantes

| Nome | RM |
|------|----|
| Léo Masago | 557768 |
| Luiz Henrique Silva | 555235 |
| Eduardo Tomazela | 556807 |

## Tecnologias

- Java 25
- Spring Boot 4.0.5
- Spring Data MongoDB
- Lombok

## Endpoints

| Método | Rota | Descrição |
|--------|------|-----------|
| GET | `/pedidos` | Lista todos os pedidos |
| GET | `/pedidos/{id}` | Busca pedido por ID |
| POST | `/pedidos` | Cria novo pedido |
| PUT | `/pedidos/{id}` | Atualiza pedido (campos opcionais) |
| DELETE | `/pedidos/{id}` | Remove pedido |

## Exemplo de requisição

```json
{
  "clienteNome": "Léo Masago",
  "valorTotal": 113.00
}
```

## Regras de negócio

- `clienteNome` é obrigatório e deve conter apenas letras
- `valorTotal` é obrigatório, não pode ser negativo e aceita no máximo 2 casas decimais
- `dataPedido` é preenchida automaticamente com a data atual
- No PUT, os campos são opcionais — apenas os campos enviados serão atualizados

## Como executar

1. Certifique-se de ter o MongoDB rodando localmente na porta `27017` ou crie um arquivo com nome application-atlas.properties e coloque a conexão do Mongo neste arquivo.
2. No arquivo `application.properties`, defina o perfil ativo:
   - `spring.profiles.active=local` para MongoDB local
   - `spring.profiles.active=atlas` para MongoDB Atlas
3. Execute a aplicação pela IDE 
4. Faça as requisições pela seguinte rota: `http://localhost:8080`

Caso queira utilizar o Atlas. Dentro de `application-atlas.properties`:

```properties
spring.data.mongodb.uri=mongodb+srv://<USUÁRIO>:<SENHA>@<HOST>/<BANCO>?appName=<NOME-DO-CLUSTER>
```

Exemplo:

```properties
spring.data.mongodb.uri=mongodb+srv://masagoleo_db_user:<SUA_SENHA>@dev-cluster.tao89qq.mongodb.net/checkpoint2?appName=dev-cluster
```


