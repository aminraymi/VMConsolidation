/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vmconsolidation;


import org.cloudbus.cloudsim.CloudletScheduler;


/**
 *
 * @author Amin
 */
public abstract class MyCloudletScheduler extends CloudletScheduler{

    /**
     * Get Ram utilization created by all cloudlets.
     *
     * @param time the time
     * @return total utilization
     */
    public abstract double getTotalUtilizationOfRam(double time);
    
    /**
     * Get Bandwidth utilization created by all cloudlets.
     *
     * @param time the time
     * @return total utilization
     */
    public abstract double getTotalUtilizationOfBw(double time);

   
}
