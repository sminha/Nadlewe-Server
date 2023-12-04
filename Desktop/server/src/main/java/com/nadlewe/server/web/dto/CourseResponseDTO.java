package com.nadlewe.server.web.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CourseResponseDTO {
    private List<Course> courses;

    @Getter
    @Setter
    public static class Course {
        private String courseName;
        private List<Place> places;
        private int coursePrice;
        private String courseImage;
        private boolean like;

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

    @Getter
    @Setter
    public static class Place {
        private String placeName;
        private double rate;
        private String menu;
        // private String menuDetail;
        private int placePrice;
        private String placeImage;

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
