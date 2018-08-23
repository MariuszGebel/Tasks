package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.DbService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TaskControllerTest {

    @InjectMocks
    private DbService dbService;

    @Mock
    private TaskRepository repository;

    @Test
    public void getTasksTest() {
        //Given
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task(1L, "title1", "task1"));
        tasks.add(new Task(2L, "title2", "task2"));

        when(repository.findAll()).thenReturn(tasks);

        //When
        List<Task> taskList = dbService.getAllTasks();

        //Then
        assertEquals(2, taskList.size());
        assertEquals("task1", taskList.get(0).getContent());
        assertEquals("title2", taskList.get(1).getTitle());
    }

    @Test
    public void getTaskTest() {
        //Given
        Task task = new Task(1L, "title1", "task1");
        when(dbService.getTask(1L)).thenReturn(Optional.ofNullable(task));

        //When
        Optional<Task> taskById = dbService.getTask(1L);

        //Then
        assertTrue(taskById.isPresent());
        assertEquals("title1", taskById.get().getTitle());
        assertNotEquals("wrong text", taskById.get().getContent());
    }

    @Test
    public void deleteTaskTest() {
        //Given
        Task task = new Task(1L, "title", "task");

        //When
        repository.deleteById(1L);

        //Then
        assertFalse(repository.exists(1L));
    }

    @Test
    public void SaveTaskTest() {
        //Given
        Task task = new Task(1L, "title", "task");
        when(repository.save(task)).thenReturn(task);

        //When
        Task taskDb = dbService.saveTask(task);

        //Then
        assertEquals(task.getId(), taskDb.getId());
    }
}