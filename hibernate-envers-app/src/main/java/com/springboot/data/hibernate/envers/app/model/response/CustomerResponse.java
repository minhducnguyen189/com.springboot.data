package com.springboot.data.hibernate.envers.app.model.response;

import com.springboot.data.hibernate.envers.app.entity.Gender;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.Date;
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
    private OffsetDateTime createdDate;
    private OffsetDateTime updatedDate;

}
