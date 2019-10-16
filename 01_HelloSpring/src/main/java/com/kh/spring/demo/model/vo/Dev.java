package com.kh.spring.demo.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//객체 생성시에 기본적으로 생성해야하는 setter/getter
//생성자, toString(), hash(), equals를
//알아서 생성해주는 라이브러리 lombok을 사용한다.

// @Setter //setter생성해주는것
// @Getter //getter생성해주는것
// @NoArgsConstructor //기본생성자 생성해주는 것
// @AllArgsConstructor //모든 변수를 가지는 생성자를 생성
// @EqualsAndHashCode //eqauls()와 hash() 오버라이딩 생성
// @ToString //toString() 생성
@AllArgsConstructor
@NoArgsConstructor
@Data //모든 내용을 포함하는 vo객체 생성
public class Dev {
	
	private String devName;
	private int devAge;
	private String devEmail;
	private String devGender;
	private String[] devLang;
	
}
