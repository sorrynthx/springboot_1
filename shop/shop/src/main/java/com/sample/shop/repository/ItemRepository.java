package com.sample.shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sample.shop.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
	
	// 이름 or 상세설명으로 아이템 찾기 
	List<Item> findByItemNmOrItemDetail(String itemNm, String itemDetail);
	
	// 특정 가격 이하 조건 찾기 
	List<Item> findByPriceLessThan(Integer price);
	
	// 특정 가격 이하 내림차순 조회 
	List<Item> findByPriceLessThanOrderByPriceDesc(Integer price);
	
	//
	@Query("select i from Item i where i.itemDetail like %:itemDetail% order by i.price desc")
	List<Item> findByItemDetail(@Param("itemDetail") String itemDetail);
}
