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

/**
 * This abstract class provides support for building sentence objects.
 * Every object that derives this class is a sentence object and any combination
 * of derived objects is also a sentence object.
 * 
 * @author Patrick Schultz <schultz.patrick@gmail.com>
 */
public abstract class Sentence {

    /**
     * Returns the value of the sentence object if known. There are three possible
     * return values: true, false, unknown (null).
     *
     * @return True, False or Null (if value isn't known).
     */
    public abstract Boolean getValue();

    /**
     * Returns the number of sentences for which the class applies to.
     *
     * @return The number of sentence objects contained in the class.
     */
    public abstract int getArgCount();

    /**
     * Returns specific sentence argument at the given index. This may return null if
     * the index is out of range or may throw an IndexOutOfBounds exception.
     *
     * @see getArgCount to check that index is in range.
     * @param i The index for the sentence object.
     * @return The sentence object.
     */
    public abstract Sentence getArg(int i);

    /**
     * Performs a deep copy of the sentence object. This method should be recursively
     * used to deep copy all objects except Literals. Literals should never be deep copied
     * by default.
     *
     * @return Deep copy of the sentence.
     */
    public abstract Sentence copy();

    /**
     * Overides the defualt toString method to provide boolean formula output consistant
     * with the nesting of the classes.
     *
     * @return String representation of the boolean formula.
     */
    @Override
    public abstract String toString();

    /**
     * Convienient setter method to set a sentence object at index i. This method
     * only replaces an existing sentence object at index i. Attempting to set
     * an index out of range should throw an exception or may be lost.
     *
     * @param i index of the sentence to replace.
     * @param arg The new sentence to replace the old one.
     */
    public abstract void setArg(int i, Sentence arg);

    /**
     * Check whether a sentence is equal. Does this by recursively checking the
     * operands for equality.
     *
     * //TODO fix for Ex/Un Quantifier and
     * //TODO this method is heavily dependent on order. May need two methods; one that
     * ignores order and one that doesn't.
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Sentence) {
            Sentence s = (Sentence) o;
            if (getClass() == s.getClass() && getArgCount() == s.getArgCount()) {
                for (int i = 0; i < getArgCount(); i++) {

                    if (!getArg(i).equals(s.getArg(i))) {
                        return false;
                    }

                }
                return true;
            }
        }

        return false;
    }
}
