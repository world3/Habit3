package org.world3.habit3.config;

import com.mongodb.MongoClientURI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.net.UnknownHostException;


@Configuration
@EnableMongoRepositories(mongoTemplateRef = "mongoHabit3Template", basePackages = "org.world3.habit3.repository")
public class MongoConfig {

    private static final String HABIT3_URI_KEY = "mongodb.database.habit3.uri";

    private static final int MONGO_CONNECTION_TIMEOUT = 1000;
    private static final int MONGO_SOCKET_TIMEOUT = 1000;
    private static final int MONGO_MIN_HEARTBEAT_FREQUENCY = 25;
    private static final int MONGO_MAX_WAIT_TIME = 1000;
    private static final int MONGO_SERVER_SELECTOR_TIMEOUT = 1000;
    private static final int MONGO_HEARTBEAT_SOCKET_TIMEOUT = 1000;
    private static final int MONGO_HEARTBEAT_CONNECTION_TIMEOUT = 1000;

    @Autowired
    private Environment environment;

    @Autowired
    private MongoMappingContext mongoMappingContext;

    public MongoClientURI mongoClientUri() {
        MongoClientURI mongoClientURI = new MongoClientURI(environment.getProperty(HABIT3_URI_KEY));

        mongoClientURI.getOptions().builder()
                .connectTimeout(MONGO_CONNECTION_TIMEOUT)
                .socketTimeout(MONGO_SOCKET_TIMEOUT)
                .minHeartbeatFrequency(MONGO_MIN_HEARTBEAT_FREQUENCY)
                .heartbeatSocketTimeout(MONGO_HEARTBEAT_SOCKET_TIMEOUT)
                .maxWaitTime(MONGO_MAX_WAIT_TIME)
                .heartbeatConnectTimeout(MONGO_HEARTBEAT_CONNECTION_TIMEOUT)
                .serverSelectionTimeout(MONGO_SERVER_SELECTOR_TIMEOUT);

        return mongoClientURI;
    }

    @Bean
    public MongoTemplate mongoHabit3Template() throws UnknownHostException {
        return new MongoTemplate(mongoFactory(), mappingMongoConverter());
    }

    @Bean
    public MongoDbFactory mongoFactory() throws UnknownHostException{
        return new SimpleMongoDbFactory(mongoClientUri());
    }

    @Bean
    public MappingMongoConverter mappingMongoConverter() throws UnknownHostException{
        DbRefResolver dbRefResolver = new DefaultDbRefResolver(mongoFactory());
        MappingMongoConverter mongoConverter = new MappingMongoConverter(dbRefResolver, mongoMappingContext);
        mongoConverter.setTypeMapper(new DefaultMongoTypeMapper(null));
        return mongoConverter;
    }

    @Bean
    public GridFsTemplate gridFsHabit3Template() throws UnknownHostException{
        return new GridFsTemplate(mongoFactory(), mappingMongoConverter());
    }

}

