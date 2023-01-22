package ir.alirezaalijani.genetic.core;

import ir.alirezaalijani.genetic.share.domain.KromozomUtil;
import ir.alirezaalijani.genetic.share.domain.PopulationGenerate;
import ir.alirezaalijani.genetic.share.domain.equation.Equation3RandomPopulationGenerate;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Alireza Alijani : <a href="https://alirezaalijani.ir">https://alirezaalijani.ir</a>
 * @email alirezaalijani.ir@gmail.com
 * @date 1/6/2023
 */
@Slf4j
public class GeneticAlg {


    public static void main(String[] args) {
        int generationMax=10000;
        double acceptableFitness=0.0;

        PopulationGenerate populationGenerate = new Equation3RandomPopulationGenerate(-50,50);
        var population = populationGenerate.populationBuilder(50);

        PopulationSelection populationSelection = new PopulationSelection(SelectionType.RouletteWheel,5,true,20,7);
        GeneticNewPopulationGenerate newPopulationGenerate=new GeneticNewPopulationGenerate(75,10,populationSelection);
        int generation=0;
        while (true){
            double best = KromozomUtil.bestFitness(population,true);
            log.info("generation {}, avg {} , best {}",generation,KromozomUtil.populationAvg(population),best);
            if (best<=acceptableFitness){
                break;
            }
            if (generation==generationMax){
                break;
            }
            population = KromozomUtil.deepCopy(newPopulationGenerate.start(population));

            generation++;
        }
    }


}
