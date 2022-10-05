package ru.otus.spring15cafe.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.Router;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.scheduling.PollerMetadata;
import ru.otus.spring15cafe.domain.OrderItem;

@Configuration
@IntegrationComponentScan
public class AppConfig {

    @Bean
    public QueueChannel itemsChannel() {
        return MessageChannels.queue(10).get();
    }

    @Bean
    public PublishSubscribeChannel foodChannel() {
        return MessageChannels.publishSubscribe().get();
    }

    @Bean
    public QueueChannel routerChannel() {
        return MessageChannels.queue(10).get();
    }

    @Bean
    public QueueChannel barChannel() {
        return MessageChannels.queue(10).get();
    }

    @Bean
    public QueueChannel kitchenChannel() {
        return MessageChannels.queue(10).get();
    }

    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    public PollerMetadata poller() {
        return Pollers.fixedRate(100).maxMessagesPerPoll(2).get();
    }

    /* 1-ый вариант: DSL Routing */

    @Bean
    public IntegrationFlow cafeFlow2() {
        return f -> f
                .log()
                .channel("itemsChannel")
                .split()
                .<OrderItem, Boolean>route(
                        OrderItem::isIced,
                        mapping -> mapping
                                .subFlowMapping(true, sf -> sf
                                        .log()
                                        .channel("barChannel")
                                        .handle("barService", "drink")
                                        .aggregate()
                                        .channel("foodChannel")
                                )
                                .subFlowMapping(false, sf -> sf
                                        .log()
                                        .channel("kitchenChannel")
                                        .handle("kitchenService", "cook")
                                        .aggregate()
                                        .channel("foodChannel")
                                )
                );
    }

    /* 2-ой вариант: использование роутера в канале
    @Bean
    public IntegrationFlow splitFlow() {
        return IntegrationFlows.from("itemsChannel")
                .log()
                .split()
                .channel("routerChannel")
                .get();
    }

    @Router(inputChannel = "routerChannel")
    public String route(Object payload) {
        if (((OrderItem) payload).getItemName().equals("1")) {
            return "barChannel";
        } else {
            return "kitchenChannel";
        }
    }

    @Bean
    public IntegrationFlow kitchenFlow() {
        return IntegrationFlows.from("kitchenChannel")
                .log()
                .split()
                .handle("kitchenService", "cook")
                .aggregate()
                .channel("foodChannel")
                .get();
    }

    @Bean
    public IntegrationFlow barFlow() {
        return IntegrationFlows.from("barChannel")
                .log()
                .split()
                .handle("barService", "drink")
                .aggregate()
                .channel("foodChannel")
                .get();
    }
     */
}
