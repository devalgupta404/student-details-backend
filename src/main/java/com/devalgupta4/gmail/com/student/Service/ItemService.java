package com.devalgupta4.gmail.com.student.Service;

import com.devalgupta4.gmail.com.student.dto.CreateItemRequestDto;
import com.devalgupta4.gmail.com.student.dto.ItemResponseDto;
import com.devalgupta4.gmail.com.student.entity.Item;
import com.devalgupta4.gmail.com.student.entity.User;
import com.devalgupta4.gmail.com.student.repository.ItemRepository;
import com.devalgupta4.gmail.com.student.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    public ItemResponseDto createItem(CreateItemRequestDto request) {
        if (!userService.isValidEmailFormat(request.getOwnerEmail())) {
            throw new IllegalArgumentException("Invalid college email domain");
        }
        User owner = userRepository.findByEmail(request.getOwnerEmail())
                .orElseThrow(() -> new IllegalArgumentException("User not found with email: " + request.getOwnerEmail()));

        Item item = new Item(
                request.getItemName(),
                request.getPrice(),
                request.getCategory(),
                request.getDescription(),
                owner
        );
        item.setImageUrl(request.getImageUrl());

        Item savedItem = itemRepository.save(item);
        return convertToResponseDto(savedItem);
    }

    public List<ItemResponseDto> getAllItems() {
        return itemRepository.findAll().stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }

    public List<ItemResponseDto> getItemsByCollege(String collegeDomain) {
        return itemRepository.findByOwner_CollegeDomain(collegeDomain).stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }

    public List<ItemResponseDto> getItemsByCategory(String category) {
        return itemRepository.findByCategory(category).stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }

    public List<ItemResponseDto> getItemsByCollegeAndCategory(String collegeDomain, String category) {
        return itemRepository.findByOwner_CollegeDomainAndCategory(collegeDomain, category).stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }

    public Optional<ItemResponseDto> getItemById(Long id) {
        return itemRepository.findById(id)
                .map(this::convertToResponseDto);
    }

    public ItemResponseDto updateItem(Long id, CreateItemRequestDto request) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Item not found with id: " + id));

        // Update fields
        item.setItemName(request.getItemName());
        item.setPrice(request.getPrice());
        item.setCategory(request.getCategory());
        item.setDescription(request.getDescription());
        item.setImageUrl(request.getImageUrl());

        Item updatedItem = itemRepository.save(item);
        return convertToResponseDto(updatedItem);
    }

    public void deleteItem(Long id) {
        if (!itemRepository.existsById(id)) {
            throw new IllegalArgumentException("Item not found with id: " + id);
        }
        itemRepository.deleteById(id);
    }

    public List<ItemResponseDto> getUserItems(String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("User not found with email: " + userEmail));

        return itemRepository.findByOwner(user).stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }

    private ItemResponseDto convertToResponseDto(Item item) {
        ItemResponseDto dto = new ItemResponseDto();
        dto.setId(item.getId());
        dto.setItemName(item.getItemName());
        dto.setPrice(item.getPrice());
        dto.setCategory(item.getCategory());
        dto.setDescription(item.getDescription());
        dto.setImageUrl(item.getImageUrl());
        dto.setCreatedAt(item.getCreatedAt());
        dto.setUpdatedAt(item.getUpdatedAt());

        if (item.getOwner() != null) {
            dto.setOwnerName(item.getOwner().getName());
            dto.setOwnerEmail(item.getOwner().getEmail());
        }

        return dto;
    }
}
