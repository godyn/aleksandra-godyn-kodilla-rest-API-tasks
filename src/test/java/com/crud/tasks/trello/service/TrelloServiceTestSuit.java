package com.crud.tasks.trello.service;

import com.crud.tasks.domain.*;
import com.crud.tasks.service.TrelloService;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

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
