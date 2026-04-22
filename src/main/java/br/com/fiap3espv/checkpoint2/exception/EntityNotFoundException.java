package br.com.fiap3espv.checkpoint2.exception;

/*
* classe de exceção herdando de RuntimeException para lançar erros caso não seja encontrado com uma mensagem
* personalizada.
*
* Vale ressaltar que esta classe teve que ser criada pois utilizei o banco MongoDB e não é possível utilizar JPA pois é
* expecífico para bancos relacionais
* */
public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String message) {
        super(message);
    }
}
