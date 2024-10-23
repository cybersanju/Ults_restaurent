package com.example.restaurant.restaurant.Controller;


import com.example.restaurant.restaurant.Model.MenuItem;
import com.example.restaurant.restaurant.Model.Order;
import com.example.restaurant.restaurant.Service.MenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MenuController {

    private static final Logger log= LoggerFactory.getLogger(MenuController.class);

    @Autowired
    MenuService menuService;

    @PostMapping("/menuItems")
    public ResponseEntity<?> addMenuItems(@RequestBody  MenuItem menuItem){
        MenuItem menuItems=menuService.addMenuItems(menuItem);
        return new  ResponseEntity<>(menuItems, HttpStatus.CREATED);
    }

    @GetMapping("/getAllMenuItems")
    public ResponseEntity<?> getMenuItems(){
        List<MenuItem> allMenuItems=menuService.getAllMenuItems();
        return new ResponseEntity<>(allMenuItems,HttpStatus.FOUND);
    }

    @PostMapping("/order")
    public ResponseEntity<?> createOrder(@RequestBody List<Order> order){
        Boolean s=menuService.createOrder(order);
        return new ResponseEntity<>(s,HttpStatus.CREATED);
    }
    @GetMapping("/order/{id}")
    public ResponseEntity<?> getOder(@PathVariable long id){
        Order orderById=menuService.getOrder(id);
        return new ResponseEntity<>(orderById,HttpStatus.FOUND);
    }

}
