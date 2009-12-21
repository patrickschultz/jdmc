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

import java.util.Iterator;

/**
 *
 * @author Patrick Schultz <schultz.patrick@gmail.com>
 */
public class Implication extends Sentence {

    // the left and right operands (left => right)
    protected Sentence left;
    protected Sentence right;

    /**
     * Default constructor.
     * @param l The left hand side of the implication.
     * @param r The right hand side of the implication.
     */
    public Implication(Sentence l, Sentence r) {
        left = l;
        right = r;
    }

    @Override
    public int getArgCount() {
        return 2;
    }

    @Override
    public Sentence getArg(int i) {
        return (i == 1) ? right : left;
    }

    /**
     * Evalutes the expression and returns the value. If either is null (recursively
     * all values must be determined), then null is returned. Otherwise, the operation
     * returns the value based on the definition of implication.
     *
     * @return True, false, null (uknown).
     */
    @Override
    public Boolean getValue() {
        Boolean l = left.getValue();
        Boolean r = right.getValue();
        if (l == null || r == null) {
            return null;
        }

        return new Boolean(!l.booleanValue() || r.booleanValue());
    }

    @Override
    public String toString() {
        return "(" + left.toString() + " -> " + right.toString() + ")";
    }

    @Override
    public Sentence copy() {
        Sentence l = left.copy();
        Sentence r = right.copy();

        return new Implication(l, r);
    }

    @Override
    public void setArg(int i, Sentence arg) {
        if (i == 0) {
            left = arg;
        } else if (i == 1) {
            right = arg;
        }
    }
}
