package ir.alirezaalijani.genetic.core;

import ir.alirezaalijani.genetic.share.domain.Kromozom;
import ir.alirezaalijani.genetic.share.domain.KromozomUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Alireza Alijani : <a href="https://alirezaalijani.ir">https://alirezaalijani.ir</a>
 * @email alirezaalijani.ir@gmail.com
 * @date 1/6/2023
 */
@Slf4j
public class GeneticNewPopulationGenerate {
    private final int crossoverPresent;
    private final int mutationPresent;
    private final PopulationSelection populationSelection;

    public GeneticNewPopulationGenerate(int crossoverPresent, int mutationPresent,
                                        PopulationSelection populationSelection) {
        this.crossoverPresent = crossoverPresent;
        this.mutationPresent = mutationPresent;
        this.populationSelection = populationSelection;
    }

    public GeneticNewPopulationGenerate(int crossoverPresent, int mutationPresent,
                                        int elitismPresent, SelectionType type,
                                        boolean order, int tournamentSize, int rouletteing) {
        this.crossoverPresent = crossoverPresent;
        this.mutationPresent = mutationPresent;
        this.populationSelection = new PopulationSelection(type, elitismPresent, order, tournamentSize, rouletteing);
    }

    public List<Kromozom> start(List<Kromozom> firstPopulation) {
        var selectedPopulation = populationSelection.select(firstPopulation);
        List<Kromozom> newPopulation = new ArrayList<>();
        while (newPopulation.size() < firstPopulation.size()) {
            var selectCouple = KromozomUtil.randomCouple(selectedPopulation);
            var crossoverCouple = KromozomUtil.crossover(selectCouple.get(0), selectCouple.get(1), crossoverPresent);
            var mutationCouple = KromozomUtil.mutation(crossoverCouple.get(0), crossoverCouple.get(1), mutationPresent);
            if (newPopulation.size() + 2 <= firstPopulation.size()) {
                newPopulation.add(mutationCouple.get(0));
                newPopulation.add(mutationCouple.get(1));
            } else {
                Collections.shuffle(mutationCouple);
                newPopulation.add(mutationCouple.get(0));
            }
        }
        return new ArrayList<>(newPopulation);
    }
}
