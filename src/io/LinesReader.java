/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io;

import static function.PassedConsumer.passC;
import static function.PassedFunction.passF;
import iterator.TIterator;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.function.Function;
import static java.util.stream.Collectors.toSet;
import java.util.stream.Stream;
import parser.SimpleMatcher;

/**
 *
 * @author masao
 */
public class LinesReader {
    final public BufferedReader r;
    static Function<File,FileReader> reader=passF(FileReader::new);
    public LinesReader(File f) {
        this(reader.apply(f));
    }
    public LinesReader(InputStream is) {
        this(new BufferedReader(new InputStreamReader(is)));
    }
    public LinesReader(FileReader r) {
        this(new BufferedReader(r));
    }
    public LinesReader(BufferedReader r) {
        this.r=r;
    }
    public void close() {
        passC(x->r.close());
    }
    /**
     * warning: if you want to be sure about closing of BufferedReader and what you 
     * want to do is to process all the lines in the BufferReader, use stream() rather 
     * than iterator().
     * @return 
     */
    public TIterator<String> iterator() {
        return TIterator.set(new ReaderLinesIterator(r));
    }
    public Stream<String> stream() {
        return iterator().stream();
    }
    public Set<String> search(SimpleMatcher matcher) {
        return stream().flatMap(l->matcher.extract(l)).collect(toSet());
    }
    public Set<String> search(String regex) {
        return search(new SimpleMatcher(regex));
    }
}
