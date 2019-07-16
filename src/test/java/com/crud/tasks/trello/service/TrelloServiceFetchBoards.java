package com.crud.tasks.trello.service;

import com.crud.tasks.domain.BadgesDto;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.service.TrelloService;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class TrelloServiceFetchBoards {

    @InjectMocks
    TrelloService trelloService;

    @Mock
    TrelloClient trelloClient;

    @Test
    public void testFetchTrelloBoards(){
        //GIVEN
        List<TrelloBoardDto> trelloBoards = new ArrayList<>();
        when(trelloClient.getTrelloBoards()).thenReturn(trelloBoards);
        //WHEN
        List<TrelloBoardDto> result = trelloService.fetchTrelloBoards();
        //THEN
        Assert.assertNotNull(result);
        Assert.assertEquals(0, result.size());

    }
}
