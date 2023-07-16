package com.example.springdataautomapping.domain.models;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Objects;

import static com.example.springdataautomapping.constants.Validation.*;
import static com.example.springdataautomapping.constants.Validation.GAME_DESCRIPTION_PATTERN;

@Getter
@Setter
public class EditGameDTO {
    private Long id;
    private String title;
    private BigDecimal price;
    private BigDecimal size;
    private String trailer;
    private String imageThumbnail;
    private String description;
    private LocalDate releaseDate;

    public void updateFields(Map<String, String> updatedValues) {
        for (String key : updatedValues.keySet()) {
            if (Objects.equals(key, "title")) {
                setTitle(updatedValues.get(key));
            } else if (Objects.equals(key, "price")) {
                setPrice(new BigDecimal(updatedValues.get(key)));
            } else if (Objects.equals(key, "size")) {
                setSize(BigDecimal.valueOf(Double.parseDouble(updatedValues.get(key))));
            } else if (Objects.equals(key, "trailer")) {
                setTrailer(updatedValues.get(key));
            } else if (Objects.equals(key, "thumbnailURL")) {
                setImageThumbnail(updatedValues.get(key));
            } else if (Objects.equals(key, "description")) {
                setDescription(updatedValues.get(key));
            } else if (Objects.equals(key, "releaseDate")) {
                String dateFormat = "dd-MM-yyyy";
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(dateFormat);
                setReleaseDate(LocalDate.parse(updatedValues.get(key),dateFormatter));
            }
        }
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