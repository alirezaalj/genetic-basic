package ir.alirezaalijani.genetic.share.domain.equation;

import ir.alirezaalijani.genetic.share.domain.PopulationGenerate;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Alireza Alijani : <a href="https://alirezaalijani.ir">https://alirezaalijani.ir</a>
 * @email alirezaalijani.ir@gmail.com
 * @date 1/6/2023
 */
class Equation3RandomPopulationGenerateTest {

    @Test
    void populationBuilder() {
        PopulationGenerate populationGenerate = new Equation3RandomPopulationGenerate();
        var population = populationGenerate.populationBuilder(20);
        assertThat(population).isNotEmpty();
        population.forEach(System.out::println);
    }
}