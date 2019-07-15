package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DbServiceTestSuit {

    @Autowired
    DbService dbService;

    @Test
    public void testGetTaskById(){
        //GIVEN
        Task testTask = new Task();
        testTask.setTitle("task_title");
        dbService.saveTask(testTask);
        long taskId = testTask.getId();
        //WHEN
        Optional<Task> resultTask = dbService.getTaskById(taskId);
        //THEN
        Assert.assertNotEquals(0L, taskId);
        Assert.assertTrue(resultTask.isPresent() && resultTask.get().getId()==taskId && resultTask.get().getTitle().equals("task_title"));
        //CLEAN-UP
        try{
            dbService.deleteTask(taskId);
        }catch(Exception e){
            System.out.println("Clean-up process failed.");
        }
    }
}
