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
    public PublishSubscribeChannel barChannel() {
        return MessageChannels.publishSubscribe().get();
    }

    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    public PollerMetadata poller() {
        return Pollers.fixedRate(100).maxMessagesPerPoll(2).get();
    }

    @Bean
    public IntegrationFlow kitchenFlow() {
        return IntegrationFlows.from("itemsChannel")
                .log()
                .split()
                .handle("kitchenService", "cook")
                .aggregate()
                .channel("barChannel")
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

}
