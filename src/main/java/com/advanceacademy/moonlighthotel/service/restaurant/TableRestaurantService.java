package com.advanceacademy.moonlighthotel.service.restaurant;

import com.advanceacademy.moonlighthotel.entity.restaurant.TableRestaurant;
import com.advanceacademy.moonlighthotel.entity.restaurant.RestaurantZone;

import java.util.List;

public interface TableRestaurantService {

        TableRestaurant createTable(TableRestaurant table);
        TableRestaurant getTableById(Long id);
        List<TableRestaurant> getTablesByZone(RestaurantZone zone);
        List<TableRestaurant> getSmokingTables(Boolean isSmoking);
        List<TableRestaurant> getTablesBySeats(Integer seats);

}