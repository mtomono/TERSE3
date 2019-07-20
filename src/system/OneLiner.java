/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system;

import java.util.List;
import jdk.jshell.JShell;
import static jdk.jshell.Snippet.Status.VALID;
import jdk.jshell.SnippetEvent;
import jdk.jshell.execution.LocalExecutionControlProvider;

/**
 *
 * @author masao
 */
public class OneLiner implements AutoCloseable {
    JShell shell;

    public OneLiner() {
        shell = JShell.builder().executionEngine(new LocalExecutionControlProvider(),null).build();
    }
    
    public String exec(String line) {
        List<SnippetEvent> events = shell.eval(line);
        if (events.get(0).status() != VALID)
            throw new RuntimeException(events.get(0).toString());
        return events.get(0).value();
    }

    @Override
    public void close() {
        shell.close();
    }

}
