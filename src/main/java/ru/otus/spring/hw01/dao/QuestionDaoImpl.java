package ru.otus.spring.hw01.dao;

import java.util.LinkedList;
import java.util.Queue;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import ru.otus.spring.hw01.domain.Task;
import ru.otus.spring.hw01.dto.Twit;

@Component
@AllArgsConstructor
public class QuestionDaoImpl implements QuestionDao {

	private final TaskDao taskDao;

	@Override
	public Queue<Twit> getQuestions() {
		Queue<Task> tasks = taskDao.getTasks();
		Queue<Twit> questions = new LinkedList<Twit>();
		Task task;
		while ((task = tasks.poll()) != null) {
			questions.add(new Twit(task.getId(), task.getQuestion()));
		}
		return questions;
	}

}
