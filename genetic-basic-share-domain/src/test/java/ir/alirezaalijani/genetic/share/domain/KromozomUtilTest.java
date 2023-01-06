package ir.alirezaalijani.genetic.share.domain;

import ir.alirezaalijani.genetic.share.domain.equation.Equation3RandomPopulationGenerate;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Alireza Alijani : <a href="https://alirezaalijani.ir">https://alirezaalijani.ir</a>
 * @email alirezaalijani.ir@gmail.com
 * @date 1/6/2023
 */
class KromozomUtilTest {

    @Test
    void sort() {
        PopulationGenerate populationGenerate = new Equation3RandomPopulationGenerate();
        var population = populationGenerate.populationBuilder(20);
        assertThat(population).isNotEmpty();
        population =KromozomUtil.sort(population,true);
        assertThat(population).isNotEmpty();
        population.forEach(System.out::println);
    }

    @Test
    void remove(){
        PopulationGenerate populationGenerate = new Equation3RandomPopulationGenerate();
        var population = populationGenerate.populationBuilder(20);
        assertThat(population).isNotEmpty();
        population =KromozomUtil.sort(population,true);
        assertThat(population).isNotEmpty();
        population =KromozomUtil.remove(population,population.subList(0,10));
        assertThat(population).hasSize(10);
        population.forEach(System.out::println);
    }

    @Test
    void crossover(){
        PopulationGenerate populationGenerate = new Equation3RandomPopulationGenerate();
        var population = populationGenerate.populationBuilder(20);
        assertThat(population).isNotEmpty();
        System.out.println("k1:"+population.get(0));
        System.out.println("k2:"+population.get(1));
        var crossoverResult =KromozomUtil.crossover(population.get(0),population.get(1),75);
        assertThat(population).isNotEmpty();
        System.out.println("ck1:"+crossoverResult.get(0));
        System.out.println("ck2:"+crossoverResult.get(1));
    }

    @Test
    void mutation(){
        PopulationGenerate populationGenerate = new Equation3RandomPopulationGenerate();
        var population = populationGenerate.populationBuilder(20);
        assertThat(population).isNotEmpty();
        System.out.println("k1:"+population.get(0));
        System.out.println("k2:"+population.get(1));
        var crossoverResult =KromozomUtil.mutation(population.get(0),population.get(1),5);
        assertThat(population).isNotEmpty();
        System.out.println("mk1:"+crossoverResult.get(0));
        System.out.println("mk2:"+crossoverResult.get(1));
    }

    @Test
    void deepCopy(){
        PopulationGenerate populationGenerate = new Equation3RandomPopulationGenerate();
        var population = populationGenerate.populationBuilder(5);
        population.forEach(kromozom -> {
            System.out.println("id"+kromozom.getId()+ " - "+kromozom);
        });
        System.out.println("new list");
        var copyList = KromozomUtil.deepCopy(population);
        population.clear();
        copyList.forEach(kromozom -> {
            System.out.println("id"+kromozom.getId()+ " - "+kromozom);
        });

    }
}