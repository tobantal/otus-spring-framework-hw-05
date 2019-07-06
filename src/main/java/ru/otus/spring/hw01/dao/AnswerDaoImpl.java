package ru.otus.spring.hw01.dao;

import java.util.LinkedList;
import java.util.Queue;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import ru.otus.spring.hw01.domain.Task;
import ru.otus.spring.hw01.dto.Twit;

@Component
@AllArgsConstructor
public class AnswerDaoImpl implements AnswerDao {

	private final TaskDao taskDao;

	@Override
	public Queue<Twit> getAnswers() {
		Queue<Task> tasks = taskDao.getTasks();
		Queue<Twit> answers = new LinkedList<Twit>();
		Task task;
		while ((task = tasks.poll()) != null) {
			answers.add(new Twit(task.getId(), task.getAnswer()));
		}
		return answers;
	}

}
