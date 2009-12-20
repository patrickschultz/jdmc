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
public class Disjunction extends Sentence {

    // the list of sentences that are ANDed
    protected ArrayList<Sentence> sentences;

    // although an AND operation is traditionally has two operands, it has
    // been extended to allow for two or more operands
    public Disjunction(Sentence a, Sentence... otherSentences) {
        sentences = new ArrayList<Sentence>();

        // prevent nesting of AND operators:
        // if the operand is of type AND, take its operands and add them
        // to this instance of AND
        if (a instanceof Disjunction) {
            for (int i = 0; i < a.getArgCount(); i++) {
                sentences.add(a.getArg(i));
            }
        } // if the operand is of any other type, add it to this AND instance
        else {
            sentences.add(a);
        }

        // loop through operands 2[, [3, [...]]]
        for (Sentence s : otherSentences) {
            // if the operand is an AND, add its operands to this AND instance
            if (s instanceof Disjunction) {
                for (int i = 0; i < s.getArgCount(); i++) {
                    sentences.add(s.getArg(i));
                }
            } // if the operand is of any other type, add it this AND instance
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
        boolean value = true;

        for (Sentence s : sentences) {
            if (s.getValue() == null) {
                return null;
            } else {
                value = value && s.getValue().booleanValue();
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
                str.append(" ^ ");
            }

            if (s instanceof Disjunction || s.getArgCount() <= 1) {
                str.append(s.toString());
            } else {
                str.append("(" + s.toString() + ")");
            }
        }

        return str.toString();
    }
}
