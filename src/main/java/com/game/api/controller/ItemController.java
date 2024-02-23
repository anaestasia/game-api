package com.game.api.controller;

import com.game.api.entity.Item;
import com.game.api.service.ItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
@Tag(name = "Item", description = "Item Controller")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Operation(
            summary = "Get all items",
            description = "Get a list of all items",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "404", description = "Not found"),
            }
    )
    @GetMapping
    public ResponseEntity<List<Item>> getAllItems() {
        List<Item> items = itemService.getAllItems();
        return new ResponseEntity<>(items, HttpStatus.OK);
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
    public ResponseEntity<Item> getItemById(@PathVariable Long id) {
        Item item = itemService.getItemById(id);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @Operation(
            summary = "Create an item",
            description = "Create a new item",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "403", description = "Invalid request")
            })
    @PostMapping
    public ResponseEntity<Item> createItem(@RequestBody Item item) {
        Item createdItem = itemService.createItem(item);
        return new ResponseEntity<>(createdItem, HttpStatus.OK);
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
    public ResponseEntity<Item> updateItem(@RequestBody Item item) {
        Item updatedItem = itemService.updateItem(item);
        return new ResponseEntity<>(updatedItem, HttpStatus.OK);
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
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
