package com.integrate.todo.rest.v1.list.toBeDeleted;

import com.integrate.todo.data.TodoListRecord;
import com.integrate.todo.data.TodoListRepository;
import com.integrate.todo.rest.v1.list.TodoList;
import org.junit.Test;

import java.util.Optional;

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

    @Test
    public void getList_returnsListWithCorrectID() {
        TodoListRepository mockRepository = mock( TodoListRepository.class );

        TodoListService service = new TodoListService( mockRepository );

        TodoListRecord record_input = new TodoListRecord("" );
        record_input.setUserID( 1 );
        Optional<TodoListRecord> expected_record = Optional.of( record_input );
        when( mockRepository.findById( 1 ) ).thenReturn( expected_record );

        TodoList expected_list = new TodoList().setTitle( "" ).setUserID( 1 );

        TodoList list = service.getList( 1 );

        assertThat( list ).isEqualTo( expected_list );
        verify( mockRepository ).findById( 1 );
    }

    @Test
    public void getList_whenDoesNotExist_returnsEmptyList(){
        TodoListRepository mockRepository = mock(TodoListRepository.class);
        TodoListService todoListService = new TodoListService(mockRepository);

        when(mockRepository.findById(4)).thenReturn(Optional.empty());

        TodoList expectedList = new TodoList();
        expectedList.setTitle("");
        expectedList.setUserID(-1);

        TodoList returnedList = todoListService.getList(4);

        assertThat(returnedList).isEqualTo(expectedList);

        verify(mockRepository).findById(4);

    }
}