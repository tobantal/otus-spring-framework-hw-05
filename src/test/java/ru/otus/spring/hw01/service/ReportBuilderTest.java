package ru.otus.spring.hw01.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import ru.otus.spring.hw01.source.LocaleMessageProvider;

@SpringBootTest
@DisplayName("Класс ReportBuilder должен ")
public class ReportBuilderTest {
	
	@MockBean
    private LocaleMessageProvider fakeLocaleMessageProvider;
	
	@Autowired
	private ReportBuilderImpl reportBuilder;
	

	@DisplayName("правильно выдавать правильный отчет")
	@Test
	public void shouldBuildReport() {
		String[] args = new String[]{"a", "b", "c"};
		given(fakeLocaleMessageProvider.getMessage("report.template", args)).willReturn("report");
		assertEquals("report", reportBuilder.buildReport(args[0], args[1], args[2]));
	}

}
