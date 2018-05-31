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


import com.integrate.todo.data.TodoItemRecord;
import com.integrate.todo.data.TodoListRecord;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class TodoList {

  private String title;
  private Integer userID;

  private List<TodoItem> todoItems = new LinkedList<>();


  public TodoList() { }


  public Integer getUserID() {
    return userID;
  }

  public TodoList setUserID(Integer userID) {
    this.userID = userID;
    return this;
  }

  public String getTitle() {
    return title;
  }

  public TodoList setTitle(String title) {
    this.title = title;
    return this;
  }


  public List<TodoItem> getTodoItems() {
    return todoItems;
  }

  public TodoList setTodoItems(List<TodoItem> todoItems) {
    this.todoItems = todoItems;
    return this;
  }

  public static TodoList fromRecord(TodoListRecord record){
    TodoList list = new TodoList();

    if( record == null ) return null;
    if( record.getTitle() != null ) list.setTitle( record.getTitle() );
    if( record.getUserID() != null ) list.setUserID( record.getUserID() );
    if( record.getTodoItems() != null ) {
      List<TodoItemRecord> todoItemRecords = record.getTodoItems();
      List<TodoItem> items = new LinkedList<>();
      for( int index = 0; index < todoItemRecords.size(); index++ )
        items.add( TodoItem.fromRecord( todoItemRecords.get( index ) ) );

      list.setTodoItems( items );
    }

    return list;
  }

  public static TodoListRecord toRecord( TodoList list ) {
    TodoListRecord record;

    if( list.getTitle() != null )
      record = new TodoListRecord( list.getTitle() );
    else record = new TodoListRecord();

    if( list.getUserID() != null && list.getUserID() > -1 )
      record.setUserID( list.getUserID() );
    if( list.getTodoItems() != null && list.getTodoItems().size() > 0 )
      record.setTodoItems( list.getTodoItems() );

    return record;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    TodoList todoList = (TodoList) o;
    return Objects.equals(title, todoList.title) &&
            Objects.equals(userID, todoList.userID);
  }

  @Override
  public int hashCode() {

    return Objects.hash(title, userID);
  }
}
