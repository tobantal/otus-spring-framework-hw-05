package ru.otus.spring.hw01.service;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import ru.otus.spring.hw01.source.LocaleMessageProvider;

@Service
@AllArgsConstructor
public class ReportBuilderImpl implements ReportBuilder {
	
	private final LocaleMessageProvider localeMessageProvider;

	@Override
	public String buildReport(String firstName, String lastName, String grade) {
		return localeMessageProvider.getMessage("report.template", new String[]{firstName, lastName, grade});
	}

}
