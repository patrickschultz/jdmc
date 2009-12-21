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
import org.ucla.cs.jdmc.bool.formula.LogicalEquivalence;
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
public class LogicalEquivalenceTest {

    public LogicalEquivalenceTest() {
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
     * Test of getValue method, of class LogicalEquivalence.
     */
    @Test
    public void testGetValue() {
        System.out.println("getValue");
        LogicalEquivalence instance = null;
        Boolean expResult = null;
        Boolean result = instance.getValue();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getArgCount method, of class LogicalEquivalence.
     */
    @Test
    public void testGetArgCount() {
        System.out.println("getArgCount");
        LogicalEquivalence instance = null;
        int expResult = 0;
        int result = instance.getArgCount();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getArg method, of class LogicalEquivalence.
     */
    @Test
    public void testGetArg() {
        System.out.println("getArg");
        int i = 0;
        LogicalEquivalence instance = null;
        Sentence expResult = null;
        Sentence result = instance.getArg(i);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class LogicalEquivalence.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        LogicalEquivalence instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of copy method, of class LogicalEquivalence.
     */
    @Test
    public void testCopy() {
        System.out.println("copy");
        LogicalEquivalence instance = null;
        Sentence expResult = null;
        Sentence result = instance.copy();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}