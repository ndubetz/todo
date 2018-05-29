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
        TodoListService mockService = mock(TodoListService.class);
        TodoListController todoListController = new TodoListController(mockService);
        TodoList expectedTodoList = new TodoList();
        TodoList todoListPassedIn = new TodoList();
        when(mockService.createTodoList(todoListPassedIn)).thenReturn(expectedTodoList);

        ResponseEntity<TodoList> responseEntity = todoListController.createList(todoListPassedIn);

        ResponseEntity expectedResponseEntity = new ResponseEntity<>(expectedTodoList,HttpStatus.CREATED);
        assertThat(responseEntity).isEqualTo(expectedResponseEntity);
        verify(mockService).createTodoList(todoListPassedIn);
    }
}