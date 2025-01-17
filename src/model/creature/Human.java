package model.creature;

import model.FamilyTree.Interface.CreatureItem;
import model.creature.Interface.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

public class Human extends Creature implements CreatureItem<Human> {
    private Human spouse;

    public Human(String name, Gender gender, LocalDate dataBirth, LocalDate dataDeath, Human mother,
                 Human father, Human spouse) {
        super(name, gender, dataBirth, dataDeath, mother, father);
        this.spouse = spouse;
    }

    public Human(String name, Gender gender, LocalDate dataBirth) {
        super(name, gender, dataBirth);
    }

    public Human(String name, Gender gender, LocalDate dataBirth, Human father, Human mother) {
        super(name, gender, dataBirth, father, mother);
    }


    public String getSpouseInfo() {
        String res = "spouse: ";
        if (spouse == null) {
            res += "no";
        } else {
            res += spouse.getName();
        }
        return res;
    }

    public Human getSpouse() {
        return spouse;
    }

    public void setSpouse(Human spouse) {
        this.spouse = spouse;
    }


    @Override
    public boolean addChildren(Human child) {
        return false;
    }

    @Override
    public List<Human> getChildren() {
        return null;
    }

    @Override
    public void setChildren(List children) {

    }

    @Override
    public String getChildrenInfo() {
        return null;
    }

    @Override
    public int getPeriod(LocalDate dataBirth, LocalDate dataDeath) {
        return 0;
    }

    @Override
    public int getAge() {
        return 0;
    }

    @Override
    public LocalDate getDataBirth() {
        return null;
    }

    @Override
    public void setDataBirth(LocalDate dataBirth) {

    }

    @Override
    public LocalDate getDataDeath() {
        return null;
    }

    @Override
    public void setDataDeath(LocalDate dataDeath) {

    }

    @Override
    public void addParent(Human parent) {

    }

    @Override
    public String getMotherInfo() {
        return null;
    }

    @Override
    public String getFatherInfo() {
        return null;
    }

    @Override
    public Human getMother() {
        return null;
    }

    @Override
    public void setMother(Human mather) {

    }

    @Override
    public Human getFather() {
        return null;
    }

    @Override
    public void setFather(Human father) {

    }

    @Override
    public String getInfo() {
        return null;
    }
}



