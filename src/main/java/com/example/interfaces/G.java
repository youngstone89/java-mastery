package com.example.interfaces;

public class G implements E, F {

    @Override
    public void hey() {
        E.super.hey();
        F.super.hey();
    }

    public static void main(String[] args) {
        new G().hey();
    }
}
