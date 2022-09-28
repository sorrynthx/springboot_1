package com.sample.shop.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.sample.shop.entity.Item;
import com.sample.shop.enums.ItemSellStatus;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class ItemRepositoryTest {

	@Autowired
	ItemRepository itemRepository;
	
	//@Test
	//@DisplayName("상품 저장 테스트 ")
	public void createItemTest() {
		
		for (int i=1; i<=10; i++) {
			Item item = new Item();
			item.setItemNm("테스트 상품" + i);
			item.setPrice(10000 + i);
			item.setItemDetail("테스트 상품" + i + " 상세 설명 ");
			item.setItemSellStatus(ItemSellStatus.SELL);
			item.setStockNumber(100);
			item.setRegTime(LocalDateTime.now());
			item.setUpdateTime(LocalDateTime.now());
		
			Item savedItem = itemRepository.save(item);
			System.out.println("===> " + savedItem.toString());
		}
	}
	
	//@Test
	//@DisplayName("상품 조회 테스트 ")
	public void findByItemNmTest() {
		this.createItemTest();
		
		List<Item> itemList = itemRepository.findByItemNmOrItemDetail("테스트 상품1", "테스트 상품5 상세 설명 ");
		for(Item item : itemList) {
			System.out.println(item.toString());
		}
	}
	
	//@Test
	//@DisplayName("가격 LessThan 테스트")
	public void findByLessThanTest() {
		this.createItemTest();
		List<Item> itemList = itemRepository.findByPriceLessThan(10005);
		for(Item item : itemList) {
			System.out.println(item.toString());
		}
	}
	
	//@Test
	//@DisplayName("가격 LessThan 테스트 내림차순")
	public void findByLessThanOrderByPriceDescTest() {
		this.createItemTest();
		List<Item> itemList = itemRepository.findByPriceLessThanOrderByPriceDesc(10005);
		for(Item item : itemList) {
			System.out.println(item.toString());
		}
	}
	
	@Test
	@DisplayName("@Query를 이용한 상품 조회 테스트")
	public void queryAnnotationTest() {
		this.createItemTest();
		List<Item> itemList = itemRepository.findByItemDetail("테스트 상품");
		for(Item item : itemList) {
			System.out.println(item.toString());
		}
	}
	
}
