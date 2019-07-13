package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class TrelloBoard {

    private String name;
    private String id;
    private List<TrelloList> lists;
}
