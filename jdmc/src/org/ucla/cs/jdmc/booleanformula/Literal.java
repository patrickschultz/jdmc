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
 * Represents a simple literal object in a boolean formula. This object can
 * have three values: true, false, unknown (null).
 * 
 * @author Patrick Schultz <schultz.patrick@gmail.com>
 */
public class Literal extends Sentence implements Cloneable {

    String id;
    Boolean value;

    /**
     * Creates a new literal object.
     * Takes a string id and sets the value to unkown (null).
     *
     * @param id unique id of the literal.
     */
    public Literal(String id) {
        this.id = id;
        this.value = null;
    }

    /**
     * Creates a literal and sets the value to true/false.
     * @param id Unique id of the literal.
     * @param v True or False Boolean object.
     */
    public Literal(String id, Boolean v) {
        this.id = id;
        this.value = v;
    }

    /**
     * Sets the value for the literal. This can be either true or false.
     * If you wish to set it from true/false to null, then create a new literal
     * with the same id and overwrite this one.
     * @param v True/False value.
     */
    public void setValue(boolean v) {
        value = new Boolean(v);
    }

    public Boolean getValue() {
        return value;
    }

    
    /**
     * A literal has no arg counts. We always return 0.
     */
    @Override
    public int getArgCount() {
        return 0;
    }

    /**
     * Since a literal is not an operator, we return null.
     */
    @Override
    public Sentence getArg(int i) {
        return null;
    }

    @Override
    public String toString() {
        return id;
    }
}
