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

import java.util.ArrayList;

/**
 *
 * @author Patrick Schultz <schultz.patrick@gmail.com>
 */
public class ExistentialQuantifier extends Sentence {

    ArrayList<Literal> literals = new ArrayList<Literal>();

    public ExistentialQuantifier(Sentence s, ArrayList<Literal> lits)
    {

    }

    public ArrayList<Literal> getQuantfiedLiterals()
    {
        //TODO
        return null;
    }

    public int getNumLits()
    {
        //TODO
        return 0;
    }

    @Override
    public int getArgCount() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Sentence getArg(int i) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Boolean getValue() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
