/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.musala.drone.task.utils.knapsack;

/**
 *
 * @author shepherd
 */
public class knapSackObject {

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the weight
     */
    public int getWeight() {
        return weight;
    }

    /**
     * @param weight the weight to set
     */
    public void setWeight(int weight) {
        this.weight = weight;
    }

    /**
     * @return the value
     */
    public int getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(int value) {
        this.value = value;
    }
    
    
    
    private String name; 
    private int weight; 
    private int value;

    public knapSackObject() {
    }

    public knapSackObject(String name, int weight, int value) {
        this.name = name;
        this.weight = weight;
        this.value = value;
    }
    
    
    
    
    
}
