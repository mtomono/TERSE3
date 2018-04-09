/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package viewlist;

import viewlist.RepeatView;
import java.util.Arrays;
import java.util.List;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author mtomono
 */
public class RepeatViewNGTest {
    
    public RepeatViewNGTest() {
    }

    @Test
    public void testSomeMethod() {
        List<Integer> tested = Arrays.asList(0, 1, 2);
        List<Integer> expected = Arrays.asList(0, 1, 2, 0, 1, 2, 0);
        assertEquals(new RepeatView<>(tested, 7), expected);
    }
    
}
