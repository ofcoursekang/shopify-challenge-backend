package com.shopify.challenge.service;

import com.shopify.challenge.exception.ResourceNotFoundException;
import com.shopify.challenge.model.Item;
import com.shopify.challenge.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;

    //get all items
    public List<Item> getAllItems(){
        return itemRepository.findAll();
    }

    //create
    public Item createItem(@RequestBody Item item){
        return itemRepository.save(item);
    }

    //get item by id
    public Optional<Item> getItemById(@PathVariable Long id){
        return itemRepository.findById(id);
    }

    //update item
    public Item updateItem(@PathVariable Long id, @RequestBody Item itemToUpdate){
        Item item = itemRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Item Not Exist With Id :" + id));
        item.setCategory(itemToUpdate.getCategory());
        item.setColor(itemToUpdate.getColor());
        item.setMaterial(itemToUpdate.getMaterial());
        item.setCounts(itemToUpdate.getCounts());
        item.setPrice(itemToUpdate.getPrice());
        item.setProvider(itemToUpdate.getProvider());

        Item updatedItem = itemRepository.save(item);
        return updatedItem;
    }

    //delete
    public Map<String, Boolean> deleteItem(@PathVariable Long id){
        Item item = itemRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Item Not Exist With Id :" + id));
        itemRepository.delete(item);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", true);
        return response;
    }

    //filter by search TEXT
    public List<Item> findItemAll(@PathVariable String searchText){
        return itemRepository.findAllItems(searchText);
    }
}
