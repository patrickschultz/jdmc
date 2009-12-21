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

import java.util.ArrayList;

/**
 * Represent universal quantification in boolean formulas. It has two parts: the
 * variables which are being quantifie and the sentance over which they are quantified.
 *
 * @author Patrick Schultz <schultz.patrick@gmail.com>
 */
public class UniversalQuantifier extends Sentence {

    Sentence sentence;              // The sentence to be quantified over.
    ArrayList<Literal> literals;    // The literals to quantify.

    /**
     * Default constructor
     *
     * @param s The sentence to quantify over. Ex. x^yVz
     * @param lits An ArrayList of literals to quantify over. It is not necessary
     * for all the literals to be in the sentence. There isn't any good reason to
     * use literal that are not in the sentence but there is no restriction to not do
     * so.
     */
    public UniversalQuantifier(Sentence s, ArrayList<Literal> lits) {
        sentence = s;
        literals = lits;
    }

    /**
     * Accessor method to the list of literals being quantified.
     * @return The ArrayList of literals.
     */
    public ArrayList<Literal> getQuantifiedLiterals() {
        return literals;
    }

    /**
     * Since Universal Quantification only has 1 argument, this will alway return
     * the value 1.
     *
     * @return 1
     */
    @Override
    public int getArgCount() {
        return 1;
    }

    /**
     * Returns the value at the given postion. This should always be position 0
     * since there is only one arg. Attempts to retrieve args at postions other
     * than 0 returns null.
     *
     * @param i The postion of the arg (this method should always be 0)
     * @return The sentence which is being quantified over. Null if position is not 0.
     */
    @Override
    public Sentence getArg(int i) {
        if (i == 0) {
            return sentence;
        }

        return null;
    }

    /**
     * Returns the value of the sentence if it can be determined. Note that this
     * does not evaluate the expression but will return true if the sentence is
     * true before the quantification.
     *
     * To evaluate the expression some other method must be used.
     *
     * @return The value of the quantified sentance as if there was no quantification.
     */
    @Override
    public Boolean getValue() {
        return sentence.getValue();
    }

    /**
     * Returns a string representation of the quantification.
     * @return String
     */
    @Override
    public String toString() {
        return "(U" + literals.toString() + " " + sentence.toString() + ")";
    }

    /**
     * Returns a deep copy of the object. Note that literal are never cloned. If you
     * wish to clone the literals then write a method to search a sentence and replace the
     * literals with a clone. This is usually not efficient or needed and we do not support it
     * directly.
     *
     * Also, please keep in mind that deepcopy is expensive and large sentences can
     * use a large amount of memory. Please use this method only when necessary.
     *
     * @return Deep copy of sentence.
     */
    @Override
    public Sentence copy() {
        return new UniversalQuantifier(sentence.copy(), (ArrayList<Literal>) literals.clone());
    }

    /**
     * Sets the sentence over which quantification takes place. This is a convienance method.
     * It should be helpful for conversion to canonical forms (CNF, DNF).
     *
     * @param i The index to change. This should always be 0 since there is only one sentence.
     * @param arg The new sentence.
     */
    @Override
    public void setArg(int i, Sentence arg) {
        if (i == 0) {
            sentence = arg;
        }
    }
}
