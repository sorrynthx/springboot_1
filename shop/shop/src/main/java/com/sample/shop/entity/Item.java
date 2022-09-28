package com.sample.shop.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.sample.shop.enums.ItemSellStatus;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="item")
@Setter
@Getter
@ToString
public class Item {
	
	// 상품 아이
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="item_id")
	private Long id;
	
	// 상품명
	@Column(nullable = false, length=50)
	private String itemNm;
	
	// 가격 
	@Column(name="price", nullable = false)
	private int price;
	
	// 재고수량 
	@Column(nullable = false)
	private int stockNumber;
	
	// 제품 상세 설명 
	@Lob
	@Column(nullable = false)
	private String itemDetail;
	
	// 상품 판매 상태 ENUM
	@Enumerated(EnumType.STRING)
	private ItemSellStatus itemSellStatus;
	
	// 등록시간 
	private LocalDateTime regTime;
	
	// 수정시간
	private LocalDateTime updateTime;
}
