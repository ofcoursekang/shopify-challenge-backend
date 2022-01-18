package com.shopify.challenge.controller;

import com.shopify.challenge.exception.ResourceNotFoundException;
import com.shopify.challenge.model.Item;
import com.shopify.challenge.repository.ItemRepository;
import com.shopify.challenge.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class ItemController {

    @Autowired
    private ItemService itemService;

    //get all items
    @GetMapping("/items")
    public List<Item> getAllItems(){
        return itemService.getAllItems();
    }

    //creat items rest api
    @PostMapping("/items")
    public Item createItem(@RequestBody Item item){
        return itemService.createItem(item);
    }

    //get items by id rest api
    @GetMapping("/items/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable Long id){
        return new ResponseEntity<>(itemService.getItemById(id).get(), HttpStatus.OK);
    }

    //update items rest api
    @PutMapping("/items/{id}")
    public ResponseEntity<Item> updateItem(@PathVariable Long id, @RequestBody Item itemToUpdate){
        return new ResponseEntity<>(itemService.updateItem(id, itemToUpdate), HttpStatus.OK);
    }

    //delete item rest api
    @DeleteMapping("/items/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteItem(@PathVariable Long id){
        return new ResponseEntity<>(itemService.deleteItem(id), HttpStatus.OK);
    }

    //filter items by search key word
    @GetMapping("/items/search/{searchText}")
    public ResponseEntity<List<Item>> findItemAll(@PathVariable String searchText){

        return new ResponseEntity<>(itemService.findItemAll(searchText), HttpStatus.OK);
    }



}
