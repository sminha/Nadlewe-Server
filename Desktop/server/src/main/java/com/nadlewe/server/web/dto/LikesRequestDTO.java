package com.nadlewe.server.web.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LikesRequestDTO {
    private Long memberId;
    private boolean like;
}
