package com.springboot.data.hibernate.envers.app.model.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class ItemRequest {


    private UUID id;
    private String itemName;
    private Long quantity;
    private Float price;

}
