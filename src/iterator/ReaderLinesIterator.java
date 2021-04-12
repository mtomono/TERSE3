/*
 Copyright 2017, 2018, 2019, 2020, 2021 Masao Tomono

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and limitations under the License.
 */
package iterator;

import static function.FunctionWithCheckedException.pass;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * Iterator which returns lines from a BufferedReader.
 * warning: if you want to be sure about closing of BufferedReader and what you 
 * want to do is to process all the lines in the BufferReader, wrap this iterator
 * into stream can help you get out of the worries about unclosed stream.
 * @author masao
 */
public class ReaderLinesIterator extends AbstractBufferedIterator<String> {
    BufferedReader r;
    public static Stream<String> s(File f) {
        return new TIterator(new ReaderLinesIterator(f)).stream();
    }
    static Function<File,FileReader> reader=pass(FileReader::new);
    public ReaderLinesIterator(File f) {
        this(reader.apply(f));
    }
    public ReaderLinesIterator(InputStream is) {
        this(new BufferedReader(new InputStreamReader(is)));
    }
    public ReaderLinesIterator(FileReader r) {
        this(new BufferedReader(r));
    }
    public ReaderLinesIterator(BufferedReader r) {
        this.r=r;
    }
    @Override
    protected void findNext() {
        try {
            if (r.ready()) 
                nextFound(r.readLine());
            else
                r.close();
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }
}
