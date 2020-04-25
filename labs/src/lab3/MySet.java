package lab3;

import java.util.*;

public class MySet extends HashSet<Integer> {

    MySet(int capacity) {
        super(capacity);
    }

    public Set<Integer> union(Set<Integer> outerSet) {
        Set<Integer> unionSet = new HashSet<>(outerSet);
        unionSet.addAll(this);
        return unionSet;
    }

    public Set<Integer> intersect(Set<Integer> outerSet) {
        Set<Integer> interSet = new HashSet<>(outerSet);
        interSet.retainAll(this);
        return interSet;
    }
}
