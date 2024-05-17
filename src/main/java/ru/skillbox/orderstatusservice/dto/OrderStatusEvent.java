package ru.skillbox.orderstatusservice.dto;
import lombok.Data;

import java.time.Instant;
@Data
public class OrderStatusEvent{
    private String status;
    private Instant date;

}
