/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agentregression;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author luis
 */
public class SlrEcuation {

    private ArrayList<Population> populationArray;
    private ArrayList<Double> xValues, yValues;
    private double mediaX, mediaY, pendiente, interseccion;
    private int populationSize = 10, chromosomeSize = 15, mutationRate = 5, elitismRate = 2, generation = 0, crossOverRate = 95, iterations = 1000, _iterations = 0;
    private Population parents, newPopulation;

    public SlrEcuation() {
        xValues = new ArrayList<>();
        yValues = new ArrayList<>();
        addXValues();
        addYValues();
        mediaX = calcularMedia(xValues);
        mediaY = calcularMedia(yValues);
        populationArray = new ArrayList<>();
        populationArray.add(setInitialPopulation());
        populationArray.get(generation).setRoulettePersentageValues();
        System.out.println("Generacion: " + String.valueOf(generation));
        System.out.println("Poblacion: ");
        System.out.println(populationArray.get(generation).toString());

    }

    private void crossOver(Population Parents, Population newPopulation) {
        for (int i = 0; i < Parents.size(); i++) {
            if (crossOverRate > (int) (Math.random() * 100) + 1) {
                newPopulation.pushChromosome(crossOver(Parents.getChromosome(i), selectParent(Parents)));
            } else {
                newPopulation.pushChromosome(Parents.getChromosome(i));
            }
        }

    }

    private Chromosome crossOver(Chromosome parent1, Chromosome parent2) {
        int crossOverCut = (int) (Math.random() * (chromosomeSize - 2) + 2);
        return new Chromosome(parent1.getPendiente().substring(0, crossOverCut) + parent2.getPendiente().substring(crossOverCut, chromosomeSize), mutationRate, yValues, xValues, mediaY, mediaX);

    }

    private Chromosome selectParent(Population parents) {
        double roulettePersentage = (double) (Math.random() * 100);
        for (int i = 0; i < parents.size(); i++) {
            if (roulettePersentage <= parents.getChromosome(i).getRoulettePersentage()) {
                return parents.getChromosome(i);
            }
        }
        return null;
    }

    private void setParents(Population newPopulation) {
        for (int i = elitismRate; i < populationSize; i++) {
            newPopulation.pushChromosome(populationArray.get(generation).getChromosome(populationSize - i - 1));
        }
    }

    public void runSlrEcuation() {
        populationArray.get(generation).sortByFitness();
        newPopulation = new Population();
        parents = new Population();
        for (int i = 0; i < elitismRate; i++) {
            newPopulation.pushChromosome(populationArray.get(generation).getChromosome(populationSize - i - 1));
        }
        setParents(parents);
        parents.setFitness();
        parents.setRoulettePersentageValues();
        crossOver(parents, newPopulation);
        newPopulation.mutatePopulation(yValues, xValues);
        newPopulation.setFitness();
        newPopulation.setRoulettePersentageValues();
        populationArray.add(new Population(newPopulation, generation + 1));
        generation++;
        _iterations++;
        System.out.println("Generacion: " + String.valueOf(generation));
        System.out.println("Poblacion: ");
        System.out.println(populationArray.get(generation).toString());

    }

    public boolean isFinished() {
        return populationArray.get(generation).isTheAnswer() || _iterations > iterations;
    }

    @Override
    public String toString() {
        if (pendiente < 0) {
            return "\nMedia de X: " + String.valueOf(mediaX) + "\nMedia de Y: " + String.valueOf(mediaY) + "\nEcuacion: Y = " + String.valueOf(interseccion) + " " + String.valueOf(pendiente);
        } else {
            return "\nMedia de X: " + String.valueOf(mediaX) + "\nMedia de Y: " + String.valueOf(mediaY) + "\nEcuacion: Y = " + String.valueOf(interseccion) + " + " + String.valueOf(pendiente);
        }
    }

    private void addXValues() {
        xValues.add((double) 23.0);
        xValues.add((double) 26.0);
        xValues.add((double) 30.0);
        xValues.add((double) 34.0);
        xValues.add((double) 43.0);
        xValues.add((double) 48.0);
        xValues.add((double) 52.0);
        xValues.add((double) 57.0);
        xValues.add((double) 58.0);
    }

    private void addYValues() {
        yValues.add((double) 651);
        yValues.add((double) 762);
        yValues.add((double) 856);
        yValues.add((double) 1063);
        yValues.add((double) 1190);
        yValues.add((double) 1298);
        yValues.add((double) 1421);
        yValues.add((double) 1440);
        yValues.add((double) 1518);

    }

    private double calcularMedia(ArrayList<Double> values) {
        double media = 0;
        for (int i = 0; i < values.size(); i++) {
            media += values.get(i);
        }
        return media / 9;
    }

    private Population setInitialPopulation() {
        Population individual = new Population();
        String _chromosome = "";
        for (int i = 0; i < populationSize; i++) {
            for (int j = 0; j < chromosomeSize; j++) {
                _chromosome += String.valueOf((int) ((Math.random() * 2)));
            }
            individual.pushChromosome(new Chromosome(_chromosome, mutationRate, yValues, xValues, mediaY, mediaX));
            _chromosome = "";
        }
        individual.setFitness();
        individual.setGeneration(0);
        return individual;
    }
}
