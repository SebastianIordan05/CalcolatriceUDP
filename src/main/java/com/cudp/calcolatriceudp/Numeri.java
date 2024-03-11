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
    private final double lhs;
    private final double rhs;
    private final char sign;

    public Numeri(double lhs, double rhs, char sign) {
        this.lhs = lhs;
        this.rhs = rhs;
        this.sign = sign;
    }
    
    public char getSign() {
        return sign;
    }

    public double getLhs() {
        return lhs;
    }

    public double getRhs() {
        return rhs;
    }

    @Override
    public String toString() {
        return "Numeri{" + "lhs=" + lhs + ", rhs=" + rhs + ", sign=" + sign + '}';
    }
}
