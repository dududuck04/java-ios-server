package com.cbm.saekalpi.app.keyword.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

/**
 * 유저 회원가입 정보
 *
 * @author 김경민
 *
 */
@Getter
@Setter
@ToString
public class KeywordPostDto {

	/**
	 * 유저의 이메일 주소.
	 * 이메일 주소는 유저의 고유 식별자로 사용되며, 로그인 및 회원가입 시 필요합니다.
	 */
	private LocalDate date;

	private List keywordList;

}
