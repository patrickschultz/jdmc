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
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Provides class or existential quantification.
 *
 * @author Patrick Schultz <schultz.patrick@gmail.com>
 */
public class ExistentialQuantifier extends Sentence {

    Sentence sentence;              // The sentence to quantify over.
    ArrayList<Literal> literals;    // The literals to quantify.

    /**
     * Default contructor. Takes int the literals to quantify and the sentence
     * to quantify over.
     */
    public ExistentialQuantifier(Sentence s, ArrayList<Literal> lits) {
        sentence = s;
        literals = lits;
    }

    /**
     * Accessor method to retrieve the ArrayList of literals.
     *
     * @return The ArrayList of literals.
     */
    public ArrayList<Literal> getQuantifiedLiterals() {
        return literals;
    }

    @Override
    public int getArgCount() {
        return 1;
    }

    @Override
    public Sentence getArg(int i) {
        if (i == 0) {
            return sentence;
        }

        return null;
    }

    @Override
    public Boolean getValue() {
        return sentence.getValue();
    }

    @Override
    public String toString() {
        return "(E" + literals.toString() + " " + sentence.toString() + ")";
    }

    @Override
    public Sentence copy() {
        return new ExistentialQuantifier(sentence.copy(), (ArrayList<Literal>) literals.clone());
    }

    @Override
    public void setArg(int i, Sentence arg) {
        if (i == 0) {
            sentence = arg;
        }
    }
}
