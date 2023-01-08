package ir.alirezaalijani.genetic.chart;

import ir.alirezaalijani.genetic.core.SelectionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Alireza Alijani : <a href="https://alirezaalijani.ir">https://alirezaalijani.ir</a>
 * @email alirezaalijani.ir@gmail.com
 * @date 1/6/2023
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GeneticRunConfig {
    private int generationMax;
    private double acceptableFitness;
    private int populationSize;
    private SelectionType selectionType;
    private int crossoverPresent;
    private int mutationPresent;
    private int elitismPresent;
    private boolean order;
    private int tournamentSize;
    private int rouletteing;
    private int problem;
    private int numberOfVazir;
    private int equ3Range;
}
