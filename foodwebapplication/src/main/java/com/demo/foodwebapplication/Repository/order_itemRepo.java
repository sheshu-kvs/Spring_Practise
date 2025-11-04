package com.demo.foodwebapplication.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.foodwebapplication.model.order_item;
import com.demo.foodwebapplication.model.orders;

public interface order_itemRepo  extends JpaRepository<order_item, Long>{
        // Find all items belonging to a specific order
    List<order_item> findByOrder(orders order);
}
