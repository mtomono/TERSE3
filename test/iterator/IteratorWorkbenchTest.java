/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package iterator;

import java.util.Iterator;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeClass;

/**
 *
 * @author mtomono
 */
public class IteratorWorkbenchTest extends IteratorWorkbench{
    
    public IteratorWorkbenchTest() {
    }

    @Override
    public Iterator<Integer> iterator1() {
        return subject1.iterator();
    }

    @Override
    public Iterator<Integer> iterator2() {
        return subject2.iterator();
    }
}
