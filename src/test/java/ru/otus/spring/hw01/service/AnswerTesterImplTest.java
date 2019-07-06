package ru.otus.spring.hw01.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

import java.util.LinkedList;
import java.util.Queue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import ru.otus.spring.hw01.dao.AnswerDao;
import ru.otus.spring.hw01.dto.Twit;
import ru.otus.spring.hw01.exception.TwitIdMatchedException;


@SpringBootTest
@DisplayName("Класс AnswerTesterImpl должен ")
public class AnswerTesterImplTest {

	@Autowired
	private AnswerTester answerTester;

	@MockBean
	private AnswerDao answerDao;

	private Queue<Twit> userAnswers;
	private Queue<Twit> rightAnswers;

	@BeforeEach
	public void setUp() {
		rightAnswers = new LinkedList<Twit>();
		userAnswers = new LinkedList<Twit>();
	}

	@DisplayName("выдавать 1 при правильном ответе")
	@Test
	public void check_right_answer() {
		rightAnswers.add(new Twit(1L, "a"));
		userAnswers.add(new Twit(1L, "a"));
		given(answerDao.getAnswers()).willReturn(rightAnswers);
		assertEquals("1", answerTester.apply(userAnswers));
	}

	@DisplayName("выдавать 1 при правильном ответе без учета регистра")
	@Test
	public void check_ignore_case() {
		rightAnswers.add(new Twit(1L, "a"));
		userAnswers.add(new Twit(1L, "A"));
		given(answerDao.getAnswers()).willReturn(rightAnswers);
		assertEquals("1", answerTester.apply(userAnswers));
	}

	@DisplayName("выдавать 0 при неправильном ответе")
	@Test
	public void check_wrong_answer() {
		rightAnswers.add(new Twit(1L, "a"));
		userAnswers.add(new Twit(1L, "x"));
		given(answerDao.getAnswers()).willReturn(rightAnswers);
		assertEquals("0", answerTester.apply(userAnswers));
	}

	@DisplayName("выбрасывать исключение TwitIdMatchedException при не равных id")
	@Test
	public void check_not_equals_id() {
		rightAnswers.add(new Twit(1L, "a"));
		userAnswers.add(new Twit(2L, "a"));
		given(answerDao.getAnswers()).willReturn(rightAnswers);
		assertThrows(TwitIdMatchedException.class, () -> {
			answerTester.apply(userAnswers);
		});
	}

	@DisplayName("правиль считать тоговую оценку")
	@Test
	public void check_total_count() {
		rightAnswers.add(new Twit(1L, "a"));
		rightAnswers.add(new Twit(2L, "b"));
		rightAnswers.add(new Twit(3L, "c"));
		rightAnswers.add(new Twit(4L, "d"));
		rightAnswers.add(new Twit(5L, "e"));

		userAnswers.add(new Twit(1L, "a"));
		userAnswers.add(new Twit(2L, "x"));
		userAnswers.add(new Twit(3L, "c"));
		userAnswers.add(new Twit(4L, "y"));
		userAnswers.add(new Twit(5L, "e"));
		given(answerDao.getAnswers()).willReturn(rightAnswers);
		assertEquals("3", answerTester.apply(userAnswers));
	}

}
