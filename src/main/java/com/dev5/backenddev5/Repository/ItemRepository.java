package com.dev5.backenddev5.Repository;

import com.dev5.backenddev5.Model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Integer> {
}
