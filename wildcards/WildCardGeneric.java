package wildcards;

import java.util.ArrayList;
import java.util.List;

public class WildCardGeneric {

    public static void addCat(List<? extends Animal> catList) {
        System.out.println("Cat Added");
    }

    public static void main(String[] args) {
        List<Animal> animalList = new ArrayList<Animal>();
        List<Cat> catList = new ArrayList<Cat>();
        List<RedCat> redCatList = new ArrayList<RedCat>();
        List<Dog> dogList = new ArrayList<Dog>();

        // add list of super class Animal of Cat class
        addCat(animalList);

        // add list of Cat class
        addCat(catList);

        // compile time error
        // can not add list of subclass RedCat of Cat class
        addCat(redCatList);

        // compile time error
        // can not add list of subclass Dog of Superclass Animal of Cat class
        addCat(dogList);
    }
}

// super class
class Animal {
}

// sub class of animal
class Cat extends Animal {
}

// sub sub class of animal
class RedCat extends Cat {
}

// sub class of animal
class Dog extends Animal {
}