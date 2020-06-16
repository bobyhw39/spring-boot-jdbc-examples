package com.enigma.purchased.services;

import com.enigma.purchased.dto.CouponPostDTO;
import com.enigma.purchased.entity.Coupon;
import com.enigma.purchased.exceptions.NotFoundException;
import com.enigma.purchased.repository.CouponRepository.CouponRepository;
import com.enigma.purchased.repository.CouponRepository.JdbcCouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CouponServices {

    @Autowired
    CouponRepository jdbcCouponRepository;

    public List<Coupon> gelAllCoupon(){
        try {
            return jdbcCouponRepository.getAllCoupon();
        }
        catch (EmptyResultDataAccessException e){
            throw new NotFoundException("not found");
        }
    }

    public void saveCoupon(CouponPostDTO coupon){
        jdbcCouponRepository.saveCoupon(coupon);
    }

    public Coupon findCouponByName(String name){
        try {
            return jdbcCouponRepository.findCouponByName(name);
        }
        catch (EmptyResultDataAccessException e){
            throw new NotFoundException(name +" not found");
        }
    }

    public void deleteCoupon(String name){
        findCouponByName(name);
        jdbcCouponRepository.deleteCoupon(name);
    }

    public void updateCoupon(Coupon coupon){
        jdbcCouponRepository.updateCoupon(coupon);
    }
}
