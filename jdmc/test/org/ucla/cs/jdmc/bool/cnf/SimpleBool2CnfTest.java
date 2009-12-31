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
package org.ucla.cs.jdmc.bool.cnf;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.ucla.cs.jdmc.bool.formula.Conjunction;
import org.ucla.cs.jdmc.bool.formula.Disjunction;
import org.ucla.cs.jdmc.bool.formula.Implication;
import org.ucla.cs.jdmc.bool.formula.Literal;
import org.ucla.cs.jdmc.bool.formula.Negation;
import static org.junit.Assert.*;
import org.ucla.cs.jdmc.bool.formula.Sentence;

/**
 *
 * @author Patrick Schultz <schultz.patrick@gmail.com>
 */
public class SimpleBool2CnfTest {

    Literal a;
    Literal b;
    Literal c;
    Literal d;
    Literal e;
    Literal f;
    Literal g;
    Literal h;
    Negation aNot;
    Negation bNot;
    Negation cNot;
    Negation dNot;
    Negation eNot;
    Negation fNot;
    Negation gNot;
    Negation hNot;
    Conjunction aOrb;
    Conjunction aOrc;
    Conjunction aOrd;
    Conjunction aOre;
    Conjunction aOrf;
    Conjunction aOrg;
    Conjunction aOrh;
    Conjunction bOrc;
    Conjunction bOrd;
    Conjunction bOre;
    Conjunction bOrf;
    Conjunction bOrg;
    Conjunction bOrh;
    Conjunction cOrd;
    Conjunction cOre;
    Conjunction cOrf;
    Conjunction cOrg;
    Conjunction cOrh;
    Disjunction aAndb;
    Disjunction aAndc;
    Disjunction aAndd;
    Disjunction aAnde;
    Disjunction aAndf;
    Disjunction aAndg;
    Disjunction aAndh;
    Disjunction bAndc;
    Disjunction bAndd;
    Disjunction bAnde;
    Disjunction bAndf;
    Disjunction bAndg;
    Disjunction bAndh;
    Disjunction cAndd;
    Disjunction cAnde;
    Disjunction cAndf;
    Disjunction cAndg;
    Disjunction cAndh;

    public SimpleBool2CnfTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
         a = new Literal("a");
         b = new Literal("b");
         c = new Literal("c");
         d = new Literal("d");
         e = new Literal("e");
         f = new Literal("f");
         g = new Literal("g");
         h = new Literal("h");

         aNot = new Negation(a);
         bNot = new Negation(b);
         cNot = new Negation(c);
         dNot = new Negation(d);
         eNot = new Negation(e);
         fNot = new Negation(f);
         gNot = new Negation(g);
         hNot = new Negation(h);

         aOrb = new Conjunction(a, b);
         aOrc = new Conjunction(a, c);
         aOrd = new Conjunction(a, d);
         aOre = new Conjunction(a, e);
         aOrf = new Conjunction(a, f);
         aOrg = new Conjunction(a, g);
         aOrh = new Conjunction(a, h);


         bOrc = new Conjunction(b, c);
         bOrd = new Conjunction(b, d);
         bOre = new Conjunction(b, e);
         bOrf = new Conjunction(b, f);
         bOrg = new Conjunction(b, g);
         bOrh = new Conjunction(b, h);

         cOrd = new Conjunction(c, d);
         cOre = new Conjunction(c, e);
         cOrf = new Conjunction(c, f);
         cOrg = new Conjunction(c, g);
         cOrh = new Conjunction(c, h);

         aAndb = new Disjunction(a, b);
         aAndc = new Disjunction(a, c);
         aAndd = new Disjunction(a, d);
         aAnde = new Disjunction(a, e);
         aAndf = new Disjunction(a, f);
         aAndg = new Disjunction(a, g);
         aAndh = new Disjunction(a, h);


         bAndc = new Disjunction(b, c);
         bAndd = new Disjunction(b, d);
         bAnde = new Disjunction(b, e);
         bAndf = new Disjunction(b, f);
         bAndg = new Disjunction(b, g);
         bAndh = new Disjunction(b, h);

         cAndd = new Disjunction(c, d);
         cAnde = new Disjunction(c, e);
         cAndf = new Disjunction(c, f);
         cAndg = new Disjunction(c, g);
         cAndh = new Disjunction(c, h);

    }

    @After
    public void tearDown() {
        a = null;
        b = null;
        c = null;
        d = null;
        e = null;
        f = null;
        g = null;
        h = null;
        aNot = null;
        bNot = null;
        cNot = null;
        dNot = null;
        eNot = null;
        fNot = null;
        gNot = null;
        hNot = null;
        aOrb = null;
        aOrc = null;
        aOrd = null;
        aOre = null;
        aOrf = null;
        aOrg = null;
        aOrh = null;
        bOrc = null;
        bOrd = null;
        bOre = null;
        bOrf = null;
        bOrg = null;
        bOrh = null;
        cOrd = null;
        cOre = null;
        cOrf = null;
        cOrg = null;
        cOrh = null;
        aAndb = null;
        aAndc = null;
        aAndd = null;
        aAnde = null;
        aAndf = null;
        aAndg = null;
        aAndh = null;
        bAndc = null;
        bAndd = null;
        bAnde = null;
        bAndf = null;
        bAndg = null;
        bAndh = null;
        cAndd = null;
        cAnde = null;
        cAndf = null;
        cAndg = null;
        cAndh = null;
    }

    /**
     * Test of getCNF method, of class SimpleBool2Cnf.
     */
    @Test
    public void testGetCNF_Sentence_boolean() {
        System.out.println("getCNF");
        Sentence s = new Implication(aAndc, cAnde);

        Sentence result = SimpleBool2Cnf.getCNF(s, true);
        // Check that we have disjunction
        assertTrue(result instanceof Disjunction);

        //Check that it is disjunction of conjunction.
        for (Sentence innerSentences : ((Disjunction) result)) {
            assertTrue(innerSentences instanceof Conjunction);
            //Check that everything else is literals or negation of literals
            for (Sentence litsAndNegations : ((Conjunction) innerSentences)) {
                if(litsAndNegations instanceof Negation)
                {
                    assertTrue(((Negation)litsAndNegations).getArg(0) instanceof Literal);
                }
                else
                    assertTrue(litsAndNegations instanceof Literal);
            }
        }
    }

    /**
     * Test of getCNF method, of class SimpleBool2Cnf.
     */
    @Test
    public void testGetCNF_Sentence() {
        System.out.println("getCNF");
        Sentence s = null;
        Sentence expResult = null;
        Sentence result = SimpleBool2Cnf.getCNF(s);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of replaceLogicalEquivalence method, of class SimpleBool2Cnf.
     */
    @Test
    public void testReplaceLogicalEquivalence() {
        System.out.println("replaceLogicalEquivalence");
        Sentence s = null;
        Sentence expResult = null;
        Sentence result = SimpleBool2Cnf.replaceLogicalEquivalenceRecursive(s);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of replaceImplication method, of class SimpleBool2Cnf.
     */
    @Test
    public void testReplaceImplication() {
        System.out.println("replaceImplication");

        Sentence s = new Implication(a, b);
        Sentence expResult = new Conjunction(aNot, b);
        Sentence result = SimpleBool2Cnf.replaceImplicationRecursive(s);
        assertEquals(expResult, result);


        s = new Implication(aAndc, cOre);
        expResult = new Conjunction(new Negation(aAndc), cOre);
        result = SimpleBool2Cnf.replaceImplicationRecursive(s);
        assertEquals(expResult, result);

        s = new Implication(a, new Implication(b,c));
        expResult = new Conjunction(aNot, bNot, c);
        result = SimpleBool2Cnf.replaceImplicationRecursive(s);
        assertEquals(expResult, result);
    }

    /**
     * Test of applyDistributivity method, of class SimpleBool2Cnf.
     */
    @Test
    public void testApplyDistributivity() {
        System.out.println("applyDistributivity");
        Sentence s = null;
        Sentence expResult = null;
        Sentence result = SimpleBool2Cnf.applyDistributivity(s);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of replaceLogicalEquivalenceRecursive method, of class SimpleBool2Cnf.
     */
    @Test
    public void testReplaceLogicalEquivalenceRecursive() {
        System.out.println("replaceLogicalEquivalenceRecursive");
        Sentence s = null;
        Sentence expResult = null;
        Sentence result = SimpleBool2Cnf.replaceLogicalEquivalenceRecursive(s);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of replaceImplicationRecursive method, of class SimpleBool2Cnf.
     */
    @Test
    public void testReplaceImplicationRecursive() {
        System.out.println("replaceImplicationRecursive");
        Sentence s = null;
        Sentence expResult = null;
        Sentence result = SimpleBool2Cnf.replaceImplicationRecursive(s);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of applyDistributivityRecursive method, of class SimpleBool2Cnf.
     */
    @Test
    public void testApplyDistributivityRecursive() {
        System.out.println("applyDistributivityRecursive");
        Sentence sentence = null;
        Sentence expResult = null;
        Sentence result = SimpleBool2Cnf.applyDistributivityRecursive(sentence);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of applyDeMorgans method, of class SimpleBool2Cnf.
     */
    @Test
    public void testApplyDeMorgans() {
        System.out.println("applyDeMorgans");
        Sentence s = null;
        Sentence expResult = null;
        Sentence result = SimpleBool2Cnf.applyDeMorgans(s);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }


}
