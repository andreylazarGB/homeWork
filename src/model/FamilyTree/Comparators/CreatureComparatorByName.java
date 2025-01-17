package model.FamilyTree.Comparators;

import model.FamilyTree.Interface.CreatureItem;

import java.util.Comparator;

public class CreatureComparatorByName<T extends CreatureItem<T>> implements Comparator<T> {
    @Override
    public int compare(T o1, T o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
