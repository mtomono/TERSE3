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

package orderedSet;

import static java.lang.Math.max;
import java.util.List;
import static java.util.stream.Collectors.toList;

/**
 *
 * @author masao
 */
public class RangeUtil {
    static public Range<Integer> shift(Range<Integer> r, int v) {
        return new Range<>(r.start()+v, r.end()+v);
    }
    
    static public Range<Long> shift(Range<Long> r, long v) {
        return new Range<>(r.start()+v, r.end()+v);
    }
    
    static public Range<Double> shift(Range<Double> r, double v) {
        return new Range<>(r.start()+v, r.end()+v);
    }
    
    static public RangeSet<Integer> shift(RangeSet<Integer> rs, int l) {
        return new RangeSet<>(rs.stream().map(r->shift(r,l)).collect(toList()));
    }
    
    static public RangeSet<Long> shift(RangeSet<Long> rs, long l) {
        return new RangeSet<>(rs.stream().map(r->shift(r,l)).collect(toList()));
    }
    
    static public RangeSet<Double> shift(RangeSet<Double> rs, double l) {
        return new RangeSet<>(rs.stream().map(r->shift(r,l)).collect(toList()));
    }
    
    static public RangeSet<Integer> unionI(List<RangeSet<Integer>> rs) {
        return rs.stream().reduce(RangeSet.empty(), (a,b)->a.union(b));
    }
    
    static public RangeSet<Long> unionL(List<RangeSet<Long>> rs) {
        return rs.stream().reduce(RangeSet.empty(), (a,b)->a.union(b));
    }
    
    static public RangeSet<Double> unionD(List<RangeSet<Double>> rs) {
        return rs.stream().reduce(RangeSet.empty(), (a,b)->a.union(b));
    }
    
    static public RangeSet<Integer> intersectI(List<RangeSet<Integer>> rs) {
        return rs.stream().reduce((a,b)->a.intersect(b)).orElse(RangeSet.empty());
    }
    
    static public RangeSet<Long> intersectL(List<RangeSet<Long>> rs) {
        return rs.stream().reduce((a,b)->a.intersect(b)).orElse(RangeSet.empty());
    }
    
    static public RangeSet<Double> intersectD(List<RangeSet<Double>> rs) {
        return rs.stream().reduce((a,b)->a.intersect(b)).orElse(RangeSet.empty());
    }
    
    static public Range<Integer> optional(int a, int b) {
        return new Range<>(a, max(a,b));
    }
    
    static public Range<Long> optional(long a, long b) {
        return new Range<>(a, max(a,b));
    }
    
    static public Range<Double> optional(double a, double b) {
        return new Range<>(a, max(a,b));
    }
    
    static public int widthI(Range<Integer> range) {
        return range.end()-range.start();
    }
    
    static public long widthL(Range<Long> range) {
        return range.end()-range.start();
    }

    static public double widthD(Range<Double> range) {
        return range.end()-range.start();
    }
}
