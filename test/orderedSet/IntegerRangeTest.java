/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package orderedSet;

import static org.testng.Assert.assertEquals;
import org.testng.annotations.Test;

/**
 *
 * @author mtomono
 */
public class IntegerRangeTest {
    Order<Integer> order;
    
    public IntegerRangeTest() {
        this.order = new NaturalOrder();
    }

    /**
     * Test of clone method, of class Fragment.
     */
    @Test
    public void testClone() {
        Range<Integer> original = new Range<>(-12, 2, order);
        Range<Integer> tested = (Range<Integer>)original.clone();
        assertEquals(original.start(), tested.start());
        assertEquals(original.end(), tested.end());
    }
    
    @Test
    public void testEquals() {
        class x {
            Range<Integer> tested = new Range<>(-12, 2, order);
            boolean test(Range<Integer> f) {
                return tested.equals(f);
            }
        }
        assertEquals(false, new x().test(new Range<>(-36, -14, order))); //-   - |     |
        assertEquals(false, new x().test(new Range<>(-36, -13, order))); //-    -|     |
        assertEquals(false, new x().test(new Range<>(-36, -12, order))); //-     +     |
        assertEquals(false, new x().test(new Range<>(-36, -11, order))); //-     |-    |
        assertEquals(false, new x().test(new Range<>(-36, -10, order))); //-     | -   |
        assertEquals(false, new x().test(new Range<>(-36,   0, order))); //-     |   - |
        assertEquals(false, new x().test(new Range<>(-36,   1, order))); //-     |    -|
        assertEquals(false, new x().test(new Range<>(-36,   2, order))); //-     |     +
        assertEquals(false, new x().test(new Range<>(-36,   3, order))); //-     |     |-
        assertEquals(false, new x().test(new Range<>(-36,   4, order))); //-     |     | -
        assertEquals(false, new x().test(new Range<>(-14,  24, order))); //    - |     |    -
        assertEquals(false, new x().test(new Range<>(-13,  24, order))); //     -|     |    -
        assertEquals(false, new x().test(new Range<>(-12,  24, order))); //      +     |    -
        assertEquals(false, new x().test(new Range<>(-11,  24, order))); //      |-    |    -
        assertEquals(false, new x().test(new Range<>(-10,  24, order))); //      | -   |    -
        assertEquals(false, new x().test(new Range<>(  0,  24, order))); //      |   - |    -
        assertEquals(false, new x().test(new Range<>(  1,  24, order))); //      |    -|    -
        assertEquals(false, new x().test(new Range<>(  2,  24, order))); //      |     +    -
        assertEquals(false, new x().test(new Range<>(  3,  24, order))); //      |     |-   -
        assertEquals(false, new x().test(new Range<>(  4,  24, order))); //      |     | -  -
        assertEquals(false, new x().test(new Range<>(-14,   0, order))); //    - |   - |
        assertEquals(false, new x().test(new Range<>(-13,   0, order))); //     -|   - |
        assertEquals(false, new x().test(new Range<>(-12,   0, order))); //      +   - |
        assertEquals(false, new x().test(new Range<>(-11,   0, order))); //      |-  - |
        assertEquals(false, new x().test(new Range<>(-10,   0, order))); //      | - - |
        assertEquals(false, new x().test(new Range<>(-10,   1, order))); //      | -  -|
        assertEquals(false, new x().test(new Range<>(-10,   2, order))); //      | -   +
        assertEquals(false, new x().test(new Range<>(-10,   3, order))); //      | -   |-
        assertEquals(false, new x().test(new Range<>(-10,   4, order))); //      | -   | -
        assertEquals(false, new x().test(new Range<>(-14,   2, order))); //    - |     +
        assertEquals(false, new x().test(new Range<>(-13,   2, order))); //     -|     +
        assertEquals(true , new x().test(new Range<>(-12,   2, order))); //      +     +
        assertEquals(false, new x().test(new Range<>(-11,   2, order))); //      |-    +
        assertEquals(false, new x().test(new Range<>(-10,   2, order))); //      | -   +
        assertEquals(false, new x().test(new Range<>(-12,   0, order))); //      +   - |
        assertEquals(false, new x().test(new Range<>(-12,   1, order))); //      +    -|
        assertEquals(true , new x().test(new Range<>(-12,   2, order))); //      +     +
        assertEquals(false, new x().test(new Range<>(-12,   3, order))); //      +     |-
        assertEquals(false, new x().test(new Range<>(-12,   4, order))); //      +     | -
    }
    
    /**
     * Test of getStart method, of class Fragment.
     */
    @Test
    public void testGetStart() {
        Range<Integer> tested = new Range<>(-12, 2, order);
        assertEquals(Integer.valueOf(-12), tested.start());
    }

    /**
     * Test of getEnd method, of class Fragment.
     */
    @Test
    public void testGetEnd() {
        Range<Integer> tested = new Range<>(-12, 2, order);
        assertEquals(Integer.valueOf(2), tested.end());
    }

    /**
     * Test of contains method, of class Fragment.
     */
    @Test
    public void testContains_GenericType() {
        class y {
            Range<Integer> tested = new Range<>(-12, 2, order);
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
            Range<Integer> tested = new Range<>(-12, 2, order);
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
            Range<Integer> tested = new Range<>(-12, 2, order);
            boolean test(Range<Integer> f) {
                return tested.isUpperThan(f);
            }
        }
        assertEquals(true , new x().test(new Range<>(-36, -14, order))); //-   - |     |
        assertEquals(true , new x().test(new Range<>(-36, -13, order))); //-    -|     |
        assertEquals(true , new x().test(new Range<>(-36, -12, order))); //-     +     |
        assertEquals(false, new x().test(new Range<>(-36, -11, order))); //-     |-    |
        assertEquals(false, new x().test(new Range<>(-36, -10, order))); //-     | -   |
        assertEquals(false, new x().test(new Range<>(-36,   0, order))); //-     |   - |
        assertEquals(false, new x().test(new Range<>(-36,   1, order))); //-     |    -|
        assertEquals(false, new x().test(new Range<>(-36,   2, order))); //-     |     +
        assertEquals(false, new x().test(new Range<>(-36,   3, order))); //-     |     |-
        assertEquals(false, new x().test(new Range<>(-36,   4, order))); //-     |     | -
        assertEquals(false, new x().test(new Range<>(-14,  24, order))); //    - |     |    -
        assertEquals(false, new x().test(new Range<>(-13,  24, order))); //     -|     |    -
        assertEquals(false, new x().test(new Range<>(-12,  24, order))); //      +     |    -
        assertEquals(false, new x().test(new Range<>(-11,  24, order))); //      |-    |    -
        assertEquals(false, new x().test(new Range<>(-10,  24, order))); //      | -   |    -
        assertEquals(false, new x().test(new Range<>(  0,  24, order))); //      |   - |    -
        assertEquals(false, new x().test(new Range<>(  1,  24, order))); //      |    -|    -
        assertEquals(false, new x().test(new Range<>(  2,  24, order))); //      |     +    -
        assertEquals(false, new x().test(new Range<>(  3,  24, order))); //      |     |-   -
        assertEquals(false, new x().test(new Range<>(  4,  24, order))); //      |     | -  -
        assertEquals(false, new x().test(new Range<>(-14,   0, order))); //    - |   - |
        assertEquals(false, new x().test(new Range<>(-13,   0, order))); //     -|   - |
        assertEquals(false, new x().test(new Range<>(-12,   0, order))); //      +   - |
        assertEquals(false, new x().test(new Range<>(-11,   0, order))); //      |-  - |
        assertEquals(false, new x().test(new Range<>(-10,   0, order))); //      | - - |
        assertEquals(false, new x().test(new Range<>(-10,   1, order))); //      | -  -|
        assertEquals(false, new x().test(new Range<>(-10,   2, order))); //      | -   +
        assertEquals(false, new x().test(new Range<>(-10,   3, order))); //      | -   |-
        assertEquals(false, new x().test(new Range<>(-10,   4, order))); //      | -   | -
        assertEquals(false, new x().test(new Range<>(-14,   2, order))); //    - |     +
        assertEquals(false, new x().test(new Range<>(-13,   2, order))); //     -|     +
        assertEquals(false, new x().test(new Range<>(-12,   2, order))); //      +     +
        assertEquals(false, new x().test(new Range<>(-11,   2, order))); //      |-    +
        assertEquals(false, new x().test(new Range<>(-10,   2, order))); //      | -   +
        assertEquals(false, new x().test(new Range<>(-12,   0, order))); //      +   - |
        assertEquals(false, new x().test(new Range<>(-12,   1, order))); //      +    -|
        assertEquals(false, new x().test(new Range<>(-12,   2, order))); //      +     +
        assertEquals(false, new x().test(new Range<>(-12,   3, order))); //      +     |-
        assertEquals(false, new x().test(new Range<>(-12,   4, order))); //      +     | -
    }

    /**
     * Test of isBelow method, of class Fragment.
     */
    @Test
    public void testIsBelow_GenericType() {
        class y {
            Range<Integer> tested = new Range<>(-12, 2, order);
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
            Range<Integer> tested = new Range<>(-12, 2, order);
            boolean test(Range<Integer> f) {
                return tested.isLowerThan(f);
            }
        }
        assertEquals(false, new x().test(new Range<>(-36, -14, order))); //-   - |     |
        assertEquals(false, new x().test(new Range<>(-36, -13, order))); //-    -|     |
        assertEquals(false, new x().test(new Range<>(-36, -12, order))); //-     +     |
        assertEquals(false, new x().test(new Range<>(-36, -11, order))); //-     |-    |
        assertEquals(false, new x().test(new Range<>(-36, -10, order))); //-     | -   |
        assertEquals(false, new x().test(new Range<>(-36,   0, order))); //-     |   - |
        assertEquals(false, new x().test(new Range<>(-36,   1, order))); //-     |    -|
        assertEquals(false, new x().test(new Range<>(-36,   2, order))); //-     |     +
        assertEquals(false, new x().test(new Range<>(-36,   3, order))); //-     |     |-
        assertEquals(false, new x().test(new Range<>(-36,   4, order))); //-     |     | -
        assertEquals(false, new x().test(new Range<>(-14,  24, order))); //    - |     |    -
        assertEquals(false, new x().test(new Range<>(-13,  24, order))); //     -|     |    -
        assertEquals(false, new x().test(new Range<>(-12,  24, order))); //      +     |    -
        assertEquals(false, new x().test(new Range<>(-11,  24, order))); //      |-    |    -
        assertEquals(false, new x().test(new Range<>(-10,  24, order))); //      | -   |    -
        assertEquals(false, new x().test(new Range<>(  0,  24, order))); //      |   - |    -
        assertEquals(false, new x().test(new Range<>(  1,  24, order))); //      |    -|    -
        assertEquals(true , new x().test(new Range<>(  2,  24, order))); //      |     +    -
        assertEquals(true , new x().test(new Range<>(  3,  24, order))); //      |     |-   -
        assertEquals(true , new x().test(new Range<>(  4,  24, order))); //      |     | -  -
        assertEquals(false, new x().test(new Range<>(-14,   0, order))); //    - |   - |
        assertEquals(false, new x().test(new Range<>(-13,   0, order))); //     -|   - |
        assertEquals(false, new x().test(new Range<>(-12,   0, order))); //      +   - |
        assertEquals(false, new x().test(new Range<>(-11,   0, order))); //      |-  - |
        assertEquals(false, new x().test(new Range<>(-10,   0, order))); //      | - - |
        assertEquals(false, new x().test(new Range<>(-10,   1, order))); //      | -  -|
        assertEquals(false, new x().test(new Range<>(-10,   2, order))); //      | -   +
        assertEquals(false, new x().test(new Range<>(-10,   3, order))); //      | -   |-
        assertEquals(false, new x().test(new Range<>(-10,   4, order))); //      | -   | -
        assertEquals(false, new x().test(new Range<>(-14,   2, order))); //    - |     +
        assertEquals(false, new x().test(new Range<>(-13,   2, order))); //     -|     +
        assertEquals(false, new x().test(new Range<>(-12,   2, order))); //      +     +
        assertEquals(false, new x().test(new Range<>(-11,   2, order))); //      |-    +
        assertEquals(false, new x().test(new Range<>(-10,   2, order))); //      | -   +
        assertEquals(false, new x().test(new Range<>(-12,   0, order))); //      +   - |
        assertEquals(false, new x().test(new Range<>(-12,   1, order))); //      +    -|
        assertEquals(false, new x().test(new Range<>(-12,   2, order))); //      +     +
        assertEquals(false, new x().test(new Range<>(-12,   3, order))); //      +     |-
        assertEquals(false, new x().test(new Range<>(-12,   4, order))); //      +     | -
    }

    /**
     * Test of doesOverrap method, of class Fragment.
     */
    @Test
    public void testDoesOverlap() {
        class x {
            Range<Integer> tested = new Range<>(-12, 2, order);
            boolean test(Range<Integer> f) {
                return tested.overlaps(f);
            }
        }
        assertEquals(false, new x().test(new Range<>(-36, -14, order))); //-   - |     |
        assertEquals(false, new x().test(new Range<>(-36, -13, order))); //-    -|     |
        assertEquals(false, new x().test(new Range<>(-36, -12, order))); //-     +     |
        assertEquals(true , new x().test(new Range<>(-36, -11, order))); //-     |-    |
        assertEquals(true , new x().test(new Range<>(-36, -10, order))); //-     | -   |
        assertEquals(true , new x().test(new Range<>(-36,   0, order))); //-     |   - |
        assertEquals(true , new x().test(new Range<>(-36,   1, order))); //-     |    -|
        assertEquals(true , new x().test(new Range<>(-36,   2, order))); //-     |     +
        assertEquals(true , new x().test(new Range<>(-36,   3, order))); //-     |     |-
        assertEquals(true , new x().test(new Range<>(-36,   4, order))); //-     |     | -
        assertEquals(true , new x().test(new Range<>(-14,  24, order))); //    - |     |    -
        assertEquals(true , new x().test(new Range<>(-13,  24, order))); //     -|     |    -
        assertEquals(true , new x().test(new Range<>(-12,  24, order))); //      +     |    -
        assertEquals(true , new x().test(new Range<>(-11,  24, order))); //      |-    |    -
        assertEquals(true , new x().test(new Range<>(-10,  24, order))); //      | -   |    -
        assertEquals(true , new x().test(new Range<>(  0,  24, order))); //      |   - |    -
        assertEquals(true , new x().test(new Range<>(  1,  24, order))); //      |    -|    -
        assertEquals(false, new x().test(new Range<>(  2,  24, order))); //      |     +    -
        assertEquals(false, new x().test(new Range<>(  3,  24, order))); //      |     |-   -
        assertEquals(false, new x().test(new Range<>(  4,  24, order))); //      |     | -  -
        assertEquals(true , new x().test(new Range<>(-14,   0, order))); //    - |   - |
        assertEquals(true , new x().test(new Range<>(-13,   0, order))); //     -|   - |
        assertEquals(true , new x().test(new Range<>(-12,   0, order))); //      +   - |
        assertEquals(true , new x().test(new Range<>(-11,   0, order))); //      |-  - |
        assertEquals(true , new x().test(new Range<>(-10,   0, order))); //      | - - |
        assertEquals(true , new x().test(new Range<>(-10,   1, order))); //      | -  -|
        assertEquals(true , new x().test(new Range<>(-10,   2, order))); //      | -   +
        assertEquals(true , new x().test(new Range<>(-10,   3, order))); //      | -   |-
        assertEquals(true , new x().test(new Range<>(-10,   4, order))); //      | -   | -
        assertEquals(true , new x().test(new Range<>(-14,   2, order))); //    - |     +
        assertEquals(true , new x().test(new Range<>(-13,   2, order))); //     -|     +
        assertEquals(true , new x().test(new Range<>(-12,   2, order))); //      +     +
        assertEquals(true , new x().test(new Range<>(-11,   2, order))); //      |-    +
        assertEquals(true , new x().test(new Range<>(-10,   2, order))); //      | -   +
        assertEquals(true , new x().test(new Range<>(-12,   0, order))); //      +   - |
        assertEquals(true , new x().test(new Range<>(-12,   1, order))); //      +    -|
        assertEquals(true , new x().test(new Range<>(-12,   2, order))); //      +     +
        assertEquals(true , new x().test(new Range<>(-12,   3, order))); //      +     |-
        assertEquals(true , new x().test(new Range<>(-12,   4, order))); //      +     | -
    }

    /**
     * Test of contains method, of class Fragment.
     */
    @Test
    public void testDoesContain_Fragment() {
        class x {
            Range<Integer> tested = new Range<>(-12, 2, order);
            boolean test(Range<Integer> f) {
                return tested.contains(f);
            }
        }
        assertEquals(false, new x().test(new Range<>(-36, -14, order))); //-   - |     |
        assertEquals(false, new x().test(new Range<>(-36, -13, order))); //-    -|     |
        assertEquals(false, new x().test(new Range<>(-36, -12, order))); //-     +     |
        assertEquals(false, new x().test(new Range<>(-36, -11, order))); //-     |-    |
        assertEquals(false, new x().test(new Range<>(-36, -10, order))); //-     | -   |
        assertEquals(false, new x().test(new Range<>(-36,   0, order))); //-     |   - |
        assertEquals(false, new x().test(new Range<>(-36,   1, order))); //-     |    -|
        assertEquals(false, new x().test(new Range<>(-36,   2, order))); //-     |     +
        assertEquals(false, new x().test(new Range<>(-36,   3, order))); //-     |     |-
        assertEquals(false, new x().test(new Range<>(-36,   4, order))); //-     |     | -
        assertEquals(false, new x().test(new Range<>(-14,  24, order))); //    - |     |    -
        assertEquals(false, new x().test(new Range<>(-13,  24, order))); //     -|     |    -
        assertEquals(false, new x().test(new Range<>(-12,  24, order))); //      +     |    -
        assertEquals(false, new x().test(new Range<>(-11,  24, order))); //      |-    |    -
        assertEquals(false, new x().test(new Range<>(-10,  24, order))); //      | -   |    -
        assertEquals(false, new x().test(new Range<>(  0,  24, order))); //      |   - |    -
        assertEquals(false, new x().test(new Range<>(  1,  24, order))); //      |    -|    -
        assertEquals(false, new x().test(new Range<>(  2,  24, order))); //      |     +    -
        assertEquals(false, new x().test(new Range<>(  3,  24, order))); //      |     |-   -
        assertEquals(false, new x().test(new Range<>(  4,  24, order))); //      |     | -  -
        assertEquals(false, new x().test(new Range<>(-14,   0, order))); //    - |   - |
        assertEquals(false, new x().test(new Range<>(-13,   0, order))); //     -|   - |
        assertEquals(true , new x().test(new Range<>(-12,   0, order))); //      +   - |
        assertEquals(true , new x().test(new Range<>(-11,   0, order))); //      |-  - |
        assertEquals(true , new x().test(new Range<>(-10,   0, order))); //      | - - |
        assertEquals(true , new x().test(new Range<>(-10,   1, order))); //      | -  -|
        assertEquals(true , new x().test(new Range<>(-10,   2, order))); //      | -   +
        assertEquals(false, new x().test(new Range<>(-10,   3, order))); //      | -   |-
        assertEquals(false, new x().test(new Range<>(-10,   4, order))); //      | -   | -
        assertEquals(false, new x().test(new Range<>(-14,   2, order))); //    - |     +
        assertEquals(false, new x().test(new Range<>(-13,   2, order))); //     -|     +
        assertEquals(true , new x().test(new Range<>(-12,   2, order))); //      +     +
        assertEquals(true , new x().test(new Range<>(-11,   2, order))); //      |-    +
        assertEquals(true , new x().test(new Range<>(-10,   2, order))); //      | -   +
        assertEquals(true , new x().test(new Range<>(-12,   0, order))); //      +   - |
        assertEquals(true , new x().test(new Range<>(-12,   1, order))); //      +    -|
        assertEquals(true , new x().test(new Range<>(-12,   2, order))); //      +     +
        assertEquals(false, new x().test(new Range<>(-12,   3, order))); //      +     |-
        assertEquals(false, new x().test(new Range<>(-12,   4, order))); //      +     | -
    }

    /**
     * Test of adjoins method, of class Fragment.
     */
    @Test
    public void testDoesAdjoin() {
        class x {
            Range<Integer> tested = new Range<>(-12, 2, order);
            boolean test(Range<Integer> f) {
                return tested.adjoins(f);
            }
        }
        assertEquals(false, new x().test(new Range<>(-36, -14, order))); //-   - |     |
        assertEquals(false, new x().test(new Range<>(-36, -13, order))); //-    -|     |
        assertEquals(true , new x().test(new Range<>(-36, -12, order))); //-     +     |
        assertEquals(false, new x().test(new Range<>(-36, -11, order))); //-     |-    |
        assertEquals(false, new x().test(new Range<>(-36, -10, order))); //-     | -   |
        assertEquals(false, new x().test(new Range<>(-36,   0, order))); //-     |   - |
        assertEquals(false, new x().test(new Range<>(-36,   1, order))); //-     |    -|
        assertEquals(false, new x().test(new Range<>(-36,   2, order))); //-     |     +
        assertEquals(false, new x().test(new Range<>(-36,   3, order))); //-     |     |-
        assertEquals(false, new x().test(new Range<>(-36,   4, order))); //-     |     | -
        assertEquals(false, new x().test(new Range<>(-14,  24, order))); //    - |     |    -
        assertEquals(false, new x().test(new Range<>(-13,  24, order))); //     -|     |    -
        assertEquals(false, new x().test(new Range<>(-12,  24, order))); //      +     |    -
        assertEquals(false, new x().test(new Range<>(-11,  24, order))); //      |-    |    -
        assertEquals(false, new x().test(new Range<>(-10,  24, order))); //      | -   |    -
        assertEquals(false, new x().test(new Range<>(  0,  24, order))); //      |   - |    -
        assertEquals(false, new x().test(new Range<>(  1,  24, order))); //      |    -|    -
        assertEquals(true , new x().test(new Range<>(  2,  24, order))); //      |     +    -
        assertEquals(false, new x().test(new Range<>(  3,  24, order))); //      |     |-   -
        assertEquals(false, new x().test(new Range<>(  4,  24, order))); //      |     | -  -
        assertEquals(false, new x().test(new Range<>(-14,   2, order))); //    - |     +
        assertEquals(false, new x().test(new Range<>(-13,   2, order))); //     -|     +
        assertEquals(false, new x().test(new Range<>(-12,   2, order))); //      +     +
        assertEquals(false, new x().test(new Range<>(-11,   2, order))); //      |-    +
        assertEquals(false, new x().test(new Range<>(-10,   2, order))); //      | -   +
        assertEquals(false, new x().test(new Range<>(-12,   0, order))); //      +   - |
        assertEquals(false, new x().test(new Range<>(-12,   1, order))); //      +    -|
        assertEquals(false, new x().test(new Range<>(-12,   2, order))); //      +     +
        assertEquals(false, new x().test(new Range<>(-12,   3, order))); //      +     |-
        assertEquals(false, new x().test(new Range<>(-12,   4, order))); //      +     | -
    }

    /**
     * Test of adjoinsAtStartWith method, of class Fragment.
     */
    @Test
    public void testDoesAdjoinAtStart() {
        class x {
            Range<Integer> tested = new Range<>(-12, 2, order);
            boolean test(Range<Integer> f) {
                return tested.adjoinsAtStartWith(f);
            }
        }
        assertEquals(false, new x().test(new Range<>(-36, -14, order))); //-   - |     |
        assertEquals(false, new x().test(new Range<>(-36, -13, order))); //-    -|     |
        assertEquals(true , new x().test(new Range<>(-36, -12, order))); //-     +     |
        assertEquals(false, new x().test(new Range<>(-36, -11, order))); //-     |-    |
        assertEquals(false, new x().test(new Range<>(-36, -10, order))); //-     | -   |
        assertEquals(false, new x().test(new Range<>(-36,   0, order))); //-     |   - |
        assertEquals(false, new x().test(new Range<>(-36,   1, order))); //-     |    -|
        assertEquals(false, new x().test(new Range<>(-36,   2, order))); //-     |     +
        assertEquals(false, new x().test(new Range<>(-36,   3, order))); //-     |     |-
        assertEquals(false, new x().test(new Range<>(-36,   4, order))); //-     |     | -
        assertEquals(false, new x().test(new Range<>(-14,  24, order))); //    - |     |    -
        assertEquals(false, new x().test(new Range<>(-13,  24, order))); //     -|     |    -
        assertEquals(false, new x().test(new Range<>(-12,  24, order))); //      +     |    -
        assertEquals(false, new x().test(new Range<>(-11,  24, order))); //      |-    |    -
        assertEquals(false, new x().test(new Range<>(-10,  24, order))); //      | -   |    -
        assertEquals(false, new x().test(new Range<>(  0,  24, order))); //      |   - |    -
        assertEquals(false, new x().test(new Range<>(  1,  24, order))); //      |    -|    -
        assertEquals(false, new x().test(new Range<>(  2,  24, order))); //      |     +    -
        assertEquals(false, new x().test(new Range<>(  3,  24, order))); //      |     |-   -
        assertEquals(false, new x().test(new Range<>(  4,  24, order))); //      |     | -  -
        assertEquals(false, new x().test(new Range<>(-14,   0, order))); //    - |   - |
        assertEquals(false, new x().test(new Range<>(-13,   0, order))); //     -|   - |
        assertEquals(false, new x().test(new Range<>(-12,   0, order))); //      +   - |
        assertEquals(false, new x().test(new Range<>(-11,   0, order))); //      |-  - |
        assertEquals(false, new x().test(new Range<>(-10,   0, order))); //      | - - |
        assertEquals(false, new x().test(new Range<>(-10,   1, order))); //      | -  -|
        assertEquals(false, new x().test(new Range<>(-10,   2, order))); //      | -   +
        assertEquals(false, new x().test(new Range<>(-10,   3, order))); //      | -   |-
        assertEquals(false, new x().test(new Range<>(-10,   4, order))); //      | -   | -
        assertEquals(false, new x().test(new Range<>(-14,   2, order))); //    - |     +
        assertEquals(false, new x().test(new Range<>(-13,   2, order))); //     -|     +
        assertEquals(false, new x().test(new Range<>(-12,   2, order))); //      +     +
        assertEquals(false, new x().test(new Range<>(-11,   2, order))); //      |-    +
        assertEquals(false, new x().test(new Range<>(-10,   2, order))); //      | -   +
        assertEquals(false, new x().test(new Range<>(-12,   0, order))); //      +   - |
        assertEquals(false, new x().test(new Range<>(-12,   1, order))); //      +    -|
        assertEquals(false, new x().test(new Range<>(-12,   2, order))); //      +     +
        assertEquals(false, new x().test(new Range<>(-12,   3, order))); //      +     |-
        assertEquals(false, new x().test(new Range<>(-12,   4, order))); //      +     | -
    }

    /**
     * Test of adjoinsAtEndWith method, of class Fragment.
     */
    @Test
    public void testDoesAdjoinAtEnd() {
        class x {
            Range<Integer> tested = new Range<>(-12, 2, order);
            boolean test(Range<Integer> f) {
                return tested.adjoinsAtEndWith(f);
            }
        }
        assertEquals(false, new x().test(new Range<>(-36, -14, order))); //-   - |     |
        assertEquals(false, new x().test(new Range<>(-36, -13, order))); //-    -|     |
        assertEquals(false, new x().test(new Range<>(-36, -12, order))); //-     +     |
        assertEquals(false, new x().test(new Range<>(-36, -11, order))); //-     |-    |
        assertEquals(false, new x().test(new Range<>(-36, -10, order))); //-     | -   |
        assertEquals(false, new x().test(new Range<>(-36,   0, order))); //-     |   - |
        assertEquals(false, new x().test(new Range<>(-36,   1, order))); //-     |    -|
        assertEquals(false, new x().test(new Range<>(-36,   2, order))); //-     |     +
        assertEquals(false, new x().test(new Range<>(-36,   3, order))); //-     |     |-
        assertEquals(false, new x().test(new Range<>(-36,   4, order))); //-     |     | -
        assertEquals(false, new x().test(new Range<>(-14,  24, order))); //    - |     |    -
        assertEquals(false, new x().test(new Range<>(-13,  24, order))); //     -|     |    -
        assertEquals(false, new x().test(new Range<>(-12,  24, order))); //      +     |    -
        assertEquals(false, new x().test(new Range<>(-11,  24, order))); //      |-    |    -
        assertEquals(false, new x().test(new Range<>(-10,  24, order))); //      | -   |    -
        assertEquals(false, new x().test(new Range<>(  0,  24, order))); //      |   - |    -
        assertEquals(false, new x().test(new Range<>(  1,  24, order))); //      |    -|    -
        assertEquals(true , new x().test(new Range<>(  2,  24, order))); //      |     +    -
        assertEquals(false, new x().test(new Range<>(  3,  24, order))); //      |     |-   -
        assertEquals(false, new x().test(new Range<>(  4,  24, order))); //      |     | -  -
        assertEquals(false, new x().test(new Range<>(-14,   0, order))); //    - |   - |
        assertEquals(false, new x().test(new Range<>(-13,   0, order))); //     -|   - |
        assertEquals(false, new x().test(new Range<>(-12,   0, order))); //      +   - |
        assertEquals(false, new x().test(new Range<>(-11,   0, order))); //      |-  - |
        assertEquals(false, new x().test(new Range<>(-10,   0, order))); //      | - - |
        assertEquals(false, new x().test(new Range<>(-10,   1, order))); //      | -  -|
        assertEquals(false, new x().test(new Range<>(-10,   2, order))); //      | -   +
        assertEquals(false, new x().test(new Range<>(-10,   3, order))); //      | -   |-
        assertEquals(false, new x().test(new Range<>(-10,   4, order))); //      | -   | -
        assertEquals(false, new x().test(new Range<>(-14,   2, order))); //    - |     +
        assertEquals(false, new x().test(new Range<>(-13,   2, order))); //     -|     +
        assertEquals(false, new x().test(new Range<>(-12,   2, order))); //      +     +
        assertEquals(false, new x().test(new Range<>(-11,   2, order))); //      |-    +
        assertEquals(false, new x().test(new Range<>(-10,   2, order))); //      | -   +
        assertEquals(false, new x().test(new Range<>(-12,   0, order))); //      +   - |
        assertEquals(false, new x().test(new Range<>(-12,   1, order))); //      +    -|
        assertEquals(false, new x().test(new Range<>(-12,   2, order))); //      +     +
        assertEquals(false, new x().test(new Range<>(-12,   3, order))); //      +     |-
        assertEquals(false, new x().test(new Range<>(-12,   4, order))); //      +     | -
    }

    /**
     * Test of intersect method, of class Fragment.
     */
    @Test
    public void testIntersect() {
        class x {
            Range<Integer> tested = new Range<>(-12, 2, order);
            Range<Integer> test(Range<Integer> f) {
                return tested.intersect(f).orElse(null);
            }
        }
        assertEquals(null                        , new x().test(new Range<>(-36, -14, order))); //-   - |     |
        assertEquals(null                        , new x().test(new Range<>(-36, -13, order))); //-    -|     |
        assertEquals(null                        , new x().test(new Range<>(-36, -12, order))); //-     +     |
        assertEquals(new Range<>(-12, -11, order), new x().test(new Range<>(-36, -11, order))); //-     |-    |
        assertEquals(new Range<>(-12, -10, order), new x().test(new Range<>(-36, -10, order))); //-     | -   |
        assertEquals(new Range<>(-12,   0, order), new x().test(new Range<>(-36,   0, order))); //-     |   - |
        assertEquals(new Range<>(-12,   1, order), new x().test(new Range<>(-36,   1, order))); //-     |    -|
        assertEquals(new Range<>(-12,   2, order), new x().test(new Range<>(-36,   2, order))); //-     |     +
        assertEquals(new Range<>(-12,   2, order), new x().test(new Range<>(-36,   3, order))); //-     |     |-
        assertEquals(new Range<>(-12,   2, order), new x().test(new Range<>(-36,   4, order))); //-     |     | -
        assertEquals(new Range<>(-12,   2, order), new x().test(new Range<>(-14,  24, order))); //    - |     |    -
        assertEquals(new Range<>(-12,   2, order), new x().test(new Range<>(-13,  24, order))); //     -|     |    -
        assertEquals(new Range<>(-12,   2, order), new x().test(new Range<>(-12,  24, order))); //      +     |    -
        assertEquals(new Range<>(-11,   2, order), new x().test(new Range<>(-11,  24, order))); //      |-    |    -
        assertEquals(new Range<>(-10,   2, order), new x().test(new Range<>(-10,  24, order))); //      | -   |    -
        assertEquals(new Range<>(  0,   2, order), new x().test(new Range<>(  0,  24, order))); //      |   - |    -
        assertEquals(new Range<>(  1,   2, order), new x().test(new Range<>(  1,  24, order))); //      |    -|    -
        assertEquals(null                        , new x().test(new Range<>(  2,  24, order))); //      |     +    -
        assertEquals(null                        , new x().test(new Range<>(  3,  24, order))); //      |     |-   -
        assertEquals(null                        , new x().test(new Range<>(  4,  24, order))); //      |     | -  -
        assertEquals(new Range<>(-12,   0, order), new x().test(new Range<>(-14,   0, order))); //    - |   - |
        assertEquals(new Range<>(-12,   0, order), new x().test(new Range<>(-13,   0, order))); //     -|   - |
        assertEquals(new Range<>(-12,   0, order), new x().test(new Range<>(-12,   0, order))); //      +   - |
        assertEquals(new Range<>(-11,   0, order), new x().test(new Range<>(-11,   0, order))); //      |-  - |
        assertEquals(new Range<>(-10,   0, order), new x().test(new Range<>(-10,   0, order))); //      | - - |
        assertEquals(new Range<>(-10,   1, order), new x().test(new Range<>(-10,   1, order))); //      | -  -|
        assertEquals(new Range<>(-10,   2, order), new x().test(new Range<>(-10,   2, order))); //      | -   +
        assertEquals(new Range<>(-10,   2, order), new x().test(new Range<>(-10,   3, order))); //      | -   |-
        assertEquals(new Range<>(-10,   2, order), new x().test(new Range<>(-10,   4, order))); //      | -   | -
        assertEquals(new Range<>(-12,   2, order), new x().test(new Range<>(-14,   2, order))); //    - |     +
        assertEquals(new Range<>(-12,   2, order), new x().test(new Range<>(-13,   2, order))); //     -|     +
        assertEquals(new Range<>(-12,   2, order), new x().test(new Range<>(-12,   2, order))); //      +     +
        assertEquals(new Range<>(-11,   2, order), new x().test(new Range<>(-11,   2, order))); //      |-    +
        assertEquals(new Range<>(-10,   2, order), new x().test(new Range<>(-10,   2, order))); //      | -   +
        assertEquals(new Range<>(-12,   0, order), new x().test(new Range<>(-12,   0, order))); //      +   - |
        assertEquals(new Range<>(-12,   1, order), new x().test(new Range<>(-12,   1, order))); //      +    -|
        assertEquals(new Range<>(-12,   2, order), new x().test(new Range<>(-12,   2, order))); //      +     +
        assertEquals(new Range<>(-12,   2, order), new x().test(new Range<>(-12,   3, order))); //      +     |-
        assertEquals(new Range<>(-12,   2, order), new x().test(new Range<>(-12,   4, order))); //      +     | -
    }

    /**
     * Test of cover method, of class Fragment.
     */
    @Test
    public void testCover() {
        class x {
            Range<Integer> tested = new Range<>(-12, 2, order);
            Range<Integer> test(Range<Integer> f) {
                return tested.cover(f);
            }
        }
        assertEquals(new Range<>(-36,   2, order), new x().test(new Range<>(-36, -14, order))); //-   - |     |
        assertEquals(new Range<>(-36,   2, order), new x().test(new Range<>(-36, -13, order))); //-    -|     |
        assertEquals(new Range<>(-36,   2, order), new x().test(new Range<>(-36, -12, order))); //-     +     |
        assertEquals(new Range<>(-36,   2, order), new x().test(new Range<>(-36, -11, order))); //-     |-    |
        assertEquals(new Range<>(-36,   2, order), new x().test(new Range<>(-36, -10, order))); //-     | -   |
        assertEquals(new Range<>(-36,   2, order), new x().test(new Range<>(-36,   0, order))); //-     |   - |
        assertEquals(new Range<>(-36,   2, order), new x().test(new Range<>(-36,   1, order))); //-     |    -|
        assertEquals(new Range<>(-36,   2, order), new x().test(new Range<>(-36,   2, order))); //-     |     +
        assertEquals(new Range<>(-36,   3, order), new x().test(new Range<>(-36,   3, order))); //-     |     |-
        assertEquals(new Range<>(-36,   4, order), new x().test(new Range<>(-36,   4, order))); //-     |     | -
        assertEquals(new Range<>(-14,  24, order), new x().test(new Range<>(-14,  24, order))); //    - |     |    -
        assertEquals(new Range<>(-13,  24, order), new x().test(new Range<>(-13,  24, order))); //     -|     |    -
        assertEquals(new Range<>(-12,  24, order), new x().test(new Range<>(-12,  24, order))); //      +     |    -
        assertEquals(new Range<>(-12,  24, order), new x().test(new Range<>(-11,  24, order))); //      |-    |    -
        assertEquals(new Range<>(-12,  24, order), new x().test(new Range<>(-10,  24, order))); //      | -   |    -
        assertEquals(new Range<>(-12,  24, order), new x().test(new Range<>(  0,  24, order))); //      |   - |    -
        assertEquals(new Range<>(-12,  24, order), new x().test(new Range<>(  1,  24, order))); //      |    -|    -
        assertEquals(new Range<>(-12,  24, order), new x().test(new Range<>(  2,  24, order))); //      |     +    -
        assertEquals(new Range<>(-12,  24, order), new x().test(new Range<>(  3,  24, order))); //      |     |-   -
        assertEquals(new Range<>(-12,  24, order), new x().test(new Range<>(  4,  24, order))); //      |     | -  -
        assertEquals(new Range<>(-14,   2, order), new x().test(new Range<>(-14,   0, order))); //    - |   - |
        assertEquals(new Range<>(-13,   2, order), new x().test(new Range<>(-13,   0, order))); //     -|   - |
        assertEquals(new Range<>(-12,   2, order), new x().test(new Range<>(-12,   0, order))); //      +   - |
        assertEquals(new Range<>(-12,   2, order), new x().test(new Range<>(-11,   0, order))); //      |-  - |
        assertEquals(new Range<>(-12,   2, order), new x().test(new Range<>(-10,   0, order))); //      | - - |
        assertEquals(new Range<>(-12,   2, order), new x().test(new Range<>(-10,   1, order))); //      | -  -|
        assertEquals(new Range<>(-12,   2, order), new x().test(new Range<>(-10,   2, order))); //      | -   +
        assertEquals(new Range<>(-12,   3, order), new x().test(new Range<>(-10,   3, order))); //      | -   |-
        assertEquals(new Range<>(-12,   4, order), new x().test(new Range<>(-10,   4, order))); //      | -   | -
        assertEquals(new Range<>(-14,   2, order), new x().test(new Range<>(-14,   2, order))); //    - |     +
        assertEquals(new Range<>(-13,   2, order), new x().test(new Range<>(-13,   2, order))); //     -|     +
        assertEquals(new Range<>(-12,   2, order), new x().test(new Range<>(-12,   2, order))); //      +     +
        assertEquals(new Range<>(-12,   2, order), new x().test(new Range<>(-11,   2, order))); //      |-    +
        assertEquals(new Range<>(-12,   2, order), new x().test(new Range<>(-10,   2, order))); //      | -   +
        assertEquals(new Range<>(-12,   2, order), new x().test(new Range<>(-12,   0, order))); //      +   - |
        assertEquals(new Range<>(-12,   2, order), new x().test(new Range<>(-12,   1, order))); //      +    -|
        assertEquals(new Range<>(-12,   2, order), new x().test(new Range<>(-12,   2, order))); //      +     +
        assertEquals(new Range<>(-12,   3, order), new x().test(new Range<>(-12,   3, order))); //      +     |-
        assertEquals(new Range<>(-12,   4, order), new x().test(new Range<>(-12,   4, order))); //      +     | -
    }

    /**
     * Test of getUpper method, of class Fragment.
     */
    @Test
    public void testGetUpper_GenericType() {
        class y {
            Range<Integer> tested = new Range<>(-12, 2, order);
            Range<Integer> test(int f) {
                return tested.getUpper(f).orElse(null);
            }
        }
        assertEquals(new Range<>(-12,   2, order), new y().test(-14));
        assertEquals(new Range<>(-12,   2, order), new y().test(-13));
        assertEquals(new Range<>(-12,   2, order), new y().test(-12));
        assertEquals(new Range<>(-11,   2, order), new y().test(-11));
        assertEquals(new Range<>(-10,   2, order), new y().test(-10));
        assertEquals(new Range<>(  0,   2, order), new y().test(0));
        assertEquals(new Range<>(  1,   2, order), new y().test(1));
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
            Range<Integer> tested = new Range<>(-12, 2, order);
            Range<Integer> test(Range<Integer> f) {
                return f.getUpperRoomIn(tested).orElse(null);
            }
        }
        assertEquals(new Range<>(-12,   2, order), new x().test(new Range<>(-36, -14, order))); //-   - |     |
        assertEquals(new Range<>(-12,   2, order), new x().test(new Range<>(-36, -13, order))); //-    -|     |
        assertEquals(new Range<>(-12,   2, order), new x().test(new Range<>(-36, -12, order))); //-     +     |
        assertEquals(new Range<>(-11,   2, order), new x().test(new Range<>(-36, -11, order))); //-     |-    |
        assertEquals(new Range<>(-10,   2, order), new x().test(new Range<>(-36, -10, order))); //-     | -   |
        assertEquals(new Range<>(  0,   2, order), new x().test(new Range<>(-36,   0, order))); //-     |   - |
        assertEquals(new Range<>(  1,   2, order), new x().test(new Range<>(-36,   1, order))); //-     |    -|
        assertEquals(null          , new x().test(new Range<>(-36,   2, order))); //-     |     +
        assertEquals(null          , new x().test(new Range<>(-36,   3, order))); //-     |     |-
        assertEquals(null          , new x().test(new Range<>(-36,   4, order))); //-     |     | -
        assertEquals(null          , new x().test(new Range<>(-14,  24, order))); //    - |     |    -
        assertEquals(null          , new x().test(new Range<>(-13,  24, order))); //     -|     |    -
        assertEquals(null          , new x().test(new Range<>(-12,  24, order))); //      +     |    -
        assertEquals(null          , new x().test(new Range<>(-11,  24, order))); //      |-    |    -
        assertEquals(null          , new x().test(new Range<>(-10,  24, order))); //      | -   |    -
        assertEquals(null          , new x().test(new Range<>(  0,  24, order))); //      |   - |    -
        assertEquals(null          , new x().test(new Range<>(  1,  24, order))); //      |    -|    -
        assertEquals(null          , new x().test(new Range<>(  2,  24, order))); //      |     +    -
        assertEquals(null          , new x().test(new Range<>(  3,  24, order))); //      |     |-   -
        assertEquals(null          , new x().test(new Range<>(  4,  24, order))); //      |     | -  -
        assertEquals(new Range<>(  0,   2, order), new x().test(new Range<>(-14,   0, order))); //    - |   - |
        assertEquals(new Range<>(  0,   2, order), new x().test(new Range<>(-13,   0, order))); //     -|   - |
        assertEquals(new Range<>(  0,   2, order), new x().test(new Range<>(-12,   0, order))); //      +   - |
        assertEquals(new Range<>(  0,   2, order), new x().test(new Range<>(-11,   0, order))); //      |-  - |
        assertEquals(new Range<>(  0,   2, order), new x().test(new Range<>(-10,   0, order))); //      | - - |
        assertEquals(new Range<>(  1,   2, order), new x().test(new Range<>(-10,   1, order))); //      | -  -|
        assertEquals(null          , new x().test(new Range<>(-10,   2, order))); //      | -   +
        assertEquals(null          , new x().test(new Range<>(-10,   3, order))); //      | -   |-
        assertEquals(null          , new x().test(new Range<>(-10,   4, order))); //      | -   | -
        assertEquals(null          , new x().test(new Range<>(-14,   2, order))); //    - |     +
        assertEquals(null          , new x().test(new Range<>(-13,   2, order))); //     -|     +
        assertEquals(null          , new x().test(new Range<>(-12,   2, order))); //      +     +
        assertEquals(null          , new x().test(new Range<>(-11,   2, order))); //      |-    +
        assertEquals(null          , new x().test(new Range<>(-10,   2, order))); //      | -   +
        assertEquals(new Range<>(  0,   2, order), new x().test(new Range<>(-12,   0, order))); //      +   - |
        assertEquals(new Range<>(  1,   2, order), new x().test(new Range<>(-12,   1, order))); //      +    -|
        assertEquals(null          , new x().test(new Range<>(-12,   2, order))); //      +     +
        assertEquals(null          , new x().test(new Range<>(-12,   3, order))); //      +     |-
        assertEquals(null          , new x().test(new Range<>(-12,   4, order))); //      +     | -
    }

    /**
     * Test of getLower method, of class Fragment.
     */
    @Test
    public void testGetLower_GenericType() {
        class y {
            Range<Integer> tested = new Range<>(-12, 2, order);
            Range<Integer> test(int f) {
                return tested.getLower(f).orElse(null);
            }
        }
        assertEquals(null          , new y().test(-14));
        assertEquals(null          , new y().test(-13));
        assertEquals(null          , new y().test(-12));
        assertEquals(new Range<>(-12, -11, order), new y().test(-11));
        assertEquals(new Range<>(-12, -10, order), new y().test(-10));
        assertEquals(new Range<>(-12,   0, order), new y().test(0));
        assertEquals(new Range<>(-12,   1, order), new y().test(1));
        assertEquals(new Range<>(-12,   2, order), new y().test(2));
        assertEquals(new Range<>(-12,   2, order), new y().test(3));
        assertEquals(new Range<>(-12,   2, order), new y().test(4));
    }

    /**
     * Test of getLower method, of class Fragment.
     */
    @Test
    public void testGetLower_Fragment() {
        class x {
            Range<Integer> tested = new Range<>(-12, 2, order);
            Range<Integer> test(Range<Integer> f) {
                return f.getLowerRoomIn(tested).orElse(null);
            }
        }
        assertEquals(null          , new x().test(new Range<>(-36, -14, order))); //-   - |     |
        assertEquals(null          , new x().test(new Range<>(-36, -13, order))); //-    -|     |
        assertEquals(null          , new x().test(new Range<>(-36, -12, order))); //-     +     |
        assertEquals(null          , new x().test(new Range<>(-36, -11, order))); //-     |-    |
        assertEquals(null          , new x().test(new Range<>(-36, -10, order))); //-     | -   |
        assertEquals(null          , new x().test(new Range<>(-36,   0, order))); //-     |   - |
        assertEquals(null          , new x().test(new Range<>(-36,   1, order))); //-     |    -|
        assertEquals(null          , new x().test(new Range<>(-36,   2, order))); //-     |     +
        assertEquals(null          , new x().test(new Range<>(-36,   3, order))); //-     |     |-
        assertEquals(null          , new x().test(new Range<>(-36,   4, order))); //-     |     | -
        assertEquals(null          , new x().test(new Range<>(-14,  24, order))); //    - |     |    -
        assertEquals(null          , new x().test(new Range<>(-13,  24, order))); //     -|     |    -
        assertEquals(null          , new x().test(new Range<>(-12,  24, order))); //      +     |    -
        assertEquals(new Range<>(-12, -11, order), new x().test(new Range<>(-11,  24, order))); //      |-    |    -
        assertEquals(new Range<>(-12, -10, order), new x().test(new Range<>(-10,  24, order))); //      | -   |    -
        assertEquals(new Range<>(-12,   0, order), new x().test(new Range<>(  0,  24, order))); //      |   - |    -
        assertEquals(new Range<>(-12,   1, order), new x().test(new Range<>(  1,  24, order))); //      |    -|    -
        assertEquals(new Range<>(-12,   2, order), new x().test(new Range<>(  2,  24, order))); //      |     +    -
        assertEquals(new Range<>(-12,   2, order), new x().test(new Range<>(  3,  24, order))); //      |     |-   -
        assertEquals(new Range<>(-12,   2, order), new x().test(new Range<>(  4,  24, order))); //      |     | -  -
        assertEquals(null          , new x().test(new Range<>(-14,   0, order))); //    - |   - |
        assertEquals(null          , new x().test(new Range<>(-13,   0, order))); //     -|   - |
        assertEquals(null          , new x().test(new Range<>(-12,   0, order))); //      +   - |
        assertEquals(new Range<>(-12, -11, order), new x().test(new Range<>(-11,   0, order))); //      |-  - |
        assertEquals(new Range<>(-12, -10, order), new x().test(new Range<>(-10,   0, order))); //      | - - |
        assertEquals(new Range<>(-12, -10, order), new x().test(new Range<>(-10,   1, order))); //      | -  -|
        assertEquals(new Range<>(-12, -10, order), new x().test(new Range<>(-10,   2, order))); //      | -   +
        assertEquals(new Range<>(-12, -10, order), new x().test(new Range<>(-10,   3, order))); //      | -   |-
        assertEquals(new Range<>(-12, -10, order), new x().test(new Range<>(-10,   4, order))); //      | -   | -
        assertEquals(null          , new x().test(new Range<>(-14,   2, order))); //    - |     +
        assertEquals(null          , new x().test(new Range<>(-13,   2, order))); //     -|     +
        assertEquals(null          , new x().test(new Range<>(-12,   2, order))); //      +     +
        assertEquals(new Range<>(-12, -11, order), new x().test(new Range<>(-11,   2, order))); //      |-    +
        assertEquals(new Range<>(-12, -10, order), new x().test(new Range<>(-10,   2, order))); //      | -   +
        assertEquals(null          , new x().test(new Range<>(-12,   0, order))); //      +   - |
        assertEquals(null          , new x().test(new Range<>(-12,   1, order))); //      +    -|
        assertEquals(null          , new x().test(new Range<>(-12,   2, order))); //      +     +
        assertEquals(null          , new x().test(new Range<>(-12,   3, order))); //      +     |-
        assertEquals(null          , new x().test(new Range<>(-12,   4, order))); //      +     | -
    }
}
