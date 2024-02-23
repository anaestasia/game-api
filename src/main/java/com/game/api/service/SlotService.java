package com.game.api.service;

import com.game.api.entity.Slot;
import com.game.api.repository.SlotRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SlotService {

    @Autowired
    private SlotRepository slotRepository;

    public List<Slot> getAllSlots() {
        return slotRepository.findAll();
    }

    public Slot getSlotById(Long id) {
        Optional<Slot> optionalSlot = slotRepository.findById(id);
        if(optionalSlot.isPresent()) {
            return optionalSlot.get();
        } else {
            throw new EntityNotFoundException("Slot not found with id: "+ id);
        }
    }

    public Slot createSlot(Slot slot) {
        return slotRepository.save(slot);
    }

    public Slot updateSlot(Slot slot) {
        // TODO : validation
        return slotRepository.save(slot);
    }

    public void deleteSlot(Long id) {
        if(slotRepository.existsById(id)) {
            slotRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Slot not found with id : "+ id);
        }
    }
}
