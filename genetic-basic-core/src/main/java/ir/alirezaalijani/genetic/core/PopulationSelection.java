package ir.alirezaalijani.genetic.core;

import ir.alirezaalijani.genetic.share.domain.Kromozom;
import ir.alirezaalijani.genetic.share.domain.KromozomUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * @author Alireza Alijani : <a href="https://alirezaalijani.ir">https://alirezaalijani.ir</a>
 * @email alirezaalijani.ir@gmail.com
 * @date 1/6/2023
 */
@Slf4j
public class PopulationSelection {
    private SelectionType type;
    private int elitismPresent;
    private boolean order;
    private int tournamentSize;
    private int rouletteing;

    public PopulationSelection(SelectionType type,
                               int elitismPresent,
                               boolean order,
                               int tournamentSize,
                               int rouletteing) {
        this.type = type;
        this.elitismPresent = elitismPresent;
        this.order = order;
        this.tournamentSize = tournamentSize;
        this.rouletteing = rouletteing;
    }

    public List<Kromozom> select(List<Kromozom> firstPopulation,SelectionType type, int elitismPresent,
                                 boolean order, int tournamentSize, int rouletteing){
        this.type = type;
        this.elitismPresent=elitismPresent;
        this.order=order;
        this.tournamentSize=tournamentSize;
        this.rouletteing = rouletteing;
        return select(firstPopulation);
    }

    public List<Kromozom> select(List<Kromozom> firstPopulation){
        var population = KromozomUtil.sort(firstPopulation,order);
        var selectedPopulation=new ArrayList<Kromozom>();
        int elitismSize = firstPopulation.size()* elitismPresent / 100;

        if (elitismPresent>0){
            log.debug("selecting elitism in population from 0 to {}",elitismSize);
            var selectedElitism = new ArrayList<>(population.subList(0, elitismSize));
            selectedPopulation.addAll(selectedElitism);
            if (selectedElitism.size()>0){
                population = population.subList(selectedElitism.size(),population.size());
                log.debug("new population after elitism selection is={}",population.size());
            }
        }

        if (type.equals(SelectionType.Tournament)){
            selectedPopulation.addAll(tournamentSelect(population,tournamentSize,order));
        }else if (type.equals(SelectionType.RouletteWheel)){
            selectedPopulation.addAll(rouletteWheelSelect(population,rouletteing,order));
        }else {
            selectedPopulation.addAll(tournamentSelect(population,tournamentSize,order));
        }
        return selectedPopulation;
    }
    private List<Kromozom> tournamentSelect(List<Kromozom> population,int tournamentSize,boolean order) {
        Collections.shuffle(population);
        var tournamentIn = population.subList(0,tournamentSize);
        var selected =KromozomUtil.sort(tournamentIn,order);
        return selected.subList(0,2);
    }

    private List<Kromozom> rouletteWheelSelect(List<Kromozom> population,int rouletteing,boolean order) {
        Collections.shuffle(population);
        double sumOfFitness = population
                .stream()
                .mapToDouble(Kromozom::fitness)
                .max().orElse(92939484.0);

        Random random=new Random();
        var selectedPopulation=new ArrayList<Kromozom>();
        population = KromozomUtil.sort(population,order);
        var fitnessRanges = toFitnessRanges(population,(int) sumOfFitness);
        Collections.shuffle(fitnessRanges);
        for (int i=0;i<rouletteing;i++){
            var rand = random.nextInt((int) sumOfFitness);
            for (var range: fitnessRanges){
                if (range.isInRange(rand)){
                    log.debug("select random on wheel:{} from: {} to :{} kromozom :{}",rand,range.from,range.to,range.kromozom.fitness());
                    selectedPopulation.add(range.kromozom);
                }
            }
        }
        return selectedPopulation;
    }

    private List<FitnessRange> toFitnessRanges(List<Kromozom> population,int lastRange){
        var popIterator = population.listIterator();
        var fitnessRanges = new ArrayList<FitnessRange>();
        while (popIterator.hasNext()){
            int from=0;
            int to = lastRange;
            if (popIterator.hasPrevious()){
                from= (int) popIterator.previous().fitness();
            }
            popIterator.next();
            var kromozom = popIterator.next();
            if (popIterator.hasNext()){
                to = (int) popIterator.next().fitness();
                popIterator.previous();
            }
            var fitnessRange = new FitnessRange(from,to,kromozom);
            fitnessRanges.add(fitnessRange);
            log.debug("convert kromozom to fitness range: {}",fitnessRange);
        }
        return fitnessRanges;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    private static class FitnessRange{
        private int from;
        private int to;

        private Kromozom kromozom;

        public boolean isInRange(int fitness){
            return from <= fitness && to > fitness;
        }

        @Override
        public String toString() {
            return "FitnessRange{" +
                    "from=" + from +
                    ", to=" + to +
                    ", fitness=" + kromozom.fitness() +
                    '}';
        }
    }
}
