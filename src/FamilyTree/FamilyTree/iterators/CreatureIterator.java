package FamilyTree.FamilyTree.iterators;

import FamilyTree.FamilyTree.CreatureItem;

import java.util.Iterator;
import java.util.List;

public class CreatureIterator<T extends CreatureItem<T>> implements Iterator<T> {
    private int index;
    private List<T> creatureList;

    public CreatureIterator(List<T> creatureList) {
        this.creatureList = creatureList;
    }


    @Override
    public boolean hasNext() {
        return creatureList.size() > index;
    }

    @Override
    public T next() {
        return creatureList.get(index++);
    }
}