package com.crud.tasks.trello.validator;

import com.crud.tasks.domain.Trello;
import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloCard;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloValidoatorTestSuits {

    @Autowired
    private TrelloValidator trelloValidator;

    @Test
    public void testValidateTrelloBoards(){
        //GIVEN
        TrelloBoard trelloBoard1 = new TrelloBoard("my_tasks", "1", new ArrayList<>());
        TrelloBoard trelloBoard2 = new TrelloBoard("test", "2", new ArrayList<>());
        List<TrelloBoard> trelloBoardList = new ArrayList<>();
        trelloBoardList.add(trelloBoard1);
        trelloBoardList.add(trelloBoard2);
        //WHEN
        List<TrelloBoard> resultList = trelloValidator.validateTrelloBoards(trelloBoardList);
        //THEN
        Assert.assertEquals(1, resultList.size() );
    }


    @Test
    public void testValidateCard(){
        //GIVEN
        TrelloCard testingTrelloCard = new TrelloCard("testName", "testDecs", "top", "5cb4d5d4952eb321ef701163");
        TrelloCard trueTrelloCard = new TrelloCard("Task 23", "Decs task 23", "top", "5cb4d5d4952eb321ef701163");
        //WHEN
        trelloValidator.validateCard(testingTrelloCard);
        trelloValidator.validateCard(trueTrelloCard);
        //THEN
        //check LOGS
    }



}
