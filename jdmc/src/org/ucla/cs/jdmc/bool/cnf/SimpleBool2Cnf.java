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
package org.ucla.cs.jdmc.bool.cnf;

import org.ucla.cs.jdmc.bool.formula.*;

/**
 * Class provides a simple boolean formula to cnf conversion. It is not intended
 * to be robust but instead a quick tool for testing and development.
 *
 * @author Patrick Schultz <schultz.patrick@gmail.com>
 */
public class SimpleBool2Cnf {

    // converts a sentence to CNF
    public static Sentence getCNF(Sentence sentence, boolean d) {

        //Sentence sentence = s.copy();
        sentence = replaceLogicalEquivalenceRecursive(sentence);
        if (d) {
            System.out.println(sentence + " [biconditionals replaced]");
        }
        sentence = replaceImplicationRecursive(sentence);
        if (d) {
            System.out.println(sentence + " [implications replaced]");
        }

        sentence = applyDeMorgansRecursive(sentence);
        if (d) {
            System.out.println(sentence + " [Applied Demorgans]");
        }

        sentence = applyDistributivityRecursive(sentence);
        if (d) {
            System.out.println(sentence + " [applied distributivity]");
        }

        //sentence = sort(sentence);


        return sentence;
    }

    public static Sentence getCNF(Sentence s) {
        return getCNF(s, false);
    }

    // replaces biconditionals with implications
    protected static Sentence replaceLogicalEquivalenceRecursive(Sentence s) {
        if (s instanceof LogicalEquivalence) {
            Sentence a = s.getArg(0);
            Sentence b = s.getArg(1);

            s = new Disjunction(new Implication(a, b), new Implication(b, a));
        }

        for (int i = 0; i < s.getArgCount(); i++) {
            Sentence arg = s.getArg(i);
            arg = replaceLogicalEquivalenceRecursive(arg);
            s.setArg(i, arg);
        }

        return s;
    }

    // replaces implications with ~A v B
    protected static Sentence replaceImplicationRecursive(Sentence s) {
        if (s instanceof Implication) {
            Sentence a = s.getArg(0);
            Sentence b = s.getArg(1);

            s = new Conjunction(new Negation(a), b);
        }

        for (int i = 0; i < s.getArgCount(); i++) {
            Sentence arg = s.getArg(i);
            arg = replaceImplicationRecursive(arg);
            s.setArg(i, arg);
        }

        return s;
    }

    protected static Sentence applyDistributivityRecursive(Sentence sentence) {
        sentence = applyDistributivity(sentence);

        for (int i = 0; i
                < sentence.getArgCount(); i++) {
            Sentence s = sentence.getArg(i);
            s = applyDistributivityRecursive(s);
            sentence.setArg(i, s);


        }
        return sentence;
    }

// distributes ORs over ANDs (the result is in the form:
// (A v ...) ^ (C v ...) ^ ...))
    protected static Sentence applyDistributivity(Sentence s) {

        if (s instanceof Conjunction) {
            Sentence ors = null;
            Sentence and = null;

            for (int i = 0; i < s.getArgCount(); i++) {
                if (and == null && s.getArg(i) instanceof Disjunction) {
                    and = s.getArg(i);
                } else {
                    if (ors == null) {
                        ors = s.getArg(i);
                    } else {
                        ors = new Conjunction(ors, s.getArg(i));
                    }
                }
            }

            if (and != null) {
                Sentence sNew = null;
                for (int i = 0; i < and.getArgCount(); i++) {
                    if (sNew == null) {
                        sNew = new Conjunction(and.getArg(i), ors);
                    } else {
                        sNew = new Disjunction(sNew, new Conjunction(and.getArg(i), ors));
                    }
                }
                s = sNew;
            }
        }

        return s;
    }

    // sorts all operators according to their literals; literals are sorted
    // alphabetically and then operators are sorted according to their
    // left most literal(s)
    //    protected static Sentence sort(Sentence s) {
    //        if (s instanceof Disjunction) {
    //            for (int i = 0; i < s.getArgCount(); i++) {
    //                s.setArg(i, sort(s.getArg(i)));
    //            }
    //
    //            for (int i = 0; i < s.getArgCount() - 1; i++) {
    //                int min = i;
    //                for (int j = i + 1; j < s.getArgCount(); j++) {
    //                    Sentence a = s.getArg(min);
    //                    Sentence b = s.getArg(j);
    //
    //                    boolean found = false;
    //                    boolean equal = true;
    //                    for (int k = 0; k < Math.min(a.getArgCount(), b.getArgCount()); k++) {
    //                        Sentence aK = a.getArg(k);
    //                        Sentence bK = b.getArg(k);
    //                        Literal symA = aK instanceof Literal ? (Literal) aK : (Literal) aK.getArg(0);
    //                        Literal symB = bK instanceof Literal ? (Literal) bK : (Literal) bK.getArg(0);
    //
    //                        if (symA.toString().compareTo(symB.toString()) > 0) {
    //                            min = j;
    //
    //                            found = true;
    //                            equal = false;
    //                            break;
    //                        } else if (symA.toString().compareTo(symB.toString()) < 0) {
    //                            equal = false;
    //                            break;
    //                        }
    //                    }
    //
    //                    if (!found && a.getArgCount() > b.getArgCount()) {
    //                        min = j;
    //                    } else if (equal && a.getArgCount() == b.getArgCount()) {
    //                        //s.setArg(j, null);
    //                        //j--;
    //                    }
    //                }
    //
    //                if (i != min) {
    //                    Sentence temp = s.getArg(i);
    //                    s.setArg(i, s.getArg(min));
    //                    s.setArg(min, temp);
    //                }
    //            }
    //        } else if (s instanceof Conjunction) {
    //            for (int i = 0; i < s.getArgCount() - 1; i++) {
    //                for (int j = i + 1; j < s.getArgCount(); j++) {
    //                    Sentence a = s.getArg(i);
    //                    Sentence b = s.getArg(j);
    //                    Literal symA = a instanceof Literal ? (Literal) a : (Literal) a.getArg(0);
    //                    Literal symB = b instanceof Literal ? (Literal) b : (Literal) b.getArg(0);
    //
    //                    if (symA.toString().compareTo(symB.toString()) > 0) {
    //                        s.setArg(i, b);
    //                        s.setArg(j, a);
    //                    } else if (symA.toString().compareTo(symB.toString()) == 0) {
    //                        //s.setArg(j, null);
    //                        //j--;
    //                    }
    //                }
    //            }
    //        }
    //
    //        return s;
    //
    private static Sentence applyDeMorgansRecursive(Sentence sentence) {
        sentence = applyDeMorgans(sentence);

        for (int i = 0; i
                < sentence.getArgCount(); i++) {
            Sentence s = sentence.getArg(i);
            s = applyDeMorgansRecursive(s);
            sentence.setArg(i, s);
        }
        return sentence;
    }

    protected static Sentence applyDeMorgans(Sentence s) {
        /*
         * Check first that we are actually dealing with a negation.
         * DeMorgans doesn't apply otherwise and we just return the original string
         */
        if (s instanceof Negation) {
            // Get the sentance that is begin negated
            Sentence arg = ((Negation) s).getArg(0);

            /**
             * Handle each case except literal. For literal we just return the orginal
             * sentance since it is reduced all the way.
             */
            if (arg instanceof Conjunction) {
                // Negate each arg and put them in a disjunction.
                return new Disjunction(new Negation(arg.getArg(0)), new Negation(arg.getArg(1)));
            } else if (arg instanceof Disjunction) {
                // Negate each arg and put them in a conjunction.
                return new Conjunction(new Negation(arg.getArg(0)), new Negation(arg.getArg(1)));
            } else if (arg instanceof ExistentialQuantifier) {
                // Negate sentence and switch to Universal Quantifier
                return new UniversalQuantifier(new Negation(arg.getArg(0)), ((ExistentialQuantifier) arg).getQuantifiedLiterals());
            } else if (arg instanceof UniversalQuantifier) {
                // Negate sentence and switch to Existential Quantifier
                return new ExistentialQuantifier(new Negation(arg.getArg(0)), ((UniversalQuantifier) arg).getQuantifiedLiterals());
            } else if (arg instanceof Negation) {
                return ((Negation) arg).getArg(0);
            }
        }

        // Return the original sentence.
        return s;

    }
}
