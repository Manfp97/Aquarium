package com.example.aquarium.Service;

import com.example.aquarium.Entities.Shop;
import com.example.aquarium.Repositories.ShopRepository;
import org.springframework.stereotype.Service;

@Service
public class ShopServi extends AbstractBusinessService<Shop, Integer, ShopRepository> {
    protected ShopServi(ShopRepository shopRepository) {super (shopRepository);}
}
