package org.example.core.chapter02;

public class Human {
    private Phone phone;

    public Human(Phone phone) {
        this.phone = phone;
    }

    public void call() {
        System.out.printf("Using phone: %s%n", phone.model);
    }
}
