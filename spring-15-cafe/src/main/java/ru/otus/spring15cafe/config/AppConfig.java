package ru.otus.spring15cafe.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.scheduling.PollerMetadata;
import ru.otus.spring15cafe.domain.OrderItem;

import static org.yaml.snakeyaml.nodes.NodeId.mapping;

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

    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    public PollerMetadata poller() {
        return Pollers.fixedRate( 100 ).maxMessagesPerPoll(2).get();
    }

    /**
     * Service Activator - работает!
     * @return
     */
    @Bean
    public IntegrationFlow cafe_Flow() {
        return IntegrationFlows.from( "itemsChannel" )
                .split()
                //.handle( "kitchenService", "cook" )
                .handle("barService", "cook")
                .aggregate()
                .channel( "foodChannel" )
                .get();
    }




    // 0:47:49 DSL Routing subFlow
/*
    @Bean
    public IntegrationFlow cafeFlow2() {
        return f -> f
                .<OrderItem, Boolean>route(
                        OrderItem::isIced,
                        mapping -> mapping
                                .subFlowMapping(true, sf -> sf
                                        .channel("icedOrders"))
                                .subFlowMapping(false, sf -> sf.channel("notIcedOrders"))
                );
    }
*/


}
