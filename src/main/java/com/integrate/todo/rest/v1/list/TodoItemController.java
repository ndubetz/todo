package com.integrate.todo.rest.v1.list;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item")
public class TodoItemController {

  private final TodoService service;


  @Autowired
  public TodoItemController( TodoService service ) {
    this.service = service;
  }


  @PostMapping("/{listID}")
  public @ResponseBody
  ResponseEntity<TodoItem> createItem(
    @RequestBody TodoItem inputItem,
    @PathVariable Integer listID
  ) {
    TodoList todoList = this.service.addItemToList( inputItem, listID );

    List<TodoItem> todoItems = todoList.getTodoItems();
    TodoItem todoItem = todoItems.get( todoItems.size() - 1 );

    return new ResponseEntity<>(
      todoItem,
      HttpStatus.CREATED
    );
  }

}
