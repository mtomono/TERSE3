/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package iterator;

import java.util.Iterator;
import java.util.ListIterator;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeClass;

/**
 *
 * @author mtomono
 */
public class ListIteratorWorkbenchTest extends ListIteratorWorkbench{
    
    public ListIteratorWorkbenchTest() {
    }

    @Override
    public ListIterator<Integer> listIterator1() {
        return subject1.listIterator();
    }

    @Override
    public ListIterator<Integer> listIterator2() {
        return subject2.listIterator();
    }
}
