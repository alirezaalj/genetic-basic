package ir.alirezaalijani.genetic.share.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * @author Alireza Alijani : <a href="https://alirezaalijani.ir">https://alirezaalijani.ir</a>
 * @email alirezaalijani.ir@gmail.com
 * @date 1/5/2023
 */
public abstract class Kromozom {
    private final String id;
    private final List<Gen> gens;
    protected final int genSize;
    protected boolean isTouched;
    public Kromozom(int genSize) {
        this.id = UUID.randomUUID().toString();
        this.gens=new ArrayList<>(genSize);
        this.genSize = genSize;
        this.isTouched=false;
    }

    public void addGen(Gen gen){
        if (gens.size()<genSize) {
            this.gens.add(gen);
            this.isTouched=true;
        }
    }

    public String getId(){
        return this.id;
    }
    public List<Gen> kromozom(){
        return this.gens;
    }
    public abstract double fitness();
    public abstract void mutation();
    public abstract Kromozom copy();
    public void setGens(List<Gen> genList){
        if (genList.size()==gens.size()){
            this.gens.clear();
            this.gens.addAll(genList);
            this.isTouched=true;
        }
    }

    protected void replaceGen(Gen gen, int index){
        if (index<=gens.size()){
            gens.set(index,gen);
        }
    }

    @Override
    public String toString() {
        return "Kromozom{" +
                "gens=" + gens +
                "fitness=" + fitness()+
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Kromozom kromozom = (Kromozom) o;
        return id.equals(kromozom.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
