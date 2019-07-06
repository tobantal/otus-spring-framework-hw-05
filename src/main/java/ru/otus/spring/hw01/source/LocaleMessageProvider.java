package ru.otus.spring.hw01.source;

import org.springframework.lang.Nullable;

public interface LocaleMessageProvider {
	
	String getMessage(String code, @Nullable Object[] args);

}
