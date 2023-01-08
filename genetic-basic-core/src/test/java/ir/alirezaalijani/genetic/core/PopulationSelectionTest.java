package ir.alirezaalijani.genetic.core;

import ir.alirezaalijani.genetic.share.domain.Kromozom;
import ir.alirezaalijani.genetic.share.domain.PopulationGenerate;
import ir.alirezaalijani.genetic.share.domain.equation.Equation3RandomPopulationGenerate;
import ir.alirezaalijani.genetic.share.domain.vezir.EightVezirRandomPopulationGenerate;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Comparator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Alireza Alijani : <a href="https://alirezaalijani.ir">https://alirezaalijani.ir</a>
 * @email alirezaalijani.ir@gmail.com
 * @date 1/6/2023
 */
class PopulationSelectionTest {

    @Test
    void selectTournament() {
        PopulationGenerate populationGenerate = new Equation3RandomPopulationGenerate();
        var population = populationGenerate.populationBuilder(30);
        population.forEach(System.out::println);

        PopulationSelection populationSelection = new PopulationSelection(SelectionType.Tournament,20,true,10,0);
        var selectPopulation = populationSelection.select(population);
        selectPopulation.forEach(System.out::println);

        population.sort(Comparator.comparingDouble(Kromozom::fitness));
        var kromozomP =population.get(0);
        selectPopulation.sort(Comparator.comparingDouble(Kromozom::fitness));
        var kromozomC =selectPopulation.get(0);

        assertThat(kromozomP).isEqualTo(kromozomC);
    }

    @Test
    void selectRouletteWheel() {
        PopulationGenerate populationGenerate = new Equation3RandomPopulationGenerate();
        var population = populationGenerate.populationBuilder(30);
        population.forEach(System.out::println);

        PopulationSelection populationSelection = new PopulationSelection(SelectionType.RouletteWheel,0,true,10,4);
        var selectPopulation = populationSelection.select(population);
        selectPopulation.forEach(System.out::println);
//
//        population.sort(Comparator.comparingDouble(Kromozom::fitness));
//        var kromozomP =population.get(0);
//        selectPopulation.sort(Comparator.comparingDouble(Kromozom::fitness));
//        var kromozomC =selectPopulation.get(0);
//
//        assertThat(kromozomP).isEqualTo(kromozomC);
    }

    @Test
    void selectRouletteWheelVazir() {
        PopulationGenerate populationGenerate = new EightVezirRandomPopulationGenerate(8);
        var population = populationGenerate.populationBuilder(30);
        population.forEach(System.out::println);

        PopulationSelection populationSelection = new PopulationSelection(SelectionType.RouletteWheel,0,true,10,4);
        var selectPopulation = populationSelection.select(population);
        selectPopulation.forEach(System.out::println);
//
//        population.sort(Comparator.comparingDouble(Kromozom::fitness));
//        var kromozomP =population.get(0);
//        selectPopulation.sort(Comparator.comparingDouble(Kromozom::fitness));
//        var kromozomC =selectPopulation.get(0);
//
//        assertThat(kromozomP).isEqualTo(kromozomC);
    }
}