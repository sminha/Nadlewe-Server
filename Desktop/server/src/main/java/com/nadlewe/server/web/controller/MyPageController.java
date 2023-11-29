package com.nadlewe.server.web.controller;

import com.nadlewe.server.web.dto.MyPageDTO;
import com.nadlewe.server.service.MyPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MyPageController {

    private final MyPageService myPageService;

    @Autowired
    public MyPageController(MyPageService myPageService) {
        this.myPageService = myPageService;
    }

    @GetMapping("/mypage/{userId}")
    public ResponseEntity<MyPageDTO> getMyPage(@PathVariable int userId) {
        try {
            MyPageDTO response = myPageService.getMyPageData(userId);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(404).build();
        }
    }
}
