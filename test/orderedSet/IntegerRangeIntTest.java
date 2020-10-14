/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package orderedSet;

import collection.TList;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.Test;

/**
 *
 * @author mtomono
 */
public class IntegerRangeIntTest {
    
    public IntegerRangeIntTest() {
    }

    /**
     * Test of clone method, of class Fragment.
     */
    @Test
    public void testClone() {
        RangeInt original = new RangeInt(-12, 2);
        RangeInt tested = (RangeInt)original.clone();
        assertEquals(original.start(), tested.start());
        assertEquals(original.end(), tested.end());
    }
    
    @Test
    public void testEquals() {
        class x {
            RangeInt tested = new RangeInt(-12, 2);
            boolean test(RangeInt f) {
                return tested.equals(f);
            }
        }
        assertEquals(false, new x().test(new RangeInt(-36, -14))); //-   - |     |
        assertEquals(false, new x().test(new RangeInt(-36, -13))); //-    -|     |
        assertEquals(false, new x().test(new RangeInt(-36, -12))); //-     +     |
        assertEquals(false, new x().test(new RangeInt(-36, -11))); //-     |-    |
        assertEquals(false, new x().test(new RangeInt(-36, -10))); //-     | -   |
        assertEquals(false, new x().test(new RangeInt(-36,   0))); //-     |   - |
        assertEquals(false, new x().test(new RangeInt(-36,   1))); //-     |    -|
        assertEquals(false, new x().test(new RangeInt(-36,   2))); //-     |     +
        assertEquals(false, new x().test(new RangeInt(-36,   3))); //-     |     |-
        assertEquals(false, new x().test(new RangeInt(-36,   4))); //-     |     | -
        assertEquals(false, new x().test(new RangeInt(-14,  24))); //    - |     |    -
        assertEquals(false, new x().test(new RangeInt(-13,  24))); //     -|     |    -
        assertEquals(false, new x().test(new RangeInt(-12,  24))); //      +     |    -
        assertEquals(false, new x().test(new RangeInt(-11,  24))); //      |-    |    -
        assertEquals(false, new x().test(new RangeInt(-10,  24))); //      | -   |    -
        assertEquals(false, new x().test(new RangeInt(  0,  24))); //      |   - |    -
        assertEquals(false, new x().test(new RangeInt(  1,  24))); //      |    -|    -
        assertEquals(false, new x().test(new RangeInt(  2,  24))); //      |     +    -
        assertEquals(false, new x().test(new RangeInt(  3,  24))); //      |     |-   -
        assertEquals(false, new x().test(new RangeInt(  4,  24))); //      |     | -  -
        assertEquals(false, new x().test(new RangeInt(-14,   0))); //    - |   - |
        assertEquals(false, new x().test(new RangeInt(-13,   0))); //     -|   - |
        assertEquals(false, new x().test(new RangeInt(-12,   0))); //      +   - |
        assertEquals(false, new x().test(new RangeInt(-11,   0))); //      |-  - |
        assertEquals(false, new x().test(new RangeInt(-10,   0))); //      | - - |
        assertEquals(false, new x().test(new RangeInt(-10,   1))); //      | -  -|
        assertEquals(false, new x().test(new RangeInt(-10,   2))); //      | -   +
        assertEquals(false, new x().test(new RangeInt(-10,   3))); //      | -   |-
        assertEquals(false, new x().test(new RangeInt(-10,   4))); //      | -   | -
        assertEquals(false, new x().test(new RangeInt(-14,   2))); //    - |     +
        assertEquals(false, new x().test(new RangeInt(-13,   2))); //     -|     +
        assertEquals(true , new x().test(new RangeInt(-12,   2))); //      +     +
        assertEquals(false, new x().test(new RangeInt(-11,   2))); //      |-    +
        assertEquals(false, new x().test(new RangeInt(-10,   2))); //      | -   +
        assertEquals(false, new x().test(new RangeInt(-12,   0))); //      +   - |
        assertEquals(false, new x().test(new RangeInt(-12,   1))); //      +    -|
        assertEquals(true , new x().test(new RangeInt(-12,   2))); //      +     +
        assertEquals(false, new x().test(new RangeInt(-12,   3))); //      +     |-
        assertEquals(false, new x().test(new RangeInt(-12,   4))); //      +     | -
    }
    
    /**
     * Test of getStart method, of class Fragment.
     */
    @Test
    public void testGetStart() {
        RangeInt tested = new RangeInt(-12, 2);
        assertEquals(-12, tested.start());
    }

    /**
     * Test of getEnd method, of class Fragment.
     */
    @Test
    public void testGetEnd() {
        RangeInt tested = new RangeInt(-12, 2);
        assertEquals(2, tested.end());
    }

    /**
     * Test of contains method, of class Fragment.
     */
    @Test
    public void testContains_GenericType() {
        class y {
            RangeInt tested = new RangeInt(-12, 2);
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
            RangeInt tested = new RangeInt(-12, 2);
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
            RangeInt tested = new RangeInt(-12, 2);
            boolean test(RangeInt f) {
                return tested.isUpperThan(f);
            }
        }
        assertEquals(true , new x().test(new RangeInt(-36, -14))); //-   - |     |
        assertEquals(true , new x().test(new RangeInt(-36, -13))); //-    -|     |
        assertEquals(true , new x().test(new RangeInt(-36, -12))); //-     +     |
        assertEquals(false, new x().test(new RangeInt(-36, -11))); //-     |-    |
        assertEquals(false, new x().test(new RangeInt(-36, -10))); //-     | -   |
        assertEquals(false, new x().test(new RangeInt(-36,   0))); //-     |   - |
        assertEquals(false, new x().test(new RangeInt(-36,   1))); //-     |    -|
        assertEquals(false, new x().test(new RangeInt(-36,   2))); //-     |     +
        assertEquals(false, new x().test(new RangeInt(-36,   3))); //-     |     |-
        assertEquals(false, new x().test(new RangeInt(-36,   4))); //-     |     | -
        assertEquals(false, new x().test(new RangeInt(-14,  24))); //    - |     |    -
        assertEquals(false, new x().test(new RangeInt(-13,  24))); //     -|     |    -
        assertEquals(false, new x().test(new RangeInt(-12,  24))); //      +     |    -
        assertEquals(false, new x().test(new RangeInt(-11,  24))); //      |-    |    -
        assertEquals(false, new x().test(new RangeInt(-10,  24))); //      | -   |    -
        assertEquals(false, new x().test(new RangeInt(  0,  24))); //      |   - |    -
        assertEquals(false, new x().test(new RangeInt(  1,  24))); //      |    -|    -
        assertEquals(false, new x().test(new RangeInt(  2,  24))); //      |     +    -
        assertEquals(false, new x().test(new RangeInt(  3,  24))); //      |     |-   -
        assertEquals(false, new x().test(new RangeInt(  4,  24))); //      |     | -  -
        assertEquals(false, new x().test(new RangeInt(-14,   0))); //    - |   - |
        assertEquals(false, new x().test(new RangeInt(-13,   0))); //     -|   - |
        assertEquals(false, new x().test(new RangeInt(-12,   0))); //      +   - |
        assertEquals(false, new x().test(new RangeInt(-11,   0))); //      |-  - |
        assertEquals(false, new x().test(new RangeInt(-10,   0))); //      | - - |
        assertEquals(false, new x().test(new RangeInt(-10,   1))); //      | -  -|
        assertEquals(false, new x().test(new RangeInt(-10,   2))); //      | -   +
        assertEquals(false, new x().test(new RangeInt(-10,   3))); //      | -   |-
        assertEquals(false, new x().test(new RangeInt(-10,   4))); //      | -   | -
        assertEquals(false, new x().test(new RangeInt(-14,   2))); //    - |     +
        assertEquals(false, new x().test(new RangeInt(-13,   2))); //     -|     +
        assertEquals(false, new x().test(new RangeInt(-12,   2))); //      +     +
        assertEquals(false, new x().test(new RangeInt(-11,   2))); //      |-    +
        assertEquals(false, new x().test(new RangeInt(-10,   2))); //      | -   +
        assertEquals(false, new x().test(new RangeInt(-12,   0))); //      +   - |
        assertEquals(false, new x().test(new RangeInt(-12,   1))); //      +    -|
        assertEquals(false, new x().test(new RangeInt(-12,   2))); //      +     +
        assertEquals(false, new x().test(new RangeInt(-12,   3))); //      +     |-
        assertEquals(false, new x().test(new RangeInt(-12,   4))); //      +     | -
    }

    /**
     * Test of isBelow method, of class Fragment.
     */
    @Test
    public void testIsBelow_GenericType() {
        class y {
            RangeInt tested = new RangeInt(-12, 2);
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
            RangeInt tested = new RangeInt(-12, 2);
            boolean test(RangeInt f) {
                return tested.isLowerThan(f);
            }
        }
        assertEquals(false, new x().test(new RangeInt(-36, -14))); //-   - |     |
        assertEquals(false, new x().test(new RangeInt(-36, -13))); //-    -|     |
        assertEquals(false, new x().test(new RangeInt(-36, -12))); //-     +     |
        assertEquals(false, new x().test(new RangeInt(-36, -11))); //-     |-    |
        assertEquals(false, new x().test(new RangeInt(-36, -10))); //-     | -   |
        assertEquals(false, new x().test(new RangeInt(-36,   0))); //-     |   - |
        assertEquals(false, new x().test(new RangeInt(-36,   1))); //-     |    -|
        assertEquals(false, new x().test(new RangeInt(-36,   2))); //-     |     +
        assertEquals(false, new x().test(new RangeInt(-36,   3))); //-     |     |-
        assertEquals(false, new x().test(new RangeInt(-36,   4))); //-     |     | -
        assertEquals(false, new x().test(new RangeInt(-14,  24))); //    - |     |    -
        assertEquals(false, new x().test(new RangeInt(-13,  24))); //     -|     |    -
        assertEquals(false, new x().test(new RangeInt(-12,  24))); //      +     |    -
        assertEquals(false, new x().test(new RangeInt(-11,  24))); //      |-    |    -
        assertEquals(false, new x().test(new RangeInt(-10,  24))); //      | -   |    -
        assertEquals(false, new x().test(new RangeInt(  0,  24))); //      |   - |    -
        assertEquals(false, new x().test(new RangeInt(  1,  24))); //      |    -|    -
        assertEquals(true , new x().test(new RangeInt(  2,  24))); //      |     +    -
        assertEquals(true , new x().test(new RangeInt(  3,  24))); //      |     |-   -
        assertEquals(true , new x().test(new RangeInt(  4,  24))); //      |     | -  -
        assertEquals(false, new x().test(new RangeInt(-14,   0))); //    - |   - |
        assertEquals(false, new x().test(new RangeInt(-13,   0))); //     -|   - |
        assertEquals(false, new x().test(new RangeInt(-12,   0))); //      +   - |
        assertEquals(false, new x().test(new RangeInt(-11,   0))); //      |-  - |
        assertEquals(false, new x().test(new RangeInt(-10,   0))); //      | - - |
        assertEquals(false, new x().test(new RangeInt(-10,   1))); //      | -  -|
        assertEquals(false, new x().test(new RangeInt(-10,   2))); //      | -   +
        assertEquals(false, new x().test(new RangeInt(-10,   3))); //      | -   |-
        assertEquals(false, new x().test(new RangeInt(-10,   4))); //      | -   | -
        assertEquals(false, new x().test(new RangeInt(-14,   2))); //    - |     +
        assertEquals(false, new x().test(new RangeInt(-13,   2))); //     -|     +
        assertEquals(false, new x().test(new RangeInt(-12,   2))); //      +     +
        assertEquals(false, new x().test(new RangeInt(-11,   2))); //      |-    +
        assertEquals(false, new x().test(new RangeInt(-10,   2))); //      | -   +
        assertEquals(false, new x().test(new RangeInt(-12,   0))); //      +   - |
        assertEquals(false, new x().test(new RangeInt(-12,   1))); //      +    -|
        assertEquals(false, new x().test(new RangeInt(-12,   2))); //      +     +
        assertEquals(false, new x().test(new RangeInt(-12,   3))); //      +     |-
        assertEquals(false, new x().test(new RangeInt(-12,   4))); //      +     | -
    }

    /**
     * Test of doesOverrap method, of class Fragment.
     */
    @Test
    public void testDoesOverlap() {
        class x {
            RangeInt tested = new RangeInt(-12, 2);
            boolean test(RangeInt f) {
                return tested.overlaps(f);
            }
        }
        assertEquals(false, new x().test(new RangeInt(-36, -14))); //-   - |     |
        assertEquals(false, new x().test(new RangeInt(-36, -13))); //-    -|     |
        assertEquals(false, new x().test(new RangeInt(-36, -12))); //-     +     |
        assertEquals(true , new x().test(new RangeInt(-36, -11))); //-     |-    |
        assertEquals(true , new x().test(new RangeInt(-36, -10))); //-     | -   |
        assertEquals(true , new x().test(new RangeInt(-36,   0))); //-     |   - |
        assertEquals(true , new x().test(new RangeInt(-36,   1))); //-     |    -|
        assertEquals(true , new x().test(new RangeInt(-36,   2))); //-     |     +
        assertEquals(true , new x().test(new RangeInt(-36,   3))); //-     |     |-
        assertEquals(true , new x().test(new RangeInt(-36,   4))); //-     |     | -
        assertEquals(true , new x().test(new RangeInt(-14,  24))); //    - |     |    -
        assertEquals(true , new x().test(new RangeInt(-13,  24))); //     -|     |    -
        assertEquals(true , new x().test(new RangeInt(-12,  24))); //      +     |    -
        assertEquals(true , new x().test(new RangeInt(-11,  24))); //      |-    |    -
        assertEquals(true , new x().test(new RangeInt(-10,  24))); //      | -   |    -
        assertEquals(true , new x().test(new RangeInt(  0,  24))); //      |   - |    -
        assertEquals(true , new x().test(new RangeInt(  1,  24))); //      |    -|    -
        assertEquals(false, new x().test(new RangeInt(  2,  24))); //      |     +    -
        assertEquals(false, new x().test(new RangeInt(  3,  24))); //      |     |-   -
        assertEquals(false, new x().test(new RangeInt(  4,  24))); //      |     | -  -
        assertEquals(true , new x().test(new RangeInt(-14,   0))); //    - |   - |
        assertEquals(true , new x().test(new RangeInt(-13,   0))); //     -|   - |
        assertEquals(true , new x().test(new RangeInt(-12,   0))); //      +   - |
        assertEquals(true , new x().test(new RangeInt(-11,   0))); //      |-  - |
        assertEquals(true , new x().test(new RangeInt(-10,   0))); //      | - - |
        assertEquals(true , new x().test(new RangeInt(-10,   1))); //      | -  -|
        assertEquals(true , new x().test(new RangeInt(-10,   2))); //      | -   +
        assertEquals(true , new x().test(new RangeInt(-10,   3))); //      | -   |-
        assertEquals(true , new x().test(new RangeInt(-10,   4))); //      | -   | -
        assertEquals(true , new x().test(new RangeInt(-14,   2))); //    - |     +
        assertEquals(true , new x().test(new RangeInt(-13,   2))); //     -|     +
        assertEquals(true , new x().test(new RangeInt(-12,   2))); //      +     +
        assertEquals(true , new x().test(new RangeInt(-11,   2))); //      |-    +
        assertEquals(true , new x().test(new RangeInt(-10,   2))); //      | -   +
        assertEquals(true , new x().test(new RangeInt(-12,   0))); //      +   - |
        assertEquals(true , new x().test(new RangeInt(-12,   1))); //      +    -|
        assertEquals(true , new x().test(new RangeInt(-12,   2))); //      +     +
        assertEquals(true , new x().test(new RangeInt(-12,   3))); //      +     |-
        assertEquals(true , new x().test(new RangeInt(-12,   4))); //      +     | -
    }

    /**
     * Test of contains method, of class Fragment.
     */
    @Test
    public void testDoesContain_Fragment() {
        class x {
            RangeInt tested = new RangeInt(-12, 2);
            boolean test(RangeInt f) {
                return tested.contains(f);
            }
        }
        assertEquals(false, new x().test(new RangeInt(-36, -14))); //-   - |     |
        assertEquals(false, new x().test(new RangeInt(-36, -13))); //-    -|     |
        assertEquals(false, new x().test(new RangeInt(-36, -12))); //-     +     |
        assertEquals(false, new x().test(new RangeInt(-36, -11))); //-     |-    |
        assertEquals(false, new x().test(new RangeInt(-36, -10))); //-     | -   |
        assertEquals(false, new x().test(new RangeInt(-36,   0))); //-     |   - |
        assertEquals(false, new x().test(new RangeInt(-36,   1))); //-     |    -|
        assertEquals(false, new x().test(new RangeInt(-36,   2))); //-     |     +
        assertEquals(false, new x().test(new RangeInt(-36,   3))); //-     |     |-
        assertEquals(false, new x().test(new RangeInt(-36,   4))); //-     |     | -
        assertEquals(false, new x().test(new RangeInt(-14,  24))); //    - |     |    -
        assertEquals(false, new x().test(new RangeInt(-13,  24))); //     -|     |    -
        assertEquals(false, new x().test(new RangeInt(-12,  24))); //      +     |    -
        assertEquals(false, new x().test(new RangeInt(-11,  24))); //      |-    |    -
        assertEquals(false, new x().test(new RangeInt(-10,  24))); //      | -   |    -
        assertEquals(false, new x().test(new RangeInt(  0,  24))); //      |   - |    -
        assertEquals(false, new x().test(new RangeInt(  1,  24))); //      |    -|    -
        assertEquals(false, new x().test(new RangeInt(  2,  24))); //      |     +    -
        assertEquals(false, new x().test(new RangeInt(  3,  24))); //      |     |-   -
        assertEquals(false, new x().test(new RangeInt(  4,  24))); //      |     | -  -
        assertEquals(false, new x().test(new RangeInt(-14,   0))); //    - |   - |
        assertEquals(false, new x().test(new RangeInt(-13,   0))); //     -|   - |
        assertEquals(true , new x().test(new RangeInt(-12,   0))); //      +   - |
        assertEquals(true , new x().test(new RangeInt(-11,   0))); //      |-  - |
        assertEquals(true , new x().test(new RangeInt(-10,   0))); //      | - - |
        assertEquals(true , new x().test(new RangeInt(-10,   1))); //      | -  -|
        assertEquals(true , new x().test(new RangeInt(-10,   2))); //      | -   +
        assertEquals(false, new x().test(new RangeInt(-10,   3))); //      | -   |-
        assertEquals(false, new x().test(new RangeInt(-10,   4))); //      | -   | -
        assertEquals(false, new x().test(new RangeInt(-14,   2))); //    - |     +
        assertEquals(false, new x().test(new RangeInt(-13,   2))); //     -|     +
        assertEquals(true , new x().test(new RangeInt(-12,   2))); //      +     +
        assertEquals(true , new x().test(new RangeInt(-11,   2))); //      |-    +
        assertEquals(true , new x().test(new RangeInt(-10,   2))); //      | -   +
        assertEquals(true , new x().test(new RangeInt(-12,   0))); //      +   - |
        assertEquals(true , new x().test(new RangeInt(-12,   1))); //      +    -|
        assertEquals(true , new x().test(new RangeInt(-12,   2))); //      +     +
        assertEquals(false, new x().test(new RangeInt(-12,   3))); //      +     |-
        assertEquals(false, new x().test(new RangeInt(-12,   4))); //      +     | -
    }

    /**
     * Test of adjoins method, of class Fragment.
     */
    @Test
    public void testDoesAdjoin() {
        class x {
            RangeInt tested = new RangeInt(-12, 2);
            boolean test(RangeInt f) {
                return tested.adjoins(f);
            }
        }
        assertEquals(false, new x().test(new RangeInt(-36, -14))); //-   - |     |
        assertEquals(false, new x().test(new RangeInt(-36, -13))); //-    -|     |
        assertEquals(true , new x().test(new RangeInt(-36, -12))); //-     +     |
        assertEquals(false, new x().test(new RangeInt(-36, -11))); //-     |-    |
        assertEquals(false, new x().test(new RangeInt(-36, -10))); //-     | -   |
        assertEquals(false, new x().test(new RangeInt(-36,   0))); //-     |   - |
        assertEquals(false, new x().test(new RangeInt(-36,   1))); //-     |    -|
        assertEquals(false, new x().test(new RangeInt(-36,   2))); //-     |     +
        assertEquals(false, new x().test(new RangeInt(-36,   3))); //-     |     |-
        assertEquals(false, new x().test(new RangeInt(-36,   4))); //-     |     | -
        assertEquals(false, new x().test(new RangeInt(-14,  24))); //    - |     |    -
        assertEquals(false, new x().test(new RangeInt(-13,  24))); //     -|     |    -
        assertEquals(false, new x().test(new RangeInt(-12,  24))); //      +     |    -
        assertEquals(false, new x().test(new RangeInt(-11,  24))); //      |-    |    -
        assertEquals(false, new x().test(new RangeInt(-10,  24))); //      | -   |    -
        assertEquals(false, new x().test(new RangeInt(  0,  24))); //      |   - |    -
        assertEquals(false, new x().test(new RangeInt(  1,  24))); //      |    -|    -
        assertEquals(true , new x().test(new RangeInt(  2,  24))); //      |     +    -
        assertEquals(false, new x().test(new RangeInt(  3,  24))); //      |     |-   -
        assertEquals(false, new x().test(new RangeInt(  4,  24))); //      |     | -  -
        assertEquals(false, new x().test(new RangeInt(-14,   2))); //    - |     +
        assertEquals(false, new x().test(new RangeInt(-13,   2))); //     -|     +
        assertEquals(false, new x().test(new RangeInt(-12,   2))); //      +     +
        assertEquals(false, new x().test(new RangeInt(-11,   2))); //      |-    +
        assertEquals(false, new x().test(new RangeInt(-10,   2))); //      | -   +
        assertEquals(false, new x().test(new RangeInt(-12,   0))); //      +   - |
        assertEquals(false, new x().test(new RangeInt(-12,   1))); //      +    -|
        assertEquals(false, new x().test(new RangeInt(-12,   2))); //      +     +
        assertEquals(false, new x().test(new RangeInt(-12,   3))); //      +     |-
        assertEquals(false, new x().test(new RangeInt(-12,   4))); //      +     | -
    }

    /**
     * Test of adjoinsAtStartWith method, of class Fragment.
     */
    @Test
    public void testDoesAdjoinAtStart() {
        class x {
            RangeInt tested = new RangeInt(-12, 2);
            boolean test(RangeInt f) {
                return tested.adjoinsAtStartWith(f);
            }
        }
        assertEquals(false, new x().test(new RangeInt(-36, -14))); //-   - |     |
        assertEquals(false, new x().test(new RangeInt(-36, -13))); //-    -|     |
        assertEquals(true , new x().test(new RangeInt(-36, -12))); //-     +     |
        assertEquals(false, new x().test(new RangeInt(-36, -11))); //-     |-    |
        assertEquals(false, new x().test(new RangeInt(-36, -10))); //-     | -   |
        assertEquals(false, new x().test(new RangeInt(-36,   0))); //-     |   - |
        assertEquals(false, new x().test(new RangeInt(-36,   1))); //-     |    -|
        assertEquals(false, new x().test(new RangeInt(-36,   2))); //-     |     +
        assertEquals(false, new x().test(new RangeInt(-36,   3))); //-     |     |-
        assertEquals(false, new x().test(new RangeInt(-36,   4))); //-     |     | -
        assertEquals(false, new x().test(new RangeInt(-14,  24))); //    - |     |    -
        assertEquals(false, new x().test(new RangeInt(-13,  24))); //     -|     |    -
        assertEquals(false, new x().test(new RangeInt(-12,  24))); //      +     |    -
        assertEquals(false, new x().test(new RangeInt(-11,  24))); //      |-    |    -
        assertEquals(false, new x().test(new RangeInt(-10,  24))); //      | -   |    -
        assertEquals(false, new x().test(new RangeInt(  0,  24))); //      |   - |    -
        assertEquals(false, new x().test(new RangeInt(  1,  24))); //      |    -|    -
        assertEquals(false, new x().test(new RangeInt(  2,  24))); //      |     +    -
        assertEquals(false, new x().test(new RangeInt(  3,  24))); //      |     |-   -
        assertEquals(false, new x().test(new RangeInt(  4,  24))); //      |     | -  -
        assertEquals(false, new x().test(new RangeInt(-14,   0))); //    - |   - |
        assertEquals(false, new x().test(new RangeInt(-13,   0))); //     -|   - |
        assertEquals(false, new x().test(new RangeInt(-12,   0))); //      +   - |
        assertEquals(false, new x().test(new RangeInt(-11,   0))); //      |-  - |
        assertEquals(false, new x().test(new RangeInt(-10,   0))); //      | - - |
        assertEquals(false, new x().test(new RangeInt(-10,   1))); //      | -  -|
        assertEquals(false, new x().test(new RangeInt(-10,   2))); //      | -   +
        assertEquals(false, new x().test(new RangeInt(-10,   3))); //      | -   |-
        assertEquals(false, new x().test(new RangeInt(-10,   4))); //      | -   | -
        assertEquals(false, new x().test(new RangeInt(-14,   2))); //    - |     +
        assertEquals(false, new x().test(new RangeInt(-13,   2))); //     -|     +
        assertEquals(false, new x().test(new RangeInt(-12,   2))); //      +     +
        assertEquals(false, new x().test(new RangeInt(-11,   2))); //      |-    +
        assertEquals(false, new x().test(new RangeInt(-10,   2))); //      | -   +
        assertEquals(false, new x().test(new RangeInt(-12,   0))); //      +   - |
        assertEquals(false, new x().test(new RangeInt(-12,   1))); //      +    -|
        assertEquals(false, new x().test(new RangeInt(-12,   2))); //      +     +
        assertEquals(false, new x().test(new RangeInt(-12,   3))); //      +     |-
        assertEquals(false, new x().test(new RangeInt(-12,   4))); //      +     | -
    }

    /**
     * Test of adjoinsAtEndWith method, of class Fragment.
     */
    @Test
    public void testDoesAdjoinAtEnd() {
        class x {
            RangeInt tested = new RangeInt(-12, 2);
            boolean test(RangeInt f) {
                return tested.adjoinsAtEndWith(f);
            }
        }
        assertEquals(false, new x().test(new RangeInt(-36, -14))); //-   - |     |
        assertEquals(false, new x().test(new RangeInt(-36, -13))); //-    -|     |
        assertEquals(false, new x().test(new RangeInt(-36, -12))); //-     +     |
        assertEquals(false, new x().test(new RangeInt(-36, -11))); //-     |-    |
        assertEquals(false, new x().test(new RangeInt(-36, -10))); //-     | -   |
        assertEquals(false, new x().test(new RangeInt(-36,   0))); //-     |   - |
        assertEquals(false, new x().test(new RangeInt(-36,   1))); //-     |    -|
        assertEquals(false, new x().test(new RangeInt(-36,   2))); //-     |     +
        assertEquals(false, new x().test(new RangeInt(-36,   3))); //-     |     |-
        assertEquals(false, new x().test(new RangeInt(-36,   4))); //-     |     | -
        assertEquals(false, new x().test(new RangeInt(-14,  24))); //    - |     |    -
        assertEquals(false, new x().test(new RangeInt(-13,  24))); //     -|     |    -
        assertEquals(false, new x().test(new RangeInt(-12,  24))); //      +     |    -
        assertEquals(false, new x().test(new RangeInt(-11,  24))); //      |-    |    -
        assertEquals(false, new x().test(new RangeInt(-10,  24))); //      | -   |    -
        assertEquals(false, new x().test(new RangeInt(  0,  24))); //      |   - |    -
        assertEquals(false, new x().test(new RangeInt(  1,  24))); //      |    -|    -
        assertEquals(true , new x().test(new RangeInt(  2,  24))); //      |     +    -
        assertEquals(false, new x().test(new RangeInt(  3,  24))); //      |     |-   -
        assertEquals(false, new x().test(new RangeInt(  4,  24))); //      |     | -  -
        assertEquals(false, new x().test(new RangeInt(-14,   0))); //    - |   - |
        assertEquals(false, new x().test(new RangeInt(-13,   0))); //     -|   - |
        assertEquals(false, new x().test(new RangeInt(-12,   0))); //      +   - |
        assertEquals(false, new x().test(new RangeInt(-11,   0))); //      |-  - |
        assertEquals(false, new x().test(new RangeInt(-10,   0))); //      | - - |
        assertEquals(false, new x().test(new RangeInt(-10,   1))); //      | -  -|
        assertEquals(false, new x().test(new RangeInt(-10,   2))); //      | -   +
        assertEquals(false, new x().test(new RangeInt(-10,   3))); //      | -   |-
        assertEquals(false, new x().test(new RangeInt(-10,   4))); //      | -   | -
        assertEquals(false, new x().test(new RangeInt(-14,   2))); //    - |     +
        assertEquals(false, new x().test(new RangeInt(-13,   2))); //     -|     +
        assertEquals(false, new x().test(new RangeInt(-12,   2))); //      +     +
        assertEquals(false, new x().test(new RangeInt(-11,   2))); //      |-    +
        assertEquals(false, new x().test(new RangeInt(-10,   2))); //      | -   +
        assertEquals(false, new x().test(new RangeInt(-12,   0))); //      +   - |
        assertEquals(false, new x().test(new RangeInt(-12,   1))); //      +    -|
        assertEquals(false, new x().test(new RangeInt(-12,   2))); //      +     +
        assertEquals(false, new x().test(new RangeInt(-12,   3))); //      +     |-
        assertEquals(false, new x().test(new RangeInt(-12,   4))); //      +     | -
    }

    /**
     * Test of intersect method, of class Fragment.
     */
    @Test
    public void testIntersect() {
        class x {
            RangeInt tested = new RangeInt(-12, 2);
            RangeInt test(RangeInt f) {
                return tested.intersect(f).orElse(null);
            }
        }
        assertEquals(null                        , new x().test(new RangeInt(-36, -14))); //-   - |     |
        assertEquals(null                        , new x().test(new RangeInt(-36, -13))); //-    -|     |
        assertEquals(null                        , new x().test(new RangeInt(-36, -12))); //-     +     |
        assertEquals(new RangeInt(-12, -11), new x().test(new RangeInt(-36, -11))); //-     |-    |
        assertEquals(new RangeInt(-12, -10), new x().test(new RangeInt(-36, -10))); //-     | -   |
        assertEquals(new RangeInt(-12,   0), new x().test(new RangeInt(-36,   0))); //-     |   - |
        assertEquals(new RangeInt(-12,   1), new x().test(new RangeInt(-36,   1))); //-     |    -|
        assertEquals(new RangeInt(-12,   2), new x().test(new RangeInt(-36,   2))); //-     |     +
        assertEquals(new RangeInt(-12,   2), new x().test(new RangeInt(-36,   3))); //-     |     |-
        assertEquals(new RangeInt(-12,   2), new x().test(new RangeInt(-36,   4))); //-     |     | -
        assertEquals(new RangeInt(-12,   2), new x().test(new RangeInt(-14,  24))); //    - |     |    -
        assertEquals(new RangeInt(-12,   2), new x().test(new RangeInt(-13,  24))); //     -|     |    -
        assertEquals(new RangeInt(-12,   2), new x().test(new RangeInt(-12,  24))); //      +     |    -
        assertEquals(new RangeInt(-11,   2), new x().test(new RangeInt(-11,  24))); //      |-    |    -
        assertEquals(new RangeInt(-10,   2), new x().test(new RangeInt(-10,  24))); //      | -   |    -
        assertEquals(new RangeInt(  0,   2), new x().test(new RangeInt(  0,  24))); //      |   - |    -
        assertEquals(new RangeInt(  1,   2), new x().test(new RangeInt(  1,  24))); //      |    -|    -
        assertEquals(null                        , new x().test(new RangeInt(  2,  24))); //      |     +    -
        assertEquals(null                        , new x().test(new RangeInt(  3,  24))); //      |     |-   -
        assertEquals(null                        , new x().test(new RangeInt(  4,  24))); //      |     | -  -
        assertEquals(new RangeInt(-12,   0), new x().test(new RangeInt(-14,   0))); //    - |   - |
        assertEquals(new RangeInt(-12,   0), new x().test(new RangeInt(-13,   0))); //     -|   - |
        assertEquals(new RangeInt(-12,   0), new x().test(new RangeInt(-12,   0))); //      +   - |
        assertEquals(new RangeInt(-11,   0), new x().test(new RangeInt(-11,   0))); //      |-  - |
        assertEquals(new RangeInt(-10,   0), new x().test(new RangeInt(-10,   0))); //      | - - |
        assertEquals(new RangeInt(-10,   1), new x().test(new RangeInt(-10,   1))); //      | -  -|
        assertEquals(new RangeInt(-10,   2), new x().test(new RangeInt(-10,   2))); //      | -   +
        assertEquals(new RangeInt(-10,   2), new x().test(new RangeInt(-10,   3))); //      | -   |-
        assertEquals(new RangeInt(-10,   2), new x().test(new RangeInt(-10,   4))); //      | -   | -
        assertEquals(new RangeInt(-12,   2), new x().test(new RangeInt(-14,   2))); //    - |     +
        assertEquals(new RangeInt(-12,   2), new x().test(new RangeInt(-13,   2))); //     -|     +
        assertEquals(new RangeInt(-12,   2), new x().test(new RangeInt(-12,   2))); //      +     +
        assertEquals(new RangeInt(-11,   2), new x().test(new RangeInt(-11,   2))); //      |-    +
        assertEquals(new RangeInt(-10,   2), new x().test(new RangeInt(-10,   2))); //      | -   +
        assertEquals(new RangeInt(-12,   0), new x().test(new RangeInt(-12,   0))); //      +   - |
        assertEquals(new RangeInt(-12,   1), new x().test(new RangeInt(-12,   1))); //      +    -|
        assertEquals(new RangeInt(-12,   2), new x().test(new RangeInt(-12,   2))); //      +     +
        assertEquals(new RangeInt(-12,   2), new x().test(new RangeInt(-12,   3))); //      +     |-
        assertEquals(new RangeInt(-12,   2), new x().test(new RangeInt(-12,   4))); //      +     | -
    }

    /**
     * Test of cover method, of class Fragment.
     */
    @Test
    public void testCover() {
        class x {
            RangeInt tested = new RangeInt(-12, 2);
            RangeInt test(RangeInt f) {
                return tested.cover(f);
            }
        }
        assertEquals(new RangeInt(-36,   2), new x().test(new RangeInt(-36, -14))); //-   - |     |
        assertEquals(new RangeInt(-36,   2), new x().test(new RangeInt(-36, -13))); //-    -|     |
        assertEquals(new RangeInt(-36,   2), new x().test(new RangeInt(-36, -12))); //-     +     |
        assertEquals(new RangeInt(-36,   2), new x().test(new RangeInt(-36, -11))); //-     |-    |
        assertEquals(new RangeInt(-36,   2), new x().test(new RangeInt(-36, -10))); //-     | -   |
        assertEquals(new RangeInt(-36,   2), new x().test(new RangeInt(-36,   0))); //-     |   - |
        assertEquals(new RangeInt(-36,   2), new x().test(new RangeInt(-36,   1))); //-     |    -|
        assertEquals(new RangeInt(-36,   2), new x().test(new RangeInt(-36,   2))); //-     |     +
        assertEquals(new RangeInt(-36,   3), new x().test(new RangeInt(-36,   3))); //-     |     |-
        assertEquals(new RangeInt(-36,   4), new x().test(new RangeInt(-36,   4))); //-     |     | -
        assertEquals(new RangeInt(-14,  24), new x().test(new RangeInt(-14,  24))); //    - |     |    -
        assertEquals(new RangeInt(-13,  24), new x().test(new RangeInt(-13,  24))); //     -|     |    -
        assertEquals(new RangeInt(-12,  24), new x().test(new RangeInt(-12,  24))); //      +     |    -
        assertEquals(new RangeInt(-12,  24), new x().test(new RangeInt(-11,  24))); //      |-    |    -
        assertEquals(new RangeInt(-12,  24), new x().test(new RangeInt(-10,  24))); //      | -   |    -
        assertEquals(new RangeInt(-12,  24), new x().test(new RangeInt(  0,  24))); //      |   - |    -
        assertEquals(new RangeInt(-12,  24), new x().test(new RangeInt(  1,  24))); //      |    -|    -
        assertEquals(new RangeInt(-12,  24), new x().test(new RangeInt(  2,  24))); //      |     +    -
        assertEquals(new RangeInt(-12,  24), new x().test(new RangeInt(  3,  24))); //      |     |-   -
        assertEquals(new RangeInt(-12,  24), new x().test(new RangeInt(  4,  24))); //      |     | -  -
        assertEquals(new RangeInt(-14,   2), new x().test(new RangeInt(-14,   0))); //    - |   - |
        assertEquals(new RangeInt(-13,   2), new x().test(new RangeInt(-13,   0))); //     -|   - |
        assertEquals(new RangeInt(-12,   2), new x().test(new RangeInt(-12,   0))); //      +   - |
        assertEquals(new RangeInt(-12,   2), new x().test(new RangeInt(-11,   0))); //      |-  - |
        assertEquals(new RangeInt(-12,   2), new x().test(new RangeInt(-10,   0))); //      | - - |
        assertEquals(new RangeInt(-12,   2), new x().test(new RangeInt(-10,   1))); //      | -  -|
        assertEquals(new RangeInt(-12,   2), new x().test(new RangeInt(-10,   2))); //      | -   +
        assertEquals(new RangeInt(-12,   3), new x().test(new RangeInt(-10,   3))); //      | -   |-
        assertEquals(new RangeInt(-12,   4), new x().test(new RangeInt(-10,   4))); //      | -   | -
        assertEquals(new RangeInt(-14,   2), new x().test(new RangeInt(-14,   2))); //    - |     +
        assertEquals(new RangeInt(-13,   2), new x().test(new RangeInt(-13,   2))); //     -|     +
        assertEquals(new RangeInt(-12,   2), new x().test(new RangeInt(-12,   2))); //      +     +
        assertEquals(new RangeInt(-12,   2), new x().test(new RangeInt(-11,   2))); //      |-    +
        assertEquals(new RangeInt(-12,   2), new x().test(new RangeInt(-10,   2))); //      | -   +
        assertEquals(new RangeInt(-12,   2), new x().test(new RangeInt(-12,   0))); //      +   - |
        assertEquals(new RangeInt(-12,   2), new x().test(new RangeInt(-12,   1))); //      +    -|
        assertEquals(new RangeInt(-12,   2), new x().test(new RangeInt(-12,   2))); //      +     +
        assertEquals(new RangeInt(-12,   3), new x().test(new RangeInt(-12,   3))); //      +     |-
        assertEquals(new RangeInt(-12,   4), new x().test(new RangeInt(-12,   4))); //      +     | -
    }

    /**
     * Test of getUpper method, of class Fragment.
     */
    @Test
    public void testGetUpper_GenericType() {
        class y {
            RangeInt tested = new RangeInt(-12, 2);
            RangeInt test(int f) {
                return tested.getUpper(f).orElse(null);
            }
        }
        assertEquals(new RangeInt(-12,   2), new y().test(-14));
        assertEquals(new RangeInt(-12,   2), new y().test(-13));
        assertEquals(new RangeInt(-12,   2), new y().test(-12));
        assertEquals(new RangeInt(-11,   2), new y().test(-11));
        assertEquals(new RangeInt(-10,   2), new y().test(-10));
        assertEquals(new RangeInt(  0,   2), new y().test(0));
        assertEquals(new RangeInt(  1,   2), new y().test(1));
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
            RangeInt tested = new RangeInt(-12, 2);
            RangeInt test(RangeInt f) {
                return f.getUpperRoomIn(tested).orElse(null);
            }
        }
        assertEquals(new RangeInt(-12,   2), new x().test(new RangeInt(-36, -14))); //-   - |     |
        assertEquals(new RangeInt(-12,   2), new x().test(new RangeInt(-36, -13))); //-    -|     |
        assertEquals(new RangeInt(-12,   2), new x().test(new RangeInt(-36, -12))); //-     +     |
        assertEquals(new RangeInt(-11,   2), new x().test(new RangeInt(-36, -11))); //-     |-    |
        assertEquals(new RangeInt(-10,   2), new x().test(new RangeInt(-36, -10))); //-     | -   |
        assertEquals(new RangeInt(  0,   2), new x().test(new RangeInt(-36,   0))); //-     |   - |
        assertEquals(new RangeInt(  1,   2), new x().test(new RangeInt(-36,   1))); //-     |    -|
        assertEquals(null          , new x().test(new RangeInt(-36,   2))); //-     |     +
        assertEquals(null          , new x().test(new RangeInt(-36,   3))); //-     |     |-
        assertEquals(null          , new x().test(new RangeInt(-36,   4))); //-     |     | -
        assertEquals(null          , new x().test(new RangeInt(-14,  24))); //    - |     |    -
        assertEquals(null          , new x().test(new RangeInt(-13,  24))); //     -|     |    -
        assertEquals(null          , new x().test(new RangeInt(-12,  24))); //      +     |    -
        assertEquals(null          , new x().test(new RangeInt(-11,  24))); //      |-    |    -
        assertEquals(null          , new x().test(new RangeInt(-10,  24))); //      | -   |    -
        assertEquals(null          , new x().test(new RangeInt(  0,  24))); //      |   - |    -
        assertEquals(null          , new x().test(new RangeInt(  1,  24))); //      |    -|    -
        assertEquals(null          , new x().test(new RangeInt(  2,  24))); //      |     +    -
        assertEquals(null          , new x().test(new RangeInt(  3,  24))); //      |     |-   -
        assertEquals(null          , new x().test(new RangeInt(  4,  24))); //      |     | -  -
        assertEquals(new RangeInt(  0,   2), new x().test(new RangeInt(-14,   0))); //    - |   - |
        assertEquals(new RangeInt(  0,   2), new x().test(new RangeInt(-13,   0))); //     -|   - |
        assertEquals(new RangeInt(  0,   2), new x().test(new RangeInt(-12,   0))); //      +   - |
        assertEquals(new RangeInt(  0,   2), new x().test(new RangeInt(-11,   0))); //      |-  - |
        assertEquals(new RangeInt(  0,   2), new x().test(new RangeInt(-10,   0))); //      | - - |
        assertEquals(new RangeInt(  1,   2), new x().test(new RangeInt(-10,   1))); //      | -  -|
        assertEquals(null          , new x().test(new RangeInt(-10,   2))); //      | -   +
        assertEquals(null          , new x().test(new RangeInt(-10,   3))); //      | -   |-
        assertEquals(null          , new x().test(new RangeInt(-10,   4))); //      | -   | -
        assertEquals(null          , new x().test(new RangeInt(-14,   2))); //    - |     +
        assertEquals(null          , new x().test(new RangeInt(-13,   2))); //     -|     +
        assertEquals(null          , new x().test(new RangeInt(-12,   2))); //      +     +
        assertEquals(null          , new x().test(new RangeInt(-11,   2))); //      |-    +
        assertEquals(null          , new x().test(new RangeInt(-10,   2))); //      | -   +
        assertEquals(new RangeInt(  0,   2), new x().test(new RangeInt(-12,   0))); //      +   - |
        assertEquals(new RangeInt(  1,   2), new x().test(new RangeInt(-12,   1))); //      +    -|
        assertEquals(null          , new x().test(new RangeInt(-12,   2))); //      +     +
        assertEquals(null          , new x().test(new RangeInt(-12,   3))); //      +     |-
        assertEquals(null          , new x().test(new RangeInt(-12,   4))); //      +     | -
    }

    /**
     * Test of getLower method, of class Fragment.
     */
    @Test
    public void testGetLower_GenericType() {
        class y {
            RangeInt tested = new RangeInt(-12, 2);
            RangeInt test(int f) {
                return tested.getLower(f).orElse(null);
            }
        }
        assertEquals(null          , new y().test(-14));
        assertEquals(null          , new y().test(-13));
        assertEquals(null          , new y().test(-12));
        assertEquals(new RangeInt(-12, -11), new y().test(-11));
        assertEquals(new RangeInt(-12, -10), new y().test(-10));
        assertEquals(new RangeInt(-12,   0), new y().test(0));
        assertEquals(new RangeInt(-12,   1), new y().test(1));
        assertEquals(new RangeInt(-12,   2), new y().test(2));
        assertEquals(new RangeInt(-12,   2), new y().test(3));
        assertEquals(new RangeInt(-12,   2), new y().test(4));
    }

    /**
     * Test of getLower method, of class Fragment.
     */
    @Test
    public void testGetLower_Fragment() {
        class x {
            RangeInt tested = new RangeInt(-12, 2);
            RangeInt test(RangeInt f) {
                return f.getLowerRoomIn(tested).orElse(null);
            }
        }
        assertEquals(null          , new x().test(new RangeInt(-36, -14))); //-   - |     |
        assertEquals(null          , new x().test(new RangeInt(-36, -13))); //-    -|     |
        assertEquals(null          , new x().test(new RangeInt(-36, -12))); //-     +     |
        assertEquals(null          , new x().test(new RangeInt(-36, -11))); //-     |-    |
        assertEquals(null          , new x().test(new RangeInt(-36, -10))); //-     | -   |
        assertEquals(null          , new x().test(new RangeInt(-36,   0))); //-     |   - |
        assertEquals(null          , new x().test(new RangeInt(-36,   1))); //-     |    -|
        assertEquals(null          , new x().test(new RangeInt(-36,   2))); //-     |     +
        assertEquals(null          , new x().test(new RangeInt(-36,   3))); //-     |     |-
        assertEquals(null          , new x().test(new RangeInt(-36,   4))); //-     |     | -
        assertEquals(null          , new x().test(new RangeInt(-14,  24))); //    - |     |    -
        assertEquals(null          , new x().test(new RangeInt(-13,  24))); //     -|     |    -
        assertEquals(null          , new x().test(new RangeInt(-12,  24))); //      +     |    -
        assertEquals(new RangeInt(-12, -11), new x().test(new RangeInt(-11,  24))); //      |-    |    -
        assertEquals(new RangeInt(-12, -10), new x().test(new RangeInt(-10,  24))); //      | -   |    -
        assertEquals(new RangeInt(-12,   0), new x().test(new RangeInt(  0,  24))); //      |   - |    -
        assertEquals(new RangeInt(-12,   1), new x().test(new RangeInt(  1,  24))); //      |    -|    -
        assertEquals(new RangeInt(-12,   2), new x().test(new RangeInt(  2,  24))); //      |     +    -
        assertEquals(new RangeInt(-12,   2), new x().test(new RangeInt(  3,  24))); //      |     |-   -
        assertEquals(new RangeInt(-12,   2), new x().test(new RangeInt(  4,  24))); //      |     | -  -
        assertEquals(null          , new x().test(new RangeInt(-14,   0))); //    - |   - |
        assertEquals(null          , new x().test(new RangeInt(-13,   0))); //     -|   - |
        assertEquals(null          , new x().test(new RangeInt(-12,   0))); //      +   - |
        assertEquals(new RangeInt(-12, -11), new x().test(new RangeInt(-11,   0))); //      |-  - |
        assertEquals(new RangeInt(-12, -10), new x().test(new RangeInt(-10,   0))); //      | - - |
        assertEquals(new RangeInt(-12, -10), new x().test(new RangeInt(-10,   1))); //      | -  -|
        assertEquals(new RangeInt(-12, -10), new x().test(new RangeInt(-10,   2))); //      | -   +
        assertEquals(new RangeInt(-12, -10), new x().test(new RangeInt(-10,   3))); //      | -   |-
        assertEquals(new RangeInt(-12, -10), new x().test(new RangeInt(-10,   4))); //      | -   | -
        assertEquals(null          , new x().test(new RangeInt(-14,   2))); //    - |     +
        assertEquals(null          , new x().test(new RangeInt(-13,   2))); //     -|     +
        assertEquals(null          , new x().test(new RangeInt(-12,   2))); //      +     +
        assertEquals(new RangeInt(-12, -11), new x().test(new RangeInt(-11,   2))); //      |-    +
        assertEquals(new RangeInt(-12, -10), new x().test(new RangeInt(-10,   2))); //      | -   +
        assertEquals(null          , new x().test(new RangeInt(-12,   0))); //      +   - |
        assertEquals(null          , new x().test(new RangeInt(-12,   1))); //      +    -|
        assertEquals(null          , new x().test(new RangeInt(-12,   2))); //      +     +
        assertEquals(null          , new x().test(new RangeInt(-12,   3))); //      +     |-
        assertEquals(null          , new x().test(new RangeInt(-12,   4))); //      +     | -
    }

    @Test
    public void testNegate() {
        System.out.println(test.TestUtils.methodName(0));
        RangeInt tested = new RangeInt(0,500);
        TList<RangeInt> result = tested.negate(TList.sof());
        TList<RangeInt> expected = TList.sof(0,500).fold(2).map(l->new RangeInt(l.get(0),l.get(1)));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testNegate1() {
        System.out.println(test.TestUtils.methodName(0));
        RangeInt tested = new RangeInt(0,500);
        TList<RangeInt> result = tested.negate(TList.sof(5,6).fold(2).map(l->new RangeInt(l.get(0),l.get(1))));
        TList<RangeInt> expected = TList.sof(0,5, 6,500).fold(2).map(l->new RangeInt(l.get(0),l.get(1)));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    @Test
    public void testNegate2() {
        System.out.println(test.TestUtils.methodName(0));
        RangeInt tested = new RangeInt(0,500);
        TList<RangeInt> result = tested.negate(TList.sof(-20,-19).fold(2).map(l->new RangeInt(l.get(0),l.get(1))));
        TList<RangeInt> expected = TList.sof(0,500).fold(2).map(l->new RangeInt(l.get(0),l.get(1)));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    @Test
    public void testNegate3() {
        System.out.println(test.TestUtils.methodName(0));
        RangeInt tested = new RangeInt(0,500);
        TList<RangeInt> result = tested.negate(TList.sof(510,512).fold(2).map(l->new RangeInt(l.get(0),l.get(1))));
        TList<RangeInt> expected = TList.sof(0,500).fold(2).map(l->new RangeInt(l.get(0),l.get(1)));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    @Test
    public void testNegate4() {
        System.out.println(test.TestUtils.methodName(0));
        RangeInt tested = new RangeInt(0,500);
        TList<RangeInt> result = tested.negate(TList.sof(-21,-20, 5,6, 510,512).fold(2).map(l->new RangeInt(l.get(0),l.get(1))));
        TList<RangeInt> expected = TList.sof(0,5, 6,500).fold(2).map(l->new RangeInt(l.get(0),l.get(1)));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    @Test
    public void testNegate5() {
        System.out.println(test.TestUtils.methodName(0));
        RangeInt tested = new RangeInt(0,500);
        TList<RangeInt> result = tested.negate(TList.sof(-21,-20, 5,60, 62,70, 510,512).fold(2).map(l->new RangeInt(l.get(0),l.get(1))));
        TList<RangeInt> expected = TList.sof(0,5, 60,62, 70,500).fold(2).map(l->new RangeInt(l.get(0),l.get(1)));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    @Test
    public void testNegate6() {
        System.out.println(test.TestUtils.methodName(0));
        RangeInt tested = new RangeInt(0,500);
        TList<RangeInt> result = tested.negate(TList.sof(-21,-20, 5,60, 62,70, 65,75, 510,512).fold(2).map(l->new RangeInt(l.get(0),l.get(1))));
        TList<RangeInt> expected = TList.sof(0,5, 60,62, 75,500).fold(2).map(l->new RangeInt(l.get(0),l.get(1)));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    @Test
    public void testNegate7() {
        System.out.println(test.TestUtils.methodName(0));
        RangeInt tested = new RangeInt(0,500);
        TList<RangeInt> result = tested.negate(TList.sof(5,60, 62,70, 65,75).fold(2).map(l->new RangeInt(l.get(0),l.get(1))));
        TList<RangeInt> expected = TList.sof(0,5, 60,62, 75,500).fold(2).map(l->new RangeInt(l.get(0),l.get(1)));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    @Test
    public void testNegateCover() {
        System.out.println(test.TestUtils.methodName(0));
        TList<RangeInt> tested = TList.sof(0,10, 11,18, 12,13, 13,15, 20,23).fold(2).map(l->new RangeInt(l.get(0),l.get(1)));
        TList<RangeInt> result = RangeInt.negateCover(tested);
        TList<RangeInt> expected = TList.sof(10,11, 18,20).fold(2).map(l->new RangeInt(l.get(0),l.get(1)));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    @Test
    public void testNegateCover2() {
        System.out.println(test.TestUtils.methodName(0));
        TList<RangeInt> tested = TList.sof(0,10, 11,18, 12,13, 13,15, 20,28, 21,24, 25,26).fold(2).map(l->new RangeInt(l.get(0),l.get(1)));
        TList<RangeInt> result = RangeInt.negateCover(tested);
        TList<RangeInt> expected = TList.sof(10,11, 18,20).fold(2).map(l->new RangeInt(l.get(0),l.get(1)));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    @Test
    public void testUnion() {
        System.out.println(test.TestUtils.methodName(0));
        TList<RangeInt> tested = TList.sof(0,10, 11,18, 12,13, 13,15, 20,23).fold(2).map(l->new RangeInt(l.get(0),l.get(1)));
        TList<RangeInt> result = RangeInt.union(tested);
        TList<RangeInt> expected = TList.sof(0,10, 11,18, 20,23).fold(2).map(l->new RangeInt(l.get(0),l.get(1)));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    @Test
    public void testUnion2() {
        System.out.println(test.TestUtils.methodName(0));
        TList<RangeInt> tested = TList.sof(0,10, 11,18, 12,13, 13,15, 20,28, 21,24, 25,26).fold(2).map(l->new RangeInt(l.get(0),l.get(1)));
        TList<RangeInt> result = RangeInt.union(tested);
        TList<RangeInt> expected = TList.sof(0,10, 11,18, 20,28).fold(2).map(l->new RangeInt(l.get(0),l.get(1)));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    @Test
    public void testUnaryIntersect0() {
        System.out.println(test.TestUtils.methodName(0));
        TList<RangeInt> tested0 = TList.sof(0,30, 11,45, 13,20, 14,28).fold(2).map(l->new RangeInt(l.get(0),l.get(1)));
        RangeInt result = RangeInt.intersect(tested0).get();
        RangeInt expected = new RangeInt(14,20);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testIntersect2() {
        System.out.println(test.TestUtils.methodName(0));
        TList<RangeInt> tested0 = TList.sof(0,10, 11,12, 13,15, 20,28).fold(2).map(l->new RangeInt(l.get(0),l.get(1)));
        TList<RangeInt> tested1 = TList.sof(0,10,        13,18, 21,24, 25,30).fold(2).map(l->new RangeInt(l.get(0),l.get(1)));
        TList<RangeInt> result = RangeInt.intersect(tested0,tested1);
        TList<RangeInt> expected = TList.sof(0,10, 13,15, 21,24, 25,28).fold(2).map(l->new RangeInt(l.get(0),l.get(1)));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
}
