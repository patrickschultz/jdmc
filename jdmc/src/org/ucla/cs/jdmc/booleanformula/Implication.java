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

/**
 *
 * @author Patrick Schultz <schultz.patrick@gmail.com>
 */
public class Implication extends Sentence {

    // the left and right operands (left => right)
    protected Sentence left;
    protected Sentence right;


    // simple constructor to initialize the operands
    public Implication(Sentence l, Sentence r)
    {
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
     * Evalutes this operator and returns its value
     * @return
     */
    @Override
    public Boolean getValue()
    {
        Boolean l = left.getValue();
        Boolean r = right.getValue();
        if(l == null || r == null)
            return null;

        return new Boolean(!l.booleanValue() || r.booleanValue());
    }

    @Override
    public String toString() {
        return "(" + left.toString() + " <=> " + right.toString() + ")";
    }

}
