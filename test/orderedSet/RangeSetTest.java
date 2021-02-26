/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package orderedSet;

import collection.TList;
import collection.c;
import java.util.Collections;
import java.util.List;
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
        assertTrue(RangeSet.<Integer>empty().isEmpty());
    }
    
    @Test
    public void testMergeIntoRangeSet() {
        System.out.println(test.TestUtils.methodName(0));
        RangeSet<Integer> result = RangeSet.mergeIntoRangeSet(TList.set(Range.c(new NaturalOrder<>(), 0,3, 5,7, -2,2, 4,8, 10,11)));
        RangeSet<Integer> expected = new RangeSet<>(Range.c(new NaturalOrder<>(), -2,3, 4,8, 10,11));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testContains__point() {
        methodNamePrint();
        RangeSet<Integer> tested = RangeSet.create(10, 15, 25, 45);
        assertFalse(tested.contains(-1));
        assertFalse(tested.contains(0));
        assertFalse(tested.contains(5));
        assertFalse(tested.contains(9));
        assertTrue(tested.contains(10));
        assertTrue(tested.contains(13));
        assertTrue(tested.contains(14));
        assertFalse(tested.contains(15));
        assertFalse(tested.contains(20));
        assertFalse(tested.contains(24));
        assertTrue(tested.contains(25));
        assertTrue(tested.contains(35));
        assertTrue(tested.contains(44));
        assertFalse(tested.contains(45));
        assertFalse(tested.contains(50));
        
    }
    
    @Test
    public void testContains__points() {
        RangeSet<Integer> tested = RangeSet.create(10, 15, 25, 45);
        assertTrue(tested.containsPoints(c.a2l(10, 13, 27, 44)));
        assertFalse(tested.containsPoints(c.a2l(9, 13, 27)));
        assertFalse(tested.containsPoints(c.a2l(10, 20, 44)));
        assertFalse(tested.containsPoints(c.a2l(10, 13, 15)));
    }
    
    @Test
    public void testContains__points_lastOneIsOut() {
        RangeSet<Integer> tested = RangeSet.create(10, 15, 25, 45);
        assertFalse(tested.containsPoints(c.a2l(10, 13, 55)));
    }
    
    @Test
    public void testContains__range() {
        RangeSet<Integer> tested = RangeSet.create(10, 15, 25, 45);
        assertTrue(tested.contains(RangeSet.create(11, 13)));
        assertTrue(tested.contains(RangeSet.create(27, 30)));
        assertFalse(tested.contains(RangeSet.create(7, 8)));
        assertFalse(tested.contains(RangeSet.create(17, 20)));
        assertFalse(tested.contains(RangeSet.create(15, 25)));
        assertFalse(tested.contains(RangeSet.create(55, 58)));
        assertFalse(tested.contains(RangeSet.create(45, 50)));
        assertFalse(tested.contains(RangeSet.create(13, 28)));
        assertFalse(tested.contains(RangeSet.create(8, 13)));
        assertFalse(tested.contains(RangeSet.create(8, 17)));
        assertFalse(tested.contains(RangeSet.create(13, 17)));
        assertFalse(tested.contains(RangeSet.create(23, 30)));
        assertFalse(tested.contains(RangeSet.create(23, 50)));
        assertFalse(tested.contains(RangeSet.create(30, 50)));
        assertTrue(tested.contains(RangeSet.create(10, 11, 12, 13, 25, 26, 27, 28, 29, 30)));
        assertFalse(tested.contains(RangeSet.create(9, 11, 12, 13, 25, 26, 27, 28, 29, 30)));
        assertFalse(tested.contains(RangeSet.create(10, 11, 12, 16, 25, 26, 27, 28, 29, 30)));
        assertFalse(tested.contains(RangeSet.create(10, 11, 12, 13, 24, 26, 27, 28, 29, 46)));
        assertTrue(tested.contains(RangeSet.create(10, 15, 25, 45)));
        assertFalse(tested.contains(RangeSet.create(9, 15, 25, 45)));
        assertFalse(tested.contains(RangeSet.create(10, 16, 25, 45)));
        assertFalse(tested.contains(RangeSet.create(10, 15, 24, 45)));
        assertFalse(tested.contains(RangeSet.create(10, 15, 25, 46)));
    }
    
    @Test
    public void testOverlaps__points() {
        RangeSet<Integer> tested = RangeSet.create(10, 15, 25, 45);
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
        RangeSet<Integer> tested = RangeSet.create(10, 15, 25, 45);
        assertFalse(tested.overlaps(RangeSet.create(7, 8)));
        assertFalse(tested.overlaps(RangeSet.create(17, 20)));
        assertFalse(tested.overlaps(RangeSet.create(15, 25)));
        assertFalse(tested.overlaps(RangeSet.create(55, 58)));
        assertFalse(tested.overlaps(RangeSet.create(45, 50)));
        assertTrue(tested.overlaps(RangeSet.create(8, 13)));
        assertTrue(tested.overlaps(RangeSet.create(8, 17)));
        assertTrue(tested.overlaps(RangeSet.create(13, 17)));
        assertTrue(tested.overlaps(RangeSet.create(13, 28)));
        assertTrue(tested.overlaps(RangeSet.create(23, 30)));
        assertTrue(tested.overlaps(RangeSet.create(23, 50)));
        assertTrue(tested.overlaps(RangeSet.create(30, 50)));
        assertTrue(tested.overlaps(RangeSet.create(10, 11, 12, 13, 25, 26, 27, 28, 29, 30)));
        assertTrue(tested.overlaps(RangeSet.create(9, 11, 12, 13, 25, 26, 27, 28, 29, 30)));
        assertTrue(tested.overlaps(RangeSet.create(10, 11, 12, 16, 25, 26, 27, 28, 29, 30)));
        assertTrue(tested.overlaps(RangeSet.create(10, 11, 12, 13, 24, 26, 27, 28, 29, 46)));
        assertTrue(tested.overlaps(RangeSet.create(10, 15, 25, 45)));
        assertTrue(tested.overlaps(RangeSet.create(9, 15, 25, 45)));
        assertTrue(tested.overlaps(RangeSet.create(12, 16, 25, 45)));
        assertTrue(tested.overlaps(RangeSet.create(12, 15, 24, 45)));
        assertTrue(tested.overlaps(RangeSet.create(12, 15, 25, 46)));
    }
    
    @Test
    public void testTouch__range() {
        RangeSet<Integer> tested = RangeSet.create(10, 15, 25, 45);
        assertTrue(tested.touch(RangeSet.create(7, 8)).isEmpty());
        assertTrue(tested.touch(RangeSet.create(17, 20)).isEmpty());
        assertTrue(tested.touch(RangeSet.create(15, 25)).isEmpty());
        assertTrue(tested.touch(RangeSet.create(55, 58)).isEmpty());
        assertTrue(tested.touch(RangeSet.create(45, 50)).isEmpty());
        assertEquals(tested.touch(RangeSet.create(8, 13)), RangeSet.create(10, 15));
        assertEquals(tested.touch(RangeSet.create(8, 17)), RangeSet.create(10, 15));
        assertEquals(tested.touch(RangeSet.create(13, 17)), RangeSet.create(10, 15));
        assertEquals(tested.touch(RangeSet.create(13, 28)), RangeSet.create(10, 15, 25, 45));
        assertEquals(tested.touch(RangeSet.create(23, 30)), RangeSet.create(25, 45));
        assertEquals(tested.touch(RangeSet.create(23, 50)), RangeSet.create(25, 45));
        assertEquals(tested.touch(RangeSet.create(30, 50)), RangeSet.create(25, 45));
        assertEquals(tested.touch(RangeSet.create(10, 11, 12, 13, 25, 26, 27, 28, 29, 30)), RangeSet.create(10, 15, 25, 45));
        assertEquals(tested.touch(RangeSet.create(9, 11, 12, 13, 25, 26, 27, 28, 29, 30)), RangeSet.create(10, 15, 25, 45));
        assertEquals(tested.touch(RangeSet.create(10, 11, 12, 16, 25, 26, 27, 28, 29, 30)), RangeSet.create(10, 15, 25, 45));
        assertEquals(tested.touch(RangeSet.create(10, 11, 12, 13, 24, 26, 27, 28, 29, 46)), RangeSet.create(10, 15, 25, 45));
        assertEquals(tested.touch(RangeSet.create(10, 15, 25, 45)), RangeSet.create(10, 15, 25, 45));
        assertEquals(tested.touch(RangeSet.create(9, 15, 25, 45)), RangeSet.create(10, 15, 25, 45));
        assertEquals(tested.touch(RangeSet.create(12, 16, 25, 45)), RangeSet.create(10, 15, 25, 45));
        assertEquals(tested.touch(RangeSet.create(12, 15, 24, 45)), RangeSet.create(10, 15, 25, 45));
        assertEquals(tested.touch(RangeSet.create(12, 15, 25, 46)), RangeSet.create(10, 15, 25, 45));
    }
    public void testIntersect__basic() {
        assertTrue(RangeSet.create(10, 15).intersect(RangeSet.create(15, 17)).isEmpty());
        assertTrue(RangeSet.create(15, 17).intersect(RangeSet.create(10, 15)).isEmpty());
        assertTrue(RangeSet.create(10, 15).intersect(RangeSet.create(17, 18)).isEmpty());
        assertTrue(RangeSet.create(17, 18).intersect(RangeSet.create(10, 15)).isEmpty());
        assertEquals(Range.create(13, 15), RangeSet.create(10, 15).intersect(RangeSet.create(13, 17)).toFragments().get(0));
        assertEquals(Range.create(13, 15), RangeSet.create(13, 17).intersect(RangeSet.create(10, 15)).toFragments().get(0));
        assertEquals(Range.create(13, 15), RangeSet.create(13, 15).intersect(RangeSet.create(10, 17)).toFragments().get(0));
        assertEquals(Range.create(13, 15), RangeSet.create(10, 17).intersect(RangeSet.create(13, 15)).toFragments().get(0));
    }
    
    @Test
    public void testIntersect__nn1() {
        List<Range<Integer>> result;

        result = RangeSet.create(
    2,    5,          9,   11,      14,   16,   18,         22,   24,   26,                     34,   36)
// ======            ======         ======      ============      ======                        ======
        .intersect(RangeSet.create(
       4,    6,    8,   10,      13,         17,   19,   21,               27,   29,   31,   33))
//    ======      ======         ============      ======                  ======      ======
        .toFragments();
        assertEquals(4, result.size());
        assertEquals(Range.create( 4,  5), result.get(0));
        assertEquals(Range.create( 9, 10), result.get(1));
        assertEquals(Range.create(14, 16), result.get(2));
        assertEquals(Range.create(19, 21), result.get(3));
    }
    
    @Test
    public void testIntersect__nn2() {
        List<Range<Integer>> result;

        result = RangeSet.create(
    2,    5,          9,   11,      14,   16,   18,         22,   24,   26,                     34,   36)
// ======            ======         ======      ============      ======                        ======
        .intersect(RangeSet.create(
 1,                                                                                                      37))
//=======================================================================================================
        .toFragments();
        assertEquals(6, result.size());
        assertEquals(Range.create( 2,  5), result.get(0));
        assertEquals(Range.create( 9, 11), result.get(1));
        assertEquals(Range.create(14, 16), result.get(2));
        assertEquals(Range.create(18, 22), result.get(3));
        assertEquals(Range.create(24, 26), result.get(4));
        assertEquals(Range.create(34, 36), result.get(5));
    }
    
    @Test
    public void testIntersect__nn3() {
        List<Range<Integer>> result;

        result = RangeSet.create(
    2,    5,          9,   11,      14,   16,   18,         22,   24,   26,                     34,   36)
// ======            ======         ======      ============      ======                        ======
        .intersect(RangeSet.create(
    2,                                                                                                36   ))
// ===================================================================================================   
        .toFragments();
        assertEquals(6, result.size());
        assertEquals(Range.create( 2,  5), result.get(0));
        assertEquals(Range.create( 9, 11), result.get(1));
        assertEquals(Range.create(14, 16), result.get(2));
        assertEquals(Range.create(18, 22), result.get(3));
        assertEquals(Range.create(24, 26), result.get(4));
        assertEquals(Range.create(34, 36), result.get(5));
    }
    
    @Test
    public void testIntersect__nn4() {
        List<Range<Integer>> result;

        result = RangeSet.create(
    2,    5,          9,   11,      14,   16,   18,         22,   24,   26,                     34,   36)
// ======            ======         ======      ============      ======                        ======
        .intersect(RangeSet.create(
       3,                                                                                          35      ))
//    =============================================================================================      
        .toFragments();
        assertEquals(6, result.size());
        assertEquals(Range.create( 3,  5), result.get(0));
        assertEquals(Range.create( 9, 11), result.get(1));
        assertEquals(Range.create(14, 16), result.get(2));
        assertEquals(Range.create(18, 22), result.get(3));
        assertEquals(Range.create(24, 26), result.get(4));
        assertEquals(Range.create(34, 35), result.get(5));
    }
    
    @Test
    public void testIntersect__nn5() {
        List<Range<Integer>> result;

        result = RangeSet.create(
    2,    5,          9,   11,      14,   16,   18,         22,   24,   26,                     34,   36)
// ======            ======         ======      ============      ======                        ======
        .intersect(RangeSet.create(
                           11,                                    24                                       ))
//                         =======================================                                       
        .toFragments();
        assertEquals(2, result.size());
        assertEquals(Range.create(14, 16), result.get(0));
        assertEquals(Range.create(18, 22), result.get(1));
    }

    @Test
    public void testIntersect__nn6() {
        List<Range<Integer>> result;

        result = RangeSet.create(
    2,    5,          9,   11,      14,   16,   18,         22,   24,   26,                     34,   36)
// ======            ======         ======      ============      ======                        ======
        .intersect(RangeSet.create(
                                       15,            20                                                   ))
//                                     ===============                                                   
        .toFragments();
        assertEquals(2, result.size());
        assertEquals(Range.create(15, 16), result.get(0));
        assertEquals(Range.create(18, 20), result.get(1));
    }
    
    @Test
    public void testIntersect__nn7() {
        List<Range<Integer>> result;

        result = RangeSet.create(
                                 13,                           23                                             )
//                               ==============================                                                   
        .intersect(RangeSet.create(
    2,    5,          9,   11,      14,   16,   18,         22,   24,   26,                     34,   36)
// ======            ======         ======      ============      ======                        ======
        ).toFragments();
        assertEquals(Range.create(14, 16), result.get(0));
        assertEquals(Range.create(18, 22), result.get(1));
        assertEquals(2, result.size());
    }

    @Test
    public void testIntersect__nn8() {
        List<Range<Integer>> result;

        result = RangeSet.create(
                                    14,                     22                                             )
//                                  ========================                                                   
        .intersect(RangeSet.create(
    2,    5,          9,   11,      14,   16,   18,         22,   24,   26,                     34,   36)
// ======            ======         ======      ============      ======                        ======
        ).toFragments();
        assertEquals(2, result.size());
        assertEquals(Range.create(14, 16), result.get(0));
        assertEquals(Range.create(18, 22), result.get(1));
    }

    @Test
    public void testIntersect__overlap1() {
        List<Range<Integer>> result;

        result = RangeSet.create(
                                 13,         17,18,         22,   24,   26                  )
//                               ============   ============      ======                  
        .intersect(RangeSet.create(
                           11,      14,   16,      19,   21,         25                  )
//                         =========      =========      ============                   
        ).toFragments();
        assertEquals(Range.create(13, 14), result.get(0));
        assertEquals(Range.create(16, 17), result.get(1));
        assertEquals(Range.create(18, 19), result.get(2));
        assertEquals(Range.create(21, 22), result.get(3));
        assertEquals(Range.create(24, 25), result.get(4));
        assertEquals(5, result.size());
    }

    @Test
    public void testIntersect__overlap2() {
        List<Range<Integer>> result;

        result = RangeSet.create(
                                                18,         22,   24,   26                  )
//                                              ============      ======                  
        .intersect(RangeSet.create(
                                             17,   19,   21,         25                  )
//                                           ======      ============                   
        ).toFragments();
        assertEquals(Range.create(18, 19), result.get(0));
        assertEquals(Range.create(21, 22), result.get(1));
        assertEquals(Range.create(24, 25), result.get(2));
        assertEquals(3, result.size());
    }

    @Test
    public void testIntersect__overlap3() {
        List<Range<Integer>> result;

        result = RangeSet.create(
                                 13,         17,18,         22                              )
//                               ============   ============                              
        .intersect(RangeSet.create(
                                          16,      19,   21,         25                  )
//                                        =========      ============                   
        ).toFragments();
        assertEquals(3, result.size());
        assertEquals(Range.create(16, 17), result.get(0));
        assertEquals(Range.create(18, 19), result.get(1));
        assertEquals(Range.create(21, 22), result.get(2));
    }

    @Test
    public void testIntersect__overlap4() {
        List<Range<Integer>> result;

        result = RangeSet.create(
                                 13,         17,                  24,   26                  )
//                               ============                     ======                  
        .intersect(RangeSet.create(
                                          16,      19,   21,         25                  )
//                                        =========      ============                   
        ).toFragments();
        assertEquals(2, result.size());
        assertEquals(Range.create(16, 17), result.get(0));
        assertEquals(Range.create(24, 25), result.get(1));
    }

    @Test
    public void testIntersect__overlap5() {
        List<Range<Integer>> result;

        result = RangeSet.create(
                                 13,         17,18,         22                              )
//                               ============   ============                              
        .intersect(RangeSet.create(
                           11,      14,                  21,         25                  )
//                         =========                     ============                   
        ).toFragments();
        assertEquals(2, result.size());
        assertEquals(Range.create(13, 14), result.get(0));
        assertEquals(Range.create(21, 22), result.get(1));
    }

    @Test
    public void testNegate() {
        assertEquals(Range.create(15, 17), RangeSet.create(10, 15).negate(Range.create(15, 17)).toFragments().get(0));
        assertEquals(Range.create(15, 17), RangeSet.create(10, 15, 18, 19).negate(Range.create(15, 17)).toFragments().get(0));
        assertEquals(Range.create(10, 15), RangeSet.create(15, 17).negate(Range.create(10, 15)).toFragments().get(0));
        assertEquals(Range.create(17, 18), RangeSet.create(10, 15).negate(Range.create(17, 18)).toFragments().get(0));
        assertEquals(Range.create(10, 15), RangeSet.create(17, 18).negate(Range.create(10, 15)).toFragments().get(0));
        assertEquals(Range.create(15, 17), RangeSet.create(10, 15).negate(Range.create(13, 17)).toFragments().get(0));
        assertEquals(Range.create(10, 13), RangeSet.create(13, 17).negate(Range.create(10, 15)).toFragments().get(0));
        assertEquals(Range.create(10, 13), RangeSet.create(13, 15).negate(Range.create(10, 17)).toFragments().get(0));
        assertEquals(Range.create(15, 17), RangeSet.create(13, 15).negate(Range.create(10, 17)).toFragments().get(1));
        assertEquals(Collections.emptyList(), RangeSet.create(10, 17).negate(Range.create(13, 15)).toFragments());
      }

    @Test
    public void testMasked__basic() {
        assertEquals(Range.create(10, 15), RangeSet.create(10, 15).maskedBy(RangeSet.create(15, 17)).toFragments().get(0));
        assertEquals(Range.create(15, 17), RangeSet.create(15, 17).maskedBy(RangeSet.create(10, 15)).toFragments().get(0));
        assertEquals(Range.create(10, 15), RangeSet.create(10, 15).maskedBy(RangeSet.create(17, 18)).toFragments().get(0));
        assertEquals(Range.create(17, 18), RangeSet.create(17, 18).maskedBy(RangeSet.create(10, 15)).toFragments().get(0));
        assertEquals(Range.create(10, 13), RangeSet.create(10, 15).maskedBy(RangeSet.create(13, 17)).toFragments().get(0));
        assertEquals(Range.create(15, 17), RangeSet.create(13, 17).maskedBy(RangeSet.create(10, 15)).toFragments().get(0));
        assertTrue(RangeSet.create(13, 15).maskedBy(RangeSet.create(10, 17)).isEmpty());
        assertEquals(Range.create(10, 13), RangeSet.create(10, 17).maskedBy(RangeSet.create(13, 15)).toFragments().get(0));
        assertEquals(Range.create(15, 17), RangeSet.create(10, 17).maskedBy(RangeSet.create(13, 15)).toFragments().get(1));
    }
    
    @Test
    public void testMasked__nn1() {
        List<Range<Integer>> result;

        result = RangeSet.create(
    2,    5,          9,   11,      14,   16,   18,         22,   24,   26,                     34,   36)
// ======            ======         ======      ============      ======                        ======
        .maskedBy(RangeSet.create(
       4,    6,    8,   10,      13,         17,   19,   21,               27,   29,   31,   33)
//    ======      ======         ============      ======                  ======      ======
        ).toFragments();
        assertEquals(6, result.size());
        assertEquals(Range.create( 2,  4), result.get(0));
        assertEquals(Range.create(10, 11), result.get(1));
        assertEquals(Range.create(18, 19), result.get(2));
        assertEquals(Range.create(21, 22), result.get(3));
        assertEquals(Range.create(24, 26), result.get(4));
        assertEquals(Range.create(34, 36), result.get(5));
    }
    
    @Test
    public void testMasked__nn1reverse() {
        List<Range<Integer>> result;

        result = RangeSet.create(
       4,    6,    8,   10,      13,         17,   19,   21,               27,   29,   31,   33)
//    ======      ======         ============      ======                  ======      ======
        .maskedBy(RangeSet.create(
    2,    5,          9,   11,      14,   16,   18,         22,   24,   26,                     34,   36)
// ======            ======         ======      ============      ======                        ======
        ).toFragments();
        assertEquals(6, result.size());
        assertEquals(Range.create( 5,  6), result.get(0));
        assertEquals(Range.create( 8,  9), result.get(1));
        assertEquals(Range.create(13, 14), result.get(2));
        assertEquals(Range.create(16, 17), result.get(3));
        assertEquals(Range.create(27, 29), result.get(4));
        assertEquals(Range.create(31, 33), result.get(5));
    }
    
    @Test
    public void testMasked__nn2() {
        List<Range<Integer>> result;

        result = RangeSet.create(
    2,    5,          9,   11,      14,   16,   18,         22,   24,   26,                     34,   36)
// ======            ======         ======      ============      ======                        ======
        .maskedBy(RangeSet.create(
 1,                                                                                                      37))
//=======================================================================================================
        .toFragments();
        assertEquals(0, result.size());
    }

    @Test
    public void testMasked__nn2reverse() {
        List<Range<Integer>> result;

        result = RangeSet.create(
 1,                                                                                                      37)
//=======================================================================================================
        .maskedBy(RangeSet.create(
    2,    5,          9,   11,      14,   16,   18,         22,   24,   26,                     34,   36)
// ======            ======         ======      ============      ======                        ======
        ).toFragments();
        assertEquals(7, result.size());
        assertEquals(Range.create( 1,  2), result.get(0));
        assertEquals(Range.create( 5,  9), result.get(1));
        assertEquals(Range.create(11, 14), result.get(2));
        assertEquals(Range.create(16, 18), result.get(3));
        assertEquals(Range.create(22, 24), result.get(4));
        assertEquals(Range.create(26, 34), result.get(5));
        assertEquals(Range.create(36, 37), result.get(6));
    }
    
    @Test
    public void testMasked__nn3() {
        List<Range<Integer>> result;

        result = RangeSet.create(
    2,    5,          9,   11,      14,   16,   18,         22,   24,   26,                     34,   36)
// ======            ======         ======      ============      ======                        ======
        .maskedBy(RangeSet.create(
    2,                                                                                                36   ))
// ===================================================================================================   
        .toFragments();
        assertEquals(0, result.size());
    }

    @Test
    public void testMasked__nn3reverse() {
        List<Range<Integer>> result;

        result = RangeSet.create(
    2,                                                                                                36   )
// ===================================================================================================   
        .maskedBy(RangeSet.create(
    2,    5,          9,   11,      14,   16,   18,         22,   24,   26,                     34,   36)
// ======            ======         ======      ============      ======                        ======
        ).toFragments();
        assertEquals(5, result.size());
        assertEquals(Range.create( 5,  9), result.get(0));
        assertEquals(Range.create(11, 14), result.get(1));
        assertEquals(Range.create(16, 18), result.get(2));
        assertEquals(Range.create(22, 24), result.get(3));
        assertEquals(Range.create(26, 34), result.get(4));
    }

    @Test
    public void testMasked__nn4() {
        List<Range<Integer>> result;

        result = RangeSet.create(
    2,    5,          9,   11,      14,   16,   18,         22,   24,   26,                     34,   36)
// ======            ======         ======      ============      ======                        ======
        .maskedBy(RangeSet.create(
       3,                                                                                          35      )
//    =============================================================================================      
        ).toFragments();
        assertEquals(2, result.size());
        assertEquals(Range.create( 2,  3), result.get(0));
        assertEquals(Range.create(35, 36), result.get(1));
    }

    @Test
    public void testMasked__nn4reverse() {
        List<Range<Integer>> result;

        result = RangeSet.create(
       3,                                                                                          35      )
//    =============================================================================================      
        .maskedBy(RangeSet.create(
    2,    5,          9,   11,      14,   16,   18,         22,   24,   26,                     34,   36)
// ======            ======         ======      ============      ======                        ======
        ).toFragments();
        assertEquals(5, result.size());
        assertEquals(Range.create( 5,  9), result.get(0));
        assertEquals(Range.create(11, 14), result.get(1));
        assertEquals(Range.create(16, 18), result.get(2));
        assertEquals(Range.create(22, 24), result.get(3));
        assertEquals(Range.create(26, 34), result.get(4));
    }
    
    @Test
    public void testMasked__nn5() {
        List<Range<Integer>> result;

        result = RangeSet.create(
    2,    5,          9,   11,      14,   16,   18,         22,   24,   26,                     34,   36)
// ======            ======         ======      ============      ======                        ======
        .maskedBy(RangeSet.create(
                           11,                                    24                                       )
//                         =======================================                                       
        ).toFragments();
        assertEquals(4, result.size());
        assertEquals(Range.create( 2,  5), result.get(0));
        assertEquals(Range.create( 9, 11), result.get(1));
        assertEquals(Range.create(24, 26), result.get(2));
        assertEquals(Range.create(34, 36), result.get(3));
    }

    @Test
    public void testMasked__nn5reverse() {
        List<Range<Integer>> result;

        result = RangeSet.create(
                           11,                                    24                                       )
//                         =======================================                                       
        .maskedBy(RangeSet.create(
    2,    5,          9,   11,      14,   16,   18,         22,   24,   26,                     34,   36)
// ======            ======         ======      ============      ======                        ======
        ).toFragments();
        assertEquals(3, result.size());
        assertEquals(Range.create(11, 14), result.get(0));
        assertEquals(Range.create(16, 18), result.get(1));
        assertEquals(Range.create(22, 24), result.get(2));
    }
    
    @Test
    public void testMasked__nn6() {
        List<Range<Integer>> result;

        result = RangeSet.create(
    2,    5,          9,   11,      14,   16,   18,         22,   24,   26,                     34,   36)
// ======            ======         ======      ============      ======                        ======
        .maskedBy(RangeSet.create(
                                       15,            20                                                   )
//                                     ===============                                                   
        ).toFragments();
        assertEquals(6, result.size());
        assertEquals(Range.create( 2,  5), result.get(0));
        assertEquals(Range.create( 9, 11), result.get(1));
        assertEquals(Range.create(14, 15), result.get(2));
        assertEquals(Range.create(20, 22), result.get(3));
        assertEquals(Range.create(24, 26), result.get(4));
        assertEquals(Range.create(34, 36), result.get(5));
    }

    @Test
    public void testMasked__nn6reverse() {
        List<Range<Integer>> result;

        result = RangeSet.create(
                                       15,            20                                                   )
//                                     ===============                                                   
        .maskedBy(RangeSet.create(
    2,    5,          9,   11,      14,   16,   18,         22,   24,   26,                     34,   36)
// ======            ======         ======      ============      ======                        ======
        ).toFragments();
        assertEquals(1, result.size());
        assertEquals(Range.create(16, 18), result.get(0));
    }

    @Test
    public void testMasked__nn7() {
        List<Range<Integer>> result;

        result = RangeSet.create(
                                 13,                           23                                             )
//                               ==============================                                                   
        .maskedBy(RangeSet.create(
    2,    5,          9,   11,      14,   16,   18,         22,   24,   26,                     34,   36)
// ======            ======         ======      ============      ======                        ======
        ).toFragments();
        assertEquals(3, result.size());
        assertEquals(Range.create(13, 14), result.get(0));
        assertEquals(Range.create(16, 18), result.get(1));
        assertEquals(Range.create(22, 23), result.get(2));
    }

    @Test
    public void testMasked__nn7reverse() {
        List<Range<Integer>> result;

        result = RangeSet.create(
    2,    5,          9,   11,      14,   16,   18,         22,   24,   26,                     34,   36)
// ======            ======         ======      ============      ======                        ======
        .maskedBy(RangeSet.create(
                                 13,                           23                                             )
//                               ==============================                                                   
        ).toFragments();
        assertEquals(4, result.size());
        assertEquals(Range.create( 2,  5), result.get(0));
        assertEquals(Range.create( 9, 11), result.get(1));
        assertEquals(Range.create(24, 26), result.get(2));
        assertEquals(Range.create(34, 36), result.get(3));
    }
    
    @Test
    public void testMasked__nn8() {
        List<Range<Integer>> result;

        result = RangeSet.create(
                                    14,                     22                                             )
//                                  ========================                                                   
        .maskedBy(RangeSet.create(
    2,    5,          9,   11,      14,   16,   18,         22,   24,   26,                     34,   36)
// ======            ======         ======      ============      ======                        ======
        ).toFragments();
        assertEquals(1, result.size());
        assertEquals(Range.create(16, 18), result.get(0));
    }

    @Test
    public void testMasked__nn8reverse() {
        List<Range<Integer>> result;

        result = RangeSet.create(
    2,    5,          9,   11,      14,   16,   18,         22,   24,   26,                     34,   36)
// ======            ======         ======      ============      ======                        ======
        .maskedBy(RangeSet.create(
                                    14,                     22                                             )
//                                  ========================                                                   
        ).toFragments();
        assertEquals(4, result.size());
        assertEquals(Range.create( 2,  5), result.get(0));
        assertEquals(Range.create( 9, 11), result.get(1));
        assertEquals(Range.create(24, 26), result.get(2));
        assertEquals(Range.create(34, 36), result.get(3));

    }

    @Test
    public void testMasked__overlap1() {
        List<Range<Integer>> result;

        result = RangeSet.create(
                                 13,         17,18,         22,   24,   26                  )
//                               ============   ============      ======                  
        .maskedBy(RangeSet.create(
                           11,      14,   16,      19,   21,         25                  )
//                         =========      =========      ============                   
        ).toFragments();
        assertEquals(3, result.size());
        assertEquals(Range.create(14, 16), result.get(0));
        assertEquals(Range.create(19, 21), result.get(1));
        assertEquals(Range.create(25, 26), result.get(2));
    }

    @Test
    public void testMasked__overlap2() {
        List<Range<Integer>> result;

        result = RangeSet.create(
                                                18,         22,   24,   26                  )
//                                              ============      ======                  
        .maskedBy(RangeSet.create(
                                             17,   19,   21,         25                  )
//                                           ======      ============                   
        ).toFragments();
        assertEquals(2, result.size());
        assertEquals(Range.create(19, 21), result.get(0));
        assertEquals(Range.create(25, 26), result.get(1));
    }

    @Test
    public void testMasked__overlap3() {
        List<Range<Integer>> result;

        result = RangeSet.create(
                                 13,         17,18,         22                              )
//                               ============   ============                              
        .maskedBy(RangeSet.create(
                                          16,      19,   21,         25                  )
//                                        =========      ============                   
        ).toFragments();
        assertEquals(2, result.size());
        assertEquals(Range.create(13, 16), result.get(0));
        assertEquals(Range.create(19, 21), result.get(1));
    }

    @Test
    public void testMasked__overlap4() {
        List<Range<Integer>> result;

        result = RangeSet.create(
                                 13,         17,                  24,   26                  )
//                               ============                     ======                  
        .maskedBy(RangeSet.create(
                                          16,      19,   21,         25                  )
//                                        =========      ============                   
        ).toFragments();
        assertEquals(2, result.size());
        assertEquals(Range.create(13, 16), result.get(0));
        assertEquals(Range.create(25, 26), result.get(1));
    }

    @Test
    public void testMasked__overlap5() {
        List<Range<Integer>> result;

        result = RangeSet.create(
                                 13,         17,18,         22                              )
//                               ============   ============                              
        .maskedBy(RangeSet.create(
                           11,      14,                  21,         25                  )
//                         =========                     ============                   
        ).toFragments();
        assertEquals(2, result.size());
        assertEquals(Range.create(14, 17), result.get(0));
        assertEquals(Range.create(18, 21), result.get(1));
    }

    @Test
    public void testMask__basic() {
        assertEquals(Range.create(15, 17), RangeSet.create(10, 15).mask(RangeSet.create(15, 17)).toFragments().get(0));
        assertEquals(Range.create(10, 15), RangeSet.create(15, 17).mask(RangeSet.create(10, 15)).toFragments().get(0));
        assertEquals(Range.create(17, 18), RangeSet.create(10, 15).mask(RangeSet.create(17, 18)).toFragments().get(0));
        assertEquals(Range.create(10, 15), RangeSet.create(17, 18).mask(RangeSet.create(10, 15)).toFragments().get(0));
        assertEquals(Range.create(15, 17), RangeSet.create(10, 15).mask(RangeSet.create(13, 17)).toFragments().get(0));
        assertEquals(Range.create(10, 13), RangeSet.create(13, 17).mask(RangeSet.create(10, 15)).toFragments().get(0));
        assertTrue(RangeSet.create(10, 17).mask(RangeSet.create(13, 15)).isEmpty());
        assertEquals(Range.create(10, 13), RangeSet.create(13, 15).mask(RangeSet.create(10, 17)).toFragments().get(0));
        assertEquals(Range.create(15, 17), RangeSet.create(13, 15).mask(RangeSet.create(10, 17)).toFragments().get(1));
    }
    
    @Test
    public void testMask__nn1() {
        List<Range<Integer>> result;

        result = RangeSet.create(
       4,    6,    8,   10,      13,         17,   19,   21,               27,   29,   31,   33)
//    ======      ======         ============      ======                  ======      ======
        .mask(RangeSet.create(
    2,    5,          9,   11,      14,   16,   18,         22,   24,   26,                     34,   36)
// ======            ======         ======      ============      ======                        ======
        ).toFragments();
        assertEquals(6, result.size());
        assertEquals(Range.create( 2,  4), result.get(0));
        assertEquals(Range.create(10, 11), result.get(1));
        assertEquals(Range.create(18, 19), result.get(2));
        assertEquals(Range.create(21, 22), result.get(3));
        assertEquals(Range.create(24, 26), result.get(4));
        assertEquals(Range.create(34, 36), result.get(5));
    }
    
    @Test
    public void testMask__nn1reverse() {
        List<Range<Integer>> result;

        result = RangeSet.create(
    2,    5,          9,   11,      14,   16,   18,         22,   24,   26,                     34,   36)
// ======            ======         ======      ============      ======                        ======
        .mask(RangeSet.create(
       4,    6,    8,   10,      13,         17,   19,   21,               27,   29,   31,   33)
//    ======      ======         ============      ======                  ======      ======
        ).toFragments();
        assertEquals(6, result.size());
        assertEquals(Range.create( 5,  6), result.get(0));
        assertEquals(Range.create( 8,  9), result.get(1));
        assertEquals(Range.create(13, 14), result.get(2));
        assertEquals(Range.create(16, 17), result.get(3));
        assertEquals(Range.create(27, 29), result.get(4));
        assertEquals(Range.create(31, 33), result.get(5));
    }
    
    @Test
    public void testMask__nn2() {
        List<Range<Integer>> result;

        result = RangeSet.create(
 1,                                                                                                      37)
//=======================================================================================================
        .mask(RangeSet.create(
    2,    5,          9,   11,      14,   16,   18,         22,   24,   26,                     34,   36))
// ======            ======         ======      ============      ======                        ======
        .toFragments();
        assertEquals(0, result.size());
    }

    @Test
    public void testMask__nn2reverse() {
        List<Range<Integer>> result;

        result = RangeSet.create(
    2,    5,          9,   11,      14,   16,   18,         22,   24,   26,                     34,   36)
// ======            ======         ======      ============      ======                        ======
        .mask(RangeSet.create(
 1,                                                                                                      37)
//=======================================================================================================
        ).toFragments();
        assertEquals(7, result.size());
        assertEquals(Range.create( 1,  2), result.get(0));
        assertEquals(Range.create( 5,  9), result.get(1));
        assertEquals(Range.create(11, 14), result.get(2));
        assertEquals(Range.create(16, 18), result.get(3));
        assertEquals(Range.create(22, 24), result.get(4));
        assertEquals(Range.create(26, 34), result.get(5));
        assertEquals(Range.create(36, 37), result.get(6));
    }
    
    @Test
    public void testMask__nn3() {
        List<Range<Integer>> result;

        result = RangeSet.create(
    2,                                                                                                36   )
// ===================================================================================================   
        .mask(RangeSet.create(
    2,    5,          9,   11,      14,   16,   18,         22,   24,   26,                     34,   36))
// ======            ======         ======      ============      ======                        ======
        .toFragments();
        assertEquals(0, result.size());
    }

    @Test
    public void testMask__nn3reverse() {
        List<Range<Integer>> result;

        result = RangeSet.create(
    2,    5,          9,   11,      14,   16,   18,         22,   24,   26,                     34,   36)
// ======            ======         ======      ============      ======                        ======
        .mask(RangeSet.create(
    2,                                                                                                36   )
// ===================================================================================================   
        ).toFragments();
        assertEquals(5, result.size());
        assertEquals(Range.create( 5,  9), result.get(0));
        assertEquals(Range.create(11, 14), result.get(1));
        assertEquals(Range.create(16, 18), result.get(2));
        assertEquals(Range.create(22, 24), result.get(3));
        assertEquals(Range.create(26, 34), result.get(4));
    }

    @Test
    public void testMask__nn4() {
        List<Range<Integer>> result;

        result = RangeSet.create(
       3,                                                                                          35      )
//    =============================================================================================      
        .mask(RangeSet.create(
    2,    5,          9,   11,      14,   16,   18,         22,   24,   26,                     34,   36)
// ======            ======         ======      ============      ======                        ======
        ).toFragments();
        assertEquals(2, result.size());
        assertEquals(Range.create( 2,  3), result.get(0));
        assertEquals(Range.create(35, 36), result.get(1));
    }

    @Test
    public void testMask__nn4reverse() {
        List<Range<Integer>> result;

        result = RangeSet.create(
    2,    5,          9,   11,      14,   16,   18,         22,   24,   26,                     34,   36)
// ======            ======         ======      ============      ======                        ======
        .mask(RangeSet.create(
       3,                                                                                          35      )
//    =============================================================================================      
        ).toFragments();
        assertEquals(5, result.size());
        assertEquals(Range.create( 5,  9), result.get(0));
        assertEquals(Range.create(11, 14), result.get(1));
        assertEquals(Range.create(16, 18), result.get(2));
        assertEquals(Range.create(22, 24), result.get(3));
        assertEquals(Range.create(26, 34), result.get(4));
    }
    
    @Test
    public void testMask__nn5() {
        List<Range<Integer>> result;

        result = RangeSet.create(
                           11,                                    24                                       )
//                         =======================================                                       
        .mask(RangeSet.create(
    2,    5,          9,   11,      14,   16,   18,         22,   24,   26,                     34,   36)
// ======            ======         ======      ============      ======                        ======
        ).toFragments();
        assertEquals(4, result.size());
        assertEquals(Range.create( 2,  5), result.get(0));
        assertEquals(Range.create( 9, 11), result.get(1));
        assertEquals(Range.create(24, 26), result.get(2));
        assertEquals(Range.create(34, 36), result.get(3));
    }

    @Test
    public void testMask__nn5reverse() {
        List<Range<Integer>> result;

        result = RangeSet.create(
    2,    5,          9,   11,      14,   16,   18,         22,   24,   26,                     34,   36)
// ======            ======         ======      ============      ======                        ======
        .mask(RangeSet.create(
                           11,                                    24                                       )
//                         =======================================                                       
        ).toFragments();
        assertEquals(3, result.size());
        assertEquals(Range.create(11, 14), result.get(0));
        assertEquals(Range.create(16, 18), result.get(1));
        assertEquals(Range.create(22, 24), result.get(2));
    }
    
    @Test
    public void testMask__nn6() {
        List<Range<Integer>> result;

        result = RangeSet.create(
                                       15,            20                                                   )
//                                     ===============                                                   
        .mask(RangeSet.create(
    2,    5,          9,   11,      14,   16,   18,         22,   24,   26,                     34,   36)
// ======            ======         ======      ============      ======                        ======
        ).toFragments();
        assertEquals(6, result.size());
        assertEquals(Range.create( 2,  5), result.get(0));
        assertEquals(Range.create( 9, 11), result.get(1));
        assertEquals(Range.create(14, 15), result.get(2));
        assertEquals(Range.create(20, 22), result.get(3));
        assertEquals(Range.create(24, 26), result.get(4));
        assertEquals(Range.create(34, 36), result.get(5));
    }

    @Test
    public void testMask__nn6reverse() {
        List<Range<Integer>> result;

        result = RangeSet.create(
    2,    5,          9,   11,      14,   16,   18,         22,   24,   26,                     34,   36)
// ======            ======         ======      ============      ======                        ======
        .mask(RangeSet.create(
                                       15,            20                                                   )
//                                     ===============                                                   
        ).toFragments();
        assertEquals(1, result.size());
        assertEquals(Range.create(16, 18), result.get(0));
    }

    @Test
    public void testMask__nn7() {
        List<Range<Integer>> result;

        result = RangeSet.create(
    2,    5,          9,   11,      14,   16,   18,         22,   24,   26,                     34,   36)
// ======            ======         ======      ============      ======                        ======
        .mask(RangeSet.create(
                                 13,                           23                                             )
//                               ==============================                                                   
        ).toFragments();
        assertEquals(3, result.size());
        assertEquals(Range.create(13, 14), result.get(0));
        assertEquals(Range.create(16, 18), result.get(1));
        assertEquals(Range.create(22, 23), result.get(2));
    }

    @Test
    public void testMask__nn7reverse() {
        List<Range<Integer>> result;

        result = RangeSet.create(
                                 13,                           23                                             )
//                               ==============================                                                   
        .mask(RangeSet.create(
    2,    5,          9,   11,      14,   16,   18,         22,   24,   26,                     34,   36)
// ======            ======         ======      ============      ======                        ======
        ).toFragments();
        assertEquals(4, result.size());
        assertEquals(Range.create( 2,  5), result.get(0));
        assertEquals(Range.create( 9, 11), result.get(1));
        assertEquals(Range.create(24, 26), result.get(2));
        assertEquals(Range.create(34, 36), result.get(3));
    }
    
    @Test
    public void testMask__nn8() {
        List<Range<Integer>> result;

        result = RangeSet.create(
    2,    5,          9,   11,      14,   16,   18,         22,   24,   26,                     34,   36)
// ======            ======         ======      ============      ======                        ======
        .mask(RangeSet.create(
                                    14,                     22                                             )
//                                  ========================                                                   
        ).toFragments();
        assertEquals(1, result.size());
        assertEquals(Range.create(16, 18), result.get(0));
    }

    @Test
    public void testMask__nn8reverse() {
        List<Range<Integer>> result;

        result = RangeSet.create(
                                    14,                     22                                             )
//                                  ========================                                                   
        .mask(RangeSet.create(
    2,    5,          9,   11,      14,   16,   18,         22,   24,   26,                     34,   36)
// ======            ======         ======      ============      ======                        ======
        ).toFragments();
        assertEquals(4, result.size());
        assertEquals(Range.create( 2,  5), result.get(0));
        assertEquals(Range.create( 9, 11), result.get(1));
        assertEquals(Range.create(24, 26), result.get(2));
        assertEquals(Range.create(34, 36), result.get(3));

    }

    @Test
    public void testMask__overlap1() {
        List<Range<Integer>> result;

        result = RangeSet.create(
                           11,      14,   16,      19,   21,         25                  )
//                         =========      =========      ============                   
        .mask(RangeSet.create(
                                 13,         17,18,         22,   24,   26                  )
//                               ============   ============      ======                  
        ).toFragments();
        assertEquals(3, result.size());
        assertEquals(Range.create(14, 16), result.get(0));
        assertEquals(Range.create(19, 21), result.get(1));
        assertEquals(Range.create(25, 26), result.get(2));
    }

    @Test
    public void testMask__overlap2() {
        List<Range<Integer>> result;

        result = RangeSet.create(
                                             17,   19,   21,         25                  )
//                                           ======      ============                   
        .mask(RangeSet.create(
                                                18,         22,   24,   26                  )
//                                              ============      ======                  
        ).toFragments();
        assertEquals(2, result.size());
        assertEquals(Range.create(19, 21), result.get(0));
        assertEquals(Range.create(25, 26), result.get(1));
    }

    @Test
    public void testMask__overlap3() {
        List<Range<Integer>> result;

        result = RangeSet.create(
                                          16,      19,   21,         25                  )
//                                        =========      ============                   
        .mask(RangeSet.create(
                                 13,         17,18,         22                              )
//                               ============   ============                              
        ).toFragments();
        assertEquals(2, result.size());
        assertEquals(Range.create(13, 16), result.get(0));
        assertEquals(Range.create(19, 21), result.get(1));
    }

    @Test
    public void testMask__overlap4() {
        List<Range<Integer>> result;

        result = RangeSet.create(
                                          16,      19,   21,         25                  )
//                                        =========      ============                   
        .mask(RangeSet.create(
                                 13,         17,                  24,   26                  )
//                               ============                     ======                  
        ).toFragments();
        assertEquals(2, result.size());
        assertEquals(Range.create(13, 16), result.get(0));
        assertEquals(Range.create(25, 26), result.get(1));
    }

    @Test
    public void testMask__overlap5() {
        List<Range<Integer>> result;

        result = RangeSet.create(
                           11,      14,                  21,         25                  )
//                         =========                     ============                   
        .mask(RangeSet.create(
                                 13,         17,18,         22                              )
//                               ============   ============                              
        ).toFragments();
        assertEquals(2, result.size());
        assertEquals(Range.create(14, 17), result.get(0));
        assertEquals(Range.create(18, 21), result.get(1));
    }

    @Test
    public void testCover() {
        assertEquals(Range.create(13, 26), RangeSet.create(
                                 13,         17,                  24,   26                  )
//                               ============                     ======                  
        .cover().orElse(null));
    }

    public void testUnion__nn1() {
        List<Range<Integer>> result;

        result = RangeSet.create(
    2,    5,          9,   11,      14,   16,   18,         22,   24,   26,                     34,   36)
// ======            ======         ======      ============      ======                        ======
        .union(RangeSet.create(
       4,    6,    8,   10,      13,         17,   19,   21,               27,   29,   31,   33)
//    ======      ======         ============      ======                  ======      ======
        ).toFragments();
        assertEquals(8, result.size());
        assertEquals(Range.create( 2,  6), result.get(0));
        assertEquals(Range.create( 8, 11), result.get(1));
        assertEquals(Range.create(13, 17), result.get(2));
        assertEquals(Range.create(18, 22), result.get(3));
        assertEquals(Range.create(24, 26), result.get(4));
        assertEquals(Range.create(27, 29), result.get(5));
        assertEquals(Range.create(31, 33), result.get(6));
        assertEquals(Range.create(34, 36), result.get(7));
    }

    @Test
    public void testUnion__overlap1() {
        List<Range<Integer>> result;

        result = RangeSet.create(
                                 13,         17,18,         22,   24,   26                  )
//                               ============   ============      ======                  
        .union(RangeSet.create(
                           11,      14,   16,      19,   21,         25                  )
//                         =========      =========      ============                   
        ).toFragments();
        assertEquals(1, result.size());
        assertEquals(Range.create(11, 26), result.get(0));
    }

}