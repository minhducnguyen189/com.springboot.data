package com.springboot.data.hibernate.envers.app.model.request;

import com.springboot.data.hibernate.envers.app.entity.OrderStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class OrderRequest {

    private UUID customerId;
    private String orderName;
    private OrderStatus orderStatus;
    private List<ItemRequest> items;

}
