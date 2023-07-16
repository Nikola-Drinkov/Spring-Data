package com.example.springdataautomapping.domain.models;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

import static com.example.springdataautomapping.constants.Validation.*;

@Getter
@Setter
public class AddGameDTO {
    private String title;
    private BigDecimal price;
    private BigDecimal size;
    private String trailer;
    private String imageThumbnail;
    private String description;
    private LocalDate releaseDate;

    public AddGameDTO(String title, BigDecimal price, BigDecimal size, String trailer, String imageThumbnail, String description, LocalDate releaseDate) {
        this.title = title;
        this.price = price;
        this.size = size;
        this.trailer = trailer;
        this.imageThumbnail = imageThumbnail;
        this.description = description;
        this.releaseDate = releaseDate;
        validate();
    }
    private void validate(){
        if(!title.matches(GAME_TITLE_PATTERN)) throw new IllegalStateException("Invalid title!");
        if(price.compareTo(BigDecimal.ZERO)<0) throw new IllegalStateException("Price is negative!");
        if(size.compareTo(BigDecimal.ZERO)<0) throw new IllegalStateException("Size is negative");
        if(!trailer.matches(GAME_TRAILER_PATTERN)||trailer.length()!=11) throw new IllegalStateException("Invalid trailer URL!");
        if(!imageThumbnail.matches(GAME_THUMBNAIL_PATTERN)) throw new IllegalStateException("Invalid thumbnail URL!");
        if(!description.matches(GAME_DESCRIPTION_PATTERN)) throw new IllegalStateException("Invalid description!");
    }
}
