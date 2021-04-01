/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system;

/**
 *
 * @author masao
 */
public class ToDoException extends RuntimeException{
    public ToDoException() {
        super();
    }
    public ToDoException(String msg) {
        super(msg);
    }
}
