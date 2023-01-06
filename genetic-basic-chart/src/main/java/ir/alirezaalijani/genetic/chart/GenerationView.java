package ir.alirezaalijani.genetic.chart;

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
public class GenerationView {
    private int generation;
    private double averageOfGeneration;
    private double bestOfGeneration;
}
