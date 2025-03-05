package com.dev5.backenddev5.Repository;

import com.dev5.backenddev5.Model.Item;
import com.dev5.backenddev5.Model.ItemDeOrden;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemDeOrdenRepository extends JpaRepository<ItemDeOrden, Integer> {
}
