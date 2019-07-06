package ru.otus.spring.hw01.service;

import java.util.Queue;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import ru.otus.spring.hw01.dao.AnswerDao;
import ru.otus.spring.hw01.dto.Twit;
import ru.otus.spring.hw01.exception.TwitIdMatchedException;

@Service
@AllArgsConstructor
public class AnswerTesterImpl implements AnswerTester {

	private final AnswerDao answerDao;

	@Override
	public String apply(Queue<Twit> userAnswers) {
		Queue<Twit> rightAnswers = answerDao.getAnswers();
		int count = 0;
		Twit rightAns;
		Twit userAns;
		while ((rightAns = rightAnswers.poll()) != null && (userAns = userAnswers.poll()) != null) {
			if(!rightAns.getId().equals(userAns.getId())) {
				throw new TwitIdMatchedException();
			}
			if (rightAns.getText().equalsIgnoreCase(userAns.getText())) {
				count++;
			}
		}
		return Integer.toString(count);
	}

}
