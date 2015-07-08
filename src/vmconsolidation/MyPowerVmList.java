/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vmconsolidation;

import java.util.ArrayList;
import java.util.List;
import org.cloudbus.cloudsim.Vm;
import org.cloudbus.cloudsim.lists.VmList;

/**
 *
 * @author Amin
 */
public class MyPowerVmList extends VmList {

    //sort ram utilization nad create map for vmsToMigrate indexes and position of them in ram utilization
    public static ArrayList<Integer> sortByRamUtilization(List<? extends Vm> vmsToMigrate) {

        ArrayList<Integer> vmsRamUtilizationPositions = new ArrayList<>();
        ArrayList<MappableRamModel> tempRamUtilizations = new ArrayList<>();

        for (int i = 0; i < vmsToMigrate.size(); i++) {
            MappableRamModel mappableRamModel = new MappableRamModel();
            mappableRamModel.ram = vmsToMigrate.get(i).getCurrentRequestedRam();
            mappableRamModel.visited = false;
            tempRamUtilizations.add(mappableRamModel);

        }

        //create empty vmsRamUtilizationPositions
        for (int i = 0; i < tempRamUtilizations.size(); i++) {
            vmsRamUtilizationPositions.add(-1);
        }

        //set vmsRamUtilizationPositions
        for (int i = 0; i < tempRamUtilizations.size(); i++) {

            int maxIndex = -1;
            int maxValue = -1;

            for (int j = 0; j < tempRamUtilizations.size(); j++) {

                if (tempRamUtilizations.get(j).visited == false && tempRamUtilizations.get(j).ram > maxValue) {

                    maxIndex = j;
                    maxValue = tempRamUtilizations.get(j).ram;
                }
            }

            vmsRamUtilizationPositions.set(maxIndex, i);
            tempRamUtilizations.get(maxIndex).visited = true;

        }

        return vmsRamUtilizationPositions;
    }

    public static ArrayList<Integer> sortByBwUtilization(List<? extends Vm> vmsToMigrate) {

        ArrayList<Integer> vmsBwUtilizationPositions = new ArrayList<>();
        ArrayList<MappableBwModel> tempBwUtilizations = new ArrayList<>();

        for (int i = 0; i < vmsToMigrate.size(); i++) {
            MappableBwModel mappableBwModel = new MappableBwModel();
            mappableBwModel.bw = vmsToMigrate.get(i).getCurrentRequestedBw();
            mappableBwModel.visited = false;
            tempBwUtilizations.add(mappableBwModel);

        }

        for (int i = 0; i < tempBwUtilizations.size(); i++) {
            vmsBwUtilizationPositions.add(-1);
        }

        for (int i = 0; i < tempBwUtilizations.size(); i++) {

            int maxIndex = -1;
            double maxValue = -1;

            for (int j = 0; j < tempBwUtilizations.size(); j++) {

                if (tempBwUtilizations.get(j).visited == false && tempBwUtilizations.get(j).bw > maxValue) {

                    maxIndex = j;
                    maxValue = tempBwUtilizations.get(j).bw;
                }
            }

            vmsBwUtilizationPositions.set(maxIndex, i);
            tempBwUtilizations.get(maxIndex).visited = true;

        }

        return vmsBwUtilizationPositions;
    }

    public static class MappableBwModel {

        private double bw;
        private boolean visited;

        public double getBw() {
            return bw;
        }

        public void setBw(double bw) {
            this.bw = bw;
        }

        public boolean isVisited() {
            return visited;
        }

        public void setVisited(boolean visited) {
            this.visited = visited;
        }

    }

    public static class MappableRamModel {

        private int ram;
        private boolean visited;

        public MappableRamModel(int ram, boolean visited) {
            this.ram = ram;
            this.visited = visited;
        }

        public MappableRamModel() {

        }

        public void setRam(int ram) {
            this.ram = ram;
        }

        public void setVisited(boolean visited) {
            this.visited = visited;
        }

        public int getRam() {
            return ram;
        }

        public boolean isVisited() {
            return visited;
        }

    }
}
