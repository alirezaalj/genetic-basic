package ir.alirezaalijani.genetic.share.domain;

import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.stream.Collectors;

/**
 * @author Alireza Alijani : <a href="https://alirezaalijani.ir">https://alirezaalijani.ir</a>
 * @email alirezaalijani.ir@gmail.com
 * @date 1/6/2023
 */
public class KromozomUtil {
    private static final Random random;
    static {
        random=new Random();
    }
    private static List<Kromozom> sort(List<Kromozom> input) {
        var copyList = new ArrayList<>(input);
        copyList.sort(Comparator.comparingDouble(Kromozom::fitness));
        return copyList;
    }

    public static List<Kromozom> sort(List<Kromozom> input, boolean order) {
        var sorted = sort(input);
        if (!order) {
            Collections.reverse(sorted);
        }
        return sorted;
    }

    public static List<Kromozom> remove(List<Kromozom> all, List<Kromozom> toRemove) {
        all.removeAll(toRemove);
        return new ArrayList<>(all);
    }

    public static List<Kromozom> crossover(Kromozom k1,Kromozom k2,int present){
        int doCrossover = random.nextInt(100);
        if (doCrossover<=present){
            boolean left=random.nextBoolean();
            int index = random.nextInt(k1.kromozom().size());
            if (left){
                var k1Gens = k1.kromozom().subList(0,index);
                var k2Gens = k2.kromozom().subList(0,index);
                var k1LeftGens = k1.kromozom().subList(index,k1.kromozom().size());
                var k2LeftGens = k2.kromozom().subList(index,k2.kromozom().size());
                List<Gen> k1NewList= new ArrayList<>();
                k1NewList.addAll(k2Gens);
                k1NewList.addAll(k1LeftGens);

                List<Gen> k2NewList= new ArrayList<>();
                k2NewList.addAll(k1Gens);
                k2NewList.addAll(k2LeftGens);

                k1.setGens(new ArrayList<>(k1NewList));
                k2.setGens(new ArrayList<>(k2NewList));

            }else {
                var k1Gens = k1.kromozom().subList(index,k1.kromozom().size());
                var k2Gens = k2.kromozom().subList(index,k2.kromozom().size());
                var k1LeftGens = k1.kromozom().subList(0,index);
                var k2LeftGens = k2.kromozom().subList(0,index);

                var k1NewGens= new ArrayList<Gen>();
                k1NewGens.addAll(k1LeftGens);
                k1NewGens.addAll(k2Gens);

                var k2NewGens=new ArrayList<Gen>();
                k2NewGens.addAll(k2LeftGens);
                k2NewGens.addAll(k1Gens);

                k1.setGens(k1NewGens);
                k2.setGens(k2NewGens);
            }
        }
        return Arrays.asList(k1,k2);
    }

    public static List<Kromozom> mutation(Kromozom k1,Kromozom k2,int present){
        int doMutation = random.nextInt(100);
        if (doMutation<=present){
            boolean k1Mutation = random.nextBoolean();
            if (k1Mutation){
                k1.mutation();
            }else {
                k2.mutation();
            }
        }
        return Arrays.asList(k1,k2);
    }

    public static List<Kromozom> randomCouple(List<Kromozom> kromozoms){
        int first = random.nextInt(kromozoms.size());
        int second = random.nextInt(kromozoms.size());
        while (second==first){
            second= random.nextInt(kromozoms.size());
        }
//        System.out.println("first and second is same:"+Objects.equals(kromozoms.get(first),kromozoms.get(second)));
        return Arrays.asList(kromozoms.get(first),kromozoms.get(second));
    }

    public static double bestFitness(List<Kromozom> kromozoms,boolean order){
        return sort(kromozoms,order).get(0).fitness();
    }

    public static Kromozom bestFitnessKromozom(List<Kromozom> kromozoms,boolean order){
        return sort(kromozoms,order).get(0);
    }

    public static double populationAvg(List<Kromozom> kromozoms){
       return kromozoms
                .stream()
                .mapToDouble(Kromozom::fitness)
                .average().orElse(-1);
    }

    public static List<Kromozom> deepCopy(List<Kromozom> kromozoms){
        var newList=new ArrayList<Kromozom>();
        kromozoms
                .forEach(kromozom -> {
                    newList.add(kromozom.copy());
                });
        return newList;
    }
}
