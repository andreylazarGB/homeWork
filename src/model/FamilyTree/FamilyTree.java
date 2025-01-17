package model.FamilyTree;

import model.FamilyTree.Interface.CreatureItem;
import model.FamilyTree.iterators.CreatureIterator;
import model.FamilyTree.Comparators.CreatureComparatorByAge;
import model.FamilyTree.Comparators.CreatureComparatorByBirthDate;
import model.FamilyTree.Comparators.CreatureComparatorByName;
import model.creature.Creature;
import model.creature.Gender;
import model.creature.Human;
import model.creature.Interface.AgeInfo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FamilyTree<E extends CreatureItem<E> & AgeInfo> implements Iterable<E>, Serializable {
    private long creatureId;
    private List<E> creatureList;

    public FamilyTree() {
        this(new ArrayList<>());
    }

    public FamilyTree(List<E> creatureList) {
        this.creatureList = creatureList;
    }

    public Creature createCreature(String name, Gender gender, LocalDate dataBirth){
        return new Human(name, gender, dataBirth);
    }

    public boolean add(E creature) {
        if (!creatureList.contains(creature)) {
            creatureList.add(creature);
            creature.setId(creatureId++);

            addToParents(creature);
            addToChildren(creature);

            return true;
        }
        return false;
    }

    public E getById(long id) {
        if (!checkId(id)) {
            return null;
        }
        ;
        for (E creature : creatureList) {
            if (creature.getId() == id) {
                return creature;
            }
        }
        return null;
    }

    public List<E> getSiblings(int id) {
        E creature = getById(id);
        if (creature == null) {
            return null;
        }
        List<E> res = new ArrayList<>();
        E mother = creature.getMother();
        E father = creature.getFather();
        for (E child : mother.getChildren()) {
            if (!child.equals(creature)) {
                res.add(child);
            }
        }
        for (E child : mother.getChildren()) {
            if (!child.equals(creature) && !res.contains(child)) {
                res.add(child);
            }
        }
        return res;
    }

    public List<E> getByName(String name) {
        List<E> res = new ArrayList<>();
        for (E creature : creatureList) {
            if (creature.getName().equals(name)) {
                res.add(creature);
            }
        }
        return res;
    }

//    public boolean setWedding(long humanId1, long humanId2) {
//        if (checkId(humanId1) && checkId(humanId2)) {
//            E human1 = getById(humanId1);
//            E human2 = getById(humanId2);
//            return setWedding(human1, human2);
//        }
//        return false;
//    }

    public boolean setWedding(Human human1, Human human2) {
        if (human1.getSpouse() == null && human2.getSpouse() == null) {
            human1.setSpouse(human2);
            human1.setSpouse(human2);
            return true;
        } else {
            return false;
        }
    }

//    public boolean setDivorce(long creatureId1, long creatureId2) {
//        if (checkId(creatureId1) && checkId(creatureId2)) {
//            E creature1 = getById(creatureId1);
//            E creature2 = getById(creatureId2);
//            return setDivorce(creature1, creature2);
//        }
//        return false;
//    }

    public boolean setDivorce(Human human1, Human human2) {
        if (human1.getSpouse() != null && human2.getSpouse() != null) {
            human1.setSpouse(null);
            human2.setSpouse(null);
            return true;
        } else {
            return false;
        }
    }

    public boolean remove(long creatureId) {
        if (checkId(creatureId)) {
            E e = getById(creatureId);
            return creatureList.remove(e);
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new CreatureIterator<>(creatureList);
    }

    public void sortByName() {
        creatureList.sort(new CreatureComparatorByName<>());
    }

    public void sortByAge() {
        creatureList.sort(new CreatureComparatorByAge<>());
    }

    public void sortByBirthDate() {
        creatureList.sort(new CreatureComparatorByBirthDate<>());
    }

    private void addToParents(E creature) {
        E mother = creature.getMother();
        E father = creature.getFather();
        if (mother != null) {
            mother.addChildren(creature);
        }
        if (father != null) {
            father.addChildren(creature);
        }
    }

    private void addToChildren(E creature) {
        for (E child : creature.getChildren()) {
            child.addParent(creature);
        }
    }

    private boolean checkId(long id) {
        if (id >= creatureId || id < 0) {
            return false;
        }
        for (E creature : creatureList) {
            if (creature.getId() == id) {
                return false;
            }
        }
        return false;
    }

    public String getCreatureListInfo() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("in tree ");
        stringBuilder.append(creatureList.size());
        stringBuilder.append(" objects\n");
        for (E creature : creatureList) {
            stringBuilder.append(creature);
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    @Override
    public String toString() {
        return getCreatureListInfo();
    }

}
