package ir.alirezaalijani.genetic.core;

import ir.alirezaalijani.genetic.share.domain.PopulationGenerate;
import ir.alirezaalijani.genetic.share.domain.equation.Equation3RandomPopulationGenerate;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Alireza Alijani : <a href="https://alirezaalijani.ir">https://alirezaalijani.ir</a>
 * @email alirezaalijani.ir@gmail.com
 * @date 1/6/2023
 */
class GeneticNewPopulationGenerateTest {

    @Test
    void start() {
        PopulationGenerate populationGenerate = new Equation3RandomPopulationGenerate();
        var population = populationGenerate.populationBuilder(20);
        assertThat(population).isNotEmpty();
        PopulationSelection populationSelection = new PopulationSelection(SelectionType.Tournament,20,true,10,0);
        GeneticNewPopulationGenerate newPopulationGenerate=new GeneticNewPopulationGenerate(75,5,populationSelection);
        var newPopulation= newPopulationGenerate.start(population);
        System.out.println("first Population");
        population.forEach(System.out::println);
        System.out.println("new Population");
        newPopulation.forEach(System.out::println);

        assertThat(population.size()).isEqualTo(newPopulation.size());
    }
}