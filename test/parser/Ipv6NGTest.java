/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

import collection.TList;
import iterator.ReaderLinesIterator;
import java.io.File;
import java.util.HashSet;
import java.util.Set;
import static java.util.stream.Collectors.toSet;
import static org.testng.Assert.*;
import org.testng.annotations.Test;
import static parser.BasicRegex.ipv6d;

/**
 *
 * @author masao
 */
public class Ipv6NGTest {
    
    public Ipv6NGTest() {
    }
    @Test
    public void testIpv6() {
        System.out.println(test.TestUtils.methodName(0));
        Set<String> result = ReaderLinesIterator.s(new File("chrome_debug20210408.log")).flatMap(l->new SimpleMatcher(ipv6d).extract(l)).collect(toSet());
        Set<String> expected = new HashSet<>(TList.sof("::","::0","::1:0","::0:Eb8e","::0:0:0","240d:1a:fd:x:x:x:x:x","2001:4451:45a2:x:x:x:x:x"));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
}
