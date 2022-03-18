package com.freelec.spring.domain.posts;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.freelec.spring.domain.BaseTimeEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter					// Getter 메소드 생성
@NoArgsConstructor		// 기본 생성자 자동 추가
@Entity					// 테이블과 링크될 클래스 나타냄 (SalesManager.java -> sales_manager table 매칭)
public class Posts extends BaseTimeEntity {					// 저장시간, 수정시간 자동으로 넣어주는 BaseTimeEntity 상속
	
	@Id														// 해당 테이블의 PK필드
	@GeneratedValue(strategy = GenerationType.IDENTITY)		// PK 생성 규칙 의미 (GenerationType.IDENTITY = auto_increment) 
	private Long id;
	
	@Column(length = 500, nullable = false)					// 선언 안해도 클래스의 필드는 컬럼이 됨(옵션 변경 시, 사용 ex. 타입, 사이즈...)
	private String title;

	@Column(columnDefinition = "TEXT", nullable = false)
	private String content;
	
	private String author;
	
	@Builder												// 해당 클래스의 빌더 패턴 클래스 생성
	public Posts(String title, String content, String author) {
		this.title = title;
		this.content = content;
		this.author = author;
	}

	public void update(String title, String content) {
		this.title = title;
		this.content = content;
	}
	
	
}
