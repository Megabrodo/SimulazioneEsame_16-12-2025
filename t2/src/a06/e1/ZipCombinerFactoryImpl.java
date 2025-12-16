package a06.e1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class ZipCombinerFactoryImpl implements ZipCombinerFactory {

    @Override
    public <X, Y> ZipCombiner<X, Y, Pair<X, Y>> classical() {
        return new ZipCombiner<X,Y,Pair<X,Y>>() {
            @Override
            public List<Pair<X, Y>> zipCombine(List<X> l1, List<Y> l2) {
                List<Pair<X, Y>> pairs = new ArrayList<>(); 
                for (int i = 0; i < l1.size(); i++) {
                    pairs.add(new Pair<X,Y>(l1.get(i), l2.get(i)));
                }
                return pairs;
            }  
        };
    }

    @Override
    public <X, Y, Z> ZipCombiner<X, Y, Z> mapFilter(Predicate<X> predicate, Function<Y, Z> mapper) {
        return new ZipCombiner<X,Y,Z>() {
            @Override
            public List<Z> zipCombine(List<X> l1, List<Y> l2) {
                List<Z> letters = new ArrayList<>(); 
                for (int i = 0; i < l1.size(); i++) {
                    if (predicate.test(l1.get(i))) {
                        letters.add(mapper.apply(l2.get(i)));
                    }
                }
                return letters;
            }  
        };
    }

    @Override
    public <Y> ZipCombiner<Integer, Y, List<Y>> taker() {
        return new ZipCombiner<Integer, Y, List<Y>>() {
            @Override
            public List<List<Y>> zipCombine(List<Integer> l1, List<Y> l2) {
                List<List<Y>> listOfLists = new ArrayList<>();
                int lastDelimeter = 0; 
                for (int i = 0; i < l1.size(); i++) {
                    List<Y> newList = new ArrayList<>();
                    for (int j = lastDelimeter; j < l1.get(i) + lastDelimeter; j++) {
                        newList.add(l2.get(j));
                    }
                    lastDelimeter = lastDelimeter + l1.get(i);
                    listOfLists.add(newList);
                }
                return listOfLists;
            }  
        };
    }

    @Override
    public <X> ZipCombiner<X, Integer, Pair<X, Integer>> countUntilZero() {
        return new ZipCombiner<X, Integer, Pair<X, Integer>>() {
            @Override
            public List<Pair<X, Integer>> zipCombine(List<X> l1, List<Integer> l2) {
                List<Pair<X, Integer>> listOfPairs = new ArrayList<>();
                int lastDelimeter = 0; 
                for (int i = 0; i < l1.size(); i++) {
                    int counter = 0;
                    for (int j = lastDelimeter; l2.get(j) != 0; j++) {
                        counter++;
                    }
                    lastDelimeter = lastDelimeter + counter + 1;
                    listOfPairs.add(new Pair<X,Integer>(l1.get(i), counter));
                }
                return listOfPairs;
            }  
        };
    }
    
}
