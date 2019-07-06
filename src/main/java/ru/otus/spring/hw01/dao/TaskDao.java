package ru.otus.spring.hw01.dao;

import java.util.Queue;

import ru.otus.spring.hw01.domain.Task;

public interface TaskDao {
	
	Queue<Task> getTasks();

}
