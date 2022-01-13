package com.springboot.data.jdbc.app.model.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class ItemRequest {

    private String itemName;
    private Long quantity;
    private Float price;

}
