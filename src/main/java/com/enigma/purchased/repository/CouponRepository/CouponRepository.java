package com.enigma.purchased.repository.CouponRepository;

import com.enigma.purchased.dto.CouponPostDTO;
import com.enigma.purchased.entity.Coupon;

import java.util.List;

public interface CouponRepository {
    Coupon findCouponByName(String name);
    List<Coupon> getAllCoupon();
    void saveCoupon(CouponPostDTO coupon);
    void deleteCoupon(String name);
    void updateCoupon(Coupon coupon);
}
