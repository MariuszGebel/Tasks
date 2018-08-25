package com.crud.tasks.domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class TrelloMapperTestSuite {

//    @Autowired
//    private TrelloMapper trelloMapper;

    private TrelloMapper trelloMapper = new TrelloMapper();

    @Test
    public void mapToBoardsTest() {
        //Given
        TrelloBoardDto trelloBoardDtoKodilla = new TrelloBoardDto("1", "Kodilla", new ArrayList<>());
        TrelloBoardDto trelloBoardDtoPrivate = new TrelloBoardDto("2", "Private", new ArrayList<>());
        List<TrelloBoardDto> trelloBoardDtoList = new ArrayList<>();
        trelloBoardDtoList.add(trelloBoardDtoKodilla);
        trelloBoardDtoList.add(trelloBoardDtoPrivate);

        //When
        List<TrelloBoard> trelloBoardList = trelloMapper.mapToBoards(trelloBoardDtoList);

        //Then
        assertEquals(2, trelloBoardList.size());
        assertEquals("1", trelloBoardList.get(0).getId());
        assertEquals("Kodilla", trelloBoardList.get(0).getName());
        assertEquals("2", trelloBoardList.get(1).getId());
        assertEquals("Private", trelloBoardList.get(1).getName());
        System.out.println("mapToBoardsTest");
        System.out.println(trelloBoardList.get(0).getId() + ", " + trelloBoardList.get(0).getName());
        System.out.println(trelloBoardList.get(1).getId() + ", " + trelloBoardList.get(1).getName());
        System.out.println();
    }

    @Test
    public void mapToBoardsDtoTest() {
        //Given
        TrelloBoard trelloBoardKodilla = new TrelloBoard("1", "Kodilla", new ArrayList<>());
        TrelloBoard trelloBoardPrivate = new TrelloBoard("2", "Private", new ArrayList<>());
        TrelloBoard trelloBoardPublic = new TrelloBoard("3", "Public", new ArrayList<>());
        List<TrelloBoard> trelloBoardList = new ArrayList<>();
        trelloBoardList.add(trelloBoardKodilla);
        trelloBoardList.add(trelloBoardPrivate);
        trelloBoardList.add(trelloBoardPublic);

        //When
        List<TrelloBoardDto> trelloBoardDtoList = trelloMapper.mapToBoardsDto(trelloBoardList);

        //Then
        assertEquals(3, trelloBoardDtoList.size());
        assertEquals("1", trelloBoardDtoList.get(0).getId());
        assertEquals("Kodilla", trelloBoardDtoList.get(0).getName());
        assertEquals("2", trelloBoardDtoList.get(1).getId());
        assertEquals("Private", trelloBoardDtoList.get(1).getName());
        assertEquals("3", trelloBoardDtoList.get(2).getId());
        assertEquals("Public", trelloBoardDtoList.get(2).getName());
        System.out.println("\nmapToBoardsDtoTest");
        System.out.println(trelloBoardDtoList.get(0).getId() + ", " + trelloBoardDtoList.get(0).getName());
        System.out.println(trelloBoardDtoList.get(1).getId() + ", " + trelloBoardDtoList.get(1).getName());
        System.out.println(trelloBoardDtoList.get(2).getId() + ", " + trelloBoardDtoList.get(2).getName());
        System.out.println();
    }

    @Test
    public void mapToListDtoTest() {
        //Given
        TrelloList trelloListToDo = new TrelloList("1", "ToDo", false);
        TrelloList trelloListInProgress = new TrelloList("2", "In Progress", false);
        TrelloList trelloListDone = new TrelloList("3", "Done", false);
        List<TrelloList> trelloListList = new ArrayList<>();
        trelloListList.add(trelloListToDo);
        trelloListList.add(trelloListInProgress);
        trelloListList.add(trelloListDone);

        //When
        List<TrelloListDto> trelloListDtoList = trelloMapper.mapToListDto(trelloListList);

        //Then
        assertEquals(3, trelloListDtoList.size());
        assertEquals("1", trelloListDtoList.get(0).getId());
        assertEquals("ToDo", trelloListDtoList.get(0).getName());
        assertEquals(false, trelloListDtoList.get(0).isClosed());
        assertEquals("2", trelloListDtoList.get(1).getId());
        assertEquals("In Progress", trelloListDtoList.get(1).getName());
        assertEquals(false, trelloListDtoList.get(1).isClosed());
        assertEquals("3", trelloListDtoList.get(2).getId());
        assertEquals("Done", trelloListDtoList.get(2).getName());
        assertEquals(false, trelloListDtoList.get(2).isClosed());
        System.out.println("mapToListDtoTest");
        System.out.println(trelloListDtoList.get(0).getId() + ", " + trelloListDtoList.get(0).getName()
                + ", " + trelloListDtoList.get(0).isClosed());
        System.out.println(trelloListDtoList.get(1).getId() + ", " + trelloListDtoList.get(1).getName()
                + ", " + trelloListDtoList.get(1).isClosed());
        System.out.println(trelloListDtoList.get(2).getId() + ", " + trelloListDtoList.get(2).getName()
                + ", " + trelloListDtoList.get(2).isClosed());
        System.out.println();
    }

    @Test
    public void mapToListTest() {
        //Given
        TrelloListDto trelloListDtoToDo = new TrelloListDto("1", "ToDo", true);
        TrelloListDto trelloListDtoInProgress = new TrelloListDto("2", "In Progress", true);
        TrelloListDto trelloListDtoDone = new TrelloListDto("3", "Done", true);
        List<TrelloListDto> trelloListDtoList = new ArrayList<>();
        trelloListDtoList.add(trelloListDtoToDo);
        trelloListDtoList.add(trelloListDtoInProgress);
        trelloListDtoList.add(trelloListDtoDone);

        //When
        List<TrelloList> trelloListList = trelloMapper.mapToList(trelloListDtoList);

        //Then
        assertEquals(3, trelloListDtoList.size());
        assertEquals("1", trelloListDtoList.get(0).getId());
        assertEquals("ToDo", trelloListDtoList.get(0).getName());
        assertEquals("2", trelloListDtoList.get(1).getId());
        assertEquals("In Progress", trelloListDtoList.get(1).getName());
        assertEquals("3", trelloListDtoList.get(2).getId());
        assertEquals("Done", trelloListDtoList.get(2).getName());
        System.out.println("mapToListDtoTest");
        System.out.println(trelloListDtoList.get(0).getId() + ", " + trelloListDtoList.get(0).getName()
                + ", " + trelloListDtoList.get(0).isClosed());
        System.out.println(trelloListDtoList.get(1).getId() + ", " + trelloListDtoList.get(1).getName()
                + ", " + trelloListDtoList.get(1).isClosed());
        System.out.println(trelloListDtoList.get(2).getId() + ", " + trelloListDtoList.get(2).getName()
                + ", " + trelloListDtoList.get(2).isClosed());
        System.out.println();
    }

    @Test
    public void mapToCardTest() {
        //Given
        TrelloCard trelloCard = new TrelloCard("Card name", "Card Description", "Card Pos", "1");
        TrelloCardDto trelloCardDto = new TrelloCardDto("Card Dto name", "Card Dto Description", "Card Dto Pos", "1Dto");

        //When
        trelloCard = trelloMapper.mapToCard(trelloCardDto);

        //Then
        assertEquals("Card Dto name", trelloCard.getName());
        assertEquals("Card Dto Description", trelloCard.getDescription());
        assertEquals("Card Dto Pos", trelloCard.getPos());
        assertEquals("1Dto", trelloCard.getListId());
        System.out.println("mapToCardTest");
        System.out.println(trelloCard.getName() + ", " + trelloCard.getDescription() + ", "
                + trelloCard.getPos() + ", " + trelloCard.getListId());
        System.out.println();
    }

    @Test
    public void mapToCardDtoTest() {
        //Given
        TrelloCard trelloCard = new TrelloCard("Card name", "Card Description", "Card Pos", "1");
        TrelloCardDto trelloCardDto = new TrelloCardDto("Card Dto name", "Card Dto Description", "Card Dto Pos", "1Dto");

        //When
        trelloCardDto = trelloMapper.mapToCardDto(trelloCard);

        //Then
        assertEquals("Card name", trelloCardDto.getName());
        assertEquals("Card Description", trelloCardDto.getDescription());
        assertEquals("Card Pos", trelloCardDto.getPos());
        assertEquals("1", trelloCardDto.getListId());
        System.out.println("mapToCardDtoTest");
        System.out.println(trelloCardDto.getName() + ", " + trelloCardDto.getDescription() + ", "
                + trelloCardDto.getPos() + ", " + trelloCardDto.getListId());
        System.out.println();
    }


}
