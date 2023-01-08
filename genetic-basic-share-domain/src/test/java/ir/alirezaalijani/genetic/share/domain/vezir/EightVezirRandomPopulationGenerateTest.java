package ir.alirezaalijani.genetic.share.domain.vezir;

import ir.alirezaalijani.genetic.share.domain.PopulationGenerate;
import ir.alirezaalijani.genetic.share.domain.equation.Equation3RandomPopulationGenerate;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Alireza Alijani : <a href="https://alirezaalijani.ir">https://alirezaalijani.ir</a>
 * @email alirezaalijani.ir@gmail.com
 * @date 1/8/2023
 */
class EightVezirRandomPopulationGenerateTest {

    @Test
    void populationBuilder() {
        PopulationGenerate populationGenerate = new EightVezirRandomPopulationGenerate(8);
        var population = populationGenerate.populationBuilder(20);
        assertThat(population).isNotEmpty();
        population.forEach(System.out::println);
    }
}