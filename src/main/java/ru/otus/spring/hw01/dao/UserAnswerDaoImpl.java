package ru.otus.spring.hw01.dao;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import ru.otus.spring.hw01.dto.Twit;
import ru.otus.spring.hw01.source.LocaleMessageProvider;

@Service
@AllArgsConstructor
public class UserAnswerDaoImpl implements UserAnswerDao {

	private static final Scanner SCANNER = new Scanner(System.in);

	private final QuestionDao questionDao;
	private final LocaleMessageProvider localeMessageProvider;

	@Override
	public Queue<Twit> getUserAnswers() {
		Queue<Twit> answers = new LinkedList<Twit>();
		Queue<Twit> questions = new LinkedList<Twit>();
		Twit firstNameTwit = new Twit(-2L, localeMessageProvider.getMessage("question.firstname", null));
		Twit lastNameTwit = new Twit(-1L, localeMessageProvider.getMessage("question.lastname", null));
		questions.add(firstNameTwit);
		questions.add(lastNameTwit);
		questions.addAll(questionDao.getQuestions());
		questions.forEach(questionTwit -> {
			System.out.println(questionTwit.getText());
			String answerText = SCANNER.next();
			answers.add(new Twit(questionTwit.getId(), answerText));
		});
		return answers;
	}

}
