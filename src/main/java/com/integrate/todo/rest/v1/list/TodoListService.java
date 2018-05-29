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

import com.integrate.todo.data.TodoListRecord;
import com.integrate.todo.data.TodoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TodoListService {

  private TodoListRepository repository;

  @Autowired
  public TodoListService(TodoListRepository repository) {
    this.repository = repository;
  }

  public TodoList createTodoList(TodoList todoList) {
    TodoListRecord record = repository.save(new TodoListRecord(todoList.getTitle()));
    return TodoList.fromRecord(record);
  }


  public TodoList getList(Integer id) {
    Optional<TodoListRecord> record = repository.findById(id);
    if (record.isPresent()){
      return TodoList.fromRecord(record.get());
    }

    TodoList nullList = new TodoList();

    nullList.setTitle( "" ).setUserID( -1 );
    return nullList;
  }

}
