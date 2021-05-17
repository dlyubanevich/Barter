package ru.dlyubanevich.user;

import com.github.cloudyrock.spring.v5.EnableMongock;
import com.github.fridujo.rabbitmq.mock.compatibility.MockConnectionFactoryFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@EnableMongock
@TestConfiguration
public class UserApplicationTests {

    @Bean
    public ConnectionFactory connectionFactory() {
        return new CachingConnectionFactory(MockConnectionFactoryFactory.build());
    }

}
