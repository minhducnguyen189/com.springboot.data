package com.springboot.data.jpa.app.model.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class ItemResponse {

    private UUID id;
    private String itemName;
    private Long quantity;
    private Float price;

}
