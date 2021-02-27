/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package orderedSet;

import function.NaturalOrder;
import collection.TList;
import collection.c;
import java.util.Collections;
import java.util.List;
import static orderedSet.Builder.intRange;
import static orderedSet.RangeSet.intRanges;
import static org.testng.Assert.*;
import org.testng.annotations.Test;
import static test.TestUtils.methodNamePrint;

/**
 *
 * @author mtomono
 */
public class RangeSetTest {
    
    public RangeSetTest() {
    }

    @Test
    public void testIsEmpty() {
        methodNamePrint();
        assertTrue(RangeSet.b().empty().isEmpty());
    }
    
    @Test
    public void testMergeIntoRangeSet() {
        System.out.println(test.TestUtils.methodName(0));
        RangeSet<Integer> result = intRanges.mergeIntoRangeSet(TList.set(Range.c(new NaturalOrder<>(), 0,3, 5,7, -2,2, 4,8, 10,11)));
        RangeSet<Integer> expected = intRanges.rp(-2,3, 4,8, 10,11);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testContains__point() {
        methodNamePrint();
        RangeSet<Integer> tested = intRanges.rp(10, 15, 25, 45);
        assertFalse(tested.containsPoint(-1));
        assertFalse(tested.containsPoint(0));
        assertFalse(tested.containsPoint(5));
        assertFalse(tested.containsPoint(9));
        assertTrue(tested.containsPoint(10));
        assertTrue(tested.containsPoint(13));
        assertTrue(tested.containsPoint(14));
        assertFalse(tested.containsPoint(15));
        assertFalse(tested.containsPoint(20));
        assertFalse(tested.containsPoint(24));
        assertTrue(tested.containsPoint(25));
        assertTrue(tested.containsPoint(35));
        assertTrue(tested.containsPoint(44));
        assertFalse(tested.containsPoint(45));
        assertFalse(tested.containsPoint(50));
        
    }
    
    @Test
    public void testContains__points() {
        RangeSet<Integer> tested = intRanges.rp(10, 15, 25, 45);
        assertTrue(tested.containsPoints(c.a2l(10, 13, 27, 44)));
        assertFalse(tested.containsPoints(c.a2l(9, 13, 27)));
        assertFalse(tested.containsPoints(c.a2l(10, 20, 44)));
        assertFalse(tested.containsPoints(c.a2l(10, 13, 15)));
    }
    
    @Test
    public void testContains__points_lastOneIsOut() {
        RangeSet<Integer> tested = intRanges.rp(10, 15, 25, 45);
        assertFalse(tested.containsPoints(c.a2l(10, 13, 55)));
    }
    
    @Test
    public void testContains__range() {
        RangeSet<Integer> tested = intRanges.rp(10, 15, 25, 45);
        assertTrue(tested.contains(intRanges.rp(11, 13)));
        assertTrue(tested.contains(intRanges.rp(27, 30)));
        assertFalse(tested.contains(intRanges.rp(7, 8)));
        assertFalse(tested.contains(intRanges.rp(17, 20)));
        assertFalse(tested.contains(intRanges.rp(15, 25)));
        assertFalse(tested.contains(intRanges.rp(55, 58)));
        assertFalse(tested.contains(intRanges.rp(45, 50)));
        assertFalse(tested.contains(intRanges.rp(13, 28)));
        assertFalse(tested.contains(intRanges.rp(8, 13)));
        assertFalse(tested.contains(intRanges.rp(8, 17)));
        assertFalse(tested.contains(intRanges.rp(13, 17)));
        assertFalse(tested.contains(intRanges.rp(23, 30)));
        assertFalse(tested.contains(intRanges.rp(23, 50)));
        assertFalse(tested.contains(intRanges.rp(30, 50)));
        assertTrue(tested.contains(intRanges.rp(10, 11, 12, 13, 25, 26, 27, 28, 29, 30)));
        assertFalse(tested.contains(intRanges.rp(9, 11, 12, 13, 25, 26, 27, 28, 29, 30)));
        assertFalse(tested.contains(intRanges.rp(10, 11, 12, 16, 25, 26, 27, 28, 29, 30)));
        assertFalse(tested.contains(intRanges.rp(10, 11, 12, 13, 24, 26, 27, 28, 29, 46)));
        assertTrue(tested.contains(intRanges.rp(10, 15, 25, 45)));
        assertFalse(tested.contains(intRanges.rp(9, 15, 25, 45)));
        assertFalse(tested.contains(intRanges.rp(10, 16, 25, 45)));
        assertFalse(tested.contains(intRanges.rp(10, 15, 24, 45)));
        assertFalse(tested.contains(intRanges.rp(10, 15, 25, 46)));
    }
    
    @Test
    public void testOverlaps__points() {
        RangeSet<Integer> tested = intRanges.rp(10, 15, 25, 45);
        assertTrue(tested.overlapsPoints(c.a2l(10, 13, 27, 44)));
        assertTrue(tested.overlapsPoints(c.a2l(9, 13, 27)));
        assertTrue(tested.overlapsPoints(c.a2l(10, 20, 44)));
        assertTrue(tested.overlapsPoints(c.a2l(10, 13, 15)));
        assertTrue(tested.overlapsPoints(c.a2l(8, 13, 55)));
        assertTrue(tested.overlapsPoints(c.a2l(8, 9, 28)));
        assertFalse(tested.overlapsPoints(c.a2l(8, 9, 55)));
        assertFalse(tested.overlapsPoints(c.a2l(8, 17, 55)));
        assertFalse(tested.overlapsPoints(c.a2l(16, 17, 55)));
        assertFalse(tested.overlapsPoints(c.a2l(50, 52, 55)));
        assertFalse(tested.overlapsPoints(c.a2l(17, 52, 55)));
    }
    
    @Test
    public void testOverlaps__range() {
        RangeSet<Integer> tested = intRanges.rp(10, 15, 25, 45);
        assertFalse(tested.overlaps(intRanges.rp(7, 8)));
        assertFalse(tested.overlaps(intRanges.rp(17, 20)));
        assertFalse(tested.overlaps(intRanges.rp(15, 25)));
        assertFalse(tested.overlaps(intRanges.rp(55, 58)));
        assertFalse(tested.overlaps(intRanges.rp(45, 50)));
        assertTrue(tested.overlaps(intRanges.rp(8, 13)));
        assertTrue(tested.overlaps(intRanges.rp(8, 17)));
        assertTrue(tested.overlaps(intRanges.rp(13, 17)));
        assertTrue(tested.overlaps(intRanges.rp(13, 28)));
        assertTrue(tested.overlaps(intRanges.rp(23, 30)));
        assertTrue(tested.overlaps(intRanges.rp(23, 50)));
        assertTrue(tested.overlaps(intRanges.rp(30, 50)));
        assertTrue(tested.overlaps(intRanges.rp(10, 11, 12, 13, 25, 26, 27, 28, 29, 30)));
        assertTrue(tested.overlaps(intRanges.rp(9, 11, 12, 13, 25, 26, 27, 28, 29, 30)));
        assertTrue(tested.overlaps(intRanges.rp(10, 11, 12, 16, 25, 26, 27, 28, 29, 30)));
        assertTrue(tested.overlaps(intRanges.rp(10, 11, 12, 13, 24, 26, 27, 28, 29, 46)));
        assertTrue(tested.overlaps(intRanges.rp(10, 15, 25, 45)));
        assertTrue(tested.overlaps(intRanges.rp(9, 15, 25, 45)));
        assertTrue(tested.overlaps(intRanges.rp(12, 16, 25, 45)));
        assertTrue(tested.overlaps(intRanges.rp(12, 15, 24, 45)));
        assertTrue(tested.overlaps(intRanges.rp(12, 15, 25, 46)));
    }
    
    @Test
    public void testTouch__range() {
        RangeSet<Integer> tested = intRanges.rp(10, 15, 25, 45);
        assertTrue(tested.touch(intRanges.rp(7, 8)).isEmpty());
        assertTrue(tested.touch(intRanges.rp(17, 20)).isEmpty());
        assertTrue(tested.touch(intRanges.rp(15, 25)).isEmpty());
        assertTrue(tested.touch(intRanges.rp(55, 58)).isEmpty());
        assertTrue(tested.touch(intRanges.rp(45, 50)).isEmpty());
        assertEquals(tested.touch(intRanges.rp(8, 13)), intRanges.rp(10, 15));
        assertEquals(tested.touch(intRanges.rp(8, 17)), intRanges.rp(10, 15));
        assertEquals(tested.touch(intRanges.rp(13, 17)), intRanges.rp(10, 15));
        assertEquals(tested.touch(intRanges.rp(13, 28)), intRanges.rp(10, 15, 25, 45));
        assertEquals(tested.touch(intRanges.rp(23, 30)), intRanges.rp(25, 45));
        assertEquals(tested.touch(intRanges.rp(23, 50)), intRanges.rp(25, 45));
        assertEquals(tested.touch(intRanges.rp(30, 50)), intRanges.rp(25, 45));
        assertEquals(tested.touch(intRanges.rp(10, 11, 12, 13, 25, 26, 27, 28, 29, 30)), intRanges.rp(10, 15, 25, 45));
        assertEquals(tested.touch(intRanges.rp(9, 11, 12, 13, 25, 26, 27, 28, 29, 30)), intRanges.rp(10, 15, 25, 45));
        assertEquals(tested.touch(intRanges.rp(10, 11, 12, 16, 25, 26, 27, 28, 29, 30)), intRanges.rp(10, 15, 25, 45));
        assertEquals(tested.touch(intRanges.rp(10, 11, 12, 13, 24, 26, 27, 28, 29, 46)), intRanges.rp(10, 15, 25, 45));
        assertEquals(tested.touch(intRanges.rp(10, 15, 25, 45)), intRanges.rp(10, 15, 25, 45));
        assertEquals(tested.touch(intRanges.rp(9, 15, 25, 45)), intRanges.rp(10, 15, 25, 45));
        assertEquals(tested.touch(intRanges.rp(12, 16, 25, 45)), intRanges.rp(10, 15, 25, 45));
        assertEquals(tested.touch(intRanges.rp(12, 15, 24, 45)), intRanges.rp(10, 15, 25, 45));
        assertEquals(tested.touch(intRanges.rp(12, 15, 25, 46)), intRanges.rp(10, 15, 25, 45));
    }
    public void testIntersect__basic() {
        assertTrue(intRanges.rp(10, 15).intersect(intRanges.rp(15, 17)).isEmpty());
        assertTrue(intRanges.rp(15, 17).intersect(intRanges.rp(10, 15)).isEmpty());
        assertTrue(intRanges.rp(10, 15).intersect(intRanges.rp(17, 18)).isEmpty());
        assertTrue(intRanges.rp(17, 18).intersect(intRanges.rp(10, 15)).isEmpty());
        assertEquals(intRange.r(13, 15), intRanges.rp(10, 15).intersect(intRanges.rp(13, 17)).toFragments().get(0));
        assertEquals(intRange.r(13, 15), intRanges.rp(13, 17).intersect(intRanges.rp(10, 15)).toFragments().get(0));
        assertEquals(intRange.r(13, 15), intRanges.rp(13, 15).intersect(intRanges.rp(10, 17)).toFragments().get(0));
        assertEquals(intRange.r(13, 15), intRanges.rp(10, 17).intersect(intRanges.rp(13, 15)).toFragments().get(0));
    }
    
    @Test
    public void testIntersect__nn1() {
        List<Range<Integer>> result;

        result = intRanges.rp(
    2,    5,          9,   11,      14,   16,   18,         22,   24,   26,                     34,   36)
// ======            ======         ======      ============      ======                        ======
        .intersect(intRanges.rp(
       4,    6,    8,   10,      13,         17,   19,   21,               27,   29,   31,   33))
//    ======      ======         ============      ======                  ======      ======
        .toFragments();
        assertEquals(4, result.size());
        assertEquals(intRange.r( 4,  5), result.get(0));
        assertEquals(intRange.r( 9, 10), result.get(1));
        assertEquals(intRange.r(14, 16), result.get(2));
        assertEquals(intRange.r(19, 21), result.get(3));
    }
    
    @Test
    public void testIntersect__nn2() {
        List<Range<Integer>> result;

        result = intRanges.rp(
    2,    5,          9,   11,      14,   16,   18,         22,   24,   26,                     34,   36)
// ======            ======         ======      ============      ======                        ======
        .intersect(intRanges.rp(
 1,                                                                                                      37))
//=======================================================================================================
        .toFragments();
        assertEquals(6, result.size());
        assertEquals(intRange.r( 2,  5), result.get(0));
        assertEquals(intRange.r( 9, 11), result.get(1));
        assertEquals(intRange.r(14, 16), result.get(2));
        assertEquals(intRange.r(18, 22), result.get(3));
        assertEquals(intRange.r(24, 26), result.get(4));
        assertEquals(intRange.r(34, 36), result.get(5));
    }
    
    @Test
    public void testIntersect__nn3() {
        List<Range<Integer>> result;

        result = intRanges.rp(
    2,    5,          9,   11,      14,   16,   18,         22,   24,   26,                     34,   36)
// ======            ======         ======      ============      ======                        ======
        .intersect(intRanges.rp(
    2,                                                                                                36   ))
// ===================================================================================================   
        .toFragments();
        assertEquals(6, result.size());
        assertEquals(intRange.r( 2,  5), result.get(0));
        assertEquals(intRange.r( 9, 11), result.get(1));
        assertEquals(intRange.r(14, 16), result.get(2));
        assertEquals(intRange.r(18, 22), result.get(3));
        assertEquals(intRange.r(24, 26), result.get(4));
        assertEquals(intRange.r(34, 36), result.get(5));
    }
    
    @Test
    public void testIntersect__nn4() {
        List<Range<Integer>> result;

        result = intRanges.rp(
    2,    5,          9,   11,      14,   16,   18,         22,   24,   26,                     34,   36)
// ======            ======         ======      ============      ======                        ======
        .intersect(intRanges.rp(
       3,                                                                                          35      ))
//    =============================================================================================      
        .toFragments();
        assertEquals(6, result.size());
        assertEquals(intRange.r( 3,  5), result.get(0));
        assertEquals(intRange.r( 9, 11), result.get(1));
        assertEquals(intRange.r(14, 16), result.get(2));
        assertEquals(intRange.r(18, 22), result.get(3));
        assertEquals(intRange.r(24, 26), result.get(4));
        assertEquals(intRange.r(34, 35), result.get(5));
    }
    
    @Test
    public void testIntersect__nn5() {
        List<Range<Integer>> result;

        result = intRanges.rp(
    2,    5,          9,   11,      14,   16,   18,         22,   24,   26,                     34,   36)
// ======            ======         ======      ============      ======                        ======
        .intersect(intRanges.rp(
                           11,                                    24                                       ))
//                         =======================================                                       
        .toFragments();
        assertEquals(2, result.size());
        assertEquals(intRange.r(14, 16), result.get(0));
        assertEquals(intRange.r(18, 22), result.get(1));
    }

    @Test
    public void testIntersect__nn6() {
        List<Range<Integer>> result;

        result = intRanges.rp(
    2,    5,          9,   11,      14,   16,   18,         22,   24,   26,                     34,   36)
// ======            ======         ======      ============      ======                        ======
        .intersect(intRanges.rp(
                                       15,            20                                                   ))
//                                     ===============                                                   
        .toFragments();
        assertEquals(2, result.size());
        assertEquals(intRange.r(15, 16), result.get(0));
        assertEquals(intRange.r(18, 20), result.get(1));
    }
    
    @Test
    public void testIntersect__nn7() {
        List<Range<Integer>> result;

        result = intRanges.rp(
                                 13,                           23                                             )
//                               ==============================                                                   
        .intersect(intRanges.rp(
    2,    5,          9,   11,      14,   16,   18,         22,   24,   26,                     34,   36)
// ======            ======         ======      ============      ======                        ======
        ).toFragments();
        assertEquals(intRange.r(14, 16), result.get(0));
        assertEquals(intRange.r(18, 22), result.get(1));
        assertEquals(2, result.size());
    }

    @Test
    public void testIntersect__nn8() {
        List<Range<Integer>> result;

        result = intRanges.rp(
                                    14,                     22                                             )
//                                  ========================                                                   
        .intersect(intRanges.rp(
    2,    5,          9,   11,      14,   16,   18,         22,   24,   26,                     34,   36)
// ======            ======         ======      ============      ======                        ======
        ).toFragments();
        assertEquals(2, result.size());
        assertEquals(intRange.r(14, 16), result.get(0));
        assertEquals(intRange.r(18, 22), result.get(1));
    }

    @Test
    public void testIntersect__overlap1() {
        List<Range<Integer>> result;

        result = intRanges.rp(
                                 13,         17,18,         22,   24,   26                  )
//                               ============   ============      ======                  
        .intersect(intRanges.rp(
                           11,      14,   16,      19,   21,         25                  )
//                         =========      =========      ============                   
        ).toFragments();
        assertEquals(intRange.r(13, 14), result.get(0));
        assertEquals(intRange.r(16, 17), result.get(1));
        assertEquals(intRange.r(18, 19), result.get(2));
        assertEquals(intRange.r(21, 22), result.get(3));
        assertEquals(intRange.r(24, 25), result.get(4));
        assertEquals(5, result.size());
    }

    @Test
    public void testIntersect__overlap2() {
        List<Range<Integer>> result;

        result = intRanges.rp(
                                                18,         22,   24,   26                  )
//                                              ============      ======                  
        .intersect(intRanges.rp(
                                             17,   19,   21,         25                  )
//                                           ======      ============                   
        ).toFragments();
        assertEquals(intRange.r(18, 19), result.get(0));
        assertEquals(intRange.r(21, 22), result.get(1));
        assertEquals(intRange.r(24, 25), result.get(2));
        assertEquals(3, result.size());
    }

    @Test
    public void testIntersect__overlap3() {
        List<Range<Integer>> result;

        result = intRanges.rp(
                                 13,         17,18,         22                              )
//                               ============   ============                              
        .intersect(intRanges.rp(
                                          16,      19,   21,         25                  )
//                                        =========      ============                   
        ).toFragments();
        assertEquals(3, result.size());
        assertEquals(intRange.r(16, 17), result.get(0));
        assertEquals(intRange.r(18, 19), result.get(1));
        assertEquals(intRange.r(21, 22), result.get(2));
    }

    @Test
    public void testIntersect__overlap4() {
        List<Range<Integer>> result;

        result = intRanges.rp(
                                 13,         17,                  24,   26                  )
//                               ============                     ======                  
        .intersect(intRanges.rp(
                                          16,      19,   21,         25                  )
//                                        =========      ============                   
        ).toFragments();
        assertEquals(2, result.size());
        assertEquals(intRange.r(16, 17), result.get(0));
        assertEquals(intRange.r(24, 25), result.get(1));
    }

    @Test
    public void testIntersect__overlap5() {
        List<Range<Integer>> result;

        result = intRanges.rp(
                                 13,         17,18,         22                              )
//                               ============   ============                              
        .intersect(intRanges.rp(
                           11,      14,                  21,         25                  )
//                         =========                     ============                   
        ).toFragments();
        assertEquals(2, result.size());
        assertEquals(intRange.r(13, 14), result.get(0));
        assertEquals(intRange.r(21, 22), result.get(1));
    }

    @Test
    public void testNegate() {
        assertEquals(intRange.r(15, 17), intRanges.rp(10, 15).negate(intRange.r(15, 17)).toFragments().get(0));
        assertEquals(intRange.r(15, 17), intRanges.rp(10, 15, 18, 19).negate(intRange.r(15, 17)).toFragments().get(0));
        assertEquals(intRange.r(10, 15), intRanges.rp(15, 17).negate(intRange.r(10, 15)).toFragments().get(0));
        assertEquals(intRange.r(17, 18), intRanges.rp(10, 15).negate(intRange.r(17, 18)).toFragments().get(0));
        assertEquals(intRange.r(10, 15), intRanges.rp(17, 18).negate(intRange.r(10, 15)).toFragments().get(0));
        assertEquals(intRange.r(15, 17), intRanges.rp(10, 15).negate(intRange.r(13, 17)).toFragments().get(0));
        assertEquals(intRange.r(10, 13), intRanges.rp(13, 17).negate(intRange.r(10, 15)).toFragments().get(0));
        assertEquals(intRange.r(10, 13), intRanges.rp(13, 15).negate(intRange.r(10, 17)).toFragments().get(0));
        assertEquals(intRange.r(15, 17), intRanges.rp(13, 15).negate(intRange.r(10, 17)).toFragments().get(1));
        assertEquals(Collections.emptyList(), intRanges.rp(10, 17).negate(intRange.r(13, 15)).toFragments());
      }

    @Test
    public void testMasked__basic() {
        assertEquals(intRange.r(10, 15), intRanges.rp(10, 15).maskedBy(intRanges.rp(15, 17)).toFragments().get(0));
        assertEquals(intRange.r(15, 17), intRanges.rp(15, 17).maskedBy(intRanges.rp(10, 15)).toFragments().get(0));
        assertEquals(intRange.r(10, 15), intRanges.rp(10, 15).maskedBy(intRanges.rp(17, 18)).toFragments().get(0));
        assertEquals(intRange.r(17, 18), intRanges.rp(17, 18).maskedBy(intRanges.rp(10, 15)).toFragments().get(0));
        assertEquals(intRange.r(10, 13), intRanges.rp(10, 15).maskedBy(intRanges.rp(13, 17)).toFragments().get(0));
        assertEquals(intRange.r(15, 17), intRanges.rp(13, 17).maskedBy(intRanges.rp(10, 15)).toFragments().get(0));
        assertTrue(intRanges.rp(13, 15).maskedBy(intRanges.rp(10, 17)).isEmpty());
        assertEquals(intRange.r(10, 13), intRanges.rp(10, 17).maskedBy(intRanges.rp(13, 15)).toFragments().get(0));
        assertEquals(intRange.r(15, 17), intRanges.rp(10, 17).maskedBy(intRanges.rp(13, 15)).toFragments().get(1));
    }
    
    @Test
    public void testMasked__nn1() {
        List<Range<Integer>> result;

        result = intRanges.rp(
    2,    5,          9,   11,      14,   16,   18,         22,   24,   26,                     34,   36)
// ======            ======         ======      ============      ======                        ======
        .maskedBy(intRanges.rp(
       4,    6,    8,   10,      13,         17,   19,   21,               27,   29,   31,   33)
//    ======      ======         ============      ======                  ======      ======
        ).toFragments();
        assertEquals(6, result.size());
        assertEquals(intRange.r( 2,  4), result.get(0));
        assertEquals(intRange.r(10, 11), result.get(1));
        assertEquals(intRange.r(18, 19), result.get(2));
        assertEquals(intRange.r(21, 22), result.get(3));
        assertEquals(intRange.r(24, 26), result.get(4));
        assertEquals(intRange.r(34, 36), result.get(5));
    }
    
    @Test
    public void testMasked__nn1reverse() {
        List<Range<Integer>> result;

        result = intRanges.rp(
       4,    6,    8,   10,      13,         17,   19,   21,               27,   29,   31,   33)
//    ======      ======         ============      ======                  ======      ======
        .maskedBy(intRanges.rp(
    2,    5,          9,   11,      14,   16,   18,         22,   24,   26,                     34,   36)
// ======            ======         ======      ============      ======                        ======
        ).toFragments();
        assertEquals(6, result.size());
        assertEquals(intRange.r( 5,  6), result.get(0));
        assertEquals(intRange.r( 8,  9), result.get(1));
        assertEquals(intRange.r(13, 14), result.get(2));
        assertEquals(intRange.r(16, 17), result.get(3));
        assertEquals(intRange.r(27, 29), result.get(4));
        assertEquals(intRange.r(31, 33), result.get(5));
    }
    
    @Test
    public void testMasked__nn2() {
        List<Range<Integer>> result;

        result = intRanges.rp(
    2,    5,          9,   11,      14,   16,   18,         22,   24,   26,                     34,   36)
// ======            ======         ======      ============      ======                        ======
        .maskedBy(intRanges.rp(
 1,                                                                                                      37))
//=======================================================================================================
        .toFragments();
        assertEquals(0, result.size());
    }

    @Test
    public void testMasked__nn2reverse() {
        List<Range<Integer>> result;

        result = intRanges.rp(
 1,                                                                                                      37)
//=======================================================================================================
        .maskedBy(intRanges.rp(
    2,    5,          9,   11,      14,   16,   18,         22,   24,   26,                     34,   36)
// ======            ======         ======      ============      ======                        ======
        ).toFragments();
        assertEquals(7, result.size());
        assertEquals(intRange.r( 1,  2), result.get(0));
        assertEquals(intRange.r( 5,  9), result.get(1));
        assertEquals(intRange.r(11, 14), result.get(2));
        assertEquals(intRange.r(16, 18), result.get(3));
        assertEquals(intRange.r(22, 24), result.get(4));
        assertEquals(intRange.r(26, 34), result.get(5));
        assertEquals(intRange.r(36, 37), result.get(6));
    }
    
    @Test
    public void testMasked__nn3() {
        List<Range<Integer>> result;

        result = intRanges.rp(
    2,    5,          9,   11,      14,   16,   18,         22,   24,   26,                     34,   36)
// ======            ======         ======      ============      ======                        ======
        .maskedBy(intRanges.rp(
    2,                                                                                                36   ))
// ===================================================================================================   
        .toFragments();
        assertEquals(0, result.size());
    }

    @Test
    public void testMasked__nn3reverse() {
        List<Range<Integer>> result;

        result = intRanges.rp(
    2,                                                                                                36   )
// ===================================================================================================   
        .maskedBy(intRanges.rp(
    2,    5,          9,   11,      14,   16,   18,         22,   24,   26,                     34,   36)
// ======            ======         ======      ============      ======                        ======
        ).toFragments();
        assertEquals(5, result.size());
        assertEquals(intRange.r( 5,  9), result.get(0));
        assertEquals(intRange.r(11, 14), result.get(1));
        assertEquals(intRange.r(16, 18), result.get(2));
        assertEquals(intRange.r(22, 24), result.get(3));
        assertEquals(intRange.r(26, 34), result.get(4));
    }

    @Test
    public void testMasked__nn4() {
        List<Range<Integer>> result;

        result = intRanges.rp(
    2,    5,          9,   11,      14,   16,   18,         22,   24,   26,                     34,   36)
// ======            ======         ======      ============      ======                        ======
        .maskedBy(intRanges.rp(
       3,                                                                                          35      )
//    =============================================================================================      
        ).toFragments();
        assertEquals(2, result.size());
        assertEquals(intRange.r( 2,  3), result.get(0));
        assertEquals(intRange.r(35, 36), result.get(1));
    }

    @Test
    public void testMasked__nn4reverse() {
        List<Range<Integer>> result;

        result = intRanges.rp(
       3,                                                                                          35      )
//    =============================================================================================      
        .maskedBy(intRanges.rp(
    2,    5,          9,   11,      14,   16,   18,         22,   24,   26,                     34,   36)
// ======            ======         ======      ============      ======                        ======
        ).toFragments();
        assertEquals(5, result.size());
        assertEquals(intRange.r( 5,  9), result.get(0));
        assertEquals(intRange.r(11, 14), result.get(1));
        assertEquals(intRange.r(16, 18), result.get(2));
        assertEquals(intRange.r(22, 24), result.get(3));
        assertEquals(intRange.r(26, 34), result.get(4));
    }
    
    @Test
    public void testMasked__nn5() {
        List<Range<Integer>> result;

        result = intRanges.rp(
    2,    5,          9,   11,      14,   16,   18,         22,   24,   26,                     34,   36)
// ======            ======         ======      ============      ======                        ======
        .maskedBy(intRanges.rp(
                           11,                                    24                                       )
//                         =======================================                                       
        ).toFragments();
        assertEquals(4, result.size());
        assertEquals(intRange.r( 2,  5), result.get(0));
        assertEquals(intRange.r( 9, 11), result.get(1));
        assertEquals(intRange.r(24, 26), result.get(2));
        assertEquals(intRange.r(34, 36), result.get(3));
    }

    @Test
    public void testMasked__nn5reverse() {
        List<Range<Integer>> result;

        result = intRanges.rp(
                           11,                                    24                                       )
//                         =======================================                                       
        .maskedBy(intRanges.rp(
    2,    5,          9,   11,      14,   16,   18,         22,   24,   26,                     34,   36)
// ======            ======         ======      ============      ======                        ======
        ).toFragments();
        assertEquals(3, result.size());
        assertEquals(intRange.r(11, 14), result.get(0));
        assertEquals(intRange.r(16, 18), result.get(1));
        assertEquals(intRange.r(22, 24), result.get(2));
    }
    
    @Test
    public void testMasked__nn6() {
        List<Range<Integer>> result;

        result = intRanges.rp(
    2,    5,          9,   11,      14,   16,   18,         22,   24,   26,                     34,   36)
// ======            ======         ======      ============      ======                        ======
        .maskedBy(intRanges.rp(
                                       15,            20                                                   )
//                                     ===============                                                   
        ).toFragments();
        assertEquals(6, result.size());
        assertEquals(intRange.r( 2,  5), result.get(0));
        assertEquals(intRange.r( 9, 11), result.get(1));
        assertEquals(intRange.r(14, 15), result.get(2));
        assertEquals(intRange.r(20, 22), result.get(3));
        assertEquals(intRange.r(24, 26), result.get(4));
        assertEquals(intRange.r(34, 36), result.get(5));
    }

    @Test
    public void testMasked__nn6reverse() {
        List<Range<Integer>> result;

        result = intRanges.rp(
                                       15,            20                                                   )
//                                     ===============                                                   
        .maskedBy(intRanges.rp(
    2,    5,          9,   11,      14,   16,   18,         22,   24,   26,                     34,   36)
// ======            ======         ======      ============      ======                        ======
        ).toFragments();
        assertEquals(1, result.size());
        assertEquals(intRange.r(16, 18), result.get(0));
    }

    @Test
    public void testMasked__nn7() {
        List<Range<Integer>> result;

        result = intRanges.rp(
                                 13,                           23                                             )
//                               ==============================                                                   
        .maskedBy(intRanges.rp(
    2,    5,          9,   11,      14,   16,   18,         22,   24,   26,                     34,   36)
// ======            ======         ======      ============      ======                        ======
        ).toFragments();
        assertEquals(3, result.size());
        assertEquals(intRange.r(13, 14), result.get(0));
        assertEquals(intRange.r(16, 18), result.get(1));
        assertEquals(intRange.r(22, 23), result.get(2));
    }

    @Test
    public void testMasked__nn7reverse() {
        List<Range<Integer>> result;

        result = intRanges.rp(
    2,    5,          9,   11,      14,   16,   18,         22,   24,   26,                     34,   36)
// ======            ======         ======      ============      ======                        ======
        .maskedBy(intRanges.rp(
                                 13,                           23                                             )
//                               ==============================                                                   
        ).toFragments();
        assertEquals(4, result.size());
        assertEquals(intRange.r( 2,  5), result.get(0));
        assertEquals(intRange.r( 9, 11), result.get(1));
        assertEquals(intRange.r(24, 26), result.get(2));
        assertEquals(intRange.r(34, 36), result.get(3));
    }
    
    @Test
    public void testMasked__nn8() {
        List<Range<Integer>> result;

        result = intRanges.rp(
                                    14,                     22                                             )
//                                  ========================                                                   
        .maskedBy(intRanges.rp(
    2,    5,          9,   11,      14,   16,   18,         22,   24,   26,                     34,   36)
// ======            ======         ======      ============      ======                        ======
        ).toFragments();
        assertEquals(1, result.size());
        assertEquals(intRange.r(16, 18), result.get(0));
    }

    @Test
    public void testMasked__nn8reverse() {
        List<Range<Integer>> result;

        result = intRanges.rp(
    2,    5,          9,   11,      14,   16,   18,         22,   24,   26,                     34,   36)
// ======            ======         ======      ============      ======                        ======
        .maskedBy(intRanges.rp(
                                    14,                     22                                             )
//                                  ========================                                                   
        ).toFragments();
        assertEquals(4, result.size());
        assertEquals(intRange.r( 2,  5), result.get(0));
        assertEquals(intRange.r( 9, 11), result.get(1));
        assertEquals(intRange.r(24, 26), result.get(2));
        assertEquals(intRange.r(34, 36), result.get(3));

    }

    @Test
    public void testMasked__overlap1() {
        List<Range<Integer>> result;

        result = intRanges.rp(
                                 13,         17,18,         22,   24,   26                  )
//                               ============   ============      ======                  
        .maskedBy(intRanges.rp(
                           11,      14,   16,      19,   21,         25                  )
//                         =========      =========      ============                   
        ).toFragments();
        assertEquals(3, result.size());
        assertEquals(intRange.r(14, 16), result.get(0));
        assertEquals(intRange.r(19, 21), result.get(1));
        assertEquals(intRange.r(25, 26), result.get(2));
    }

    @Test
    public void testMasked__overlap2() {
        List<Range<Integer>> result;

        result = intRanges.rp(
                                                18,         22,   24,   26                  )
//                                              ============      ======                  
        .maskedBy(intRanges.rp(
                                             17,   19,   21,         25                  )
//                                           ======      ============                   
        ).toFragments();
        assertEquals(2, result.size());
        assertEquals(intRange.r(19, 21), result.get(0));
        assertEquals(intRange.r(25, 26), result.get(1));
    }

    @Test
    public void testMasked__overlap3() {
        List<Range<Integer>> result;

        result = intRanges.rp(
                                 13,         17,18,         22                              )
//                               ============   ============                              
        .maskedBy(intRanges.rp(
                                          16,      19,   21,         25                  )
//                                        =========      ============                   
        ).toFragments();
        assertEquals(2, result.size());
        assertEquals(intRange.r(13, 16), result.get(0));
        assertEquals(intRange.r(19, 21), result.get(1));
    }

    @Test
    public void testMasked__overlap4() {
        List<Range<Integer>> result;

        result = intRanges.rp(
                                 13,         17,                  24,   26                  )
//                               ============                     ======                  
        .maskedBy(intRanges.rp(
                                          16,      19,   21,         25                  )
//                                        =========      ============                   
        ).toFragments();
        assertEquals(2, result.size());
        assertEquals(intRange.r(13, 16), result.get(0));
        assertEquals(intRange.r(25, 26), result.get(1));
    }

    @Test
    public void testMasked__overlap5() {
        List<Range<Integer>> result;

        result = intRanges.rp(
                                 13,         17,18,         22                              )
//                               ============   ============                              
        .maskedBy(intRanges.rp(
                           11,      14,                  21,         25                  )
//                         =========                     ============                   
        ).toFragments();
        assertEquals(2, result.size());
        assertEquals(intRange.r(14, 17), result.get(0));
        assertEquals(intRange.r(18, 21), result.get(1));
    }

    @Test
    public void testMask__basic() {
        assertEquals(intRange.r(15, 17), intRanges.rp(10, 15).mask(intRanges.rp(15, 17)).toFragments().get(0));
        assertEquals(intRange.r(10, 15), intRanges.rp(15, 17).mask(intRanges.rp(10, 15)).toFragments().get(0));
        assertEquals(intRange.r(17, 18), intRanges.rp(10, 15).mask(intRanges.rp(17, 18)).toFragments().get(0));
        assertEquals(intRange.r(10, 15), intRanges.rp(17, 18).mask(intRanges.rp(10, 15)).toFragments().get(0));
        assertEquals(intRange.r(15, 17), intRanges.rp(10, 15).mask(intRanges.rp(13, 17)).toFragments().get(0));
        assertEquals(intRange.r(10, 13), intRanges.rp(13, 17).mask(intRanges.rp(10, 15)).toFragments().get(0));
        assertTrue(intRanges.rp(10, 17).mask(intRanges.rp(13, 15)).isEmpty());
        assertEquals(intRange.r(10, 13), intRanges.rp(13, 15).mask(intRanges.rp(10, 17)).toFragments().get(0));
        assertEquals(intRange.r(15, 17), intRanges.rp(13, 15).mask(intRanges.rp(10, 17)).toFragments().get(1));
    }
    
    @Test
    public void testMask__nn1() {
        List<Range<Integer>> result;

        result = intRanges.rp(
       4,    6,    8,   10,      13,         17,   19,   21,               27,   29,   31,   33)
//    ======      ======         ============      ======                  ======      ======
        .mask(intRanges.rp(
    2,    5,          9,   11,      14,   16,   18,         22,   24,   26,                     34,   36)
// ======            ======         ======      ============      ======                        ======
        ).toFragments();
        assertEquals(6, result.size());
        assertEquals(intRange.r( 2,  4), result.get(0));
        assertEquals(intRange.r(10, 11), result.get(1));
        assertEquals(intRange.r(18, 19), result.get(2));
        assertEquals(intRange.r(21, 22), result.get(3));
        assertEquals(intRange.r(24, 26), result.get(4));
        assertEquals(intRange.r(34, 36), result.get(5));
    }
    
    @Test
    public void testMask__nn1reverse() {
        List<Range<Integer>> result;

        result = intRanges.rp(
    2,    5,          9,   11,      14,   16,   18,         22,   24,   26,                     34,   36)
// ======            ======         ======      ============      ======                        ======
        .mask(intRanges.rp(
       4,    6,    8,   10,      13,         17,   19,   21,               27,   29,   31,   33)
//    ======      ======         ============      ======                  ======      ======
        ).toFragments();
        assertEquals(6, result.size());
        assertEquals(intRange.r( 5,  6), result.get(0));
        assertEquals(intRange.r( 8,  9), result.get(1));
        assertEquals(intRange.r(13, 14), result.get(2));
        assertEquals(intRange.r(16, 17), result.get(3));
        assertEquals(intRange.r(27, 29), result.get(4));
        assertEquals(intRange.r(31, 33), result.get(5));
    }
    
    @Test
    public void testMask__nn2() {
        List<Range<Integer>> result;

        result = intRanges.rp(
 1,                                                                                                      37)
//=======================================================================================================
        .mask(intRanges.rp(
    2,    5,          9,   11,      14,   16,   18,         22,   24,   26,                     34,   36))
// ======            ======         ======      ============      ======                        ======
        .toFragments();
        assertEquals(0, result.size());
    }

    @Test
    public void testMask__nn2reverse() {
        List<Range<Integer>> result;

        result = intRanges.rp(
    2,    5,          9,   11,      14,   16,   18,         22,   24,   26,                     34,   36)
// ======            ======         ======      ============      ======                        ======
        .mask(intRanges.rp(
 1,                                                                                                      37)
//=======================================================================================================
        ).toFragments();
        assertEquals(7, result.size());
        assertEquals(intRange.r( 1,  2), result.get(0));
        assertEquals(intRange.r( 5,  9), result.get(1));
        assertEquals(intRange.r(11, 14), result.get(2));
        assertEquals(intRange.r(16, 18), result.get(3));
        assertEquals(intRange.r(22, 24), result.get(4));
        assertEquals(intRange.r(26, 34), result.get(5));
        assertEquals(intRange.r(36, 37), result.get(6));
    }
    
    @Test
    public void testMask__nn3() {
        List<Range<Integer>> result;

        result = intRanges.rp(
    2,                                                                                                36   )
// ===================================================================================================   
        .mask(intRanges.rp(
    2,    5,          9,   11,      14,   16,   18,         22,   24,   26,                     34,   36))
// ======            ======         ======      ============      ======                        ======
        .toFragments();
        assertEquals(0, result.size());
    }

    @Test
    public void testMask__nn3reverse() {
        List<Range<Integer>> result;

        result = intRanges.rp(
    2,    5,          9,   11,      14,   16,   18,         22,   24,   26,                     34,   36)
// ======            ======         ======      ============      ======                        ======
        .mask(intRanges.rp(
    2,                                                                                                36   )
// ===================================================================================================   
        ).toFragments();
        assertEquals(5, result.size());
        assertEquals(intRange.r( 5,  9), result.get(0));
        assertEquals(intRange.r(11, 14), result.get(1));
        assertEquals(intRange.r(16, 18), result.get(2));
        assertEquals(intRange.r(22, 24), result.get(3));
        assertEquals(intRange.r(26, 34), result.get(4));
    }

    @Test
    public void testMask__nn4() {
        List<Range<Integer>> result;

        result = intRanges.rp(
       3,                                                                                          35      )
//    =============================================================================================      
        .mask(intRanges.rp(
    2,    5,          9,   11,      14,   16,   18,         22,   24,   26,                     34,   36)
// ======            ======         ======      ============      ======                        ======
        ).toFragments();
        assertEquals(2, result.size());
        assertEquals(intRange.r( 2,  3), result.get(0));
        assertEquals(intRange.r(35, 36), result.get(1));
    }

    @Test
    public void testMask__nn4reverse() {
        List<Range<Integer>> result;

        result = intRanges.rp(
    2,    5,          9,   11,      14,   16,   18,         22,   24,   26,                     34,   36)
// ======            ======         ======      ============      ======                        ======
        .mask(intRanges.rp(
       3,                                                                                          35      )
//    =============================================================================================      
        ).toFragments();
        assertEquals(5, result.size());
        assertEquals(intRange.r( 5,  9), result.get(0));
        assertEquals(intRange.r(11, 14), result.get(1));
        assertEquals(intRange.r(16, 18), result.get(2));
        assertEquals(intRange.r(22, 24), result.get(3));
        assertEquals(intRange.r(26, 34), result.get(4));
    }
    
    @Test
    public void testMask__nn5() {
        List<Range<Integer>> result;

        result = intRanges.rp(
                           11,                                    24                                       )
//                         =======================================                                       
        .mask(intRanges.rp(
    2,    5,          9,   11,      14,   16,   18,         22,   24,   26,                     34,   36)
// ======            ======         ======      ============      ======                        ======
        ).toFragments();
        assertEquals(4, result.size());
        assertEquals(intRange.r( 2,  5), result.get(0));
        assertEquals(intRange.r( 9, 11), result.get(1));
        assertEquals(intRange.r(24, 26), result.get(2));
        assertEquals(intRange.r(34, 36), result.get(3));
    }

    @Test
    public void testMask__nn5reverse() {
        List<Range<Integer>> result;

        result = intRanges.rp(
    2,    5,          9,   11,      14,   16,   18,         22,   24,   26,                     34,   36)
// ======            ======         ======      ============      ======                        ======
        .mask(intRanges.rp(
                           11,                                    24                                       )
//                         =======================================                                       
        ).toFragments();
        assertEquals(3, result.size());
        assertEquals(intRange.r(11, 14), result.get(0));
        assertEquals(intRange.r(16, 18), result.get(1));
        assertEquals(intRange.r(22, 24), result.get(2));
    }
    
    @Test
    public void testMask__nn6() {
        List<Range<Integer>> result;

        result = intRanges.rp(
                                       15,            20                                                   )
//                                     ===============                                                   
        .mask(intRanges.rp(
    2,    5,          9,   11,      14,   16,   18,         22,   24,   26,                     34,   36)
// ======            ======         ======      ============      ======                        ======
        ).toFragments();
        assertEquals(6, result.size());
        assertEquals(intRange.r( 2,  5), result.get(0));
        assertEquals(intRange.r( 9, 11), result.get(1));
        assertEquals(intRange.r(14, 15), result.get(2));
        assertEquals(intRange.r(20, 22), result.get(3));
        assertEquals(intRange.r(24, 26), result.get(4));
        assertEquals(intRange.r(34, 36), result.get(5));
    }

    @Test
    public void testMask__nn6reverse() {
        List<Range<Integer>> result;

        result = intRanges.rp(
    2,    5,          9,   11,      14,   16,   18,         22,   24,   26,                     34,   36)
// ======            ======         ======      ============      ======                        ======
        .mask(intRanges.rp(
                                       15,            20                                                   )
//                                     ===============                                                   
        ).toFragments();
        assertEquals(1, result.size());
        assertEquals(intRange.r(16, 18), result.get(0));
    }

    @Test
    public void testMask__nn7() {
        List<Range<Integer>> result;

        result = intRanges.rp(
    2,    5,          9,   11,      14,   16,   18,         22,   24,   26,                     34,   36)
// ======            ======         ======      ============      ======                        ======
        .mask(intRanges.rp(
                                 13,                           23                                             )
//                               ==============================                                                   
        ).toFragments();
        assertEquals(3, result.size());
        assertEquals(intRange.r(13, 14), result.get(0));
        assertEquals(intRange.r(16, 18), result.get(1));
        assertEquals(intRange.r(22, 23), result.get(2));
    }

    @Test
    public void testMask__nn7reverse() {
        List<Range<Integer>> result;

        result = intRanges.rp(
                                 13,                           23                                             )
//                               ==============================                                                   
        .mask(intRanges.rp(
    2,    5,          9,   11,      14,   16,   18,         22,   24,   26,                     34,   36)
// ======            ======         ======      ============      ======                        ======
        ).toFragments();
        assertEquals(4, result.size());
        assertEquals(intRange.r( 2,  5), result.get(0));
        assertEquals(intRange.r( 9, 11), result.get(1));
        assertEquals(intRange.r(24, 26), result.get(2));
        assertEquals(intRange.r(34, 36), result.get(3));
    }
    
    @Test
    public void testMask__nn8() {
        List<Range<Integer>> result;

        result = intRanges.rp(
    2,    5,          9,   11,      14,   16,   18,         22,   24,   26,                     34,   36)
// ======            ======         ======      ============      ======                        ======
        .mask(intRanges.rp(
                                    14,                     22                                             )
//                                  ========================                                                   
        ).toFragments();
        assertEquals(1, result.size());
        assertEquals(intRange.r(16, 18), result.get(0));
    }

    @Test
    public void testMask__nn8reverse() {
        List<Range<Integer>> result;

        result = intRanges.rp(
                                    14,                     22                                             )
//                                  ========================                                                   
        .mask(intRanges.rp(
    2,    5,          9,   11,      14,   16,   18,         22,   24,   26,                     34,   36)
// ======            ======         ======      ============      ======                        ======
        ).toFragments();
        assertEquals(4, result.size());
        assertEquals(intRange.r( 2,  5), result.get(0));
        assertEquals(intRange.r( 9, 11), result.get(1));
        assertEquals(intRange.r(24, 26), result.get(2));
        assertEquals(intRange.r(34, 36), result.get(3));

    }

    @Test
    public void testMask__overlap1() {
        List<Range<Integer>> result;

        result = intRanges.rp(
                           11,      14,   16,      19,   21,         25                  )
//                         =========      =========      ============                   
        .mask(intRanges.rp(
                                 13,         17,18,         22,   24,   26                  )
//                               ============   ============      ======                  
        ).toFragments();
        assertEquals(3, result.size());
        assertEquals(intRange.r(14, 16), result.get(0));
        assertEquals(intRange.r(19, 21), result.get(1));
        assertEquals(intRange.r(25, 26), result.get(2));
    }

    @Test
    public void testMask__overlap2() {
        List<Range<Integer>> result;

        result = intRanges.rp(
                                             17,   19,   21,         25                  )
//                                           ======      ============                   
        .mask(intRanges.rp(
                                                18,         22,   24,   26                  )
//                                              ============      ======                  
        ).toFragments();
        assertEquals(2, result.size());
        assertEquals(intRange.r(19, 21), result.get(0));
        assertEquals(intRange.r(25, 26), result.get(1));
    }

    @Test
    public void testMask__overlap3() {
        List<Range<Integer>> result;

        result = intRanges.rp(
                                          16,      19,   21,         25                  )
//                                        =========      ============                   
        .mask(intRanges.rp(
                                 13,         17,18,         22                              )
//                               ============   ============                              
        ).toFragments();
        assertEquals(2, result.size());
        assertEquals(intRange.r(13, 16), result.get(0));
        assertEquals(intRange.r(19, 21), result.get(1));
    }

    @Test
    public void testMask__overlap4() {
        List<Range<Integer>> result;

        result = intRanges.rp(
                                          16,      19,   21,         25                  )
//                                        =========      ============                   
        .mask(intRanges.rp(
                                 13,         17,                  24,   26                  )
//                               ============                     ======                  
        ).toFragments();
        assertEquals(2, result.size());
        assertEquals(intRange.r(13, 16), result.get(0));
        assertEquals(intRange.r(25, 26), result.get(1));
    }

    @Test
    public void testMask__overlap5() {
        List<Range<Integer>> result;

        result = intRanges.rp(
                           11,      14,                  21,         25                  )
//                         =========                     ============                   
        .mask(intRanges.rp(
                                 13,         17,18,         22                              )
//                               ============   ============                              
        ).toFragments();
        assertEquals(2, result.size());
        assertEquals(intRange.r(14, 17), result.get(0));
        assertEquals(intRange.r(18, 21), result.get(1));
    }

    @Test
    public void testCover() {
        assertEquals(intRange.r(13, 26), intRanges.rp(
                                 13,         17,                  24,   26                  )
//                               ============                     ======                  
        .cover().orElse(null));
    }

    public void testUnion__nn1() {
        List<Range<Integer>> result;

        result = intRanges.rp(
    2,    5,          9,   11,      14,   16,   18,         22,   24,   26,                     34,   36)
// ======            ======         ======      ============      ======                        ======
        .union(intRanges.rp(
       4,    6,    8,   10,      13,         17,   19,   21,               27,   29,   31,   33)
//    ======      ======         ============      ======                  ======      ======
        ).toFragments();
        assertEquals(8, result.size());
        assertEquals(intRange.r( 2,  6), result.get(0));
        assertEquals(intRange.r( 8, 11), result.get(1));
        assertEquals(intRange.r(13, 17), result.get(2));
        assertEquals(intRange.r(18, 22), result.get(3));
        assertEquals(intRange.r(24, 26), result.get(4));
        assertEquals(intRange.r(27, 29), result.get(5));
        assertEquals(intRange.r(31, 33), result.get(6));
        assertEquals(intRange.r(34, 36), result.get(7));
    }

    @Test
    public void testUnion__overlap1() {
        List<Range<Integer>> result;

        result = intRanges.rp(
                                 13,         17,18,         22,   24,   26                  )
//                               ============   ============      ======                  
        .union(intRanges.rp(
                           11,      14,   16,      19,   21,         25                  )
//                         =========      =========      ============                   
        ).toFragments();
        assertEquals(1, result.size());
        assertEquals(intRange.r(11, 26), result.get(0));
    }

}