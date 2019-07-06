package ru.otus.spring.hw01.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;

import java.util.LinkedList;
import java.util.Queue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import ru.otus.spring.hw01.dao.AnswerDao;
import ru.otus.spring.hw01.dao.TaskDao;
import ru.otus.spring.hw01.domain.Task;
import ru.otus.spring.hw01.dto.Twit;

@SpringBootTest
@DisplayName("Класс AnswersSupplier должен ")
public class AnswersSupplierTest {
	
	@Autowired
	private AnswerDao answerDao;
	
	@MockBean
	private TaskDao taskDao;
	
	@DisplayName("выводить правильные ответы")
	@Test
	public void shouldGetRightAnswers() {
		Queue<Task> tasks = new LinkedList<>();
		tasks.add(new Task(1L, "question1", "ans1"));
		given(taskDao.getTasks()).willReturn(tasks);
		
		Twit ans = answerDao.getAnswers().poll();
		assertTrue(ans.getId().equals(1L));
		assertTrue(ans.getText().equals("ans1"));
	}
}
