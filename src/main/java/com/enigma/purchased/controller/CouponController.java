package com.enigma.purchased.controller;

import com.enigma.purchased.dto.CouponPostDTO;
import com.enigma.purchased.entity.Coupon;
import com.enigma.purchased.exceptions.ErrorDetails;
import com.enigma.purchased.repository.CouponRepository.CouponRepository;
import com.enigma.purchased.services.CouponServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Date;
import java.util.List;

@RestController
@Api(value = "Coupon applications")
@RequestMapping("coupon")
@Validated
public class CouponController {

    @Autowired
    CouponServices couponServices;

    @GetMapping("")
    @ApiOperation(value = "View all coupon" , response = Coupon.class)
    public List<Coupon> viewAllCoupon(){
        return couponServices.gelAllCoupon();
    }

    @GetMapping("/search")
    @ApiOperation(value = "Search coupon" , response = Coupon.class)
    public Coupon searchCouponByname(@RequestParam String name){
        return couponServices.findCouponByName(name);
    }

    @PostMapping("")
    @ApiOperation(value = "Add new Coupon", response = ErrorDetails.class)
    public ErrorDetails saveCoupon(@Valid @RequestBody CouponPostDTO couponPostDTO){
        couponServices.saveCoupon(couponPostDTO);
        return new ErrorDetails(new Date(System.currentTimeMillis()), "add coupon " + couponPostDTO.getName() + " success", "200", "/coupon");
    }

    @DeleteMapping("")
    @ApiOperation(value = "Delete a Coupon", response = ErrorDetails.class)
    public ErrorDetails deleteCoupon(@Valid @RequestBody String name){
        couponServices.deleteCoupon(name);
        return new ErrorDetails(new Date(System.currentTimeMillis()), "delete coupon " + name + " success", "200", "/coupon");
    }

    @PutMapping("")
    @ApiOperation(value = "Update a Coupon", response = ErrorDetails.class)
    public ErrorDetails updateCoupon(@Valid @RequestBody Coupon coupon){
        couponServices.updateCoupon(coupon);
        return new ErrorDetails(new Date(System.currentTimeMillis()), "update coupon " + coupon.getName() + " success", "200", "/coupon");
    }
}
