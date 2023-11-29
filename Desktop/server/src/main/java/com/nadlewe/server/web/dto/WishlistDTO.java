package com.nadlewe.server.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class WishlistDTO {
    private int wishlistId;
    private String title;
    private List<PlaceDTO> places;
    private int price;
    private String image;
}
