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
 * Class represent negation operation. This is a simple class that maintains a single
 * sentence object that will be negated.
 *
 * @author Patrick Schultz <schultz.patrick@gmail.com>
 */
public class Negation extends Sentence {

    Sentence left;      // There is only one sentence object because negation is a unary operation.

    /**
     * Default constructor.
     * @param s The sentence to be negated.
     */
    public Negation(Sentence s) {
        left = s;
    }


    @Override
    public int getArgCount() {
        return 1;
    }

    @Override
    public Sentence getArg(int i) {
        return left;
    }

    @Override
    public Boolean getValue() {
        Boolean l = left.getValue();
        if (l == null) {
            return null;
        }

        return new Boolean(!l.booleanValue());
    }

    @Override
    public String toString() {
        if (left instanceof Literal) {
            return "~" + left.toString();
        } else {
            return "~(" + left.toString() + ")";
        }
    }

    @Override
    public Sentence copy() {
        Sentence l = left.copy();
        return new Negation(l);
    }

    @Override
    public void setArg(int i, Sentence s) {
        if(i == 0)
            left = s;
    }
}
