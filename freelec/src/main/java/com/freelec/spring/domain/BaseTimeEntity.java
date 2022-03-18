package com.freelec.spring.domain;

import java.time.LocalDateTime;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;

@Getter
@MappedSuperclass									// JPA Entity Ŭ�������� BaseTimeEntity�� ����� ��� �ʵ��(createdDate, modifedDate)�� Į������ �ڵ����� �ν�
@EntityListeners(AuditingEntityListener.class)		// BaseTimeEntity Ŭ������ Auditing ����� ����
public abstract class BaseTimeEntity {				// ��� Entity�� ���� Ŭ������ �Ǿ� Entity���� createdDate, modifiedDate�� �ڵ����� �����ϴ� ����
	
	@CreatedDate									// Entity�� �����Ǿ� ����� ��, �ð��� �ڵ� ����
	private LocalDateTime createdDate;
	
	@LastModifiedDate								// ��ȸ�� Entity�� ���� ������ ��, �ð��� �ڵ� ����
	private LocalDateTime modifiedDate;
}
