package ru.otus.spring.hw01.source;

import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LocaleMessageProviderImpl implements LocaleMessageProvider {

	private MessageSource messageSource;
	private LocaleInfo localeInfo;

	@Override
	public String getMessage(String code, Object[] args) throws NoSuchMessageException {
		return messageSource.getMessage(code, args, localeInfo.getLocale());
	}

}
