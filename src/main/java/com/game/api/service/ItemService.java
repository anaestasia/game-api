package com.game.api.service;

import com.game.api.dto.request.ItemRequestDTO;
import com.game.api.dto.response.ItemResponseDTO;
import com.game.api.entity.Item;
import com.game.api.exception.NameAlreadyTakenException;
import com.game.api.exception.NoRessourcesFoundException;
import com.game.api.mapper.ItemMapper;
import com.game.api.repository.ItemRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Autowired
    private ItemMapper itemMapper;

    /* -- GET ALL -- */
    public List<ItemResponseDTO> getAllItems() {
        List<Item> items = itemRepository.findAll();
        if(items.isEmpty()) {
            throw new NoRessourcesFoundException("No items found");
        } else {
            return items.stream()
                    .map(itemMapper::itemToItemResponseDTO)
                    .collect(Collectors.toList());
        }
    }

    /* -- GET BY ID -- */
    public ItemResponseDTO getItemById(Long id) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Item not found with id : " + id));
        return itemMapper.itemToItemResponseDTO(item);
    }

    /* -- CREATE -- */
    public ItemResponseDTO createItem(ItemRequestDTO requestDTO) {
        // Check if item's name already exists
        if (itemRepository.findByName(requestDTO.getName()).isPresent()) {
            throw new NameAlreadyTakenException("This item already exist. Please, choose another name.");
        }
        return itemMapper.itemToItemResponseDTO(
                itemRepository.save(Item.builder().name(requestDTO.getName()).build())
        );
    }

    /* -- UPDATE -- */
    public ItemResponseDTO updateItem(Long id, ItemRequestDTO requestDTO) {

        // Get the character if exists
        Item updatedItem = itemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Item not found with id : " + id));

        // Update fields // TODO : If something change only
        updatedItem.setName(requestDTO.getName());
        updatedItem.setDescription(requestDTO.getDescription());
        updatedItem.setType(requestDTO.getType());
        updatedItem.setBonus(requestDTO.getBonus());
        updatedItem.setStat(requestDTO.getStat());

        // Save in DB
        itemRepository.save(updatedItem);

        // Return ResponseDTO
        return itemMapper.itemToItemResponseDTO(updatedItem);
    }

    /* -- DELETE -- */
    public void deleteItem(Long id) {
        Item item =  itemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Item not found with id : "+ id));
        itemRepository.delete(item);
    }
}
