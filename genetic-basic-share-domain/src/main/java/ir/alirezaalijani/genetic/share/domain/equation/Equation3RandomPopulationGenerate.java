package ir.alirezaalijani.genetic.share.domain.equation;

import ir.alirezaalijani.genetic.share.domain.Gen;
import ir.alirezaalijani.genetic.share.domain.Kromozom;
import ir.alirezaalijani.genetic.share.domain.PopulationGenerate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Alireza Alijani : <a href="https://alirezaalijani.ir">https://alirezaalijani.ir</a>
 * @email alirezaalijani.ir@gmail.com
 * @date 1/5/2023
 */
public class Equation3RandomPopulationGenerate implements PopulationGenerate {
    private final Random random;
    private final int from;
    private final int to;

    public Equation3RandomPopulationGenerate(int from,int to) {
        this.random = new Random();
        this.from=from;
        this.to = to;
    }

    public Equation3RandomPopulationGenerate() {
        this.random = new Random();
        this.from=-100;
        this.to=100;
    }

    @Override
    public List<Kromozom> populationBuilder(int size) {
        List<Kromozom> equation3Kromozoms=new ArrayList<>(size);
        for (int i=0;i<size;i++){
            equation3Kromozoms.add(createKromozom());
        }
        return equation3Kromozoms;
    }

    private Equation3Kromozom createKromozom(){
        Gen genX= createGen();
        Gen genY= createGen();
        Gen genZ= createGen();
        var kromozom =new Equation3Kromozom(3);
        kromozom.addGen(genX);
        kromozom.addGen(genY);
        kromozom.addGen(genZ);
        return kromozom;
    }

    private EquationGen createGen(){
        return new EquationGen(random.nextInt(to - from) + from);
    }
}
