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

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Patrick Schultz <schultz.patrick@gmail.com>
 */
public abstract class Sentence {

    /**
     *
     * @return
     */
    public abstract Boolean getValue();

    /**
     *
     * @return
     */
    public abstract int getArgCount();

    /**
     *
     * @param i
     * @return
     */
    public abstract Sentence getArg(int i);

    /**
     *
     * @return
     */
    @Override
    public abstract String toString();

    /**
     * Check whether a sentence is equal. Does this by recursively checking the
     * operands for equality.
     *
     * //TODO fix for Ex/Un Quantifier and
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
