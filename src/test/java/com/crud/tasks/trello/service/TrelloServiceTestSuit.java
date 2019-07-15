package com.crud.tasks.trello.service;

import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCard;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.service.TrelloService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloServiceTestSuit {

    @Autowired
    TrelloService trelloService;

    @Test
    public void testCreateTrelloCard(){
        //GIVEN
        TrelloCardDto trelloCardDto = new TrelloCardDto("name", "des", "top", "5cb4d5d4952eb321ef701163");
        //WHEN
        CreatedTrelloCardDto resultCard = trelloService.createTrelloCard(trelloCardDto);
        //THEN
        Assert.assertNotNull(resultCard);
        Assert.assertEquals("name", resultCard.getName());
    }
}
