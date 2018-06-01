package com.integrate.todo.rest.v1.list.toBeDeleted;

import com.integrate.todo.data.TodoItemRecord;
import com.integrate.todo.data.TodoItemRepository;
import com.integrate.todo.rest.v1.list.TodoItem;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

public class TodoItemServiceTest {

  @Test
  public void createTodoItem_savesToRepository() {

    Integer expectedItemID = 3;
    Integer expectedListID = 2;
    String expectedContent = "Specific content string";

    TodoItemRepository mockRepository = mock( TodoItemRepository.class );
    TodoItemService service = new TodoItemService( mockRepository );

    TodoItem expectedItem = new TodoItem().setContent( expectedContent );

    TodoItemRecord expectedRecord = new TodoItemRecord()
      .setId( expectedItemID )
      .setContent( expectedContent );

    TodoItemRecord inputRecord = new TodoItemRecord().setContent( expectedContent );


    when( mockRepository.save( inputRecord ) )
      .thenReturn( expectedRecord );

    TodoItem todoItem = service.createTodoItem(
      new TodoItem().setContent( expectedContent ),
      expectedListID
    );


    verify( mockRepository ).save( inputRecord );
    assertThat( todoItem ).isEqualTo( expectedItem );

  }


  @Test
  public void createTodoItem_addsItemToAppropriateList() {
    // if i pass in an item
    // the list with the appropriate ID has this item added to its toDoItems

//    TodoListService

  }


}