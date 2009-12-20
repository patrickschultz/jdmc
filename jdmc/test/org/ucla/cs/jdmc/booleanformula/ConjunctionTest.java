/*
 *  Copyright (C) 2009 Patrick Schultz <schultz.patrick@gmail.com>
 * 
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 * 
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 * 
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.ucla.cs.jdmc.booleanformula;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Patrick Schultz <schultz.patrick@gmail.com>
 */
public class ConjunctionTest {

    public ConjunctionTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getArgCount method, of class Conjunction.
     */
    @Test
    public void testGetArgCount() {
        System.out.println("getArgCount");
        Literal l = new Literal("a");
        Literal r = new Literal("b");
        Conjunction instance = new Conjunction(l, r);
        int expResult = 2;
        int result = instance.getArgCount();
        assertEquals(expResult, result);

        Literal r2 = new Literal("c");
        instance = new Conjunction(l, r, r2);
        expResult = 3;
        result = instance.getArgCount();
        assertEquals(expResult, result);

    }

    /**
     * Test of getArg method, of class Conjunction.
     */
    @Test
    public void testGetArg() {
        System.out.println("getArg");
        Literal l = new Literal("a");
        Literal r = new Literal("b");
        Conjunction instance = new Conjunction(l, r);
        Sentence expResult = l;
        Sentence result = instance.getArg(0);
        assertEquals(expResult, result);

        expResult = r;
        result = instance.getArg(1);
        assertEquals(expResult, result);

        Literal r2 = new Literal("c");
        instance = new Conjunction(l, r, r2);
        expResult = r2;
        result = instance.getArg(2);
        assertEquals(expResult, result);

    }

    /**
     * Test of getValue method, of class Conjunction.
     */
    @Test
    public void testGetValue() {
        System.out.println("getValue0");
        Literal l = new Literal("a");
        Literal r = new Literal("b");
        Conjunction instance = new Conjunction(l, r);
        Boolean expResult = null;
        Boolean result = instance.getValue();
        assertEquals(expResult, result);

        System.out.println("getValue1");
        l = new Literal("a", true);
        r = new Literal("b", false);
        instance = new Conjunction(l, r);
        expResult = true;
        result = instance.getValue();
        assertEquals(expResult, result);

        System.out.println("getValue2");
        l = new Literal("a", true);
        r = new Literal("b", true);
        Literal r2 = new Literal("c", true);
        instance = new Conjunction(l, r, r2);
        expResult = true;
        result = instance.getValue();
        assertEquals(expResult, result);

        System.out.println("getValue3");
        l = new Literal("a", false);
        r = new Literal("b", false);
        r2 = new Literal("c", false);
        instance = new Conjunction(l, r, r2);
        expResult = false;
        result = instance.getValue();
        assertEquals(expResult, result);

        System.out.println("getValue4");
        l = new Literal("a", true);
        r = new Literal("b", true);
        r2 = new Literal("c");
        instance = new Conjunction(l, r, r2);
        expResult = null;
        result = instance.getValue();
        assertEquals(expResult, result);

        System.out.println("getValue5");
        l = new Literal("a", false);
        r = new Literal("b", true);
        r2 = new Literal("c", false);
        instance = new Conjunction(l, r, r2);
        expResult = true;
        result = instance.getValue();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Conjunction.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Literal l = new Literal("a");
        Literal r = new Literal("b");
        Conjunction instance = new Conjunction(l, r);
        String expResult = "a v b";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
}
