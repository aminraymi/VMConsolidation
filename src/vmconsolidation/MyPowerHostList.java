/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vmconsolidation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.cloudbus.cloudsim.Host;


/**
 *
 * @author Amin
 */
public class MyPowerHostList {

    public static <T extends Host> void sortByAvailableCpu(List<T> hostList) {
        Collections.sort(hostList, new Comparator<T>() {

            @Override
            public int compare(T a, T b) throws ClassCastException {
                Double aAvailableCpu = a.getAvailableMips();
                Double bAvailableCpu = b.getAvailableMips();
                return bAvailableCpu.compareTo(aAvailableCpu);
            }
        });
    }

    public static ArrayList<Integer> sortByAvailableRam(List<PowerHost> hosts) {

        ArrayList<Integer> hostsAvalaibleRamPositions = new ArrayList<>();
        ArrayList<MappableRamModel> tempHostAvailableRam = new ArrayList<>();

        for (int i = 0; i < hosts.size(); i++) {
            MappableRamModel mappableRamModel = new MappableRamModel();
            mappableRamModel.ram = hosts.get(i).getRamProvisioner().getAvailableRam();
            mappableRamModel.visited = false;
            tempHostAvailableRam.add(mappableRamModel);
        }

        for (int i = 0; i < tempHostAvailableRam.size(); i++) {
            hostsAvalaibleRamPositions.add(-1);
        }

        for (int i = 0; i < tempHostAvailableRam.size(); i++) {

            int maxIndex = -1;
            int maxValue = -1;

            for (int j = 0; j < tempHostAvailableRam.size(); j++) {
                if (tempHostAvailableRam.get(j).visited == false && tempHostAvailableRam.get(j).ram > maxValue) {

                    maxIndex = j;
                    maxValue = tempHostAvailableRam.get(j).ram;
                }
            }

            hostsAvalaibleRamPositions.set(maxIndex, i);
            tempHostAvailableRam.get(maxIndex).visited = true;
        }
        return hostsAvalaibleRamPositions;
    }

    public static ArrayList<Integer> sortByAvailableBw(List<PowerHost> hosts) {

        ArrayList<Integer> hostsAvalaibleBwPositions = new ArrayList<>();
        ArrayList<MappableBwModel> tempHostAvailableBw = new ArrayList<>();

        for (int i = 0; i < hosts.size(); i++) {
            MappableBwModel mappableBwModel = new MappableBwModel();
            mappableBwModel.bw = hosts.get(i).getBwProvisioner().getAvailableBw();
            mappableBwModel.visited = false;
            tempHostAvailableBw.add(mappableBwModel);
        }

        for (int i = 0; i < tempHostAvailableBw.size(); i++) {
            hostsAvalaibleBwPositions.add(-1);
        }

        for (int i = 0; i < tempHostAvailableBw.size(); i++) {

            int maxIndex = -1;
            double maxValue = -1;

            for (int j = 0; j < tempHostAvailableBw.size(); j++) {
                if (tempHostAvailableBw.get(j).visited == false && tempHostAvailableBw.get(j).bw > maxValue) {

                    maxIndex = j;
                    maxValue = tempHostAvailableBw.get(j).bw;
                }
            }

            hostsAvalaibleBwPositions.set(maxIndex, i);
            tempHostAvailableBw.get(maxIndex).visited = true;
        }
        return hostsAvalaibleBwPositions;

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
