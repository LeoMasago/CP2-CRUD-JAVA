package br.com.fiap3espv.checkpoint2.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoConfig {
    //lendo a URI diretamenta do application.properties que estiver ativo
    @Value("${spring.data.mongodb.uri}")
    private String mongoUri;

    //criando cliente MongoDB utilizando a URI obtida acima.
    //OBS: fiz dessa forma pois o Spring-Boot estava ignorando a URI e conectando no localhost por padrão
    @Bean
    public MongoClient mongoClient() {
        return MongoClients.create(mongoUri);
    }

    //criando o MongoTemplate para interação com o banco MongoDB
    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoClient(), "checkpoint2");
    }
}
