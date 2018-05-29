package com.integrate.todo.rest.v1.list;

import com.integrate.todo.data.TodoListRecord;
import com.integrate.todo.data.TodoListRepository;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class TodoListServiceTest {

    @Test
    public void createTodoList_savesToRepository() {
        TodoListRepository mockRepository = mock(TodoListRepository.class);
        String expectedTitle = "Specific value";
        TodoListRecord expectedRecord = new TodoListRecord(expectedTitle);
        int expectedID = 3;
        expectedRecord.setUserID(expectedID);

        TodoListRecord todoRecordPassedIn = new TodoListRecord(expectedTitle);
        when(mockRepository.save(todoRecordPassedIn)).thenReturn(expectedRecord);

        TodoList expectedTodoList = new TodoList().setTitle(expectedTitle).setUserID(expectedID);

        TodoListService todoListService = new TodoListService(mockRepository);

        TodoList todoList = todoListService.createTodoList(new TodoList().setTitle(expectedTitle));

        verify(mockRepository).save(todoRecordPassedIn);
        assertThat(todoList).isEqualTo(expectedTodoList);
    }
}