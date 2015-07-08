/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vmconsolidation.model;

import java.util.ArrayList;
import vmconsolidation.PowerHost;

/**
 *
 * @author Amin
 */
public class RountingModel {
    
    private ArrayList<PowerHost> hosts;
    private int nextHost;

    public ArrayList<PowerHost> getHosts() {
        return hosts;
    }

    public void setHosts(ArrayList<PowerHost> hosts) {
        this.hosts = hosts;
    }

    public int getNextHost() {
        return nextHost;
    }

    public void setNextHost(int nextHost) {
        this.nextHost = nextHost;
    }
    
    
}
