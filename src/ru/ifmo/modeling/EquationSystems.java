package ru.ifmo.modeling;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

public class EquationSystems {


    public static Function<Double, List<Double>> createEquationSystem1(List<Double> initial, double epsilon, int maxIteration) {
        return t -> {
            double K1 = Coefficients.getKCounter(1).apply(t);
            double K2 = Coefficients.getKCounter(2).apply(t);
            double K3 = Coefficients.getKCounter(3).apply(t);
            ArrayList<String> elementNames = new ArrayList<>(Arrays.asList("AlCl", "AlCl2", "AlCl3", "H2", "HCl"));
            ArrayList<Double> pg = new ArrayList<>(Arrays.asList(0., 0., 0., 0., 10000.));
            BiFunction<List<Double>, Integer, Double> mf = (vars, k) -> Coefficients.getDCounter(elementNames.get(k)).apply(t) * (pg.get(k) - vars.get(k));
            List<Function<List<Double>, Double>> functions = Arrays.asList(
                    // 1
                    vars -> Math.pow(vars.get(4), 2) - K1 * Math.pow(vars.get(0), 2) * vars.get(3),
                    vars -> Math.pow(vars.get(4), 2) - K2 * vars.get(1) * vars.get(3),
                    vars -> Math.pow(vars.get(4), 6) - K3 * Math.pow(vars.get(2), 2) * Math.pow(vars.get(3), 3),
                    vars -> mf.apply(vars, 4) + 2 * mf.apply(vars, 3),
                    vars -> mf.apply(vars, 0) + 2 * mf.apply(vars, 1) + 3 * mf.apply(vars, 2) + mf.apply(vars, 4)
            );
            List<List<Function<List<Double>, Double>>> derivatives = Arrays.asList(
                    Arrays.asList(  // derivatives of function(0)
                            vars -> -2 * K1 * vars.get(0) * vars.get(3),
                            vars -> 0.,
                            vars -> 0.,
                            vars -> -K1 * Math.pow(vars.get(0), 2),
                            vars -> 2 * vars.get(4)
                    ),
                    Arrays.asList(  // derivatives of function(1)
                            vars -> 0.,
                            vars -> -K2 * vars.get(3),
                            vars -> 0.,
                            vars -> -K2 * vars.get(1),
                            vars -> 2 * vars.get(4)
                    ),
                    Arrays.asList(  // derivatives of function(2)
                            vars -> 0.,
                            vars -> 0.,
                            vars -> -2 * K3 * vars.get(2) * Math.pow(vars.get(3), 3),
                            vars -> -3 * K3 * Math.pow(vars.get(2), 2) * Math.pow(vars.get(3), 2),
                            vars -> 6 * Math.pow(vars.get(4), 5)
                    ),
                    Arrays.asList( // derivatives of function(3)
                            vars -> 0.,
                            vars -> 0.,
                            vars -> 0.,
                            vars -> -2 * Coefficients.getDCounter("H2").apply(t),
                            vars -> -Coefficients.getDCounter("HCl").apply(t)
                    ),
                    Arrays.asList( // derivatives of function(4)
                            vars -> -Coefficients.getDCounter("AlCl").apply(t),
                            vars -> -2 * Coefficients.getDCounter("AlCl2").apply(t),
                            vars -> -3 * Coefficients.getDCounter("AlCl3").apply(t),
                            vars -> 0.,
                            vars -> -Coefficients.getDCounter("HCl").apply(t)
                    )
            );

            return new SystemOfEquationsSolve(functions, derivatives).getSolution(initial, epsilon, maxIteration);
        };

    }


    public static Function<Double, List<Double>> createEquationSystem2(List<Double> initial, double epsilon, int maxIteration) {
        return t -> {
            double K4 = Coefficients.getKCounter(4).apply(t);
            double K5 = Coefficients.getKCounter(5).apply(t);
            double K6 = Coefficients.getKCounter(6).apply(t);
            ArrayList<String> elementNames = new ArrayList<>(Arrays.asList("GaCl", "GaCl2", "GaCl3", "H2", "HCl"));
            ArrayList<Double> pg = new ArrayList<>(Arrays.asList(0., 0., 0., 0., 10000.));
            BiFunction<List<Double>, Integer, Double> mf = (vars, k) -> Coefficients.getDCounter(elementNames.get(k)).apply(t) * (pg.get(k) - vars.get(k));
            List<Function<List<Double>, Double>> functions = Arrays.asList(
                    vars -> Math.pow(vars.get(4), 2) - K4 * Math.pow(vars.get(0), 2) * vars.get(3),
                    vars -> Math.pow(vars.get(4), 2) - K5 * vars.get(1) * vars.get(3),
                    vars -> Math.pow(vars.get(4), 6) - K6 * Math.pow(vars.get(2), 2) * Math.pow(vars.get(3), 3),
                    vars -> mf.apply(vars, 4) + 2 * mf.apply(vars, 3),
                    vars -> mf.apply(vars, 0) + 2 * mf.apply(vars, 1) + 3 * mf.apply(vars, 2) + mf.apply(vars, 4)
            );
            List<List<Function<List<Double>, Double>>> derivatives = Arrays.asList(
                    Arrays.asList(  // derivatives of function(0)
                            vars -> -2 * K4 * vars.get(0) * vars.get(3),
                            vars -> 0.,
                            vars -> 0.,
                            vars -> -K4 * Math.pow(vars.get(0), 2),
                            vars -> 2 * vars.get(4)
                    ),
                    Arrays.asList(  // derivatives of function(1)
                            vars -> 0.,
                            vars -> -K5 * vars.get(3),
                            vars -> 0.,
                            vars -> -K5 * vars.get(1),
                            vars -> 2 * vars.get(4)
                    ),
                    Arrays.asList(  // derivatives of function(2)
                            vars -> 0.,
                            vars -> 0.,
                            vars -> -2 * K6 * vars.get(2) * Math.pow(vars.get(3), 3),
                            vars -> -3 * K6 * Math.pow(vars.get(2), 2) * Math.pow(vars.get(3), 2),
                            vars -> 6 * Math.pow(vars.get(4), 5)
                    ),
                    Arrays.asList( // derivatives of function(3)
                            vars -> 0.,
                            vars -> 0.,
                            vars -> 0.,
                            vars -> -2 * Coefficients.getDCounter("H2").apply(t),
                            vars -> -Coefficients.getDCounter("HCl").apply(t)
                    ),
                    Arrays.asList( // derivatives of function(4)
                            vars -> -Coefficients.getDCounter("GaCl").apply(t),
                            vars -> -2 * Coefficients.getDCounter("GaCl2").apply(t),
                            vars -> -3 * Coefficients.getDCounter("GaCl3").apply(t),
                            vars -> 0.,
                            vars -> -Coefficients.getDCounter("HCl").apply(t)
                    )
            );

            List<Double> vars = Arrays.asList(143454.41462371068, 150.62237912096737, 0.050557360480266655, 15612.759947482575, 5.945035356649541);
            List<Double> result = functions.stream().map(f -> f.apply(vars)).collect(Collectors.toList());
            return new SystemOfEquationsSolve(functions, derivatives).getSolution(initial, epsilon, maxIteration);
        };

    }


    /**
     * Order of variables: AlCl3(0), GaCl(1), NH3(2), HCl(3), H2(4), x(5)
     * Initial values are p^g.
     *
     * @param H2Portion portion of H2 in H2 + N2.
     *                  2 cases of this value should be considered: 0 and 0.1
     * @return function x^g -> list of P_i^g (5), x, G_i (5)
     */
    public static Function<Double, List<Double>> createEquationSystem3(double H2Portion, List<Double> initial, double epsilon, int maxIterationNumber) {
        double t = 1100 + 273;
        double K9 = Coefficients.getKCounter(9).apply(t);
        double K10 = Coefficients.getKCounter(10).apply(t);
        ArrayList<String> elementNames = new ArrayList<>(Arrays.asList("AlCl3", "GaCl", "NH3", "HCl", "H2"));
        return xg -> {
            ArrayList<Double> pg = new ArrayList<>(Arrays.asList(xg * 30, (1 - xg) * 30, 1500., 0., H2Portion * 98470));
            BiFunction<List<Double>, Integer, Double> mf = (vars, k) -> Coefficients.getDCounter(elementNames.get(k)).apply(t) * (pg.get(k) - vars.get(k));

            List<Function<List<Double>, Double>> functions = Arrays.asList(
                    vars -> vars.get(0) * vars.get(2) - K9 * vars.get(5) * Math.pow(vars.get(3), 3),
                    vars -> vars.get(1) * vars.get(2) - K10 * (1 - vars.get(5)) * vars.get(3) * vars.get(4),
                    vars -> mf.apply(vars, 3) + 2 * mf.apply(vars, 4) + 3 * mf.apply(vars, 2),
                    vars -> 3 * mf.apply(vars, 0) + mf.apply(vars, 1) + mf.apply(vars, 3),
                    vars -> mf.apply(vars, 0) + mf.apply(vars, 1) - mf.apply(vars, 2),
                    vars -> mf.apply(vars, 0) * (1 - vars.get(5)) - mf.apply(vars, 1) * vars.get(5)
            );
            List<List<Function<List<Double>, Double>>> derivatives = Arrays.asList(
                    Arrays.asList(  // derivatives of function(0)
                            vars -> vars.get(2),
                            vars -> 0.,
                            vars -> vars.get(0),
                            vars -> -3 * K9 * vars.get(5) * Math.pow(vars.get(3), 2),
                            vars -> 0.,
                            vars -> -K9 * Math.pow(vars.get(3), 3)
                    ),
                    Arrays.asList(  // derivatives of function(1)
                            vars -> 0.,
                            vars -> vars.get(2),
                            vars -> vars.get(1),
                            vars -> -K10 * (1 - vars.get(5)) * vars.get(4),
                            vars -> -K10 * (1 - vars.get(5)) * vars.get(3),
                            vars -> K10 * vars.get(3) * vars.get(4)
                    ),
                    Arrays.asList(  // derivatives of function(2)
                            vars -> 0.,
                            vars -> 0.,
                            vars -> -3 * Coefficients.getDCounter("NH3").apply(t),
                            vars -> -Coefficients.getDCounter("HCl").apply(t),
                            vars -> -2 * Coefficients.getDCounter("H2").apply(t),
                            vars -> 0.
                    ),
                    Arrays.asList( // derivatives of function(3)
                            vars -> -3 * Coefficients.getDCounter("AlCl3").apply(t),
                            vars -> -Coefficients.getDCounter("GaCl").apply(t),
                            vars -> 0.,
                            vars -> -2 * Coefficients.getDCounter("HCl").apply(t),
                            vars -> 0.,
                            vars -> 0.
                    ),
                    Arrays.asList( // derivatives of function(4)
                            vars -> -Coefficients.getDCounter("AlCl3").apply(t),
                            vars -> -Coefficients.getDCounter("GaCl").apply(t),
                            vars -> Coefficients.getDCounter("NH3").apply(t),
                            vars -> 0.,
                            vars -> 0.,
                            vars -> 0.
                    ),
                    Arrays.asList( // derivatives of function(5)
                            vars -> -Coefficients.getDCounter("AlCl3").apply(t) * (1 - vars.get(5)),
                            vars -> Coefficients.getDCounter("GaCl").apply(t) * vars.get(5),
                            vars -> 0.,
                            vars -> 0.,
                            vars -> 0.,
                            vars -> -mf.apply(vars, 0) - mf.apply(vars, 1)
                    )
            );

            /*
            System.out.println(xg);
            for (List<Function<List<Double>, Double>> derivative : derivatives) {
                for (Function<List<Double>, Double> function : derivative) {
                    System.out.print(String.format("%.7f ", function.apply(initial)));
                }
                System.out.println();
            }
            System.out.println();
             */

            return new SystemOfEquationsSolve(functions, derivatives).getSolution(initial, epsilon, maxIterationNumber);
        };
    }
}
