/*-
 * ‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾
 * Autonomic Proprietary 1.0
 * ——————————————————————————————————————————————————————————————————————————————
 * Copyright (C) 2018 Autonomic LLC - All rights reserved
 * ——————————————————————————————————————————————————————————————————————————————
 * Proprietary and confidential.
 *
 * NOTICE:  All information contained herein is, and remains the property of
 * Autonomic Incorporated and its suppliers, if any.  The intellectual and
 * technical concepts contained herein are proprietary to Autonomic Incorporated
 * and its suppliers and may be covered by U.S. and Foreign Patents, patents in
 * process, and are protected by trade secret or copyright law. Dissemination of
 * this information or reproduction of this material is strictly forbidden unless
 * prior written permission is obtained from Autonomic Incorporated.
 *
 * Unauthorized copy of this file, via any medium is strictly prohibited.
 * ______________________________________________________________________________
 */

package com.integrate.todo.rest.v1.list;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/list")
public class TodoListController {

  private final TodoService service;

  @Autowired
  public TodoListController( TodoService service ) {
      this.service = service;
  }

  @PostMapping
  public @ResponseBody
  ResponseEntity<TodoList> createList(@RequestBody TodoList todoList) {
     return new ResponseEntity<>(
       this.service.createTodoList( todoList ),
       HttpStatus.CREATED
     );
  }

  @GetMapping("/{id}")
  public @ResponseBody
  ResponseEntity<TodoList> readList( @PathVariable Integer id ) {
    TodoList list = this.service.getList( id );
    if( list.getUserID() == -1 )
      return new ResponseEntity<>( list, HttpStatus.NO_CONTENT );
    return new ResponseEntity<>( list, HttpStatus.OK );
  }

  @PutMapping
  public @ResponseBody
  TodoList updateList() { return null; }

  @DeleteMapping
  public void deleteList() {

  }
}
