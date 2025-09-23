package com.devalgupta4.gmail.com.student.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateItemRequestDto {
    private String itemName;
    private Double price;
    private String category;
    private String description;
    private String imageUrl;
    private String ownerEmail; // To identify the owner
}
