package ru.skillbox.orderstatusservice.listener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import ru.skillbox.orderstatusservice.dto.OrderEvent;
import ru.skillbox.orderstatusservice.dto.OrderStatusEvent;

import java.time.Instant;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderEventListener {
    private final KafkaTemplate<String, OrderStatusEvent> kafkaTemplate;

    @KafkaListener(topics = "order-topic", groupId = "order-status-group")
    public void listenOrderTopic(OrderEvent orderEvent) {
        log.info("order-status-service заказ: {} получен", orderEvent);
        String status = "CREATED";
        Instant date = Instant.now();

        OrderStatusEvent orderStatusEvent = new OrderStatusEvent();
        orderStatusEvent.setStatus(status);
        orderStatusEvent.setDate(date);
        log.info("order-status-service отправляет ответ: {}", orderStatusEvent);

        kafkaTemplate.send("order-status-topic", orderStatusEvent);
    }
}
