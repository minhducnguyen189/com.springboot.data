package com.springboot.data.model.request;

import com.springboot.data.model.OrderStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OrderRequest {

    private String orderName;
    private OrderStatus orderStatus;
    private List<ItemRequest> items;

}
