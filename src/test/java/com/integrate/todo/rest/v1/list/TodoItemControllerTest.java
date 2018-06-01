package com.integrate.todo.rest.v1.list;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class TodoItemControllerTest {

  private TodoService mockService;
  private TodoItemController controller;


  @Before
  public void setup() {
    this.mockService = mock( TodoService.class );
    this.controller = new TodoItemController( mockService );
  }


  @Test
  public void createItem_returnsHttpStatusCreated() {

    Integer inputListID = 4;
    String expectedItemContent = "Get Some Work Done";

    TodoItem inputItem = new TodoItem().setContent( expectedItemContent );
    TodoList expectedList = new TodoList();
    TodoItem expectedItem = new TodoItem().setContent( expectedItemContent );

    expectedList.getTodoItems().add( expectedItem );

    ResponseEntity expectedResponse = new ResponseEntity<>(
      expectedItem,
      HttpStatus.CREATED
    );


    when( this.mockService.addItemToList( inputItem, inputListID ) )
      .thenReturn( expectedList );

    ResponseEntity<TodoItem> response = this.controller.createItem( inputItem, inputListID );


    verify( this.mockService ).
      addItemToList( inputItem, inputListID );

    assertThat( response )
      .isEqualTo( expectedResponse );

  }

}