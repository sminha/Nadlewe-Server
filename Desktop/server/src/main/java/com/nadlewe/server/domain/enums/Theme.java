package com.nadlewe.server.domain.enums;

import lombok.Getter;

@Getter
public enum Theme {
    MEAL("식사", Kind.KOREAN, Kind.WESTERN, Kind.CHINESE, Kind.JAPANESE, Kind.FASTFOOD, Kind.OTHER),
    DRINK("마시기", Kind.CAFE, Kind.BEER_SOJU, Kind.MAKGEOLLI, Kind.WINE, Kind.WHISKEY, Kind.COCKTAIL),
    ACTIVITY("활동", Kind.INDOOR, Kind.OUTDOOR, Kind.GAME_ENTERTAINMENT, Kind.HEALING, Kind.ESCAPE_ROOM, Kind.CLASS, Kind.MOVIE, Kind.EXHIBITION, Kind.BOOKSTORE);

    private final String theme;
    private final Kind[] kinds;

    Theme(String theme, Kind... kinds) {
        this.theme = theme;
        this.kinds = kinds;
    }
}