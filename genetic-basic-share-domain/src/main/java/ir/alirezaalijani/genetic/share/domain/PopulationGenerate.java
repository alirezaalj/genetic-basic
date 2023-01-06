package ir.alirezaalijani.genetic.share.domain;

import java.util.List;

/**
 * @author Alireza Alijani : <a href="https://alirezaalijani.ir">https://alirezaalijani.ir</a>
 * @email alirezaalijani.ir@gmail.com
 * @date 1/5/2023
 */
public interface PopulationGenerate {
    List<Kromozom> populationBuilder(int size);
}
