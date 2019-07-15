package com.crud.tasks.trello.facade;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloList;
import com.crud.tasks.domain.TrelloListDto;
import com.crud.tasks.mapper.TrelloMapper;
import com.crud.tasks.service.TrelloService;
import com.crud.tasks.trello.validator.TrelloValidator;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrelloFacadeTest {

    @InjectMocks
    private TrelloFacade trelloFacade;

    @Mock
    private TrelloService trelloService;

    @Mock
    private TrelloValidator trelloValidator;

    @Mock
    private TrelloMapper trelloMapper;

    @Test
    public void shouldFetchEmptyList(){
        //GIVEN
        List<TrelloListDto> trelloListDtos = new ArrayList<>();
        trelloListDtos.add(new TrelloListDto("1", "test_list", false));

        List<TrelloBoardDto> trelloBoardDtos = new ArrayList<>();
        trelloBoardDtos.add(new TrelloBoardDto("1", "test", trelloListDtos));

        List<TrelloList> mappedTrelloLists = new ArrayList<>();
        mappedTrelloLists.add(new TrelloList("1", "test_list", false));

        List<TrelloBoard> mappedTrelloBoards = new ArrayList<>();
        mappedTrelloBoards.add(new TrelloBoard("1", "test", mappedTrelloLists));

        when(trelloService.fetchTrelloBoards()).thenReturn(trelloBoardDtos);
        when(trelloMapper.mapToBoards(trelloBoardDtos)).thenReturn(mappedTrelloBoards);
        when(trelloMapper.mapToBoardsDto(anyList())).thenReturn(new ArrayList<>());
        when(trelloValidator.validateTrelloBoards(mappedTrelloBoards)).thenReturn(new ArrayList<>());
        //WHEN
        List<TrelloBoardDto> resultTrelloBoardDtos = trelloFacade.fetchTrelloBoards();
        //THEN
        Assert.assertNotNull(resultTrelloBoardDtos);
        Assert.assertEquals(0, resultTrelloBoardDtos.size());
    }

    @Test
    public void shouldFetchTrelloBoards(){
        //GIVEN
        List<TrelloListDto> trelloListDtos = new ArrayList<>();
        trelloListDtos.add(new TrelloListDto("my_list", "1", false));

        List<TrelloBoardDto> trelloBoardDtos = new ArrayList<>();
        trelloBoardDtos.add(new TrelloBoardDto("my_tasks", "1", trelloListDtos));

        List<TrelloList> mappedTrelloLists = new ArrayList<>();
        mappedTrelloLists.add(new TrelloList("my_list", "1", false));

        List<TrelloBoard> mappedTrelloBoards = new ArrayList<>();
        mappedTrelloBoards.add(new TrelloBoard("my_tasks", "1", mappedTrelloLists));

        when(trelloService.fetchTrelloBoards()).thenReturn(trelloBoardDtos);
        when(trelloMapper.mapToBoards(trelloBoardDtos)).thenReturn(mappedTrelloBoards);
        when(trelloMapper.mapToBoardsDto(anyList())).thenReturn(trelloBoardDtos);
        when(trelloValidator.validateTrelloBoards(mappedTrelloBoards)).thenReturn(mappedTrelloBoards);
        //WHEN
        List<TrelloBoardDto> resultTrelloBoardDtos = trelloFacade.fetchTrelloBoards();
        //THEN
        Assert.assertNotNull(resultTrelloBoardDtos);
        Assert.assertEquals(1, resultTrelloBoardDtos.size());
        resultTrelloBoardDtos.forEach(resultTrelloBoardDto -> {
            Assert.assertEquals("1", resultTrelloBoardDto.getId());
            Assert.assertEquals("my_tasks", resultTrelloBoardDto.getName());
            resultTrelloBoardDto.getLists().forEach(trelloListDto -> {
                Assert.assertEquals("1", trelloListDto.getId());
                Assert.assertEquals("my_list", trelloListDto.getName());
                Assert.assertEquals(false, trelloListDto.isClosed());
            });
        });
    }
}
