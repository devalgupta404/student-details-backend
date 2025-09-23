package com.devalgupta4.gmail.com.student.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class ItemResponseDto {
    private Long id;
    private String itemName;
    private Double price;
    private String category;
    private String description;
    private String imageUrl;
    private String ownerName;
    private String ownerEmail;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
