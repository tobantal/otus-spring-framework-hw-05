package ru.otus.spring.hw01.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Getter
public class Task {
	
	private Long id;
	private String question;
	private String answer;
	
}
