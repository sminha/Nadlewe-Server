package com.dgtour.web.controller;

import com.dgtour.web.dto.RegisterResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import com.dgtour.web.dto.RegisterRequestDTO;

@RestController
@RequestMapping("/api/mypage")
public class MypageController {

    // [TODO] mypage for common

    // [TODO] mypage for buyer
    /*@GetMapping("/buyer")
    public ResponseEntity<String> getPurchases() {

        return ResponseEntity.ok("View purchases as a buyer!");
    }*/

    // [TODO] mypage for seller
    @PostMapping("/seller/register")
    public ResponseEntity<RegisterResponseDTO> registerPackage(@RequestBody RegisterRequestDTO request) {

        // {“category”:”지역 관광지/레저”, “pName”:”새별프렌즈 동물원”, “pDescription”:”새별오름 앞, 동물 친구들을 만나는 시간”, “pPrice”: 18000, “pImage”: ”새별프렌즈 동물원 이미지 경로”}

        RegisterResponseDTO response = new RegisterResponseDTO();

        // [TODO] insert data in DB (현재는 insert 성공 가정)
        boolean registrationSuccess = true;

        if (registrationSuccess) {
            response.setMessage("등록 성공입니다");
        } else {
            response.setMessage("등록 실패입니다");
        }

        return ResponseEntity.ok(response);
    }

    // [TODO] mypage for manager
    /*@GetMapping("/admin/manage-services")
    public ResponseEntity<String> manageServices() {

        return ResponseEntity.ok("Manage services as an admin!");
    }*/
}
