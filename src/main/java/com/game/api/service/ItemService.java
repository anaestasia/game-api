package com.game.api.service;

import com.game.api.entity.Item;
import com.game.api.repository.ItemRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public Item getItemById(Long id) {
        Optional<Item> optionalItem = itemRepository.findById(id);
        if(optionalItem.isPresent()) {
            return optionalItem.get();
        } else {
            throw new EntityNotFoundException("Item not found with id: "+ id);
        }
    }

    public Item createItem(Item item) {
        return itemRepository.save(item);
    }

    public Item updateItem(Item item) {
        // TODO : validation
        return itemRepository.save(item);
    }

    public void deleteItem(Long id) {
        if(itemRepository.existsById(id)) {
            itemRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Item not found with id : "+ id);
        }
    }
}
