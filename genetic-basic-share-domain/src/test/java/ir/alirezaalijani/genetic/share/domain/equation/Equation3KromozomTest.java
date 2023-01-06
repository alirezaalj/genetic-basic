package ir.alirezaalijani.genetic.share.domain.equation;

import ir.alirezaalijani.genetic.share.domain.Kromozom;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Alireza Alijani : <a href="https://alirezaalijani.ir">https://alirezaalijani.ir</a>
 * @email alirezaalijani.ir@gmail.com
 * @date 1/5/2023
 */
class Equation3KromozomTest {

    @Test
    void fitness() {
        Kromozom kromozom=new Equation3Kromozom(3);
        kromozom.addGen(new EquationGen(3));
        kromozom.addGen(new EquationGen(1));
        kromozom.addGen(new EquationGen(-1));
        assertThat(kromozom.fitness()).isEqualTo(0.0);
    }
}