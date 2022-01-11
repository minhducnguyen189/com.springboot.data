package com.springboot.data.jpa.app.model.request;

import com.springboot.data.jpa.app.entity.Gender;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CustomerRequest {

    private String fullName;
    private String email;
    private String address;
    private String phone;
    private Gender gender;
    private Date dob;
    private List<OrderRequest> orders;

}
