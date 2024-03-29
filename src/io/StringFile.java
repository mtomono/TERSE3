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
package io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author masao
 */
public class StringFile {
    File file;
    
    public StringFile(String name) {
        file = new File(name);
    }
    
    public void write(String content) throws IOException {
        file.createNewFile();
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(content);
            writer.flush();
            writer.close();
        }
    }
    
    /**
     * write w(ithout exception)
     * StringFile is often used as a handy measure to make a file.
     * for that purpose, we can skip exception checking.
     * @param content 
     */
    public void writew(String content) {
        try {
            write(content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
