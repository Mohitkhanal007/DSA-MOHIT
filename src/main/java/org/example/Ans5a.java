package org.example;

import java.util.Arrays;
import java.util.Random;

public class Ans5a {

    private static final int NUM_CITIES = 5;

    private static final int[][] DISTANCE_MATRIX = {
            {0, 2, 9, 10, 7},
            {2, 0, 6, 4, 2},
            {9, 6, 0, 8, 7},
            {10, 4, 8, 0, 6},
            {7, 2, 7, 6, 0}
    };

    public static void main(String[] args) {
        int[] currentSolution = generateInitialSolution();

        System.out.println("Initial Solution: " + Arrays.toString(currentSolution));
        System.out.println("Initial Cost: " + calculateTotalDistance(currentSolution));

        int[] bestSolution = hillClimbing(currentSolution);

        System.out.println("Best Solution Found: " + Arrays.toString(bestSolution));
        System.out.println("Cost of Best Solution: " + calculateTotalDistance(bestSolution));
    }

    private static int[] generateInitialSolution() {
        int[] solution = new int[Ans5a.NUM_CITIES];
        for (int i = 0; i < Ans5a.NUM_CITIES; i++) {
            solution[i] = i;
        }

        Random rand = new Random();
        for (int i = 0; i < Ans5a.NUM_CITIES; i++) {
            int j = rand.nextInt(Ans5a.NUM_CITIES);
            int temp = solution[i];
            solution[i] = solution[j];
            solution[j] = temp;
        }

        return solution;
    }

    private static int calculateTotalDistance(int[] solution) {
        int totalDistance = 0;
        for (int i = 0; i < solution.length - 1; i++) {
            totalDistance += Ans5a.DISTANCE_MATRIX[solution[i]][solution[i + 1]];
        }

        totalDistance += Ans5a.DISTANCE_MATRIX[solution[solution.length - 1]][solution[0]];
        return totalDistance;
    }

    private static int[] hillClimbing(int[] currentSolution) {
        int[] bestSolution = Arrays.copyOf(currentSolution, currentSolution.length);
        int bestCost = calculateTotalDistance(bestSolution);

        boolean improvement = true;

        while (improvement) {
            improvement = false;

            for (int i = 1; i < currentSolution.length - 1; i++) {
                for (int j = i + 1; j < currentSolution.length; j++) {
                    int[] newSolution = swapCities(bestSolution, i, j);
                    int newCost = calculateTotalDistance(newSolution);

                    if (newCost < bestCost) {
                        bestSolution = newSolution;
                        bestCost = newCost;
                        improvement = true;
                    }
                }
            }
        }

        return bestSolution;
    }

    private static int[] swapCities(int[] solution, int i, int j) {
        int[] newSolution = Arrays.copyOf(solution, solution.length);
        int temp = newSolution[i];
        newSolution[i] = newSolution[j];
        newSolution[j] = temp;
        return newSolution;
    }
}