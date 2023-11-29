package com.nadlewe.server.web.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/*{"TagKeys":["식사","식사","카페","활동"],"selectedCategory":["와인","칵테일","방탈출"],"priceRange":[70000,130000]}*/

@Getter
@Setter
public class CourseRequestDTO {
    private int minPrice;
    private int maxPrice;
    private List<String> themes;
    private List<String> kinds;

    public void setPriceRange(List<Integer> priceRange) {
        if (priceRange != null && priceRange.size() == 2) {
            this.minPrice = priceRange.get(0);
            this.maxPrice = priceRange.get(1);
        }
    }

    public void setTagKeys(List<String> tagKeys) {
        this.themes = tagKeys;
    }

    public void setSelectedCategory(List<String> selectedCategory) {
        this.kinds = selectedCategory;
    }

    // for postman test
    @Override
    public String toString() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return super.toString();
        }
    }
}