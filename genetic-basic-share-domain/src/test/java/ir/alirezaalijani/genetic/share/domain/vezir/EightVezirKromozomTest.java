package ir.alirezaalijani.genetic.share.domain.vezir;

import ir.alirezaalijani.genetic.share.domain.Kromozom;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Alireza Alijani : <a href="https://alirezaalijani.ir">https://alirezaalijani.ir</a>
 * @email alirezaalijani.ir@gmail.com
 * @date 1/8/2023
 */
class EightVezirKromozomTest {

    @Test
    void fitness() {
        Kromozom kromozom=new EightVezirKromozom(4);
        kromozom.addGen(new EightVezirGen(2));
        kromozom.addGen(new EightVezirGen(0));
        kromozom.addGen(new EightVezirGen(2));
        kromozom.addGen(new EightVezirGen(0));

        System.out.println(kromozom.fitness());

        kromozom=new EightVezirKromozom(4);
        kromozom.addGen(new EightVezirGen(2));
        kromozom.addGen(new EightVezirGen(0));
        kromozom.addGen(new EightVezirGen(3));
        kromozom.addGen(new EightVezirGen(0));

        System.out.println(kromozom.fitness());

        kromozom=new EightVezirKromozom(4);
        kromozom.addGen(new EightVezirGen(2));
        kromozom.addGen(new EightVezirGen(0));
        kromozom.addGen(new EightVezirGen(3));
        kromozom.addGen(new EightVezirGen(1));

        System.out.println(kromozom.fitness());

    }

    @Test
    void mutation() {
        Kromozom kromozom=new EightVezirKromozom(4);
        kromozom.addGen(new EightVezirGen(2));
        kromozom.addGen(new EightVezirGen(0));
        kromozom.addGen(new EightVezirGen(2));
        kromozom.addGen(new EightVezirGen(0));

        System.out.println(kromozom);
        kromozom.mutation();
        System.out.println(kromozom);
    }

    @Test
    void copy() {
    }

}