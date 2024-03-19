package com.game.api.controller;

import com.game.api.entity.Slot;
import com.game.api.service.SlotService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/slots")
@Tag(name = "Slot", description = "Slot Controller")
public class SlotController {

    private final SlotService slotService;

    @Operation(
            summary = "Get all slots",
            description = "Get a list of all slots",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "404", description = "Not found"),
            }
    )
    @GetMapping
    public ResponseEntity<List<Slot>> getAllSlots() {
        List<Slot> slots = slotService.getAllSlots();
        return new ResponseEntity<>(slots, HttpStatus.OK);
    }

    // TODO : GetItemByName

    @Operation(
            summary = "Get one slot by ID",
            description = "Get a slot based on its ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "404", description = "Not found"),
                    @ApiResponse(responseCode = "403", description = "Invalid request")
            })
    @GetMapping("/{id}")
    public ResponseEntity<Slot> getSlotById(@PathVariable Long id) {
        Slot slot = slotService.getSlotById(id);
        return new ResponseEntity<>(slot, HttpStatus.OK);
    }

    @Operation(
            summary = "Create a slot",
            description = "Create a new slot",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "403", description = "Invalid request")
            })
    @PostMapping
    public ResponseEntity<Slot> createSlot(@RequestBody Slot slot) {
        Slot createdSlot = slotService.createSlot(slot);
        return new ResponseEntity<>(createdSlot, HttpStatus.OK);
    }

    @Operation(
            summary = "Update a slot by ID",
            description = "Modify a slot based on its ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "404", description = "Not found"),
                    @ApiResponse(responseCode = "403", description = "Invalid request")
            })
    @PutMapping("/{id}")
    public ResponseEntity<Slot> updateSlot(@RequestBody Slot slot) {
        Slot updatedSlot = slotService.updateSlot(slot);
        return new ResponseEntity<>(updatedSlot, HttpStatus.OK);
    }

    @Operation(
            summary = "Delete a slot by ID",
            description = "Delete a slot based on its ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "404", description = "Not found"),
                    @ApiResponse(responseCode = "403", description = "Invalid request")
            })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSlot(@PathVariable Long id) {
        slotService.deleteSlot(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
