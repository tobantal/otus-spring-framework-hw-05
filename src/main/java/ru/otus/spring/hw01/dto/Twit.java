package ru.otus.spring.hw01.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Getter
public class Twit {

	private final Long id;
	private final String text;

}
