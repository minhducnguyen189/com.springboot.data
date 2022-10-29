package com.springboot.data.hibernate.envers.app.model.response;

import com.springboot.data.hibernate.envers.app.entity.OrderStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
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

}
