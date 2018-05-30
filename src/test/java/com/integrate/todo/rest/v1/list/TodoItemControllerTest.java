package com.integrate.todo.rest.v1.list;

import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class TodoItemControllerTest {

  @Test
  public void createItem_returnsHttpStatusCreated() {

    TodoItemService mockItemService = mock( TodoItemService.class );
    TodoItemController itemController = new TodoItemController( mockItemService );

    Integer inputListID = 4;
    TodoItem inputItem = new TodoItem();
    TodoItem expectedItem = new TodoItem();

    ResponseEntity expectedResponse = new ResponseEntity<>(
      expectedItem,
      HttpStatus.CREATED
    );


    when( mockItemService.createTodoItem( inputItem, inputListID ) )
      .thenReturn( expectedItem );

    ResponseEntity<TodoItem> response = itemController.createItem( inputItem, inputListID );


    assertThat( response ).isEqualTo( expectedResponse );
    verify( mockItemService ).createTodoItem( inputItem, inputListID );

  }

}