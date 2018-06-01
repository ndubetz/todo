package com.integrate.todo.rest.v1.list;

import java.util.Optional;

import com.integrate.todo.data.TodoItemRecord;
import com.integrate.todo.data.TodoItemRepository;
import com.integrate.todo.data.TodoListRecord;
import com.integrate.todo.data.TodoListRepository;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TodoServiceTest {

  private TodoListRepository mockRepository;

  private TodoService service;

  @Before
  public void setup() {

    this.mockRepository = mock( TodoListRepository.class );

    this.service = new TodoService( this.mockRepository);

  }

  @Test
  public void createTodoList_savesToRepository() {

    int expectedID = 3;
    String expectedTitle = "Specific value";
    TodoListRecord expectedRecord = new TodoListRecord( expectedTitle ).setUserID( expectedID );
    TodoList expectedTodoList = new TodoList()
      .setTitle( expectedTitle )
      .setUserID( expectedID );

    TodoListRecord inputRecord = new TodoListRecord( expectedTitle );


    when( this.mockRepository.save( inputRecord ) )
      .thenReturn( expectedRecord );

    TodoList todoList = this.service.createTodoList(
      new TodoList().setTitle( expectedTitle )
    );


    verify( mockRepository )
      .save( inputRecord );

    assertThat( todoList )
      .isEqualTo( expectedTodoList );
  }

  @Test
  public void getList_returnsListWithCorrectID() {
    int expectedListID = 1;
    TodoList expected_list = new TodoList()
      .setTitle( "" )
      .setUserID(expectedListID);

    TodoListRecord record_input = new TodoListRecord("" );

    record_input.setUserID(expectedListID);

    Optional<TodoListRecord> expected_record = Optional.of( record_input );


    when( this.mockRepository.findById( expectedListID ) )
      .thenReturn( expected_record );

    TodoList list = this.service.getList(expectedListID);


    verify( this.mockRepository )
      .findById( expectedListID );

    assertThat( list )
      .isEqualTo( expected_list );
  }

  @Test
  public void getList_whenDoesNotExist_returnsEmptyList(){
    int expectedListID = 4;
    TodoList expectedList = new TodoList()
      .setTitle( "" )
      .setUserID( -1 );


    when( mockRepository.findById( expectedListID ) )
      .thenReturn( Optional.empty() );

    TodoList returnedList = this.service.getList(expectedListID);


    verify( this.mockRepository)
      .findById( expectedListID );

    assertThat( returnedList )
      .isEqualTo( expectedList );
  }

  @Test
  public void addItemToList_addsItemToAppropriateList() {

    Integer newListID = 8;
    String expectedItemContent = "Some Content";
    String expectedListTitle = "A List of To-Dos";

    TodoItem expectedItem = new TodoItem().setContent( expectedItemContent );
    TodoList expectedList = new TodoList()
      .setTitle( expectedListTitle )
      .setUserID( newListID );

    TodoItem newItem = new TodoItem().setContent( expectedItemContent );
    TodoList newList = new TodoList()
      .setTitle( expectedListTitle )
      .setUserID( newListID );

    TodoListRecord expectedRecord = TodoList.toRecord( newList );


    when( this.mockRepository.findById( newListID ) )
      .thenReturn( Optional.of( expectedRecord ) );

    expectedList.getTodoItems().add( expectedItem );

    newList = this.service.addItemToList( newItem, newListID );


    System.out.println( "List length is: " + newList.getTodoItems().size() );

    verify( this.mockRepository )
      .findById( newListID );

    assertThat( newList )
      .isEqualTo( expectedList );
  }

}