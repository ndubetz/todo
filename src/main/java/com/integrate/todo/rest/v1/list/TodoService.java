package com.integrate.todo.rest.v1.list;

import com.integrate.todo.data.TodoListRecord;
import com.integrate.todo.data.TodoListRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class TodoService {

  private TodoListRepository repository;


  @Autowired
  public TodoService( TodoListRepository repository ) {
    this.repository = repository;
  }


  public TodoList getList(Integer id) {
    Optional<TodoListRecord> record = this.repository.findById(id);

    if ( record.isPresent() )
      return TodoList.fromRecord( record.get() );

    TodoList nullList = new TodoList()
      .setTitle( "" )
      .setUserID( -1 );

    return nullList;
  }

  public TodoList createTodoList(TodoList todoList) {
    TodoListRecord record = this.repository.save(new TodoListRecord(todoList.getTitle()));
    return TodoList.fromRecord(record);
  }

  public TodoList addItemToList(TodoItem item, Integer listID) {

    TodoList list = this.getList( listID );

    if( list == null ) return null;

    list.getTodoItems().add( item );

    this.repository.save( TodoList.toRecord( list ) );

    return list;
  }

}
