package ir.alirezaalijani.genetic.share.domain.vezir;

import ir.alirezaalijani.genetic.share.domain.Gen;
import ir.alirezaalijani.genetic.share.domain.Kromozom;
import ir.alirezaalijani.genetic.share.domain.PopulationGenerate;
import ir.alirezaalijani.genetic.share.domain.equation.Equation3Kromozom;
import ir.alirezaalijani.genetic.share.domain.equation.EquationGen;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Alireza Alijani : <a href="https://alirezaalijani.ir">https://alirezaalijani.ir</a>
 * @email alirezaalijani.ir@gmail.com
 * @date 1/8/2023
 */
public class EightVezirRandomPopulationGenerate implements PopulationGenerate {

    private final Random random;
    private final int size;

    public EightVezirRandomPopulationGenerate(int size) {
        this.random = new Random();
        this.size = size;
    }

    public EightVezirRandomPopulationGenerate() {
        this.random = new Random();
        this.size = 8;
    }
    @Override
    public List<Kromozom> populationBuilder(int size) {
        List<Kromozom> kromozomList=new ArrayList<>(size);
        for (int i=0;i<size;i++){
            kromozomList.add(createKromozom());
        }
        return kromozomList;
    }

    private EightVezirKromozom createKromozom(){
        var kromozom =new EightVezirKromozom(size);
        for (int i=0;i<size;i++){
            kromozom.addGen(createGen());
        }
        return kromozom;
    }

    private EightVezirGen createGen(){
        return new EightVezirGen(random.nextInt(size));
    }
}
