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
package org.ucla.cs.jdmc.bool.formula;

import org.ucla.cs.jdmc.bool.formula.Sentence;
import org.ucla.cs.jdmc.bool.formula.Conjunction;
import org.ucla.cs.jdmc.bool.formula.ExistentialQuantifier;
import org.ucla.cs.jdmc.bool.formula.Literal;
import java.util.ArrayList;
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
public class ExistentialQuantifierTest {

    public ExistentialQuantifierTest() {
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
     * Test of getQuantifiedLiterals method, of class ExistentialQuantifier.
     */
    @Test
    public void testGetQuantifiedLiterals() {
        System.out.println("getQuantifiedLiterals");

        Literal l = new Literal("a");
        Literal r = new Literal("b");
        Conjunction conj = new Conjunction(l, r);
        ArrayList<Literal> al = new ArrayList<Literal>();
        al.add(l);
        ExistentialQuantifier instance = new ExistentialQuantifier(conj, al);
        ArrayList expResult = al;
        ArrayList result = instance.getQuantifiedLiterals();
        assertEquals(expResult, result);

    }

    /**
     * Test of getArgCount method, of class ExistentialQuantifier.
     */
    @Test
    public void testGetArgCount() {
        System.out.println("getArgCount");
        Literal l = new Literal("a");
        Literal r = new Literal("b");
        Conjunction conj = new Conjunction(l, r);
        ArrayList<Literal> al = new ArrayList<Literal>();
        al.add(l);
        ExistentialQuantifier instance = new ExistentialQuantifier(conj, al);
        int expResult = al.size();
        int result = instance.getArgCount();
        assertEquals(expResult, result);

        al.add(r);
        instance = new ExistentialQuantifier(conj, al);
        expResult = al.size();
        result = instance.getArgCount();
        assertEquals(expResult, result);
    }

    /**
     * Test of getArg method, of class ExistentialQuantifier.
     */
    @Test
    public void testGetArg() {
        System.out.println("getArg");
        Literal l = new Literal("a");
        Literal r = new Literal("b");
        Conjunction conj = new Conjunction(l, r);
        ArrayList<Literal> al = new ArrayList<Literal>();
        al.add(l);
        ExistentialQuantifier instance = new ExistentialQuantifier(conj, al);
        Sentence expResult = l;
        Sentence result = instance.getArg(0);
        assertEquals(expResult, result);

        al.add(r);
        instance = new ExistentialQuantifier(conj, al);
        expResult = r;
        result = instance.getArg(1);
        assertEquals(expResult, result);
    }

    /**
     * Test of getValue method, of class ExistentialQuantifier.
     */
    @Test
    public void testGetValue() {
        System.out.println("getValue");
        ExistentialQuantifier instance = null;
        Boolean expResult = null;
        Boolean result = instance.getValue();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class ExistentialQuantifier.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Literal l = new Literal("a");
        Literal r = new Literal("b");
        Conjunction conj = new Conjunction(l, r);
        ArrayList<Literal> al = new ArrayList<Literal>();
        al.add(l);
        ExistentialQuantifier instance = new ExistentialQuantifier(conj, al);

        String expResult = "(E[a] a v b)";
        String result = instance.toString();
        assertEquals(expResult, result);

        al.add(r);
        instance = new ExistentialQuantifier(conj, al);
        expResult = "(E[a, b] a v b)";
        result = instance.toString();
        assertEquals(expResult, result);

        
    }

    /**
     * Test of copy method, of class ExistentialQuantifier.
     */
    @Test
    public void testCopy() {
        System.out.println("copy");
        ExistentialQuantifier instance = null;
        Sentence expResult = null;
        Sentence result = instance.copy();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
