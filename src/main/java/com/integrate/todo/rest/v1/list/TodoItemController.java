package com.integrate.todo.rest.v1.list;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/list/item")
public class TodoItemController {

  private final TodoItemService todoItemService;


  @Autowired
  public TodoItemController( TodoItemService todoItemService ) {
    this.todoItemService = todoItemService;
  }

  @PostMapping("/{listID}")
  public @ResponseBody
  ResponseEntity<TodoItem> createItem( @RequestBody TodoItem inputItem, @PathVariable Integer listID ) {
    TodoItem todoItem = this.todoItemService.createTodoItem(inputItem, listID );

    return new ResponseEntity<>(
      todoItem,
      HttpStatus.CREATED
    );
  }

}
