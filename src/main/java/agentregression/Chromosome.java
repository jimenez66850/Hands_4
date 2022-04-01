/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agentregression;

import java.util.ArrayList;

/**
 *
 * @author luisjimenezmendoza
 */
public class Chromosome {

    private double doubleContent = 0, mediaY, fitness, interseccion, mediaX, roulettePersentage;
    private int mutationRate;
    private String pendiente;
    private ArrayList<Double> yCalculatedValues;

    public Chromosome(String _pendiente, int _mutationRate, ArrayList<Double> _yValues, ArrayList<Double> _xValues, double _mediaY, double _mediaX) {
        pendiente = _pendiente;
        roulettePersentage = 0;
        mutationRate = _mutationRate;
        mediaY = _mediaY;
        mediaX = _mediaX;
        setDouble();
        interseccion = calcularInterseccion();
        yCalculatedValues = new ArrayList<>();
        calculateYValues(_xValues);
        setFitness(_yValues);

    }

    public String getPendiente() {
        return pendiente;
    }

    private double superiorPart(ArrayList<Double> yValues) {
        double _fitness = 0;
        for (int i = 0; i < yCalculatedValues.size(); i++) {
            _fitness += (double) Math.pow((yValues.get(i) - yCalculatedValues.get(i)), 2);
        }
        return _fitness;
    }

    private double slrEcuation(double _xValue) {
        return interseccion + (doubleContent * _xValue);
    }

    private double inferiorPart(ArrayList<Double> yValues) {
        double _fitness = 0;
        for (int i = 0; i < yValues.size(); i++) {
            _fitness += (double) Math.pow(yValues.get(i) - mediaY, 2);
        }
        fitness = _fitness;
        return _fitness;
    }

    public void calculateYValues(ArrayList<Double> _xValues) {
        yCalculatedValues = new ArrayList<>();

        for (int i = 0; i < _xValues.size(); i++) {
            yCalculatedValues.add(slrEcuation(_xValues.get(i)));
        }
    }

    public void setRoulettePersentage(double roulettePersentage) {
        this.roulettePersentage = roulettePersentage;
    }

    public double getRoulettePersentage() {
        return roulettePersentage;
    }

    public void setFitness(ArrayList<Double> _yValues) {
        double _fitness;
        _fitness = (double) ((superiorPart(_yValues) / inferiorPart(_yValues)));
        fitness = (1 - _fitness) * 100;
    }

    private double getDoublePart(String doublePart) {
        double _doublePart = 0;
        for (double i = 0; i < doublePart.length(); i += 1) {
            if (doublePart.charAt((int) i) == '1') {
                _doublePart += 1 / (Math.pow(2, i + 1));
            }
        }
        return _doublePart;
    }

    public void setDouble() {
        int integerPart = Integer.parseInt(pendiente.substring(1, 11), 2);
        double doublePart = getDoublePart(pendiente.substring(11, 15));
        if (pendiente.charAt(0) == '1') {
            doubleContent = (double) (((double) integerPart + doublePart) * -1);
        } else {
            doubleContent = (double) (((double) integerPart + doublePart));
        }

    }

    public void mutate(ArrayList<Double> _yValues, ArrayList<Double> _xValues) {
        String _pendiente = String.valueOf(pendiente.charAt(1));
        for (int i = 1; i < pendiente.length(); i++) {
            int numero = (int) (Math.random() * 100);
            if (numero < mutationRate) {
                if (pendiente.charAt(i) == '1') {
                    _pendiente += '0';
                } else {
                    _pendiente += '1';
                }
            } else {
                _pendiente += pendiente.charAt(i);
            }
        }
        pendiente = _pendiente;
        setDouble();
        interseccion = calcularInterseccion();
        calculateYValues(_xValues);
        setFitness(_yValues);
    }

    public double getFitness() {
        return fitness;
    }

    public double getInterseccion() {
        return interseccion;
    }

    public boolean isTheAnswer() {
        return fitness < 100 && fitness > 95;
    }

    private double calcularInterseccion() {
        return mediaY - (mediaX * doubleContent);
    }

    @Override
    public String toString() {
        return "[ " + pendiente + " ]-->[ " + String.valueOf(doubleContent) + "]-->[" + String.valueOf(interseccion) + "]--> [" + String.valueOf(fitness) + "]";
    }
}
