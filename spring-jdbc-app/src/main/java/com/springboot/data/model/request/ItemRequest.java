package com.springboot.data.model.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ItemRequest {

    private String itemName;
    private Long quantity;
    private Float price;

}
