package ru.otus.spring.hw01.dao;

import java.util.Queue;

import ru.otus.spring.hw01.dto.Twit;

public interface UserAnswerDao {

	Queue<Twit> getUserAnswers();
}
