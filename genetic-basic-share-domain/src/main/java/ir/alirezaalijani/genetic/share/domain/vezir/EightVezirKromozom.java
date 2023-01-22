package ir.alirezaalijani.genetic.share.domain.vezir;

import ir.alirezaalijani.genetic.share.domain.Gen;
import ir.alirezaalijani.genetic.share.domain.Kromozom;
import ir.alirezaalijani.genetic.share.domain.equation.Equation3Kromozom;
import ir.alirezaalijani.genetic.share.domain.equation.EquationGen;

import java.util.Random;

/**
 * @author Alireza Alijani : <a href="https://alirezaalijani.ir">https://alirezaalijani.ir</a>
 * @email alirezaalijani.ir@gmail.com
 * @date 1/8/2023
 */
public class EightVezirKromozom extends Kromozom {
    private Double fitness = null;
    public EightVezirKromozom(int genSize) {
        super(genSize);
    }
    @Override
    public void mutation() {
        Random random=new Random();
        int index = random.nextInt(genSize-1);
        Gen gen=new EightVezirGen(random.nextInt(genSize));
        replaceGen(gen,index);
    }

    @Override
    public Kromozom copy() {
        Kromozom newKromozom = new EightVezirKromozom(this.kromozom().size());
        this.kromozom()
                .forEach(gen -> {
                    newKromozom.addGen(new EightVezirGen(gen.getGen()));
                });
        return newKromozom;
    }
    @Override
    public double fitness() {
        if (this.fitness == null || isTouched) {
            this.fitness =fitness_function();
        }
        return fitness;
    }
    private double fitness_function() {
        int threats = 0;
        // total possible threats = C(2, 8) = 28
        // this value is for 8 queens
        final int total_possible_threats = choose(genSize,2);
        for (int col = 0; col < genSize; col++) {
            int col_val = (int) kromozom().get(col).getGen();
            for (int ptr = col + 1; ptr < genSize; ptr++) {
                int ptr_val = (int) kromozom().get(ptr).getGen();
                if (col_val == ptr_val) {
                    // in same row
                    threats++;
                } else if (ptr - col == ptr_val - col_val) {
                    // diagonal  `/`
                    threats++;
                } else if (ptr - col == col_val - ptr_val) {
                    // diagonal `\`
                    threats++;
                }
            }
        }
        return (threats / (double) total_possible_threats);
    }

    public static int choose(long total, long choose){
        if(total < choose)
            return 0;
        if(choose == 0 || choose == total)
            return 1;
        return choose(total-1,choose-1)+choose(total-1,choose);
    }
}
