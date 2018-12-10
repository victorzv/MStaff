package com.aspose.test;

import com.aspose.test.dom.IPerson;
import com.aspose.test.service.CalculateSalary;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class Staff {
    /*
    * first IPerson - sub
    * second IPerson - chief
    * */
    Map<IPerson, IPerson> treePersons = new HashMap<>();
    Map<IPerson, BigDecimal> persons = new HashMap<>();

    public Staff() {
    }

    public void addPerson(IPerson IPerson){
        if (!persons.containsKey(IPerson)){
            persons.put(IPerson, BigDecimal.ZERO);
        }
    }

    public void setSalary(IPerson IPerson, BigDecimal salary){
        if(persons.containsKey(IPerson)){
            persons.put(IPerson, salary);
        }
    }

    public Map<IPerson, BigDecimal> getAllSalary(){
        return persons;
    }

    public BigDecimal getSalary(IPerson IPerson){
        if (persons.containsKey(IPerson)){
            return persons.get(IPerson);
        }
        return null;
    }

    public boolean hasSalary(Set<IPerson> IPersonSet){
        return IPersonSet.stream().mapToDouble(x->getSalary(x).doubleValue()).sum() > 0;
    }

    public Set<IPerson> getWithoutSalary(Set<IPerson> IPersonSet){
        return IPersonSet.stream().filter(x->getSalary(x).compareTo(BigDecimal.ZERO)==0).collect(Collectors.toSet());
    }

    public Set<IPerson> getWithSalary(Set<IPerson> IPersonSet){
        return IPersonSet.stream().filter(x->getSalary(x) != null).collect(Collectors.toSet());
    }

    public boolean addPesonChief(IPerson IPerson, IPerson chief){
        addPerson(IPerson);
        addPerson(chief);
        if (!chief.canHaveSub()) {return false;}
        if (!treePersons.containsKey(IPerson)){
            treePersons.put(IPerson, chief);
        }
        return true;
    }

    public boolean addSubsToChief(IPerson chief, IPerson...sub){
        addPerson(chief);
        if (!chief.canHaveSub()) {return false;}
        for(IPerson p : sub){
            addPerson(p);
            if (!treePersons.containsKey(p)){
                treePersons.put(p, chief);
            }
        }
        return true;
    }

    public boolean addSubstoChief(IPerson chief, List<IPerson> sub){
        addPerson(chief);
        if (!chief.canHaveSub()) {return false;}
        for(IPerson p : sub){
            addPerson(p);
            if (!treePersons.containsKey(p)){
                treePersons.put(p, chief);
            }
        }
        return true;
    }

    public IPerson getChief(IPerson IPerson){
        IPerson p = null;
        if (treePersons.containsKey(IPerson)){
            p = treePersons.get(IPerson);
        }
        return p;
    }

    public Set<IPerson> getSub(IPerson chief){
        return treePersons.entrySet()
                .stream()
                .filter(x->x.getValue().equals(chief))
                .map(x->x.getKey())
                .collect(Collectors.toSet());
    }

    private Set<IPerson> getLowestNodes(){
        return persons.entrySet()
                .stream()
                .filter(map->!isInChief(map.getKey()))
                .map(x->x.getKey())
                .collect(Collectors.toSet());
    }

    public Set<IPerson> whoGoesToChief(IPerson chief){
        Set<IPerson> lowestNodes = getLowestNodes();
        Set<IPerson> chiefLowestNodes = new HashSet<>();
        for(IPerson p : lowestNodes){
            IPerson temp = getChief(p);
            while (temp != chief && temp != null) {
                temp = getChief(temp);
            }
            if (temp != null){ chiefLowestNodes.add(p); }
        }
        return chiefLowestNodes;
    }

    /*
    * returns groupped IPerson - chief, Set<IPerson> - children
    * */
    public Map<IPerson, Set<IPerson>> getNeibourguds(Set<IPerson> child){
        Map<IPerson, Set<IPerson>> neibourguds = new HashMap<>();
        for(IPerson p : child){
            IPerson tempChief = treePersons.get(p);
            Set<IPerson> tempChildren = neibourguds.getOrDefault(tempChief, new HashSet<IPerson>());
//            tempChildren.add(p);
            neibourguds.put(tempChief, tempChildren);
        }
        for(IPerson p : neibourguds.keySet()){
            neibourguds.put(p, getSub(p));
        }
        return neibourguds;
    }

    private boolean isInChief(IPerson IPerson){
        long count = treePersons.entrySet()
                .stream()
                .filter(map-> IPerson.equals(map.getValue()))
                .count();
        return count > 0;
    }

    public void flashSalary(){
        for(IPerson p : persons.keySet()){
            persons.put(p, BigDecimal.ZERO);
        }
    }

    public IPerson getHead(){
        Set<IPerson> subs = treePersons.keySet();
        Collection<IPerson> chiefs = treePersons.values();

        for (IPerson p : chiefs){
            if (!subs.contains(p)){
                return p;
            }
        }

        return null;
    }

    public void prepareCalculate(IPerson person){
        CalculateSalary calculateSalary = new CalculateSalary();
        Set<IPerson> setPersons = getSub(person);
        if (setPersons.size() == 0 || hasSalary(setPersons)){
            calculateSalary.calculate(person, this);
        }
        else{
            for(IPerson p : setPersons){
                if (getSalary(p).compareTo(BigDecimal.ZERO) > 0)
                    continue;
                prepareCalculate(p);
                calculateSalary.calculate(p, this);
            }
        }
    }

}
