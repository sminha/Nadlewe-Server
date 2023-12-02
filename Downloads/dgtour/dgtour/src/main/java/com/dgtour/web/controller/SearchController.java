package com.dgtour.web.controller;

import com.dgtour.domain.enums.CategoryId;
import com.dgtour.web.dto.PackageDTO;
import com.dgtour.web.dto.SearchByCategoryDTO;
import com.dgtour.web.dto.SearchByPackageDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/packages")
public class SearchController {

    @GetMapping("/{cId}")
    public ResponseEntity<SearchByCategoryDTO> searchByCategory(@PathVariable String cId) {

        List<SearchByCategoryDTO.CategoryResponseDTO> packages = new ArrayList<>();

        // CategoryId categoryId = CategoryId.valueOf(cId);

        // [TODO] get data from DB
        SearchByCategoryDTO.CategoryResponseDTO package1 = new SearchByCategoryDTO.CategoryResponseDTO();
        //package1.setCategory(categoryId);
        package1.setPName("새별프렌즈 동물원");
        package1.setPDescription("새별오름 앞, 동물 친구들을 만나는 시간");
        package1.setPPrice(18000);
        package1.setPDiscount(22);
        package1.setPImage("새별프렌즈 동물원 이미지 경로");
        packages.add(package1);

        SearchByCategoryDTO.CategoryResponseDTO package2 = new SearchByCategoryDTO.CategoryResponseDTO();
        // package2.setCategory(categoryId);
        package2.setPName("제주바당비행 패러글라이딩체험");
        package2.setPDescription("도시의 번잡함에 지친 당신에게 추천!");
        package2.setPPrice(85000);
        package2.setPDiscount(29);
        package2.setPImage("제주바당비행 패러글라이딩체험 이미지 경로");
        packages.add(package2);

        SearchByCategoryDTO.CategoryResponseDTO package3 = new SearchByCategoryDTO.CategoryResponseDTO();
        // package3.setCategory(categoryId);
        package3.setPName("투명카약(국제리더스클럽)");
        package3.setPDescription("에메랄드 빛 제주 바다 위에서 추억 만들기");
        package3.setPPrice(15000);
        package3.setPDiscount(0);
        package3.setPImage("투명카약(국제리더스클럽) 이미지 경로");
        packages.add(package3);

        SearchByCategoryDTO response = new SearchByCategoryDTO();
        response.setPackages(packages);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{cId}/{pId}")
    public ResponseEntity<SearchByPackageDTO> searchByPackage(@PathVariable String pId) {
        // 실제 로직은 데이터베이스에서 pId에 해당하는 패키지를 조회하는 로직으로 대체되어야 합니다.

        // [TODO] get data from DB
        SearchByPackageDTO response = new SearchByPackageDTO();
        // response.setCategory("Category1");
        response.setPName("새별프렌즈 동물원");
        response.setPDescription("새별오름 앞, 동물 친구들을 만나는 시간");
        response.setPRate(4.5);
        response.setPPrice(18000);
        response.setPDiscount(22);
        response.setPImage("새별프렌즈 동물원 이미지 경로");

        return ResponseEntity.ok(response);
    }
}
