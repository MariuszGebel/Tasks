package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyList;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest1 {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DbService dbService;

    @MockBean
    private TaskMapper taskMapper;

    @Test
    public void shouldGetTasks() throws Exception {
        //Given
        TaskDto taskDto1 = new TaskDto(1L, "Title1", "Content1");
        TaskDto taskDto2 = new TaskDto(2L, "Title2", "Content2");
        List<TaskDto> taskDtoList = new ArrayList<>();
        taskDtoList.add(taskDto1);
        taskDtoList.add(taskDto2);

        when(taskMapper.mapToTaskDtoList(anyList())).thenReturn(taskDtoList);

        //When @ Then
        mockMvc.perform(get("/v1/task/getTasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].title", is("Title1")))
                .andExpect(jsonPath("$[0].content", is("Content1")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].title", is("Title2")))
                .andExpect(jsonPath("$[1].content", is("Content2")));
    }

    @Test
    public void shouldGetTask() throws Exception {
        //Given
        TaskDto taskDto1 = new TaskDto(1L, "Title1", "Content1");
        TaskDto taskDto2 = new TaskDto(2L, "Title2", "Content2");
        List<TaskDto> taskDtoList = new ArrayList<>();
        taskDtoList.add(taskDto1);
        taskDtoList.add(taskDto2);

        Task task = new Task();

        when(taskMapper.mapToTaskDto(any())).thenReturn(taskDto1);
        when(dbService.getTask(anyLong())).thenReturn(Optional.ofNullable(task));

        //When & Then
        mockMvc.perform(get("/v1/task/getTask").param("taskId", "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("Title1")))
                .andExpect(jsonPath("$.content", is("Content1")));
    }

    @Test
    public void shouldDeleteTask() throws Exception {
        Task task = new Task(1L, "Title", "Content");
        long taskId = 1L;

        dbService.deleteTask(taskId);

        verify(dbService).deleteTask(taskId);
    }

    @Test
    public void shouldUpdateTask() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(1L, "Title", "Content");
        Task task = new Task();

        when(taskMapper.mapToTask(any())).thenReturn(task);
        when(dbService.saveTask(task)).thenReturn(any());
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        //When & Then
        mockMvc.perform(put("/v1/task/updateTask")
                .content(jsonContent)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("Title")))
                .andExpect(jsonPath("$.content", is("Content")));
    }

    @Test
    public void shouldCreateTask() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(1L, "Title", "Content");
        Task task = new Task(1L, "Title", "Content");

        taskMapper.mapToTask(taskDto);
        dbService.saveTask(task);

        //When & Then
        verify(taskMapper).mapToTask(taskDto);
        verify(dbService).saveTask(task);
    }
}
