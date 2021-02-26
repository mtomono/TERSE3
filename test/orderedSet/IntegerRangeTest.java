/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package orderedSet;

import collection.TList;
import java.util.Optional;
import static orderedSet.Builder.intRange;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.Test;

/**
 *
 * @author mtomono
 */
public class IntegerRangeTest {
    
    public IntegerRangeTest() {
    }

    /**
     * Test of clone method, of class Fragment.
     */
    @Test
    public void testClone() {
        Range<Integer> original = intRange.r(-12, 2);
        Range<Integer> tested = (Range<Integer>)original.clone();
        assertEquals(original.start(), tested.start());
        assertEquals(original.end(), tested.end());
    }
    
    @Test
    public void testEquals() {
        class x {
            Range<Integer> tested = intRange.r(-12, 2);
            boolean test(Range<Integer> f) {
                return tested.equals(f);
            }
        }
        assertEquals(false, new x().test(intRange.r(-36, -14))); //-   - |     |
        assertEquals(false, new x().test(intRange.r(-36, -13))); //-    -|     |
        assertEquals(false, new x().test(intRange.r(-36, -12))); //-     +     |
        assertEquals(false, new x().test(intRange.r(-36, -11))); //-     |-    |
        assertEquals(false, new x().test(intRange.r(-36, -10))); //-     | -   |
        assertEquals(false, new x().test(intRange.r(-36,   0))); //-     |   - |
        assertEquals(false, new x().test(intRange.r(-36,   1))); //-     |    -|
        assertEquals(false, new x().test(intRange.r(-36,   2))); //-     |     +
        assertEquals(false, new x().test(intRange.r(-36,   3))); //-     |     |-
        assertEquals(false, new x().test(intRange.r(-36,   4))); //-     |     | -
        assertEquals(false, new x().test(intRange.r(-14,  24))); //    - |     |    -
        assertEquals(false, new x().test(intRange.r(-13,  24))); //     -|     |    -
        assertEquals(false, new x().test(intRange.r(-12,  24))); //      +     |    -
        assertEquals(false, new x().test(intRange.r(-11,  24))); //      |-    |    -
        assertEquals(false, new x().test(intRange.r(-10,  24))); //      | -   |    -
        assertEquals(false, new x().test(intRange.r(  0,  24))); //      |   - |    -
        assertEquals(false, new x().test(intRange.r(  1,  24))); //      |    -|    -
        assertEquals(false, new x().test(intRange.r(  2,  24))); //      |     +    -
        assertEquals(false, new x().test(intRange.r(  3,  24))); //      |     |-   -
        assertEquals(false, new x().test(intRange.r(  4,  24))); //      |     | -  -
        assertEquals(false, new x().test(intRange.r(-14,   0))); //    - |   - |
        assertEquals(false, new x().test(intRange.r(-13,   0))); //     -|   - |
        assertEquals(false, new x().test(intRange.r(-12,   0))); //      +   - |
        assertEquals(false, new x().test(intRange.r(-11,   0))); //      |-  - |
        assertEquals(false, new x().test(intRange.r(-10,   0))); //      | - - |
        assertEquals(false, new x().test(intRange.r(-10,   1))); //      | -  -|
        assertEquals(false, new x().test(intRange.r(-10,   2))); //      | -   +
        assertEquals(false, new x().test(intRange.r(-10,   3))); //      | -   |-
        assertEquals(false, new x().test(intRange.r(-10,   4))); //      | -   | -
        assertEquals(false, new x().test(intRange.r(-14,   2))); //    - |     +
        assertEquals(false, new x().test(intRange.r(-13,   2))); //     -|     +
        assertEquals(true , new x().test(intRange.r(-12,   2))); //      +     +
        assertEquals(false, new x().test(intRange.r(-11,   2))); //      |-    +
        assertEquals(false, new x().test(intRange.r(-10,   2))); //      | -   +
        assertEquals(false, new x().test(intRange.r(-12,   0))); //      +   - |
        assertEquals(false, new x().test(intRange.r(-12,   1))); //      +    -|
        assertEquals(true , new x().test(intRange.r(-12,   2))); //      +     +
        assertEquals(false, new x().test(intRange.r(-12,   3))); //      +     |-
        assertEquals(false, new x().test(intRange.r(-12,   4))); //      +     | -
    }
    
    /**
     * Test of getStart method, of class Fragment.
     */
    @Test
    public void testGetStart() {
        Range<Integer> tested = intRange.r(-12, 2);
        assertEquals(Integer.valueOf(-12), tested.start());
    }

    /**
     * Test of getEnd method, of class Fragment.
     */
    @Test
    public void testGetEnd() {
        Range<Integer> tested = intRange.r(-12, 2);
        assertEquals(Integer.valueOf(2), tested.end());
    }

    /**
     * Test of contains method, of class Fragment.
     */
    @Test
    public void testContains_GenericType() {
        class y {
            Range<Integer> tested = intRange.r(-12, 2);
            boolean test(int f) {
                return tested.contains(f);
            }
        }
        assertEquals(false, new y().test(-14));
        assertEquals(false, new y().test(-13));
        assertEquals(true , new y().test(-12));
        assertEquals(true , new y().test(-11));
        assertEquals(true , new y().test(-10));
        assertEquals(true , new y().test(0));
        assertEquals(true , new y().test(1));
        assertEquals(false, new y().test(2));
        assertEquals(false, new y().test(3));
        assertEquals(false, new y().test(4));
    }

    /**
     * Test of isAbove method, of class Fragment.
     */
    @Test
    public void testIsAbove_GenericType() {
        class y {
            Range<Integer> tested = intRange.r(-12, 2);
            boolean test(int f) {
                return tested.isAbove(f);
            }
        }
        assertEquals(true , new y().test(-14));
        assertEquals(true , new y().test(-13));
        assertEquals(false, new y().test(-12));
        assertEquals(false, new y().test(-11));
        assertEquals(false, new y().test(-10));
        assertEquals(false, new y().test(0));
        assertEquals(false, new y().test(1));
        assertEquals(false, new y().test(2));
        assertEquals(false, new y().test(3));
        assertEquals(false, new y().test(4));
    }

    /**
     * Test of isAbove method, of class Fragment.
     */
    @Test
    public void testIsUpperThan_Fragment() {
        class x {
            Range<Integer> tested = intRange.r(-12, 2);
            boolean test(Range<Integer> f) {
                return tested.isUpperThan(f);
            }
        }
        assertEquals(true , new x().test(intRange.r(-36, -14))); //-   - |     |
        assertEquals(true , new x().test(intRange.r(-36, -13))); //-    -|     |
        assertEquals(true , new x().test(intRange.r(-36, -12))); //-     +     |
        assertEquals(false, new x().test(intRange.r(-36, -11))); //-     |-    |
        assertEquals(false, new x().test(intRange.r(-36, -10))); //-     | -   |
        assertEquals(false, new x().test(intRange.r(-36,   0))); //-     |   - |
        assertEquals(false, new x().test(intRange.r(-36,   1))); //-     |    -|
        assertEquals(false, new x().test(intRange.r(-36,   2))); //-     |     +
        assertEquals(false, new x().test(intRange.r(-36,   3))); //-     |     |-
        assertEquals(false, new x().test(intRange.r(-36,   4))); //-     |     | -
        assertEquals(false, new x().test(intRange.r(-14,  24))); //    - |     |    -
        assertEquals(false, new x().test(intRange.r(-13,  24))); //     -|     |    -
        assertEquals(false, new x().test(intRange.r(-12,  24))); //      +     |    -
        assertEquals(false, new x().test(intRange.r(-11,  24))); //      |-    |    -
        assertEquals(false, new x().test(intRange.r(-10,  24))); //      | -   |    -
        assertEquals(false, new x().test(intRange.r(  0,  24))); //      |   - |    -
        assertEquals(false, new x().test(intRange.r(  1,  24))); //      |    -|    -
        assertEquals(false, new x().test(intRange.r(  2,  24))); //      |     +    -
        assertEquals(false, new x().test(intRange.r(  3,  24))); //      |     |-   -
        assertEquals(false, new x().test(intRange.r(  4,  24))); //      |     | -  -
        assertEquals(false, new x().test(intRange.r(-14,   0))); //    - |   - |
        assertEquals(false, new x().test(intRange.r(-13,   0))); //     -|   - |
        assertEquals(false, new x().test(intRange.r(-12,   0))); //      +   - |
        assertEquals(false, new x().test(intRange.r(-11,   0))); //      |-  - |
        assertEquals(false, new x().test(intRange.r(-10,   0))); //      | - - |
        assertEquals(false, new x().test(intRange.r(-10,   1))); //      | -  -|
        assertEquals(false, new x().test(intRange.r(-10,   2))); //      | -   +
        assertEquals(false, new x().test(intRange.r(-10,   3))); //      | -   |-
        assertEquals(false, new x().test(intRange.r(-10,   4))); //      | -   | -
        assertEquals(false, new x().test(intRange.r(-14,   2))); //    - |     +
        assertEquals(false, new x().test(intRange.r(-13,   2))); //     -|     +
        assertEquals(false, new x().test(intRange.r(-12,   2))); //      +     +
        assertEquals(false, new x().test(intRange.r(-11,   2))); //      |-    +
        assertEquals(false, new x().test(intRange.r(-10,   2))); //      | -   +
        assertEquals(false, new x().test(intRange.r(-12,   0))); //      +   - |
        assertEquals(false, new x().test(intRange.r(-12,   1))); //      +    -|
        assertEquals(false, new x().test(intRange.r(-12,   2))); //      +     +
        assertEquals(false, new x().test(intRange.r(-12,   3))); //      +     |-
        assertEquals(false, new x().test(intRange.r(-12,   4))); //      +     | -
    }

    /**
     * Test of isBelow method, of class Fragment.
     */
    @Test
    public void testIsBelow_GenericType() {
        class y {
            Range<Integer> tested = intRange.r(-12, 2);
            boolean test(int f) {
                return tested.isBelow(f);
            }
        }
        assertEquals(false, new y().test(-14));
        assertEquals(false, new y().test(-13));
        assertEquals(false, new y().test(-12));
        assertEquals(false, new y().test(-11));
        assertEquals(false, new y().test(-10));
        assertEquals(false, new y().test(0));
        assertEquals(false, new y().test(1));
        assertEquals(true , new y().test(2));
        assertEquals(true , new y().test(3));
        assertEquals(true , new y().test(4));
    }

    /**
     * Test of isBelow method, of class Fragment.
     */
    @Test
    public void testIsLowerThan_Fragment() {
        class x {
            Range<Integer> tested = intRange.r(-12, 2);
            boolean test(Range<Integer> f) {
                return tested.isLowerThan(f);
            }
        }
        assertEquals(false, new x().test(intRange.r(-36, -14))); //-   - |     |
        assertEquals(false, new x().test(intRange.r(-36, -13))); //-    -|     |
        assertEquals(false, new x().test(intRange.r(-36, -12))); //-     +     |
        assertEquals(false, new x().test(intRange.r(-36, -11))); //-     |-    |
        assertEquals(false, new x().test(intRange.r(-36, -10))); //-     | -   |
        assertEquals(false, new x().test(intRange.r(-36,   0))); //-     |   - |
        assertEquals(false, new x().test(intRange.r(-36,   1))); //-     |    -|
        assertEquals(false, new x().test(intRange.r(-36,   2))); //-     |     +
        assertEquals(false, new x().test(intRange.r(-36,   3))); //-     |     |-
        assertEquals(false, new x().test(intRange.r(-36,   4))); //-     |     | -
        assertEquals(false, new x().test(intRange.r(-14,  24))); //    - |     |    -
        assertEquals(false, new x().test(intRange.r(-13,  24))); //     -|     |    -
        assertEquals(false, new x().test(intRange.r(-12,  24))); //      +     |    -
        assertEquals(false, new x().test(intRange.r(-11,  24))); //      |-    |    -
        assertEquals(false, new x().test(intRange.r(-10,  24))); //      | -   |    -
        assertEquals(false, new x().test(intRange.r(  0,  24))); //      |   - |    -
        assertEquals(false, new x().test(intRange.r(  1,  24))); //      |    -|    -
        assertEquals(true , new x().test(intRange.r(  2,  24))); //      |     +    -
        assertEquals(true , new x().test(intRange.r(  3,  24))); //      |     |-   -
        assertEquals(true , new x().test(intRange.r(  4,  24))); //      |     | -  -
        assertEquals(false, new x().test(intRange.r(-14,   0))); //    - |   - |
        assertEquals(false, new x().test(intRange.r(-13,   0))); //     -|   - |
        assertEquals(false, new x().test(intRange.r(-12,   0))); //      +   - |
        assertEquals(false, new x().test(intRange.r(-11,   0))); //      |-  - |
        assertEquals(false, new x().test(intRange.r(-10,   0))); //      | - - |
        assertEquals(false, new x().test(intRange.r(-10,   1))); //      | -  -|
        assertEquals(false, new x().test(intRange.r(-10,   2))); //      | -   +
        assertEquals(false, new x().test(intRange.r(-10,   3))); //      | -   |-
        assertEquals(false, new x().test(intRange.r(-10,   4))); //      | -   | -
        assertEquals(false, new x().test(intRange.r(-14,   2))); //    - |     +
        assertEquals(false, new x().test(intRange.r(-13,   2))); //     -|     +
        assertEquals(false, new x().test(intRange.r(-12,   2))); //      +     +
        assertEquals(false, new x().test(intRange.r(-11,   2))); //      |-    +
        assertEquals(false, new x().test(intRange.r(-10,   2))); //      | -   +
        assertEquals(false, new x().test(intRange.r(-12,   0))); //      +   - |
        assertEquals(false, new x().test(intRange.r(-12,   1))); //      +    -|
        assertEquals(false, new x().test(intRange.r(-12,   2))); //      +     +
        assertEquals(false, new x().test(intRange.r(-12,   3))); //      +     |-
        assertEquals(false, new x().test(intRange.r(-12,   4))); //      +     | -
    }

    /**
     * Test of doesOverrap method, of class Fragment.
     */
    @Test
    public void testDoesOverlap() {
        class x {
            Range<Integer> tested = intRange.r(-12, 2);
            boolean test(Range<Integer> f) {
                return tested.overlaps(f);
            }
        }
        assertEquals(false, new x().test(intRange.r(-36, -14))); //-   - |     |
        assertEquals(false, new x().test(intRange.r(-36, -13))); //-    -|     |
        assertEquals(false, new x().test(intRange.r(-36, -12))); //-     +     |
        assertEquals(true , new x().test(intRange.r(-36, -11))); //-     |-    |
        assertEquals(true , new x().test(intRange.r(-36, -10))); //-     | -   |
        assertEquals(true , new x().test(intRange.r(-36,   0))); //-     |   - |
        assertEquals(true , new x().test(intRange.r(-36,   1))); //-     |    -|
        assertEquals(true , new x().test(intRange.r(-36,   2))); //-     |     +
        assertEquals(true , new x().test(intRange.r(-36,   3))); //-     |     |-
        assertEquals(true , new x().test(intRange.r(-36,   4))); //-     |     | -
        assertEquals(true , new x().test(intRange.r(-14,  24))); //    - |     |    -
        assertEquals(true , new x().test(intRange.r(-13,  24))); //     -|     |    -
        assertEquals(true , new x().test(intRange.r(-12,  24))); //      +     |    -
        assertEquals(true , new x().test(intRange.r(-11,  24))); //      |-    |    -
        assertEquals(true , new x().test(intRange.r(-10,  24))); //      | -   |    -
        assertEquals(true , new x().test(intRange.r(  0,  24))); //      |   - |    -
        assertEquals(true , new x().test(intRange.r(  1,  24))); //      |    -|    -
        assertEquals(false, new x().test(intRange.r(  2,  24))); //      |     +    -
        assertEquals(false, new x().test(intRange.r(  3,  24))); //      |     |-   -
        assertEquals(false, new x().test(intRange.r(  4,  24))); //      |     | -  -
        assertEquals(true , new x().test(intRange.r(-14,   0))); //    - |   - |
        assertEquals(true , new x().test(intRange.r(-13,   0))); //     -|   - |
        assertEquals(true , new x().test(intRange.r(-12,   0))); //      +   - |
        assertEquals(true , new x().test(intRange.r(-11,   0))); //      |-  - |
        assertEquals(true , new x().test(intRange.r(-10,   0))); //      | - - |
        assertEquals(true , new x().test(intRange.r(-10,   1))); //      | -  -|
        assertEquals(true , new x().test(intRange.r(-10,   2))); //      | -   +
        assertEquals(true , new x().test(intRange.r(-10,   3))); //      | -   |-
        assertEquals(true , new x().test(intRange.r(-10,   4))); //      | -   | -
        assertEquals(true , new x().test(intRange.r(-14,   2))); //    - |     +
        assertEquals(true , new x().test(intRange.r(-13,   2))); //     -|     +
        assertEquals(true , new x().test(intRange.r(-12,   2))); //      +     +
        assertEquals(true , new x().test(intRange.r(-11,   2))); //      |-    +
        assertEquals(true , new x().test(intRange.r(-10,   2))); //      | -   +
        assertEquals(true , new x().test(intRange.r(-12,   0))); //      +   - |
        assertEquals(true , new x().test(intRange.r(-12,   1))); //      +    -|
        assertEquals(true , new x().test(intRange.r(-12,   2))); //      +     +
        assertEquals(true , new x().test(intRange.r(-12,   3))); //      +     |-
        assertEquals(true , new x().test(intRange.r(-12,   4))); //      +     | -
    }

    /**
     * Test of contains method, of class Fragment.
     */
    @Test
    public void testDoesContain_Fragment() {
        class x {
            Range<Integer> tested = intRange.r(-12, 2);
            boolean test(Range<Integer> f) {
                return tested.contains(f);
            }
        }
        assertEquals(false, new x().test(intRange.r(-36, -14))); //-   - |     |
        assertEquals(false, new x().test(intRange.r(-36, -13))); //-    -|     |
        assertEquals(false, new x().test(intRange.r(-36, -12))); //-     +     |
        assertEquals(false, new x().test(intRange.r(-36, -11))); //-     |-    |
        assertEquals(false, new x().test(intRange.r(-36, -10))); //-     | -   |
        assertEquals(false, new x().test(intRange.r(-36,   0))); //-     |   - |
        assertEquals(false, new x().test(intRange.r(-36,   1))); //-     |    -|
        assertEquals(false, new x().test(intRange.r(-36,   2))); //-     |     +
        assertEquals(false, new x().test(intRange.r(-36,   3))); //-     |     |-
        assertEquals(false, new x().test(intRange.r(-36,   4))); //-     |     | -
        assertEquals(false, new x().test(intRange.r(-14,  24))); //    - |     |    -
        assertEquals(false, new x().test(intRange.r(-13,  24))); //     -|     |    -
        assertEquals(false, new x().test(intRange.r(-12,  24))); //      +     |    -
        assertEquals(false, new x().test(intRange.r(-11,  24))); //      |-    |    -
        assertEquals(false, new x().test(intRange.r(-10,  24))); //      | -   |    -
        assertEquals(false, new x().test(intRange.r(  0,  24))); //      |   - |    -
        assertEquals(false, new x().test(intRange.r(  1,  24))); //      |    -|    -
        assertEquals(false, new x().test(intRange.r(  2,  24))); //      |     +    -
        assertEquals(false, new x().test(intRange.r(  3,  24))); //      |     |-   -
        assertEquals(false, new x().test(intRange.r(  4,  24))); //      |     | -  -
        assertEquals(false, new x().test(intRange.r(-14,   0))); //    - |   - |
        assertEquals(false, new x().test(intRange.r(-13,   0))); //     -|   - |
        assertEquals(true , new x().test(intRange.r(-12,   0))); //      +   - |
        assertEquals(true , new x().test(intRange.r(-11,   0))); //      |-  - |
        assertEquals(true , new x().test(intRange.r(-10,   0))); //      | - - |
        assertEquals(true , new x().test(intRange.r(-10,   1))); //      | -  -|
        assertEquals(true , new x().test(intRange.r(-10,   2))); //      | -   +
        assertEquals(false, new x().test(intRange.r(-10,   3))); //      | -   |-
        assertEquals(false, new x().test(intRange.r(-10,   4))); //      | -   | -
        assertEquals(false, new x().test(intRange.r(-14,   2))); //    - |     +
        assertEquals(false, new x().test(intRange.r(-13,   2))); //     -|     +
        assertEquals(true , new x().test(intRange.r(-12,   2))); //      +     +
        assertEquals(true , new x().test(intRange.r(-11,   2))); //      |-    +
        assertEquals(true , new x().test(intRange.r(-10,   2))); //      | -   +
        assertEquals(true , new x().test(intRange.r(-12,   0))); //      +   - |
        assertEquals(true , new x().test(intRange.r(-12,   1))); //      +    -|
        assertEquals(true , new x().test(intRange.r(-12,   2))); //      +     +
        assertEquals(false, new x().test(intRange.r(-12,   3))); //      +     |-
        assertEquals(false, new x().test(intRange.r(-12,   4))); //      +     | -
    }

    /**
     * Test of adjoins method, of class Fragment.
     */
    @Test
    public void testDoesAdjoin() {
        class x {
            Range<Integer> tested = intRange.r(-12, 2);
            boolean test(Range<Integer> f) {
                return tested.adjoins(f);
            }
        }
        assertEquals(false, new x().test(intRange.r(-36, -14))); //-   - |     |
        assertEquals(false, new x().test(intRange.r(-36, -13))); //-    -|     |
        assertEquals(true , new x().test(intRange.r(-36, -12))); //-     +     |
        assertEquals(false, new x().test(intRange.r(-36, -11))); //-     |-    |
        assertEquals(false, new x().test(intRange.r(-36, -10))); //-     | -   |
        assertEquals(false, new x().test(intRange.r(-36,   0))); //-     |   - |
        assertEquals(false, new x().test(intRange.r(-36,   1))); //-     |    -|
        assertEquals(false, new x().test(intRange.r(-36,   2))); //-     |     +
        assertEquals(false, new x().test(intRange.r(-36,   3))); //-     |     |-
        assertEquals(false, new x().test(intRange.r(-36,   4))); //-     |     | -
        assertEquals(false, new x().test(intRange.r(-14,  24))); //    - |     |    -
        assertEquals(false, new x().test(intRange.r(-13,  24))); //     -|     |    -
        assertEquals(false, new x().test(intRange.r(-12,  24))); //      +     |    -
        assertEquals(false, new x().test(intRange.r(-11,  24))); //      |-    |    -
        assertEquals(false, new x().test(intRange.r(-10,  24))); //      | -   |    -
        assertEquals(false, new x().test(intRange.r(  0,  24))); //      |   - |    -
        assertEquals(false, new x().test(intRange.r(  1,  24))); //      |    -|    -
        assertEquals(true , new x().test(intRange.r(  2,  24))); //      |     +    -
        assertEquals(false, new x().test(intRange.r(  3,  24))); //      |     |-   -
        assertEquals(false, new x().test(intRange.r(  4,  24))); //      |     | -  -
        assertEquals(false, new x().test(intRange.r(-14,   2))); //    - |     +
        assertEquals(false, new x().test(intRange.r(-13,   2))); //     -|     +
        assertEquals(false, new x().test(intRange.r(-12,   2))); //      +     +
        assertEquals(false, new x().test(intRange.r(-11,   2))); //      |-    +
        assertEquals(false, new x().test(intRange.r(-10,   2))); //      | -   +
        assertEquals(false, new x().test(intRange.r(-12,   0))); //      +   - |
        assertEquals(false, new x().test(intRange.r(-12,   1))); //      +    -|
        assertEquals(false, new x().test(intRange.r(-12,   2))); //      +     +
        assertEquals(false, new x().test(intRange.r(-12,   3))); //      +     |-
        assertEquals(false, new x().test(intRange.r(-12,   4))); //      +     | -
    }

    /**
     * Test of adjoinsAtStartWith method, of class Fragment.
     */
    @Test
    public void testDoesAdjoinAtStart() {
        class x {
            Range<Integer> tested = intRange.r(-12, 2);
            boolean test(Range<Integer> f) {
                return tested.adjoinsAtStartWith(f);
            }
        }
        assertEquals(false, new x().test(intRange.r(-36, -14))); //-   - |     |
        assertEquals(false, new x().test(intRange.r(-36, -13))); //-    -|     |
        assertEquals(true , new x().test(intRange.r(-36, -12))); //-     +     |
        assertEquals(false, new x().test(intRange.r(-36, -11))); //-     |-    |
        assertEquals(false, new x().test(intRange.r(-36, -10))); //-     | -   |
        assertEquals(false, new x().test(intRange.r(-36,   0))); //-     |   - |
        assertEquals(false, new x().test(intRange.r(-36,   1))); //-     |    -|
        assertEquals(false, new x().test(intRange.r(-36,   2))); //-     |     +
        assertEquals(false, new x().test(intRange.r(-36,   3))); //-     |     |-
        assertEquals(false, new x().test(intRange.r(-36,   4))); //-     |     | -
        assertEquals(false, new x().test(intRange.r(-14,  24))); //    - |     |    -
        assertEquals(false, new x().test(intRange.r(-13,  24))); //     -|     |    -
        assertEquals(false, new x().test(intRange.r(-12,  24))); //      +     |    -
        assertEquals(false, new x().test(intRange.r(-11,  24))); //      |-    |    -
        assertEquals(false, new x().test(intRange.r(-10,  24))); //      | -   |    -
        assertEquals(false, new x().test(intRange.r(  0,  24))); //      |   - |    -
        assertEquals(false, new x().test(intRange.r(  1,  24))); //      |    -|    -
        assertEquals(false, new x().test(intRange.r(  2,  24))); //      |     +    -
        assertEquals(false, new x().test(intRange.r(  3,  24))); //      |     |-   -
        assertEquals(false, new x().test(intRange.r(  4,  24))); //      |     | -  -
        assertEquals(false, new x().test(intRange.r(-14,   0))); //    - |   - |
        assertEquals(false, new x().test(intRange.r(-13,   0))); //     -|   - |
        assertEquals(false, new x().test(intRange.r(-12,   0))); //      +   - |
        assertEquals(false, new x().test(intRange.r(-11,   0))); //      |-  - |
        assertEquals(false, new x().test(intRange.r(-10,   0))); //      | - - |
        assertEquals(false, new x().test(intRange.r(-10,   1))); //      | -  -|
        assertEquals(false, new x().test(intRange.r(-10,   2))); //      | -   +
        assertEquals(false, new x().test(intRange.r(-10,   3))); //      | -   |-
        assertEquals(false, new x().test(intRange.r(-10,   4))); //      | -   | -
        assertEquals(false, new x().test(intRange.r(-14,   2))); //    - |     +
        assertEquals(false, new x().test(intRange.r(-13,   2))); //     -|     +
        assertEquals(false, new x().test(intRange.r(-12,   2))); //      +     +
        assertEquals(false, new x().test(intRange.r(-11,   2))); //      |-    +
        assertEquals(false, new x().test(intRange.r(-10,   2))); //      | -   +
        assertEquals(false, new x().test(intRange.r(-12,   0))); //      +   - |
        assertEquals(false, new x().test(intRange.r(-12,   1))); //      +    -|
        assertEquals(false, new x().test(intRange.r(-12,   2))); //      +     +
        assertEquals(false, new x().test(intRange.r(-12,   3))); //      +     |-
        assertEquals(false, new x().test(intRange.r(-12,   4))); //      +     | -
    }

    /**
     * Test of adjoinsAtEndWith method, of class Fragment.
     */
    @Test
    public void testDoesAdjoinAtEnd() {
        class x {
            Range<Integer> tested = intRange.r(-12, 2);
            boolean test(Range<Integer> f) {
                return tested.adjoinsAtEndWith(f);
            }
        }
        assertEquals(false, new x().test(intRange.r(-36, -14))); //-   - |     |
        assertEquals(false, new x().test(intRange.r(-36, -13))); //-    -|     |
        assertEquals(false, new x().test(intRange.r(-36, -12))); //-     +     |
        assertEquals(false, new x().test(intRange.r(-36, -11))); //-     |-    |
        assertEquals(false, new x().test(intRange.r(-36, -10))); //-     | -   |
        assertEquals(false, new x().test(intRange.r(-36,   0))); //-     |   - |
        assertEquals(false, new x().test(intRange.r(-36,   1))); //-     |    -|
        assertEquals(false, new x().test(intRange.r(-36,   2))); //-     |     +
        assertEquals(false, new x().test(intRange.r(-36,   3))); //-     |     |-
        assertEquals(false, new x().test(intRange.r(-36,   4))); //-     |     | -
        assertEquals(false, new x().test(intRange.r(-14,  24))); //    - |     |    -
        assertEquals(false, new x().test(intRange.r(-13,  24))); //     -|     |    -
        assertEquals(false, new x().test(intRange.r(-12,  24))); //      +     |    -
        assertEquals(false, new x().test(intRange.r(-11,  24))); //      |-    |    -
        assertEquals(false, new x().test(intRange.r(-10,  24))); //      | -   |    -
        assertEquals(false, new x().test(intRange.r(  0,  24))); //      |   - |    -
        assertEquals(false, new x().test(intRange.r(  1,  24))); //      |    -|    -
        assertEquals(true , new x().test(intRange.r(  2,  24))); //      |     +    -
        assertEquals(false, new x().test(intRange.r(  3,  24))); //      |     |-   -
        assertEquals(false, new x().test(intRange.r(  4,  24))); //      |     | -  -
        assertEquals(false, new x().test(intRange.r(-14,   0))); //    - |   - |
        assertEquals(false, new x().test(intRange.r(-13,   0))); //     -|   - |
        assertEquals(false, new x().test(intRange.r(-12,   0))); //      +   - |
        assertEquals(false, new x().test(intRange.r(-11,   0))); //      |-  - |
        assertEquals(false, new x().test(intRange.r(-10,   0))); //      | - - |
        assertEquals(false, new x().test(intRange.r(-10,   1))); //      | -  -|
        assertEquals(false, new x().test(intRange.r(-10,   2))); //      | -   +
        assertEquals(false, new x().test(intRange.r(-10,   3))); //      | -   |-
        assertEquals(false, new x().test(intRange.r(-10,   4))); //      | -   | -
        assertEquals(false, new x().test(intRange.r(-14,   2))); //    - |     +
        assertEquals(false, new x().test(intRange.r(-13,   2))); //     -|     +
        assertEquals(false, new x().test(intRange.r(-12,   2))); //      +     +
        assertEquals(false, new x().test(intRange.r(-11,   2))); //      |-    +
        assertEquals(false, new x().test(intRange.r(-10,   2))); //      | -   +
        assertEquals(false, new x().test(intRange.r(-12,   0))); //      +   - |
        assertEquals(false, new x().test(intRange.r(-12,   1))); //      +    -|
        assertEquals(false, new x().test(intRange.r(-12,   2))); //      +     +
        assertEquals(false, new x().test(intRange.r(-12,   3))); //      +     |-
        assertEquals(false, new x().test(intRange.r(-12,   4))); //      +     | -
    }

    /**
     * Test of intersect method, of class Fragment.
     */
    @Test
    public void testIntersect() {
        class x {
            Range<Integer> tested = intRange.r(-12, 2);
            Range<Integer> test(Range<Integer> f) {
                return tested.intersect(f).orElse(null);
            }
        }
        assertEquals(null                        , new x().test(intRange.r(-36, -14))); //-   - |     |
        assertEquals(null                        , new x().test(intRange.r(-36, -13))); //-    -|     |
        assertEquals(null                        , new x().test(intRange.r(-36, -12))); //-     +     |
        assertEquals(intRange.r(-12, -11), new x().test(intRange.r(-36, -11))); //-     |-    |
        assertEquals(intRange.r(-12, -10), new x().test(intRange.r(-36, -10))); //-     | -   |
        assertEquals(intRange.r(-12,   0), new x().test(intRange.r(-36,   0))); //-     |   - |
        assertEquals(intRange.r(-12,   1), new x().test(intRange.r(-36,   1))); //-     |    -|
        assertEquals(intRange.r(-12,   2), new x().test(intRange.r(-36,   2))); //-     |     +
        assertEquals(intRange.r(-12,   2), new x().test(intRange.r(-36,   3))); //-     |     |-
        assertEquals(intRange.r(-12,   2), new x().test(intRange.r(-36,   4))); //-     |     | -
        assertEquals(intRange.r(-12,   2), new x().test(intRange.r(-14,  24))); //    - |     |    -
        assertEquals(intRange.r(-12,   2), new x().test(intRange.r(-13,  24))); //     -|     |    -
        assertEquals(intRange.r(-12,   2), new x().test(intRange.r(-12,  24))); //      +     |    -
        assertEquals(intRange.r(-11,   2), new x().test(intRange.r(-11,  24))); //      |-    |    -
        assertEquals(intRange.r(-10,   2), new x().test(intRange.r(-10,  24))); //      | -   |    -
        assertEquals(intRange.r(  0,   2), new x().test(intRange.r(  0,  24))); //      |   - |    -
        assertEquals(intRange.r(  1,   2), new x().test(intRange.r(  1,  24))); //      |    -|    -
        assertEquals(null                        , new x().test(intRange.r(  2,  24))); //      |     +    -
        assertEquals(null                        , new x().test(intRange.r(  3,  24))); //      |     |-   -
        assertEquals(null                        , new x().test(intRange.r(  4,  24))); //      |     | -  -
        assertEquals(intRange.r(-12,   0), new x().test(intRange.r(-14,   0))); //    - |   - |
        assertEquals(intRange.r(-12,   0), new x().test(intRange.r(-13,   0))); //     -|   - |
        assertEquals(intRange.r(-12,   0), new x().test(intRange.r(-12,   0))); //      +   - |
        assertEquals(intRange.r(-11,   0), new x().test(intRange.r(-11,   0))); //      |-  - |
        assertEquals(intRange.r(-10,   0), new x().test(intRange.r(-10,   0))); //      | - - |
        assertEquals(intRange.r(-10,   1), new x().test(intRange.r(-10,   1))); //      | -  -|
        assertEquals(intRange.r(-10,   2), new x().test(intRange.r(-10,   2))); //      | -   +
        assertEquals(intRange.r(-10,   2), new x().test(intRange.r(-10,   3))); //      | -   |-
        assertEquals(intRange.r(-10,   2), new x().test(intRange.r(-10,   4))); //      | -   | -
        assertEquals(intRange.r(-12,   2), new x().test(intRange.r(-14,   2))); //    - |     +
        assertEquals(intRange.r(-12,   2), new x().test(intRange.r(-13,   2))); //     -|     +
        assertEquals(intRange.r(-12,   2), new x().test(intRange.r(-12,   2))); //      +     +
        assertEquals(intRange.r(-11,   2), new x().test(intRange.r(-11,   2))); //      |-    +
        assertEquals(intRange.r(-10,   2), new x().test(intRange.r(-10,   2))); //      | -   +
        assertEquals(intRange.r(-12,   0), new x().test(intRange.r(-12,   0))); //      +   - |
        assertEquals(intRange.r(-12,   1), new x().test(intRange.r(-12,   1))); //      +    -|
        assertEquals(intRange.r(-12,   2), new x().test(intRange.r(-12,   2))); //      +     +
        assertEquals(intRange.r(-12,   2), new x().test(intRange.r(-12,   3))); //      +     |-
        assertEquals(intRange.r(-12,   2), new x().test(intRange.r(-12,   4))); //      +     | -
    }

    /**
     * Test of cover method, of class Fragment.
     */
    @Test
    public void testCover() {
        class x {
            Range<Integer> tested = intRange.r(-12, 2);
            Range<Integer> test(Range<Integer> f) {
                return tested.cover(f);
            }
        }
        assertEquals(intRange.r(-36,   2), new x().test(intRange.r(-36, -14))); //-   - |     |
        assertEquals(intRange.r(-36,   2), new x().test(intRange.r(-36, -13))); //-    -|     |
        assertEquals(intRange.r(-36,   2), new x().test(intRange.r(-36, -12))); //-     +     |
        assertEquals(intRange.r(-36,   2), new x().test(intRange.r(-36, -11))); //-     |-    |
        assertEquals(intRange.r(-36,   2), new x().test(intRange.r(-36, -10))); //-     | -   |
        assertEquals(intRange.r(-36,   2), new x().test(intRange.r(-36,   0))); //-     |   - |
        assertEquals(intRange.r(-36,   2), new x().test(intRange.r(-36,   1))); //-     |    -|
        assertEquals(intRange.r(-36,   2), new x().test(intRange.r(-36,   2))); //-     |     +
        assertEquals(intRange.r(-36,   3), new x().test(intRange.r(-36,   3))); //-     |     |-
        assertEquals(intRange.r(-36,   4), new x().test(intRange.r(-36,   4))); //-     |     | -
        assertEquals(intRange.r(-14,  24), new x().test(intRange.r(-14,  24))); //    - |     |    -
        assertEquals(intRange.r(-13,  24), new x().test(intRange.r(-13,  24))); //     -|     |    -
        assertEquals(intRange.r(-12,  24), new x().test(intRange.r(-12,  24))); //      +     |    -
        assertEquals(intRange.r(-12,  24), new x().test(intRange.r(-11,  24))); //      |-    |    -
        assertEquals(intRange.r(-12,  24), new x().test(intRange.r(-10,  24))); //      | -   |    -
        assertEquals(intRange.r(-12,  24), new x().test(intRange.r(  0,  24))); //      |   - |    -
        assertEquals(intRange.r(-12,  24), new x().test(intRange.r(  1,  24))); //      |    -|    -
        assertEquals(intRange.r(-12,  24), new x().test(intRange.r(  2,  24))); //      |     +    -
        assertEquals(intRange.r(-12,  24), new x().test(intRange.r(  3,  24))); //      |     |-   -
        assertEquals(intRange.r(-12,  24), new x().test(intRange.r(  4,  24))); //      |     | -  -
        assertEquals(intRange.r(-14,   2), new x().test(intRange.r(-14,   0))); //    - |   - |
        assertEquals(intRange.r(-13,   2), new x().test(intRange.r(-13,   0))); //     -|   - |
        assertEquals(intRange.r(-12,   2), new x().test(intRange.r(-12,   0))); //      +   - |
        assertEquals(intRange.r(-12,   2), new x().test(intRange.r(-11,   0))); //      |-  - |
        assertEquals(intRange.r(-12,   2), new x().test(intRange.r(-10,   0))); //      | - - |
        assertEquals(intRange.r(-12,   2), new x().test(intRange.r(-10,   1))); //      | -  -|
        assertEquals(intRange.r(-12,   2), new x().test(intRange.r(-10,   2))); //      | -   +
        assertEquals(intRange.r(-12,   3), new x().test(intRange.r(-10,   3))); //      | -   |-
        assertEquals(intRange.r(-12,   4), new x().test(intRange.r(-10,   4))); //      | -   | -
        assertEquals(intRange.r(-14,   2), new x().test(intRange.r(-14,   2))); //    - |     +
        assertEquals(intRange.r(-13,   2), new x().test(intRange.r(-13,   2))); //     -|     +
        assertEquals(intRange.r(-12,   2), new x().test(intRange.r(-12,   2))); //      +     +
        assertEquals(intRange.r(-12,   2), new x().test(intRange.r(-11,   2))); //      |-    +
        assertEquals(intRange.r(-12,   2), new x().test(intRange.r(-10,   2))); //      | -   +
        assertEquals(intRange.r(-12,   2), new x().test(intRange.r(-12,   0))); //      +   - |
        assertEquals(intRange.r(-12,   2), new x().test(intRange.r(-12,   1))); //      +    -|
        assertEquals(intRange.r(-12,   2), new x().test(intRange.r(-12,   2))); //      +     +
        assertEquals(intRange.r(-12,   3), new x().test(intRange.r(-12,   3))); //      +     |-
        assertEquals(intRange.r(-12,   4), new x().test(intRange.r(-12,   4))); //      +     | -
    }

    /**
     * Test of getUpper method, of class Fragment.
     */
    @Test
    public void testGetUpper_GenericType() {
        class y {
            Range<Integer> tested = intRange.r(-12, 2);
            Range<Integer> test(int f) {
                return tested.getUpper(f).orElse(null);
            }
        }
        assertEquals(intRange.r(-12,   2), new y().test(-14));
        assertEquals(intRange.r(-12,   2), new y().test(-13));
        assertEquals(intRange.r(-12,   2), new y().test(-12));
        assertEquals(intRange.r(-11,   2), new y().test(-11));
        assertEquals(intRange.r(-10,   2), new y().test(-10));
        assertEquals(intRange.r(  0,   2), new y().test(0));
        assertEquals(intRange.r(  1,   2), new y().test(1));
        assertEquals(null          , new y().test(2));
        assertEquals(null          , new y().test(3));
        assertEquals(null          , new y().test(4));
    }

    /**
     * Test of getUpper method, of class Fragment.
     */
    @Test
    public void testGetUpper_Fragment() {
        class x {
            Range<Integer> tested = intRange.r(-12, 2);
            Range<Integer> test(Range<Integer> f) {
                return f.getUpperRoomIn(tested).orElse(null);
            }
        }
        assertEquals(intRange.r(-12,   2), new x().test(intRange.r(-36, -14))); //-   - |     |
        assertEquals(intRange.r(-12,   2), new x().test(intRange.r(-36, -13))); //-    -|     |
        assertEquals(intRange.r(-12,   2), new x().test(intRange.r(-36, -12))); //-     +     |
        assertEquals(intRange.r(-11,   2), new x().test(intRange.r(-36, -11))); //-     |-    |
        assertEquals(intRange.r(-10,   2), new x().test(intRange.r(-36, -10))); //-     | -   |
        assertEquals(intRange.r(  0,   2), new x().test(intRange.r(-36,   0))); //-     |   - |
        assertEquals(intRange.r(  1,   2), new x().test(intRange.r(-36,   1))); //-     |    -|
        assertEquals(null          , new x().test(intRange.r(-36,   2))); //-     |     +
        assertEquals(null          , new x().test(intRange.r(-36,   3))); //-     |     |-
        assertEquals(null          , new x().test(intRange.r(-36,   4))); //-     |     | -
        assertEquals(null          , new x().test(intRange.r(-14,  24))); //    - |     |    -
        assertEquals(null          , new x().test(intRange.r(-13,  24))); //     -|     |    -
        assertEquals(null          , new x().test(intRange.r(-12,  24))); //      +     |    -
        assertEquals(null          , new x().test(intRange.r(-11,  24))); //      |-    |    -
        assertEquals(null          , new x().test(intRange.r(-10,  24))); //      | -   |    -
        assertEquals(null          , new x().test(intRange.r(  0,  24))); //      |   - |    -
        assertEquals(null          , new x().test(intRange.r(  1,  24))); //      |    -|    -
        assertEquals(null          , new x().test(intRange.r(  2,  24))); //      |     +    -
        assertEquals(null          , new x().test(intRange.r(  3,  24))); //      |     |-   -
        assertEquals(null          , new x().test(intRange.r(  4,  24))); //      |     | -  -
        assertEquals(intRange.r(  0,   2), new x().test(intRange.r(-14,   0))); //    - |   - |
        assertEquals(intRange.r(  0,   2), new x().test(intRange.r(-13,   0))); //     -|   - |
        assertEquals(intRange.r(  0,   2), new x().test(intRange.r(-12,   0))); //      +   - |
        assertEquals(intRange.r(  0,   2), new x().test(intRange.r(-11,   0))); //      |-  - |
        assertEquals(intRange.r(  0,   2), new x().test(intRange.r(-10,   0))); //      | - - |
        assertEquals(intRange.r(  1,   2), new x().test(intRange.r(-10,   1))); //      | -  -|
        assertEquals(null          , new x().test(intRange.r(-10,   2))); //      | -   +
        assertEquals(null          , new x().test(intRange.r(-10,   3))); //      | -   |-
        assertEquals(null          , new x().test(intRange.r(-10,   4))); //      | -   | -
        assertEquals(null          , new x().test(intRange.r(-14,   2))); //    - |     +
        assertEquals(null          , new x().test(intRange.r(-13,   2))); //     -|     +
        assertEquals(null          , new x().test(intRange.r(-12,   2))); //      +     +
        assertEquals(null          , new x().test(intRange.r(-11,   2))); //      |-    +
        assertEquals(null          , new x().test(intRange.r(-10,   2))); //      | -   +
        assertEquals(intRange.r(  0,   2), new x().test(intRange.r(-12,   0))); //      +   - |
        assertEquals(intRange.r(  1,   2), new x().test(intRange.r(-12,   1))); //      +    -|
        assertEquals(null          , new x().test(intRange.r(-12,   2))); //      +     +
        assertEquals(null          , new x().test(intRange.r(-12,   3))); //      +     |-
        assertEquals(null          , new x().test(intRange.r(-12,   4))); //      +     | -
    }

    /**
     * Test of getLower method, of class Fragment.
     */
    @Test
    public void testGetLower_GenericType() {
        class y {
            Range<Integer> tested = intRange.r(-12, 2);
            Range<Integer> test(int f) {
                return tested.getLower(f).orElse(null);
            }
        }
        assertEquals(null          , new y().test(-14));
        assertEquals(null          , new y().test(-13));
        assertEquals(null          , new y().test(-12));
        assertEquals(intRange.r(-12, -11), new y().test(-11));
        assertEquals(intRange.r(-12, -10), new y().test(-10));
        assertEquals(intRange.r(-12,   0), new y().test(0));
        assertEquals(intRange.r(-12,   1), new y().test(1));
        assertEquals(intRange.r(-12,   2), new y().test(2));
        assertEquals(intRange.r(-12,   2), new y().test(3));
        assertEquals(intRange.r(-12,   2), new y().test(4));
    }

    /**
     * Test of getLower method, of class Fragment.
     */
    @Test
    public void testGetLower_Fragment() {
        class x {
            Range<Integer> tested = intRange.r(-12, 2);
            Range<Integer> test(Range<Integer> f) {
                return f.getLowerRoomIn(tested).orElse(null);
            }
        }
        assertEquals(null          , new x().test(intRange.r(-36, -14))); //-   - |     |
        assertEquals(null          , new x().test(intRange.r(-36, -13))); //-    -|     |
        assertEquals(null          , new x().test(intRange.r(-36, -12))); //-     +     |
        assertEquals(null          , new x().test(intRange.r(-36, -11))); //-     |-    |
        assertEquals(null          , new x().test(intRange.r(-36, -10))); //-     | -   |
        assertEquals(null          , new x().test(intRange.r(-36,   0))); //-     |   - |
        assertEquals(null          , new x().test(intRange.r(-36,   1))); //-     |    -|
        assertEquals(null          , new x().test(intRange.r(-36,   2))); //-     |     +
        assertEquals(null          , new x().test(intRange.r(-36,   3))); //-     |     |-
        assertEquals(null          , new x().test(intRange.r(-36,   4))); //-     |     | -
        assertEquals(null          , new x().test(intRange.r(-14,  24))); //    - |     |    -
        assertEquals(null          , new x().test(intRange.r(-13,  24))); //     -|     |    -
        assertEquals(null          , new x().test(intRange.r(-12,  24))); //      +     |    -
        assertEquals(intRange.r(-12, -11), new x().test(intRange.r(-11,  24))); //      |-    |    -
        assertEquals(intRange.r(-12, -10), new x().test(intRange.r(-10,  24))); //      | -   |    -
        assertEquals(intRange.r(-12,   0), new x().test(intRange.r(  0,  24))); //      |   - |    -
        assertEquals(intRange.r(-12,   1), new x().test(intRange.r(  1,  24))); //      |    -|    -
        assertEquals(intRange.r(-12,   2), new x().test(intRange.r(  2,  24))); //      |     +    -
        assertEquals(intRange.r(-12,   2), new x().test(intRange.r(  3,  24))); //      |     |-   -
        assertEquals(intRange.r(-12,   2), new x().test(intRange.r(  4,  24))); //      |     | -  -
        assertEquals(null          , new x().test(intRange.r(-14,   0))); //    - |   - |
        assertEquals(null          , new x().test(intRange.r(-13,   0))); //     -|   - |
        assertEquals(null          , new x().test(intRange.r(-12,   0))); //      +   - |
        assertEquals(intRange.r(-12, -11), new x().test(intRange.r(-11,   0))); //      |-  - |
        assertEquals(intRange.r(-12, -10), new x().test(intRange.r(-10,   0))); //      | - - |
        assertEquals(intRange.r(-12, -10), new x().test(intRange.r(-10,   1))); //      | -  -|
        assertEquals(intRange.r(-12, -10), new x().test(intRange.r(-10,   2))); //      | -   +
        assertEquals(intRange.r(-12, -10), new x().test(intRange.r(-10,   3))); //      | -   |-
        assertEquals(intRange.r(-12, -10), new x().test(intRange.r(-10,   4))); //      | -   | -
        assertEquals(null          , new x().test(intRange.r(-14,   2))); //    - |     +
        assertEquals(null          , new x().test(intRange.r(-13,   2))); //     -|     +
        assertEquals(null          , new x().test(intRange.r(-12,   2))); //      +     +
        assertEquals(intRange.r(-12, -11), new x().test(intRange.r(-11,   2))); //      |-    +
        assertEquals(intRange.r(-12, -10), new x().test(intRange.r(-10,   2))); //      | -   +
        assertEquals(null          , new x().test(intRange.r(-12,   0))); //      +   - |
        assertEquals(null          , new x().test(intRange.r(-12,   1))); //      +    -|
        assertEquals(null          , new x().test(intRange.r(-12,   2))); //      +     +
        assertEquals(null          , new x().test(intRange.r(-12,   3))); //      +     |-
        assertEquals(null          , new x().test(intRange.r(-12,   4))); //      +     | -
    }

    @Test
    public void testNegate() {
        System.out.println(test.TestUtils.methodName(0));
        Range<Integer> tested = intRange.r(0,500);
        TList<Range<Integer>> result = tested.negate(TList.sof());
        TList<Range<Integer>> expected = TList.sof(0,500).fold(2).map(l->intRange.r(l.get(0),l.get(1)));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testNegate1() {
        System.out.println(test.TestUtils.methodName(0));
        Range<Integer> tested = intRange.r(0,500);
        TList<Range<Integer>> result = tested.negate(TList.sof(5,6).fold(2).map(l->intRange.r(l.get(0),l.get(1))));
        TList<Range<Integer>> expected = TList.sof(0,5, 6,500).fold(2).map(l->intRange.r(l.get(0),l.get(1)));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    @Test
    public void testNegate2() {
        System.out.println(test.TestUtils.methodName(0));
        Range<Integer> tested = intRange.r(0,500);
        TList<Range<Integer>> result = tested.negate(TList.sof(-20,-19).fold(2).map(l->intRange.r(l.get(0),l.get(1))));
        TList<Range<Integer>> expected = TList.sof(0,500).fold(2).map(l->intRange.r(l.get(0),l.get(1)));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    @Test
    public void testNegate3() {
        System.out.println(test.TestUtils.methodName(0));
        Range<Integer> tested = intRange.r(0,500);
        TList<Range<Integer>> result = tested.negate(TList.sof(510,512).fold(2).map(l->intRange.r(l.get(0),l.get(1))));
        TList<Range<Integer>> expected = TList.sof(0,500).fold(2).map(l->intRange.r(l.get(0),l.get(1)));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    @Test
    public void testNegate4() {
        System.out.println(test.TestUtils.methodName(0));
        Range<Integer> tested = intRange.r(0,500);
        TList<Range<Integer>> result = tested.negate(TList.sof(-21,-20, 5,6, 510,512).fold(2).map(l->intRange.r(l.get(0),l.get(1))));
        TList<Range<Integer>> expected = TList.sof(0,5, 6,500).fold(2).map(l->intRange.r(l.get(0),l.get(1)));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    @Test
    public void testNegate5() {
        System.out.println(test.TestUtils.methodName(0));
        Range<Integer> tested = intRange.r(0,500);
        TList<Range<Integer>> result = tested.negate(TList.sof(-21,-20, 5,60, 62,70, 510,512).fold(2).map(l->intRange.r(l.get(0),l.get(1))));
        TList<Range<Integer>> expected = TList.sof(0,5, 60,62, 70,500).fold(2).map(l->intRange.r(l.get(0),l.get(1)));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    @Test
    public void testNegate6() {
        System.out.println(test.TestUtils.methodName(0));
        Range<Integer> tested = intRange.r(0,500);
        TList<Range<Integer>> result = tested.negate(TList.sof(-21,-20, 5,60, 62,70, 65,75, 510,512).fold(2).map(l->intRange.r(l.get(0),l.get(1))));
        TList<Range<Integer>> expected = TList.sof(0,5, 60,62, 75,500).fold(2).map(l->intRange.r(l.get(0),l.get(1)));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    @Test
    public void testNegate7() {
        System.out.println(test.TestUtils.methodName(0));
        Range<Integer> tested = intRange.r(0,500);
        TList<Range<Integer>> result = tested.negate(TList.sof(5,60, 62,70, 65,75).fold(2).map(l->intRange.r(l.get(0),l.get(1))));
        TList<Range<Integer>> expected = TList.sof(0,5, 60,62, 75,500).fold(2).map(l->intRange.r(l.get(0),l.get(1)));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    @Test
    public void testNegateCover() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Range<Integer>> tested = TList.sof(0,10, 11,18, 12,13, 13,15, 20,23).fold(2).map(l->intRange.r(l.get(0),l.get(1)));
        TList<Range<Integer>> result = Range.negateCover(tested);
        TList<Range<Integer>> expected = TList.sof(10,11, 18,20).fold(2).map(l->intRange.r(l.get(0),l.get(1)));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    @Test
    public void testNegateCover2() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Range<Integer>> tested = TList.sof(0,10, 11,18, 12,13, 13,15, 20,28, 21,24, 25,26).fold(2).map(l->intRange.r(l.get(0),l.get(1)));
        TList<Range<Integer>> result = Range.negateCover(tested);
        TList<Range<Integer>> expected = TList.sof(10,11, 18,20).fold(2).map(l->intRange.r(l.get(0),l.get(1)));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    @Test
    public void testUnion() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Range<Integer>> tested = TList.sof(0,10, 11,18, 12,13, 13,15, 20,23).fold(2).map(l->intRange.r(l.get(0),l.get(1)));
        TList<Range<Integer>> result = Range.union(tested);
        TList<Range<Integer>> expected = TList.sof(0,10, 11,18, 20,23).fold(2).map(l->intRange.r(l.get(0),l.get(1)));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    @Test
    public void testUnion2() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Range<Integer>> tested = TList.sof(0,10, 11,18, 12,13, 13,15, 20,28, 21,24, 25,26).fold(2).map(l->intRange.r(l.get(0),l.get(1)));
        TList<Range<Integer>> result = Range.union(tested);
        TList<Range<Integer>> expected = TList.sof(0,10, 11,18, 20,28).fold(2).map(l->intRange.r(l.get(0),l.get(1)));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    @Test
    public void testUnaryIntersect0() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Range<Integer>> tested0 = TList.sof(0,30, 11,45, 13,20, 14,28).fold(2).map(l->intRange.r(l.get(0),l.get(1)));
        Range<Integer> result = Range.intersect(tested0).get();
        Range<Integer> expected = intRange.r(14,20);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    @Test
    public void testBinaryIntersect0() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Range<Integer>> tested0 = TList.sof(0,10, 11,12, 13,15, 20,28).fold(2).map(l->intRange.r(l.get(0),l.get(1)));
        TList<Range<Integer>> tested1 = TList.sof(0,10,        13,18, 21,24, 25,30).fold(2).map(l->intRange.r(l.get(0),l.get(1)));
        TList<Range<Integer>> result = Range.intersect(tested0,tested1);
        TList<Range<Integer>> expected = TList.sof(0,10, 13,15, 21,24, 25,28).fold(2).map(l->intRange.r(l.get(0),l.get(1)));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testIntersectPoints0() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Range<Integer>> tested0 = TList.sof(0,10, 11,12, 13,15, 20,28, 30,35, 37,40).fold(2).map(l->intRange.r(l.get(0),l.get(1)));
        TList<Integer> tested1 = TList.sof(-10,-1,2,3,3,5,10,12,14,16,16,16);
        TList<Optional<RangeInt>> result = Range.intersectPoints(tested0, tested1);
        TList<Optional<RangeInt>> expected = TList.sof(Optional.of(new RangeInt(2,6)),Optional.empty(),Optional.of(new RangeInt(8,9)),Optional.empty(),Optional.empty(),Optional.empty());
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testIntersectPoints1() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Range<Integer>> tested0 = TList.sof(0,10, 11,12, 13,15).fold(2).map(l->intRange.r(l.get(0),l.get(1)));
        TList<Integer> tested1 = TList.sof(-10,-1,2,3,3,5,10,12,14,14,14);
        TList<Optional<RangeInt>> result = Range.intersectPoints(tested0, tested1);
        TList<Optional<RangeInt>> expected = TList.sof(Optional.of(new RangeInt(2,6)),Optional.empty(),Optional.of(new RangeInt(8,11)));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
}
