package com.github.parkhana.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.github.parkhana.common.SocialType;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class User {
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String name;
	@Column
	private String password;
	@Column
	private String email;
	@Column
	private String principal;
	
	@Column
	@Enumerated(EnumType.STRING)
	private SocialType socialType;
	
	@Builder
	public User(Long id, String name, String password, String email, String principal, SocialType socialType) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.email = email;
		this.principal = principal;
		this.socialType = socialType;
	}
}
