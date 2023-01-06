package ir.alirezaalijani.genetic.share.domain.equation;

import ir.alirezaalijani.genetic.share.domain.Gen;
import ir.alirezaalijani.genetic.share.domain.Kromozom;

import java.util.Random;

/**
 * @author Alireza Alijani : <a href="https://alirezaalijani.ir">https://alirezaalijani.ir</a>
 * @email alirezaalijani.ir@gmail.com
 * @date 1/5/2023
 */

public class Equation3Kromozom extends Kromozom {

    private Double fitness = null;
    public Equation3Kromozom(int genSize) {
        super(genSize);
    }

    @Override
    public double fitness() {
        if (this.fitness == null || isTouched) {
            Gen[] gens = kromozom().toArray(new Gen[genSize]);
            int x = (int) gens[0].getGen();
            int y = (int) gens[1].getGen();
            int z = (int) gens[2].getGen();
            // 4x + 3 y^2 + z - 14 = 0
            this.fitness = (4 * x) + (3 * (Math.pow(y, 2))) + z - 14;
        }
        if (this.fitness<0){
            this.fitness*=-1;
        }
        return this.fitness;
    }

    @Override
    public Kromozom copy(){
        Kromozom newKromozom = new Equation3Kromozom(this.kromozom().size());
        this.kromozom()
                .forEach(gen -> {
                    newKromozom.addGen(new EquationGen(gen.getGen()));
                });
        return newKromozom;
    }
    @Override
    public void mutation() {
        Random random=new Random();
        int index = random.nextInt(2);
        Gen gen=new EquationGen(random.nextInt(10 - -10) + -10);
        replaceGen(gen,index);
    }

}
