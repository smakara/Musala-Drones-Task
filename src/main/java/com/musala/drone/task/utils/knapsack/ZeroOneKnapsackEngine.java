/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.musala.drone.task.utils.knapsack;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import com.musala.drone.task.dto.LoadedMedication;

/**
 *
 * @author shepherd
 */


public class ZeroOneKnapsackEngine {
	
	private LoadedMedication  loadedMedication;
	private List<LoadedMedication> LoadedMedicationList = new ArrayList<LoadedMedication>();

    public ZeroOneKnapsackEngine(List<knapSackObject> knapSackList, int _maxWeight) {
        ZeroOneKnapsack zok = new ZeroOneKnapsack(_maxWeight); 

        // making the list of items that you want to be carried
        for (knapSackObject o : knapSackList) {
            zok.add(o.getName(), o.getWeight(), o.getValue());
        }


        // calculate the solution:
        List<Item> itemList = zok.calcSolution();

        // write out the solution in the standard output
        if (zok.isCalculated()) {
            NumberFormat nf = NumberFormat.getInstance();

            System.out.println(
                    "Maximal weight           = "
                    + nf.format(zok.getMaxWeight() / 100.0) + " kg"
            );
            System.out.println(
                    "Total weight of solution = "
                    + nf.format(zok.getSolutionWeight() / 100.0) + " kg"
            );
            System.out.println(
                    "Total value              = "
                    + zok.getProfit()
            );
            System.out.println();
            System.out.println(
                    "You can carry the following Medication "
                    + "in the knapsack:"
            );
            for (Item item : itemList) {
                if (item.getInKnapsack() == 1) {
                	loadedMedication= new LoadedMedication ();
                	loadedMedication.setMedicationName(item.getName()) ;
                	loadedMedication.setWeight( item.getWeight()) ;
                	LoadedMedicationList.add(loadedMedication);
                	
                    System.out.format(
                            "%1$-23s %2$-3s %3$-5s %4$-15s \n",
                            item.getName(),
                            item.getWeight(), "grammes  ",
                            "(value = " + item.getValue() + ")"
                    );
                }
            }
        } else {
            System.out.println(
                    "The problem is not solved. "
                    + "Maybe you gave wrong data."
            );
        }

    }

	/**
	 * @return the loadedMedication
	 */
	public LoadedMedication getLoadedMedication() {
		return loadedMedication;
	}

	/**
	 * @param loadedMedication the loadedMedication to set
	 */
	public void setLoadedMedication(LoadedMedication loadedMedication) {
		this.loadedMedication = loadedMedication;
	}

	/**
	 * @return the loadedMedicationList
	 */
	public List<LoadedMedication> getLoadedMedicationList() {
		return LoadedMedicationList;
	}

	/**
	 * @param loadedMedicationList the loadedMedicationList to set
	 */
	public void setLoadedMedicationList(List<LoadedMedication> loadedMedicationList) {
		LoadedMedicationList = loadedMedicationList;
	}

	
    
}
