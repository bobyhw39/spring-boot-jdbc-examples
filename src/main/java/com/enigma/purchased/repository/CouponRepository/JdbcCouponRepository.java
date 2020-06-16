package com.enigma.purchased.repository.CouponRepository;

import com.enigma.purchased.dto.CouponPostDTO;
import com.enigma.purchased.entity.Coupon;
import com.enigma.purchased.exceptions.BadRequestException;
import com.enigma.purchased.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcCouponRepository implements CouponRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Coupon findCouponByName(String name) {
        return jdbcTemplate.queryForObject(
                "select * from coupon where name=?",
                new Object[]{name},
                (rs, rowNum) -> new Coupon(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("owner"),
                        rs.getString("description"),
                        rs.getString("amount"),
                        rs.getDate("start_from"),
                        rs.getDate("end_in")
                )
        );
    }

    @Override
    public List<Coupon> getAllCoupon() {
            return jdbcTemplate.query("select * from coupon",
                    (rs, rowNum) -> new Coupon(
                            rs.getLong("id"),
                            rs.getString("name"),
                            rs.getString("owner"),
                            rs.getString("description"),
                            rs.getString("amount"),
                            rs.getDate("start_from"),
                            rs.getDate("end_in")
                    )
            );
    }

    @Override
    public void saveCoupon(CouponPostDTO coupon) {
        jdbcTemplate.update(
                "insert into coupon (name,owner,description,amount,start_from,end_in) values(?,?,?,?,?,?) ",
                coupon.getName(),coupon.getOwner(),coupon.getDescription(),coupon.getAmount(),coupon.getStartFrom(),coupon.getEndIn()
        );
    }

    @Override
    public void deleteCoupon(String name) {
        jdbcTemplate.update("delete coupon where name=?",name);
    }

    @Override
    public void updateCoupon(Coupon coupon) {
        jdbcTemplate.update("update coupon set name= ? where id = ?",coupon.getName(),coupon.getId());
        jdbcTemplate.update("update coupon set description= ? where id = ?",coupon.getDescription(),coupon.getId());
        jdbcTemplate.update("update coupon set amount= ? where id = ?",coupon.getAmount(),coupon.getId());
        jdbcTemplate.update("update coupon set start_from= ? where id = ?",coupon.getStartFrom(),coupon.getId());
        jdbcTemplate.update("update coupon set end_in= ? where id = ?",coupon.getEndIn(),coupon.getId());
    }
}
