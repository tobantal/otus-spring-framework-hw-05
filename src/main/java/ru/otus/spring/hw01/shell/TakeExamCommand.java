package ru.otus.spring.hw01.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import lombok.RequiredArgsConstructor;
import ru.otus.spring.hw01.service.Examinator;

@ShellComponent
@RequiredArgsConstructor
public class TakeExamCommand {
	
	private final Examinator examinator;
	
	@ShellMethod("Take an Exam")
	public void exam() {
		examinator.takeAnExam();
	}

}
