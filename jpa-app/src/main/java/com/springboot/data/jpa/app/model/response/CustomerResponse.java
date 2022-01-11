package com.springboot.data.jpa.app.model.response;

import com.springboot.data.jpa.app.entity.Gender;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class CustomerResponse {

    private UUID id;
    private String fullName;
    private String email;
    private String address;
    private String phone;
    private Gender gender;
    private Date dob;
    private List<OrderResponse> orders;

}
