package com.nadlewe.server.service;

import com.nadlewe.server.web.dto.MyPageDTO;
import com.nadlewe.server.web.dto.PlaceDTO;
import com.nadlewe.server.web.dto.PurchaseDTO;
import com.nadlewe.server.web.dto.WishlistDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyPageService {

    public MyPageDTO getMyPageData(int userId) {
        // Simulating data retrieval from a database
        List<PurchaseDTO> purchases = createSamplePurchases();
        List<WishlistDTO> wishlist = createSampleWishlist();

        return new MyPageDTO(userId, "user_image_url", "홍길동", 27, "남자", purchases, wishlist);
    }

    // for postman test
    private List<PurchaseDTO> createSamplePurchases() {
        List<PurchaseDTO> purchases = new ArrayList<>();

        PlaceDTO place1 = new PlaceDTO("박지후 스시", 5);
        PlaceDTO place2 = new PlaceDTO("남산타워", 3);
        PlaceDTO place3 = new PlaceDTO("오뤼르 베이커리", 4);

        List<PlaceDTO> placesForPurchase1 = new ArrayList<>();
        placesForPurchase1.add(place1);
        placesForPurchase1.add(place2);
        placesForPurchase1.add(place3);

        PurchaseDTO purchase1 = new PurchaseDTO(1, "저녁 야경 데이트", placesForPurchase1, 87820, "purchase_image_url_1");

        PlaceDTO place4 = new PlaceDTO("산타돈부리", 4.5);
        PlaceDTO place5 = new PlaceDTO("필동면옥", 5);

        List<PlaceDTO> placesForPurchase2 = new ArrayList<>();
        placesForPurchase2.add(place4);
        placesForPurchase2.add(place5);

        PurchaseDTO purchase2 = new PurchaseDTO(2, "충무로 맛집 데이트", placesForPurchase2, 50500, "purchase_image_url_2");

        purchases.add(purchase1);
        purchases.add(purchase2);

        return purchases;
    }

    // for postman test
    private List<WishlistDTO> createSampleWishlist() {
        List<WishlistDTO> wishlist = new ArrayList<>();

        PlaceDTO place1 = new PlaceDTO("남산타워", 3);
        PlaceDTO place2 = new PlaceDTO("장충단 공원", 4.2);

        List<PlaceDTO> placesForPurchase1 = new ArrayList<>();
        placesForPurchase1.add(place1);
        placesForPurchase1.add(place2);

        WishlistDTO wishlist1 = new WishlistDTO(1, "남산타워 가을 데이트", placesForPurchase1, 15000, "wishlist_image_url_1");

        wishlist.add(wishlist1);

        return wishlist;
    }
}
