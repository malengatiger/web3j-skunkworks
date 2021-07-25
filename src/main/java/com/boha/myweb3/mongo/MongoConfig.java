package com.boha.myweb3.mongo;

import com.boha.myweb3.util.E;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.model.changestream.ChangeStreamDocument;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.function.Consumer;
import java.util.logging.Logger;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

@EnableCaching
@Configuration
public class MongoConfig {
    private static final Logger LOGGER = Logger.getLogger(MongoConfig.class.getSimpleName());

    public MongoConfig() {
        LOGGER.info(mm+ "MongoConfig constructed ...");
    }

    @Value("${mongoString}")
    String mongoString;
    @Value("${spring.profiles.active}")
    private String profile;


    private static final String mm = E.PANDA + E.PANDA +E.PANDA + E.PANDA +E.PANDA + E.PANDA + "MongoConfig: ";


    @Bean
    public MongoClient mongo() {
        LOGGER.info(mm + "Connection string recovered from properties " );
        String mURL = mongoString;

        ConnectionString connectionString = new ConnectionString( mURL);
        LOGGER.info(mm + "MongoDB Connection userName: " + E.RED_APPLE + " = " + connectionString.getUsername());

        CodecRegistry pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));

        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .codecRegistry(pojoCodecRegistry)
                .build();

        LOGGER.info(mm + "MongoClientSettings have been set with pojoCodecRegistry, setting up MongoClient ....");
        MongoClient client = MongoClients.create(settings);
        for (Document document : client.listDatabases()) {
            LOGGER.info(mm + "Database Document: "+ E.FROG + document.toJson() + E.FROG);
        }

        LOGGER.info(mm + E.RED_APPLE + "MongoDB Database is ready for use! " + E.RED_APPLE);

        return client;


    }

    // mongo user: money kkTiger3
    // mongodb+srv://money:kkTiger3@cluster0.fsy9k.mongodb.net/web3db?retryWrites=true&w=majority

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        LOGGER.info(mm + "MongoTemplate: database name: "+ E.FROG + "web3db" + E.FROG);
        return new MongoTemplate(mongo(), "web3db");
    }
}

