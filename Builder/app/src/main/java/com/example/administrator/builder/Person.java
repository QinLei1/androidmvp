package com.example.administrator.builder;

/**
 * Created by Administrator on 2018/5/14.
 */

public class Person {
    private String name;
    private int age;
    private String height;
    private String weight;
    public Person(PersonBuilder builder){
        this.name = builder.name;
        this.age = builder.age;
        this.height = builder.height;
        this.weight = builder.weight;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }
    static class PersonBuilder{
        private String name;
        private int age;
        private String height;
        private String weight;
        public PersonBuilder name(String name){
            this.name = name;
            return this;
        }
        public PersonBuilder age(int age){
            this.age = age;
            return this;
        }
        public PersonBuilder height(String height){
            this.name = height;
            return this;
        }
        public PersonBuilder weight(String weight){
            this.name = weight;
            return this;
        }
        public Person build(){
            Person person = new Person(this);
            return person;
        }
    }
}
