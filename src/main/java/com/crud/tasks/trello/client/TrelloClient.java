package com.crud.tasks.trello.client;

import com.crud.tasks.config.TrelloConfig;
import com.crud.tasks.domain.CreatedTrelloCard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class TrelloClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(TrelloClient.class);
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private TrelloConfig trelloConfig;

    /*
    private String generateUrl(){

        URI url = UriComponentsBuilder.fromHttpUrl(trelloConfig.getTrelloApiEndpoint()+"/members/"+trelloConfig.getTrelloUsername()+"/boards")
                .queryParam("key",  trelloConfig.getTrelloAppKey())
                .queryParam("token", trelloConfig.getTrelloToken())
                .queryParam("fields", "name,id")
                .queryParam("lists", "all").build().encode().toUri();

        return url.toString();
    }
    */


    public List<TrelloBoardDto> getTrelloBoards(){

        URI url = UriComponentsBuilder.fromHttpUrl(trelloConfig.getTrelloApiEndpoint()+"/members/"+trelloConfig.getTrelloUsername()+"/boards")
                .queryParam("key",  trelloConfig.getTrelloAppKey())
                .queryParam("token", trelloConfig.getTrelloToken())
                .queryParam("fields", "name,id")
                .queryParam("lists", "all").build().encode().toUri();

        try {
            TrelloBoardDto[] boardsResponse = restTemplate.getForObject(url, TrelloBoardDto[].class);
            return Optional.ofNullable(boardsResponse)
                    .map(trelloBoardDtos -> Arrays.asList(trelloBoardDtos))
                    .orElse(Arrays.asList(new TrelloBoardDto[0]));
        }catch(RestClientException e){
            LOGGER.error(e.getMessage(), e);
            return new ArrayList<>();
        }
    }

    public CreatedTrelloCard createNewCard(TrelloCardDto trelloCardDto){
        URI url = UriComponentsBuilder.fromHttpUrl(trelloConfig.getTrelloApiEndpoint()+ "/cards")
                .queryParam("key", trelloConfig.getTrelloAppKey())
                .queryParam("token", trelloConfig.getTrelloToken())
                .queryParam("name", trelloCardDto.getName())
                .queryParam("desc", trelloCardDto.getDescription())
                .queryParam("pos", trelloCardDto.getPos())
                .queryParam("idList", trelloCardDto.getListId()).build().encode().toUri();

        return restTemplate.postForObject(url, null, CreatedTrelloCard.class);
    }

}