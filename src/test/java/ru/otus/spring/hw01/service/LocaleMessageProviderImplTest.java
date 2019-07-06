package ru.otus.spring.hw01.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

import java.util.Locale;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.MessageSource;

import ru.otus.spring.hw01.source.LocaleInfo;
import ru.otus.spring.hw01.source.LocaleMessageProvider;

@SpringBootTest
@DisplayName("Класс LocaleMessageProviderImpl должен ")
public class LocaleMessageProviderImplTest {

	@Autowired
	private LocaleMessageProvider localeMessageProvider;
	
	@MockBean
	private MessageSource messageSource;
	
	@MockBean
	private LocaleInfo localeInfo;
	
	@DisplayName("правильно получать сообщение, учитывая локаль")
	@Test
	public void checkGetMessageFromLocaleMessageProviderImpl() {
		Locale locale = new Locale("en", "US");
		String code = "qwery";
		String[] args = {"a", "b", "c"};
		given(localeInfo.getLocale()).willReturn(locale);
		given(messageSource.getMessage(code, args, locale)).willReturn("xyz");
		assertEquals("xyz", localeMessageProvider.getMessage(code, args));
	}

}
