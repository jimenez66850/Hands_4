/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agentregression;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author luisjimenezmendoza
 */
public class Population {

    private ArrayList<Chromosome> dna;
    private int generation;
    private double roulette;
    private double fitness;

    public Population() {
        dna = new ArrayList<>();
        generation = 0;
        fitness = 0;
        roulette = 0;
    }

    public Population(Population _population, int _generation) {
        dna = new ArrayList<>(_population.getDna());
        generation = _generation;
        setFitness();
    }

    public ArrayList<Chromosome> getDna() {
        return dna;
    }

    public void setRoulettePersentageValues() {
        double _roulette = 0;
        double division;
        for (int i = 0; i < dna.size(); i++) {
            division = Math.abs(dna.get(i).getFitness() / fitness);
            _roulette += (division*100); 
            dna.get(dna.size()-1-i).setRoulettePersentage((_roulette));
        }
        roulette = _roulette;

    }

    public double getRoulettePersentage() {
        return roulette;
    }

    public void mutatePopulation(ArrayList<Double> _yValues,ArrayList<Double> _xValues) {
        for (int i = 0; i < dna.size(); i++) {
            dna.get(i).mutate(_yValues,_xValues);
        }
    }

    public int size() {
        return dna.size();
    }

    public void pushChromosome(Chromosome chromosome) {
        dna.add(chromosome);
    }

    public Chromosome getChromosome(int index) {
        return dna.get(index);
    }

    public void setChromosome(int index, Chromosome chromosome) {
        dna.add(index, chromosome);
    }

    public void setGeneration(int _generation) {
        generation = _generation;
    }

    public int getGeneration() {
        return generation;
    }

    public void sortByFitness() {
        Collections.sort(dna, new Comparator<Chromosome>() {
            @Override
            public int compare(Chromosome p1, Chromosome p2) {
                return new Double(p1.getFitness()).compareTo(new Double(p2.getFitness()));
            }
        });
    }

    public boolean isTheAnswer() {
        for (int i = 0; i < dna.size(); i++) {
            if (dna.get(i).isTheAnswer()) {
                return true;
            }
        }
        return false;
    }

    public double getFitness() {
        return fitness;
    }

    public void setFitness() {
        double _fitness = 0;
        for (int i = 0; i < dna.size(); i++) {
            _fitness += dna.get(i).getFitness();
        }
        fitness = _fitness;
    }

    @Override
    public String toString() {
        String _content = "";
        for (int i = 0; i < dna.size(); i++) {
            _content += dna.get(i).toString() + "\n";
        }
        _content += "Population fitness = " + String.valueOf(fitness);
        return _content;
    }

}
