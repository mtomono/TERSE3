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

package view;

import java.util.List;
import java.util.Scanner;
import parser.Parser;
import parser.StrSource;
import static parser.Parsers.*;

/**
 *
 * @author masao
 */
public class Interaction {
    List<Prompt> prompts;
    static final String prompt = ">";
    static final Parser<String, Character, String> end = spaces.next(str("quit").or(str("exit").tr()).or(str("end").tr())).prev(spaces);
    Console console;

    public Interaction(List<Prompt> prompts) {
        this(prompts, new Console());
    }
    
    public Interaction(List<Prompt> prompts, Console console) {
        this.prompts = prompts;
        this.console = console;
    }
    
    public void main() {
        try (Scanner in = new Scanner(console.in)) {
            while (true) {
                prompt();
                String line = in.nextLine();
                if (end.matches(new StrSource(line)))
                    return;
                if (prompts.stream().sequential().noneMatch(p->p.execIfMatch(line, console.out)))
                    System.out.println("no such command");
            }
        }
    }
    
    public void prompt() {
        console.out.print(prompt);
    }
}
