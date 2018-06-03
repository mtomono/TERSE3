/*
 Copyright 2017, 2018 Masao Tomono

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and limitations under the License.
 */

package string;

import java.util.List;
import java.util.function.Function;

/**
 *
 * @author masao
 */
public class Message {
    StringBuilder body;

    /**
     * New Line
     * start a message with String
     * @param body
     * @return 
     */
    public static Message nl(String body) {
        return new Message(body);
    }
    
    /**
     * New Line
     * start a message with double
     * @param body
     * @return 
     */
    public static Message nl(double body) {
        return nl(Double.toString(body));
    }

    /**
     * New Line
     * start a message with int
     * @param body
     * @return 
     */
    public static Message nl(int body) {
        return nl(Integer.toString(body));
    }
    
    /**
     * New Line
     * start a message with long
     * @param body
     * @return 
     */
    public static Message nl(long body) {
        return nl(Long.toString(body));
    }
    
    /**
     * New Line
     * start a message with char
     * @param body
     * @return 
     */
    public static Message nl(char body) {
        return nl(Strings.n(body, 1));
    }
    
    /**
     * New Line
     * start a message with Object::toString()
     * @param body
     * @return 
     */
    public static Message nl(Object body) {
        return nl(body.toString());
    }
    
    /**
     * New Line
     * start a message with empty String
     * @param body
     * @return 
     */
    public static Message nl() {
        return nl("");
    }
    
    public Message(String body) {
        this.body = new StringBuilder(body);
    }
    
    /**
     * Concatenate
     * add String after message
     * @param msg
     * @param body
     * @return 
     */
    public Message c(String msg) {
        body.append(msg);
        return this;
    }
    
    /**
     * Concatenate
     * add char after message
     * @param msg
     * @param body
     * @return 
     */
    public Message c(char msg) {
        body.append(msg);
        return this;
    }
    
    /**
     * Concatenate
     * add double after message
     * @param msg
     * @param body
     * @return 
     */
    public Message c(double msg) {
        body.append(msg);
        return this;
    }
    
    /**
     * Concatenate
     * add int after message
     * @param msg
     * @param body
     * @return 
     */
    public Message c(int msg) {
        body.append(msg);
        return this;
    }
    
    /**
     * Concatenate
     * add long after message
     * @param msg
     * @param body
     * @return 
     */
    public Message c(long msg) {
        body.append(msg);
        return this;
    }

    /**
     * Concatenate
     * add Object::toString() after message
     * @param o
     * @param body
     * @return 
     */
    public Message c(Object o) {
        body.append(o);
        return this;
    }
    
    public Message c(Message m) {
        body.append(m);
        return this;
    }
    
    /**
     * Space
     * add n spaces after message
     * @param n
     * @param body
     * @return 
     */
    public Message s(int n) {
        body.append(Strings.n(' ', n));
        return this;
    }
    
    /**
     * Space
     * add 1 spaces after message
     * @param body
     * @return 
     */
    public Message s() {
        return s(1);
    }
    
    /**
     * Space to
     * add spaces after message so that something added next will be at nth
     * from left end
     * needs fixing : to deal with the situation where there already is a cr 
     * in the message
     * @param n
     * @param body
     * @return 
     */
    public Message sto(int n) {
        int lastCr = body.lastIndexOf("¥n");
        int length = lastCr<0?body.length():body.length()-lastCr;
        if (length < n)
            return s(n-length);
        else
            return c('\n').s(n);
    }
    
    /**
     * Carriage Return
     * add return after message
     * @param body
     * @return 
     */
    public Message cr() {
        return c('\n');
    }
    
    public <T> Message csv(List<T> list, Function<T, Object>item, String delimiter) {
        if (list.isEmpty())
            return this;
        if (list.size() > 0)
            c(item.apply(list.get(0)));
        list.subList(1, list.size()).forEach(l->c(delimiter).c(item.apply(l)));
        return this;
    }
    
    /**
     * Repeat List
     * add list content after message.
     * each item is rendered by Function 'line'
     * @param <T>
     * @param list
     * @param line
     * @param body
     * @return 
     */
    public <T> Message lines(List<T> list, Function<T, Object> line) {
        return csv(list, line, "\n");
    }
    
    public <T> Message lines(List<T> list) {
        return lines(list, l->l);
    }
    
    public <T> Message csv(List<T> list, Function<T, Object> item) {
        return csv(list, item, ",");
    }
    
    public <T> Message csv(List<T> list, String delimiter) {
        return csv(list, l->l, delimiter);
    }
    
    public <T> Message csv(List<T> list) {
        return csv(list, i->i);
    }
    
    /**
     * parenthesis.
     * wrap around whole body with parenthesis.
     * @param start
     * @param end
     * @return 
     */
    public Message p(String start, String end) {
        body.insert(0, start);
        body.append(end);
        return this;
    }
    
    @Override
    public String toString() {
        return body.toString();
    }
}
