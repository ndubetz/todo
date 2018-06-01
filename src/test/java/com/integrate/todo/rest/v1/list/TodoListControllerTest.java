package com.integrate.todo.rest.v1.list;

import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class TodoListControllerTest {

    @Test
    public void createList_returnsHttpStatusCreated() {
        TodoService mockService = mock( TodoService.class );
        TodoListController todoListController = new TodoListController(mockService);

        TodoList expectedTodoList = new TodoList();
        TodoList todoListPassedIn = new TodoList();


        when( mockService.createTodoList( todoListPassedIn ) )
          .thenReturn( expectedTodoList );

        ResponseEntity<TodoList> responseEntity = todoListController.createList( todoListPassedIn );
        ResponseEntity expectedResponseEntity = new ResponseEntity<>(
          expectedTodoList,
          HttpStatus.CREATED
        );


        verify( mockService )
          .createTodoList( todoListPassedIn );

        assertThat( responseEntity )
          .isEqualTo( expectedResponseEntity );
    }

    @Test
    public void getList_returnsListAndHttpStatus200() {
        TodoService mockService = mock( TodoService.class );
        TodoListController todoListController = new TodoListController( mockService );

        TodoList expectedTodoList = new TodoList().setUserID( 1 );

        when( mockService.getList( 1 ) )
          .thenReturn( expectedTodoList );

        ResponseEntity<TodoList> resultList = todoListController.readList(1);
        ResponseEntity expectedResponse = new ResponseEntity<>(
          expectedTodoList,
          HttpStatus.OK
        );


        verify( mockService )
          .getList( 1 );

        assertThat( resultList )
          .isEqualTo( expectedResponse );
    }

    @Test
    public void getList_whenDoesntExist_returnsHttpStatus204(){
        TodoService mockService = mock( TodoService.class );
        TodoListController todoListController = new TodoListController( mockService );

        TodoList expectedTodoList = new TodoList().setUserID( -1 );
        int input_id = 8;


        when( mockService.getList( input_id ) )
          .thenReturn( expectedTodoList );

        ResponseEntity<TodoList> resultList = todoListController.readList( input_id );
        ResponseEntity expectedResponse = new ResponseEntity<>(
          expectedTodoList,
          HttpStatus.NO_CONTENT
        );


        verify( mockService )
          .getList( input_id );

        assertThat( resultList )
          .isEqualTo( expectedResponse );
    }
}
