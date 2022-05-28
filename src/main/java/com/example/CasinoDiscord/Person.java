package com.example.CasinoDiscord;


import javax.persistence.*;

@Entity
@Table(name = "people")
public class Person {


    @SequenceGenerator(
            name = "person_key_generator",
            sequenceName = "person_key_generator",
            allocationSize = 1,
            initialValue = 1
    )
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "person_key_generator")
    @Id
    Integer id;
    @Column(name = "username")
    String username;

    @Column(name = "money")
    float money;

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    public Person(String username) {
        this.username = username;
    }

    public Person() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return username;
    }

    public void setName(String username) {
        this.username = username;
    }
}
