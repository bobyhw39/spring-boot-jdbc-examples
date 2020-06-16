package com.enigma.purchased.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.sql.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CouponPostDTO {

    @NotEmpty(message = "Name may not be empty")
    private String name;

    @NotEmpty(message = "Owner may not be empty")
    private String owner;

    @NotEmpty(message = "Description may not be empty")
    private String description;

    @NotEmpty(message = "Amount may not be empty")
    private String amount;

    @NotEmpty(message = "Date Start From may not be empty")
    private Date startFrom;

    @NotEmpty(message = "Date End In may not be empty")
    private Date endIn;
}
