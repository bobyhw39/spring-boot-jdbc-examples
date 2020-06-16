package com.enigma.purchased.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Coupon {
    private Long id;
    private String name;
    private String owner;
    private String description;
    private String amount;
    private Date startFrom;
    private Date endIn;
}
