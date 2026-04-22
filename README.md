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


