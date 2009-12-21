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

/**
 * Class provides support for conjunction of any size length or arguments.
 *
 * @author Patrick Schultz <schultz.patrick@gmail.com>
 */
public class Conjunction extends Sentence implements Iterable<Sentence> {

    protected ArrayList<Sentence> sentences;

    /**
     * Default constructor. Accepts a variable list of sentence objects but requires a minimum of two.
     * Constructor will check that the arguments given are not a conjunction themselves but if so, will combine
     * them into this conjunction.
     *
     * @param a The first sentence to add to the conjunction.
     * @param otherSentences A variable length argument to add to the conjunction.
     */
    public Conjunction(Sentence a, Sentence... otherSentences) {
        sentences = new ArrayList<Sentence>();

        /**
         * Check for nested conjunction and pull into one.
         */
        if (a instanceof Conjunction) {
            for (int i = 0; i < a.getArgCount(); i++) {
                sentences.add(a.getArg(i));
            }
        } // Add any other elements that are not conjunctions.
        else {
            sentences.add(a);
        }

        /**
         * We repeat the same process above but for the variable list arguments.
         */
        for (Sentence s : otherSentences) {
            /**
             * Check for nested conjunction and pull into one.
             */
            if (s instanceof Conjunction) {
                for (int i = 0; i < s.getArgCount(); i++) {
                    sentences.add(s.getArg(i));
                }
            } // Add any other elements that are not conjunctions.
            else {
                sentences.add(s);
            }
        }
    }

    @Override
    public int getArgCount() {
        return sentences.size();
    }

    @Override
    public Sentence getArg(int i) {
        return sentences.get(i);
    }

    @Override
    public Boolean getValue() {
        boolean value = false;

        for (Sentence s : sentences) {
            if (s.getValue() == null) {
                return null;
            } else {
                value = value || s.getValue().booleanValue();
            }
        }

        return new Boolean(value);
    }

    @Override
    public String toString() {
        StringBuffer str = null;

        for (Sentence s : sentences) {
            if (str == null) {
                str = new StringBuffer();
            } else {
                str.append(" v ");
            }

            if (s instanceof Conjunction || s.getArgCount() <= 1) {
                str.append(s.toString());
            } else {
                str.append("(" + s.toString() + ")");
            }
        }

        return str.toString();
    }

    @Override
    public Sentence copy() {

        Sentence copyArray[] = new Sentence[sentences.size() - 1];
        Sentence firstCopySentence = sentences.get(0).copy();
        for (int i = 0; i < copyArray.length; i++) {
            Sentence sentence = sentences.get(i + 1).copy();
            copyArray[i] = sentence;
        }

        Conjunction copy = new Conjunction(firstCopySentence, copyArray);
        return copy;
    }

    @Override
    public void setArg(int i, Sentence s) {
        if (s instanceof Conjunction) {
            sentences.remove(i);
            for (int j = 0; j < s.getArgCount(); j++) {
                sentences.add(i + j, s.getArg(j));
            }
        } else if (s == null) {
            sentences.remove(i);
        } else {
            sentences.set(i, s);
        }
    }

     /**
     * Iterator object to allow support for fancy for loop objects.
     *
     * @return An iterator to the sentences in the conjunction.
     */
    public Iterator<Sentence> iterator() {
        return sentences.iterator();
    }
}


