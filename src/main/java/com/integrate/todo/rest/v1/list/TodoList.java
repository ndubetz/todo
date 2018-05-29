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

import java.util.Objects;

public class TodoList {
  private String title;
  private Integer userID;

  public TodoList() {
    this.title = title;
  }

  public TodoList setTitle(String title) {
    this.title = title;
    return this;
  }

  public TodoList setUserID(Integer userID) {
    this.userID = userID;
    return this;
  }

  public Integer getUserID() {
    return userID;
  }

  public String getTitle() {
    return title;
  }

  public static TodoList fromRecord(TodoListRecord record){
    return new TodoList().setTitle(record.getTitle()).setUserID(record.getUserID());

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
