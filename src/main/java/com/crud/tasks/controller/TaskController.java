package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/task")
public class TaskController {

    @RequestMapping(method= RequestMethod.GET, name= "getTasks")
    public List<TaskDto> getTasks(){
        return new ArrayList<>();
    }

    @RequestMapping(method= RequestMethod.GET, name= "getTask")
    public TaskDto getTask(Long taskId){
        return new TaskDto(1L, "test titile", "test content");
    }

    @RequestMapping(method= RequestMethod.DELETE, name= "deleteTask")
    public void deleteTask(Long taskId){

    }

    @RequestMapping(method= RequestMethod.PUT, name= "updateTask")
    public TaskDto updateTask(TaskDto taskDto){
        return new TaskDto(1L, "edited test titile", "edited test content");
    }

    @RequestMapping(method= RequestMethod.POST, name= "createTask")
    public void createTask(TaskDto taskDto){};
}
