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
package org.ucla.cs.jdmc.bool.sat;

import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.sat4j.core.ASolverFactory;
import org.sat4j.core.VecInt;
import org.sat4j.minisat.SolverFactory;
import org.sat4j.specs.ContradictionException;
import org.sat4j.specs.IProblem;
import org.sat4j.specs.ISolver;
import org.sat4j.specs.IVecInt;
import org.sat4j.specs.TimeoutException;
import org.ucla.cs.jdmc.bool.formula.Conjunction;
import org.ucla.cs.jdmc.bool.formula.Literal;
import org.ucla.cs.jdmc.bool.formula.Negation;
import org.ucla.cs.jdmc.bool.formula.Sentence;

/**
 * CnfSat4jSolver provides an abstraction between a cnf sentence and
 * the SAT solver. The user only needs to concern themselves with the sentence
 * generation and this class will handle the SAT solving.
 * 
 * @author Patrick Schultz <schultz.patrick@gmail.com>
 */
public class CnfSat4jSolver  {

    static final int MAXVAR = 1000000;
    static final int NBCLAUSES = 500000;
    int idCounter;
    Map<String, Integer> idMap;
    protected IVecInt literals = new VecInt();
    private final ASolverFactory factory = SolverFactory.instance();

    public CnfSat4jSolver() {
        idMap = new TreeMap<String, Integer>();
        idCounter = 0;
    }

    public boolean isSatisfiable(Sentence s) throws TimeoutException {

        if (s == null || s.getArgCount() == 0) {
            return false;
        }

        ISolver solver = configureSolver();

        // Feed the solver using Dimacs format
        for (int i = 0; i < s.getArgCount(); i++) {
            processArgs(s.getArg(i));
            if (!literals.isEmpty()) {
                try {
                    solver.addClause(literals);
                } catch (ContradictionException ex) {
                    Logger.getLogger(CnfSat4jSolver.class.getName()).log(Level.FINE, "Empty clause");
                    return false;
                }
            }
            literals.clear();
        }

        // we are done. Working now on the IProblem interface
        IProblem problem = solver;

        if (problem.isSatisfiable()) {
            return true;
        } else {
            return false;
        }

    }

    protected ISolver configureSolver() {
        ISolver asolver = factory.defaultSolver();
        asolver.setTimeout(Integer.MAX_VALUE);
        asolver.setDBSimplificationAllowed(true);
        return asolver;
    }

    private void processArgs(Sentence s) {
        if (s instanceof Conjunction) {
            for (int i = 0; i < s.getArgCount(); i++) {
                Sentence litsAndNegations = ((Conjunction) s).getArg(i);
                literals.push(processLits(litsAndNegations));
            }
        } else {
            literals.push(processLits(s));
        }
    }

    private int processLits(Sentence litsAndNegations) {
        Literal l;
        boolean negated;

        if (litsAndNegations instanceof Negation) {
            assert (((Negation) litsAndNegations).getArg(0) instanceof Literal);
            l = (Literal) ((Negation) litsAndNegations).getArg(0);
            negated = true;
        } else {
            assert (litsAndNegations instanceof Literal);
            l = (Literal) litsAndNegations;
            negated = false;
        }
        Integer existingId = idMap.get(l.getId());
        // If the id is not in the map, we add it.
        if (existingId == null) {
            int tempId = ++idCounter;
            idMap.put(l.getId(), tempId);
            existingId = tempId;
        }
        return negated ? -(existingId.intValue()) : existingId.intValue();
    }
}
