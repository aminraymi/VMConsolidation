/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vmconsolidation;


import vmconsolidation.planetlab.PlanetLabRunner;



/**
 *
 * @author Amin
 */
public class VMConsolidation {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        
        
        
        boolean enableOutput = true;
        boolean outputToFile = true;
        String inputFolder = "C:\\cloudsim-3.0.3\\examples\\workload\\planetlab";
        String outputFolder = "d:\\thesis-results";
        String workload = "20110412"; // PlanetLab workload
        String vmAllocationPolicy = "iqr"; // Inter Quartile Range (IQR) VM allocation policy
        String vmSelectionPolicy = "mmt"; // Minimum Migration Time (MMT) VM selection policy
        String parameter = "1.5"; // the safety parameter of the IQR policy

        new PlanetLabRunner(
                enableOutput,
                outputToFile,
                inputFolder,
                outputFolder,
                workload,
                vmAllocationPolicy,
                vmSelectionPolicy,
                parameter);
    }

}
