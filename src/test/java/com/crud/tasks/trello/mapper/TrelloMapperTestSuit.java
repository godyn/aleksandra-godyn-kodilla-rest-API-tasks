package com.crud.tasks.trello.mapper;

import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TrelloMapper;
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
public class TrelloMapperTestSuit {

    @Autowired
    TrelloMapper trelloMapper;

    TrelloListDto trelloListDto;
    List<TrelloListDto> trelloListDtos;

    TrelloBoardDto trelloBoardDto;
    List<TrelloBoardDto> trelloBoardDtos;

    TrelloList trelloList;
    List<TrelloList> trelloLists;

    TrelloBoard trelloBoard;
    List<TrelloBoard> trelloBoards;

    TrelloCardDto trelloCardDto;
    TrelloCard trelloCard;


    @Before
    public void before(){

        trelloListDto = new TrelloListDto("list1", "listId1", false);
        trelloListDtos = new ArrayList<>();
        trelloListDtos.add(trelloListDto);

        trelloBoardDto = new TrelloBoardDto("board1", "boardId1", trelloListDtos);
        trelloBoardDtos= new ArrayList<>();
        trelloBoardDtos.add(trelloBoardDto);

        trelloList = new TrelloList("list2", "listId2", true);
        trelloLists = new ArrayList<>();
        trelloLists.add(trelloList);

        trelloBoard = new TrelloBoard("board2", "boardId2", trelloLists);
        trelloBoards = new ArrayList<>();
        trelloBoards.add(trelloBoard);

        trelloCardDto = new TrelloCardDto("card1", "desc1", "pos1", "cardId1");

        trelloCard = new TrelloCard("card2", "desc2", "pos2", "cardId2");
    }

    @Test
    public void testMapToList(){
        //GIVEN
        //WHEN
        List<TrelloList> resultList = trelloMapper.mapToList(trelloListDtos);
        //THEN
        Assert.assertEquals(1, resultList.size());
        Assert.assertEquals("list1", resultList.get(0).getName());
        Assert.assertEquals("listId1", resultList.get(0).getId());
        Assert.assertEquals(false, resultList.get(0).isClosed());
    }

    @Test
    public void testMapToBoards(){
        //GIVEN
        //WHEN
        List<TrelloBoard> resultList = trelloMapper.mapToBoards(trelloBoardDtos);
        //THEN
        Assert.assertEquals(1, resultList.size());
        Assert.assertEquals("board1", resultList.get(0).getName());
        Assert.assertEquals("boardId1", resultList.get(0).getId());
    }

    @Test
    public void testMapToListDto(){
        //GIVEN
        //WHEN
        List<TrelloListDto> resultList = trelloMapper.mapToListDto(trelloLists);
        //THEN
        Assert.assertEquals(1, resultList.size());
        Assert.assertEquals("list2", resultList.get(0).getName());
        Assert.assertEquals("listId2", resultList.get(0).getId());
        Assert.assertEquals(true, resultList.get(0).isClosed());
    }

    @Test
    public void testMapToBoardsDto(){
        //GIVEN
        //WHEN
        List<TrelloBoardDto> resultList = trelloMapper.mapToBoardsDto(trelloBoards);
        //THEN
        Assert.assertEquals(1, resultList.size());
        Assert.assertEquals("board2", resultList.get(0).getName());
        Assert.assertEquals("boardId2", resultList.get(0).getId());
    }

    @Test
    public void testMapToCard(){
        //GIVEN
        //WHEN
        TrelloCard result = trelloMapper.mapToCard(trelloCardDto);
        //THEN
        Assert.assertEquals("card1", result.getName());
        Assert.assertEquals("cardId1", result.getListId());
    }

    @Test
    public void testMapToCardDto(){
        //GIVEN
        //WHEN
        TrelloCardDto result = trelloMapper.mapToCardDto(trelloCard);
        //THEN
        Assert.assertEquals("card2", result.getName());
        Assert.assertEquals("cardId2", result.getListId());
    }
}
