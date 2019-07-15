package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.domain.TrelloList;
import com.crud.tasks.repository.TaskRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskMapperTestSuit {

    @Autowired
    TaskMapper taskMapper;

    @Autowired
    TaskRepository taskRepository;

    Task task;
    TaskDto taskDto;
    List<Task> tasksList;

    @Before
    public void before(){
        task= new Task(13, "name1", "content1");
        taskDto = new TaskDto(15, "name2", "content2");
        tasksList = new ArrayList<>();
        tasksList.add(task);
    }

    @After
    public void after(){
        //CLEAN-UP
        taskRepository.deleteById(task.getId());
    }

    @Test
    public void testMapToTaskDto(){
        //GIVEN
        //WHEN
        TaskDto result = taskMapper.mapToTaskDto(task);
        //THEN
        Assert.assertNotNull(result);
        Assert.assertEquals("name1", result.getTitle());
        Assert.assertEquals("content1", result.getContent());
        Assert.assertEquals(13, result.getId());
    }

    @Test
    public void testMapToTask(){
        //GIVEN
        //WHEN
        Task result = taskMapper.mapToTask(taskDto);
        //THEN
        Assert.assertNotNull(result);
        Assert.assertEquals("name2", result.getTitle());
        Assert.assertEquals("content2", result.getContent());
        Assert.assertEquals(15, result.getId());
        //CLEAN-UP
        taskRepository.deleteById(result.getId());
    }

    @Test
    public void testMapToTaskDtoList(){
        //GIVEN
        //WHEN
        List<TaskDto> resultList = taskMapper.mapToTaskDtoList(tasksList);
        //THEN
        Assert.assertEquals(1, resultList.size());
        Assert.assertEquals("name1", resultList.get(0).getTitle());
        Assert.assertEquals("content1", resultList.get(0).getContent());
        Assert.assertEquals(13, resultList.get(0).getId());
    }
}
