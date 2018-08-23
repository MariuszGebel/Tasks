package com.crud.tasks.controller;

import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.service.TrelloService;
import com.crud.tasks.trello.facade.TrelloFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/trello")
@CrossOrigin("*")
public class TrelloController {

    @Autowired
//    private TrelloClient trelloClient;
//    private TrelloService trelloService;
    private TrelloFacade trelloFacade;

    @RequestMapping(method = RequestMethod.GET, value = "/getTrelloBoards")
    public List<TrelloBoardDto> getTrelloBoards() {
        return trelloFacade.fetchTrelloBoards();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/createTrelloCard")
    public CreatedTrelloCardDto createTrelloCard(@RequestBody TrelloCardDto trelloCardDto) {
        return trelloFacade.createCard(trelloCardDto);
    }

//    @RequestMapping(method = RequestMethod.GET, value = "getTrelloBoards")
//    public List<TrelloBoardDto> getTrelloBoards() {
//        return trelloService.fetchTrelloBoards();
//    }
//
//    @RequestMapping(method = RequestMethod.POST, value = "createTrelloCard")
//    public CreatedTrelloCardDto createTrelloCard(@RequestBody TrelloCardDto trelloCardDto) {
//        return trelloService.createTrelloCard(trelloCardDto);
//    }

//    public Optional <List<TrelloBoardDto>> getTrelloBoards() {
//
//        List<TrelloBoardDto> trelloBoards = trelloClient.getTrelloBoards();
//
//        trelloBoards.forEach(trelloBoardDto -> System.out.println(trelloBoardDto.getId() + " " + trelloBoardDto.getName()));
//        trelloBoards.stream()
//                .filter(trelloBoardDto -> trelloBoardDto.getId().contains("Kodilla") ||
//                trelloBoardDto.getName().contains("Kodilla"))
//                .collect(Collectors.toList())
//                .forEach(trelloBoardDto -> System.out.println(trelloBoardDto.getId() + " "
//                + trelloBoardDto.getName()));
//
//        return Optional.of(trelloBoards);
//    }

//    public void getTrelloBoards() {
//        List<TrelloBoardDto> trelloBoards = trelloClient.getTrelloBoards();
//
//        trelloBoards.forEach(trelloBoardDto -> {
//
//            System.out.println(trelloBoardDto.getName() + " - " + trelloBoardDto.getId());
//
//            System.out.println("This board contains lists: ");
//
//            trelloBoardDto.getLists().forEach(trelloList ->
//                    System.out.println(trelloList.getName() + " - " + trelloList.getId() + " - " + trelloList.isClosed()));
//
//        });
//    }

//    public List<TrelloBoardDto> getTrelloBoards() {
//        return trelloClient.getTrelloBoards();
//    }

//    @RequestMapping(method = RequestMethod.POST, value = "createTrelloCard")
//    public CreatedTrelloCardDto createdTrelloCard(@RequestBody TrelloCardDto trelloCardDto) {
//        return trelloClient.createNewCard(trelloCardDto);
//    }

}