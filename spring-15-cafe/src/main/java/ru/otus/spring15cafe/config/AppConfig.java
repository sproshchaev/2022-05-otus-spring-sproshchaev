package ru.otus.spring15cafe.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.scheduling.PollerMetadata;
import ru.otus.spring15cafe.domain.OrderItem;

/**
 * Класс AppConfig содержит конфигурацию Spring Integration
 */
@Configuration
@IntegrationComponentScan
public class AppConfig {

    @Bean
    public QueueChannel itemsChannel() {
        return MessageChannels.queue(10).get();
    }

    @Bean
    public QueueChannel kitchenChannel() {
        return MessageChannels.queue(10).get();
    }

    @Bean
    public QueueChannel barChannel() {
        return MessageChannels.queue(10).get();
    }

    @Bean
    public PublishSubscribeChannel readyChannel() {
        return MessageChannels.publishSubscribe().get();
    }

    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    public PollerMetadata poller() {
        return Pollers.fixedRate(100).maxMessagesPerPoll(2).get();
    }

    @Bean
    public IntegrationFlow cafeFlow() {
        return f -> f
                .log()
                .channel("itemsChannel")
                .split()
                .<OrderItem, Boolean>route(
                        OrderItem::isBarAssortment,
                        mapping -> mapping
                                .subFlowMapping(true, sf -> sf
                                        .log()
                                        .channel("barChannel")
                                        .handle("barService", "drink")
                                )
                                .subFlowMapping(false, sf -> sf
                                        .log()
                                        .channel("kitchenChannel")
                                        .handle("kitchenService", "cook")
                                )
                ).aggregate()
                .channel("readyChannel");
    }

    @Bean
    public IntegrationFlow deliveryOrderFlow() {
        return IntegrationFlows.from("readyChannel")
                .log()
                .split()
                .handle("deliveryService", "print")
                .aggregate()
                .handle("deliveryService", "deliver")
                .get();
    }

}