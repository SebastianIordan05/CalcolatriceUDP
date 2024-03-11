/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cudp.calcolatriceudp;

import java.io.Serializable;

/**
 *
 * @author seba2
 */
public class Numeri implements Serializable {
    private int lhs;
    private int rhs;

    public Numeri(int lhs, int rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    public int getLhs() {
        return lhs;
    }

    public int getRhs() {
        return rhs;
    }
    
    
}
