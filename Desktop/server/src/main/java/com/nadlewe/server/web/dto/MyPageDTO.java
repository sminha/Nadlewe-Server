package com.nadlewe.server.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class MyPageDTO {
    private int userId;
    private String image;
    private String name;
    private int age;
    private String gender;
    //private List<PurchaseDTO> purchases;
    private List<WishlistDTO> wishlist;
}
