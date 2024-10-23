package com.example.restaurant.restaurant.Service;


import com.example.restaurant.restaurant.Model.MenuItem;
import com.example.restaurant.restaurant.Model.Order;
import com.example.restaurant.restaurant.Model.OrderItem;
import com.example.restaurant.restaurant.Repo.MenuRepo;
import com.example.restaurant.restaurant.Repo.OrderRepo;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.lang.management.MemoryNotificationInfo;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MenuService {

    @Autowired
    MenuRepo menuRepo;

    @Autowired
    OrderRepo orderRepo;

    private static final Logger log= LoggerFactory.getLogger(MenuService.class);

    @Transactional
    public MenuItem addMenuItems(MenuItem menuItem) {
        try{
        //CHECK CONSTRAINS CONDITIONS
        if(menuItem.getPrice()<0){
            log.debug("Checking for positive number {}",menuItem.getPrice());
           throw new IllegalArgumentException();
        }
        }catch (IllegalArgumentException a){
            log.error("price must be positive integer");
            throw new IllegalArgumentException("Price Positve");
        }
        return menuRepo.save(menuItem);
    }

    public List<MenuItem> getAllMenuItems() {
        log.debug("Fetching all data from Menu");
        return menuRepo.findAll();
    }

    public Boolean createOrder(List<Order> order) {
        try{
            log.debug("Check Constrains like order must be greater than one");
            if(order.isEmpty()){
                log.info("List is null,");
                throw new NullPointerException();
            }

            log.info("To filter the data without phone number and name");
            List<Order> filteredlist= order.stream()
                    .filter(order1 -> (!order1.getCustomerName().isEmpty()&&!order1.getCustomerPhone().isEmpty()))
                    .collect(Collectors.toList());

            for(Order orders:filteredlist) {
                for(OrderItem orderItem:orders.getItems()){
                    MenuItem menuItem=menuRepo.getReferenceById(orderItem.getMenuItem().getId());

                    if(menuItem.getId()==0){
                        log.error("no menu present ");
                        throw new NullPointerException();
                    }
                    log.info("Checking before Saving Data");
                    if(menuItem.getAvailableQuantity()>orderItem.getQuantity()){
                        log.debug("SAVE THE DATA");
                        orderRepo.save(orders);
                        log.info("Decrement available data ");
                        menuItem.setAvailableQuantity(menuItem.getAvailableQuantity()-orderItem.getQuantity());
                        menuRepo.save(menuItem);
                    }
                }
            }

        }catch (NullPointerException ignored){
            log.error("Null list");
            throw new NullPointerException("Not atleast one order in the list");
        }
        return true;
    }

    public Order getOrder(long id) {
        log.info("Get Order By Id");
        return orderRepo.getReferenceById(id);
    }
}
