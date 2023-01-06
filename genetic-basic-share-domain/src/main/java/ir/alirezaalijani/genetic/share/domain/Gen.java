package ir.alirezaalijani.genetic.share.domain;

import lombok.ToString;

/**
 * @author Alireza Alijani : <a href="https://alirezaalijani.ir">https://alirezaalijani.ir</a>
 * @email alirezaalijani.ir@gmail.com
 * @date 1/5/2023
 */
public abstract class Gen {
    private final double gen;

    public Gen(double gen) {
        this.gen = gen;
    }

    public double getGen(){
        return this.gen;
    }

    @Override
    public String toString() {
        return "Gen{" +
                "gen=" + gen +
                '}';
    }
}
