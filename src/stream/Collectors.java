/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stream;

import java.util.Optional;
import java.util.stream.Collector;
import static java.util.stream.Collectors.collectingAndThen;

/**
 *
 * @author masao
 */
public class Collectors {
    public static <T,A,R> Collector<T, A, Optional<R>> toOptional(Collector<T,A,R> collector) {
        return collectingAndThen(collector,r->Optional.of(r));
    }
}
