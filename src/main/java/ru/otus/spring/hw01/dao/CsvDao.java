package ru.otus.spring.hw01.dao;

import java.io.InputStream;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import lombok.AllArgsConstructor;
import ru.otus.spring.hw01.domain.Task;
import ru.otus.spring.hw01.exception.ColumnNumberException;
import ru.otus.spring.hw01.exception.CsvFileNotFoundException;
import ru.otus.spring.hw01.source.LocaleInfo;

@Component
@AllArgsConstructor
public class CsvDao implements TaskDao {

	private final LocaleInfo localeInfo;

	@Override
	public Queue<Task> getTasks() {
		InputStream inputStream = getCsvFileInputStreamOrThrow(getCsvPath());

		Queue<Task> queue = new LinkedList<>();

		try (Scanner scanner = new Scanner(inputStream);) {
			while (scanner.hasNextLine()) {
				Task task = taskParseOrThrow(scanner.nextLine());
				queue.add(task);
			}
		}
		return queue;
	}
	
	private String getCsvPath() {
		return String.format("tasks_%s_%s.csv", localeInfo.getLocale().getLanguage(), localeInfo.getLocale().getCountry());
	}

	private InputStream getCsvFileInputStreamOrThrow(String csvPath) {
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(csvPath);
		if (inputStream == null) {
			throw new CsvFileNotFoundException(csvPath + " not found");
		}
		return inputStream;
	}

	private Task taskParseOrThrow(String line) {
		String[] args = line.split(";");
		if (args.length != 3) {
			throw new ColumnNumberException();
		}
		if (StringUtils.isEmpty(args[0]) || StringUtils.isEmpty(args[1]) || StringUtils.isEmpty(args[2])) {
			throw new NullPointerException();
		}
		return new Task(Long.parseLong(args[0]), args[1], args[2]);
	}

}
