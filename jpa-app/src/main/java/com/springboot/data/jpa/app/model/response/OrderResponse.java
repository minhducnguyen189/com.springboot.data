package com.springboot.data.jpa.app.model.response;

import com.springboot.data.jpa.app.entity.OrderStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class OrderResponse {

    private UUID id;
    private UUID customerId;
    private LocalDateTime createdDate;
    private LocalDateTime lastUpdatedDate;
    private OrderStatus orderStatus;
    private List<ItemResponse> items;

}
