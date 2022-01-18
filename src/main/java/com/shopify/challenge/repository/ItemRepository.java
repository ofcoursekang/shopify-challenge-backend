package com.shopify.challenge.repository;

import com.shopify.challenge.model.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query("SELECT i FROM Item i WHERE CONCAT(i.color, i.provider, i.category, i.material) LIKE %?1%")
    List<Item> findAllItems(@Param("searchText") String searchText);
}


