package com.game.api.controller;

import com.game.api.dto.request.ItemRequestDTO;
import com.game.api.dto.response.ItemResponseDTO;
import com.game.api.entity.Item;
import com.game.api.service.ItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/items")
@Tag(name = "Item", description = "Item Controller")
public class ItemController {

    private final ItemService itemService;

    @Operation(
            summary = "Get all items",
            description = "Get a list of all items",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "404", description = "Not found"),
            }
    )
    @GetMapping
    public ResponseEntity<List<ItemResponseDTO>> getAllItems() {
        List<ItemResponseDTO> items = itemService.getAllItems();
        return ResponseEntity.ok(items);
    }

    // TODO : GetItemByName

    @Operation(
            summary = "Get one item by ID",
            description = "Get an item based on its ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "404", description = "Not found"),
                    @ApiResponse(responseCode = "403", description = "Invalid request")
            })
    @GetMapping("/{id}")
    public ResponseEntity<ItemResponseDTO> getItemById(@PathVariable Long id) {
        ItemResponseDTO item = itemService.getItemById(id);
        return ResponseEntity.ok(item);
    }

    @Operation(
            summary = "Create an item",
            description = "Create a new item",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "403", description = "Invalid request")
            })
    @PostMapping
    public ResponseEntity<ItemResponseDTO> createItem(@Valid @RequestBody ItemRequestDTO requestDTO) {
        ItemResponseDTO createdItem = itemService.createItem(requestDTO);
        return ResponseEntity.ok(createdItem);
    }

    @Operation(
            summary = "Update an item by ID",
            description = "Modify an item based on its ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "404", description = "Not found"),
                    @ApiResponse(responseCode = "403", description = "Invalid request")
            })
    @PutMapping("/{id}")
    public ResponseEntity<ItemResponseDTO> updateItem(@PathVariable Long id, @Valid @RequestBody ItemRequestDTO requestDTO) {
        ItemResponseDTO updatedItem = itemService.updateItem(id, requestDTO);
        return ResponseEntity.ok(updatedItem);
    }

    @Operation(
            summary = "Delete an item by ID",
            description = "Delete an item based on its ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "404", description = "Not found"),
                    @ApiResponse(responseCode = "403", description = "Invalid request")
            })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }
}
