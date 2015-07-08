/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vmconsolidation;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.cloudbus.cloudsim.Host;
import org.cloudbus.cloudsim.Vm;
import org.cloudbus.cloudsim.util.MathUtil;
import vmconsolidation.model.RountingModel;

/**
 *
 * @author Amin
 */
public class PlacementUtils {

    public static String getVmTypeByUtilizations(List<? extends Vm> vmsToMigrate, ArrayList<Integer> vmsRamUtilizationPositions, ArrayList<Integer> vmsBwUtilizationPositions, int vmIndex) {

        int firstSplitterIndex = vmsToMigrate.size() / 3;
        int secondsplitterIndex = firstSplitterIndex * 2;
        firstSplitterIndex--;
        secondsplitterIndex--;

        StringBuilder vmType = new StringBuilder();

        if (vmIndex <= firstSplitterIndex) {
            vmType.append("H");
        } else if (vmIndex <= secondsplitterIndex) {
            vmType.append("M");
        } else {
            vmType.append("L");
        }

        if (vmsRamUtilizationPositions.get(vmIndex) <= firstSplitterIndex) {
            vmType.append("H");
        } else if (vmsRamUtilizationPositions.get(vmIndex) <= secondsplitterIndex) {
            vmType.append("M");
        } else {
            vmType.append("L");
        }

        if (vmsBwUtilizationPositions.get(vmIndex) <= firstSplitterIndex) {
            vmType.append("H");
        } else if (vmsBwUtilizationPositions.get(vmIndex) <= secondsplitterIndex) {
            vmType.append("M");
        } else {
            vmType.append("L");
        }

        return vmType.toString();
    }

    public static HashMap<String, ArrayList<PowerHost>> getHostsTypesByResourceAvailability(List<PowerHost> sortByAvailabeMipsHostList, ArrayList<Integer> hostsRamUtilizationPositions, ArrayList<Integer> hostsBwUtilizationPositions) {

        HashMap<String, ArrayList<PowerHost>> hostsTypes = new HashMap<>();

        int firstSplitterIndex = sortByAvailabeMipsHostList.size() / 3;
        int secondsplitterIndex = firstSplitterIndex * 2;
        firstSplitterIndex--;
        secondsplitterIndex--;

        // create host types hashmap
        int hostIndex = 0;
        for (PowerHost host : sortByAvailabeMipsHostList) {

            StringBuilder hostTypeString = new StringBuilder();
            if (hostIndex <= firstSplitterIndex) {
                hostTypeString.append("H");
            } else if (hostIndex <= secondsplitterIndex) {
                hostTypeString.append("M");
            } else {
                hostTypeString.append("L");
            }

            if (hostsRamUtilizationPositions.get(hostIndex) <= firstSplitterIndex) {
                hostTypeString.append("H");
            } else if (hostsRamUtilizationPositions.get(hostIndex) <= secondsplitterIndex) {
                hostTypeString.append("M");
            } else {
                hostTypeString.append("L");
            }

            if (hostsBwUtilizationPositions.get(hostIndex) <= firstSplitterIndex) {
                hostTypeString.append("H");
            } else if (hostsBwUtilizationPositions.get(hostIndex) <= secondsplitterIndex) {
                hostTypeString.append("M");
            } else {
                hostTypeString.append("L");
            }

            //System.err.println(hostTypeString);
            if (hostsTypes.containsKey(hostTypeString.toString())) {
                hostsTypes.get(hostTypeString.toString()).add(host);

            } else {
                ArrayList<PowerHost> hosts = new ArrayList<>();
                hosts.add(host);
                hostsTypes.put(hostTypeString.toString(), hosts);
            }

            hostIndex++;

        }
        return hostsTypes;
    }

    private static HashMap<String, ArrayList<String>> getRoutingConfiguration() {

        HashMap<String, ArrayList<String>> routingConfig = new HashMap<>();

        routingConfig.put("HHH", new ArrayList<String>(Arrays.asList(
                "HHH", "HHM", "HHL", "HMH", "HMM", "HML",
                "HLH", "HLM", "HLL", "MHH", "MHM", "MHL",
                "MMH", "MMM", "MML", "MLH", "MLM", "MLL",
                "LHH", "LHM", "LHL", "LMH", "LMM", "LML",
                "LLH", "LLM", "LLL"
        )));

        routingConfig.put("HHM", new ArrayList<String>(Arrays.asList(
                "HHM", "HHH", "HHL", "HMM", "HMH", "HML",
                "HLM", "HLH", "HLL", "MHM", "MHH", "MHL",
                "MMM", "MMH", "MML", "MLM", "MLH", "MLL",
                "LHM", "LHH", "LHL", "LMM", "LMH", "LML",
                "LLM", "LLH", "LLL"
        )));

        routingConfig.put("HHL", new ArrayList<String>(Arrays.asList(
                "HHL", "HHM", "HHH", "HML", "HMM", "HMH",
                "HLL", "HLM", "HLH", "MHL", "MHM", "MHH",
                "MML", "MMM", "MMH", "MLL", "MLM", "MLH",
                "LHL", "LHM", "LHH", "LML", "LMM", "LMH",
                "LLL", "LLM", "LLH"
        )));

        routingConfig.put("HMH", new ArrayList<String>(Arrays.asList(
                "HMH", "HMM", "HML", "HHH", "HHM", "HHL",
                "HLH", "HLM", "HLL", "MMH", "MMM", "MML",
                "MHH", "MHM", "MHL", "MLH", "MLM", "MLL",
                "LMH", "LMM", "LML", "LHH", "LHM", "LHL",
                "LLH", "LLM", "LLL"
        )));

        routingConfig.put("HMM", new ArrayList<String>(Arrays.asList(
                "HMM", "HMH", "HML", "HHM", "HHH", "HHL",
                "HLM", "HLH", "HLL", "MMM", "MMH", "MML",
                "MHM", "MHH", "MHL", "MLM", "MLH", "MLL",
                "LMM", "LMH", "LML", "LHM", "LHH", "LHL",
                "LLM", "LLH", "LLL"
        )));

        routingConfig.put("HML", new ArrayList<String>(Arrays.asList(
                "HML", "HMM", "HMH", "HHL", "HHM", "HHH",
                "HLL", "HLM", "HLH", "MML", "MMM", "MMH",
                "MHL", "MHM", "MHH", "MLL", "MLM", "MLH",
                "LML", "LMM", "LMH", "LHL", "LHM", "LHH",
                "LLL", "LLM", "LLH"
        )));

        routingConfig.put("HLH", new ArrayList<String>(Arrays.asList(
                "HLH", "HLM", "HLL", "HMH", "HMM", "HML",
                "HHH", "HHM", "HHL", "MLH", "MLM", "MLL",
                "MMH", "MMM", "MML", "MHH", "MHM", "MHL",
                "LLH", "LLM", "LLL", "LMH", "LMM", "LML",
                "LHH", "LHM", "LHL"
        )));

        routingConfig.put("HLM", new ArrayList<String>(Arrays.asList(
                "HLM", "HLH", "HLL", "HMM", "HMH", "HML",
                "HHM", "HHH", "HHL", "MLM", "MLH", "MLL",
                "MMM", "MMH", "MML", "MHM", "MHH", "MHL",
                "LLM", "LLH", "LLL", "LMM", "LMH", "LML",
                "LHM", "LHH", "LHL"
        )));

        routingConfig.put("HLL", new ArrayList<String>(Arrays.asList(
                "HLL", "HLM", "HLH", "HML", "HMM", "HMH",
                "HHL", "HHM", "HHH", "MLL", "MLM", "MLH",
                "MML", "MMM", "MMH", "MHL", "MHM", "MHH",
                "LLL", "LLM", "LLH", "LML", "LMM", "LMH",
                "LHL", "LHM", "LHH"
        )));

        routingConfig.put("MHH", new ArrayList<String>(Arrays.asList(
                "MHH", "MHM", "MHL", "MMH", "MMM", "MML",
                "MLH", "MLM", "MLL", "HHH", "HHM", "HHL",
                "HMH", "HMM", "HML", "HLH", "HLM", "HLL",
                "LHH", "LHM", "LHL", "LMH", "LMM", "LML",
                "LLH", "LLM", "LLL"
        )));

        routingConfig.put("MHM", new ArrayList<String>(Arrays.asList(
                "MHM", "MHH", "MHL", "MMM", "MMH", "MML",
                "MLM", "MLH", "MLL", "HHM", "HHH", "HHL",
                "HMM", "HMH", "HML", "HLM", "HLH", "HLL",
                "LHM", "LHH", "LHL", "LMM", "LMH", "LML",
                "LLM", "LLH", "LLL"
        )));

        routingConfig.put("MHL", new ArrayList<String>(Arrays.asList(
                "MHL", "MHM", "MHH", "MML", "MMM", "MMH",
                "MLL", "MLM", "MLH", "HHL", "HHM", "HHH",
                "HML", "HMM", "HMH", "HLL", "HLM", "HLH",
                "LHL", "LHM", "LHH", "LML", "LMM", "LMH",
                "LLL", "LLM", "LLH"
        )));

        routingConfig.put("MMH", new ArrayList<String>(Arrays.asList(
                "MMH", "MMM", "MML", "MHH", "MHM", "MHL",
                "MLH", "MLM", "MLL", "HMH", "HMM", "HML",
                "HHH", "HHM", "HHL", "HLH", "HLM", "HLL",
                "LMH", "LMM", "LML", "LHH", "LHM", "LHL",
                "LLH", "LLM", "LLL"
        )));

        routingConfig.put("MMM", new ArrayList<String>(Arrays.asList(
                "MMM", "MMH", "MML", "MHM", "MHH", "MHL",
                "MLM", "MLH", "MLL", "HMM", "HMH", "HML",
                "HHM", "HHH", "HHL", "HLM", "HLH", "HLL",
                "LMM", "LMH", "LML", "LHM", "LHH", "LHL",
                "LLM", "LLH", "LLL"
        )));

        routingConfig.put("MML", new ArrayList<String>(Arrays.asList(
                "MML", "MMM", "MMH", "MHL", "MHM", "MHH",
                "MLL", "MLM", "MLH", "HML", "HMM", "HMH",
                "HHL", "HHM", "HHH", "HLL", "HLM", "HLH",
                "LML", "LMM", "LMH", "LHL", "LHM", "LHH",
                "LLL", "LLM", "LLH"
        )));

        routingConfig.put("MLH", new ArrayList<String>(Arrays.asList(
                "MLH", "MLM", "MLL", "MMH", "MMM", "MML",
                "MHH", "MHM", "MHL", "HLH", "HLM", "HLL",
                "HMH", "HMM", "HML", "HHH", "HHM", "HHL",
                "LLH", "LLM", "LLL", "LMH", "LMM", "LML",
                "LHH", "LHM", "LHL"
        )));

        routingConfig.put("MLM", new ArrayList<String>(Arrays.asList(
                "MLM", "MLH", "MLL", "MMM", "MMH", "MML",
                "MHM", "MHH", "MHL", "HLM", "HLH", "HLL",
                "HMM", "HMH", "HML", "HHM", "HHH", "HHL",
                "LLM", "LLH", "LLL", "LMM", "LMH", "LML",
                "LHM", "LHH", "LHL"
        )));

        routingConfig.put("MLL", new ArrayList<String>(Arrays.asList(
                "MLL", "MLM", "MLH", "MML", "MMM", "MMH",
                "MHL", "MHM", "MHH", "HLL", "HLM", "HLH",
                "HML", "HMM", "HMH", "HHL", "HHM", "HHH",
                "LLL", "LLM", "LLH", "LML", "LMM", "LMH",
                "LHL", "LHM", "LHH"
        )));

        routingConfig.put("LHH", new ArrayList<String>(Arrays.asList(
                "LHH", "LHM", "LHL", "LMH", "LMM", "LML",
                "LLH", "LLM", "LLL", "MHH", "MHM", "MHL",
                "MMH", "MMM", "MML", "MLH", "MLM", "MLL",
                "HHH", "HHM", "HHL", "HMH", "HMM", "HML",
                "HLH", "HLM", "HLL"
        )));

        routingConfig.put("LHM", new ArrayList<String>(Arrays.asList(
                "LHM", "LHH", "LHL", "LMM", "LMH", "LML",
                "LLM", "LLH", "LLL", "MHM", "MHH", "MHL",
                "MMM", "MMH", "MML", "MLM", "MLH", "MLL",
                "HHM", "HHH", "HHL", "HMM", "HMH", "HML",
                "HLM", "HLH", "HLL"
        )));

        routingConfig.put("LHL", new ArrayList<String>(Arrays.asList(
                "LHL", "LHM", "LHH", "LML", "LMM", "LMH",
                "LLL", "LLM", "LLH", "MHL", "MHM", "MHH",
                "MML", "MMM", "MMH", "MLL", "MLM", "MLH",
                "HHL", "HHM", "HHH", "HML", "HMM", "HMH",
                "HLL", "HLM", "HLH"
        )));

        routingConfig.put("LMH", new ArrayList<String>(Arrays.asList(
                "LMH", "LMM", "LML", "LHH", "LHM", "LHL",
                "LLH", "LLM", "LLL", "MMH", "MMM", "MML",
                "MHH", "MHM", "MHL", "MLH", "MLM", "MLL",
                "HMH", "HMM", "HML", "HHH", "HHM", "HHL",
                "HLH", "HLM", "HLL"
        )));

        routingConfig.put("LMM", new ArrayList<String>(Arrays.asList(
                "LMM", "LMH", "LML", "LHM", "LHH", "LHL",
                "LLM", "LLH", "LLL", "MMM", "MMH", "MML",
                "MHM", "MHH", "MHL", "MLM", "MLH", "MLL",
                "HMM", "HMH", "HML", "HHM", "HHH", "HHL",
                "HLM", "HLH", "HLL"
        )));

        routingConfig.put("LML", new ArrayList<String>(Arrays.asList(
                "LML", "LMM", "LMH", "LHL", "LHM", "LHH",
                "LLL", "LLM", "LLH", "MML", "MMM", "MMH",
                "MHL", "MHM", "MHH", "MLL", "MLM", "MLH",
                "HML", "HMM", "HMH", "HHL", "HHM", "HHH",
                "HLL", "HLM", "HLH"
        )));

        routingConfig.put("LLH", new ArrayList<String>(Arrays.asList(
                "LLH", "LLM", "LLL", "LMH", "LMM", "LML",
                "LHH", "LHM", "LHL", "MLH", "MLM", "MLL",
                "MMH", "MMM", "MML", "MHH", "MHM", "MHL",
                "HLH", "HLM", "HLL", "HMH", "HMM", "HML",
                "HHH", "HHM", "HHL"
        )));

        routingConfig.put("LLM", new ArrayList<String>(Arrays.asList(
                "LLM", "LLH", "LLL", "LMM", "LMH", "LML",
                "LHM", "LHH", "LHL", "MLM", "MLH", "MLL",
                "MMM", "MMH", "MML", "MHM", "MHH", "MHL",
                "HLM", "HLH", "HLL", "HMM", "HMH", "HML",
                "HHM", "HHH", "HHL"
        )));

        routingConfig.put("LLL", new ArrayList<String>(Arrays.asList(
                "LLL", "LLM", "LLH", "LML", "LMM", "LMH",
                "LHL", "LHM", "LHH", "MLL", "MLM", "MLH",
                "MML", "MMM", "MMH", "MHL", "MHM", "MHH",
                "HLL", "HLM", "HLH", "HML", "HMM", "HMH",
                "HHL", "HHM", "HHH"
        )));

        return routingConfig;
    }

    public static HashMap<String, RountingModel> createRoutingTable(HashMap<String, ArrayList<PowerHost>> hostTypes) {

        HashMap<String, ArrayList<String>> routingConfiguration = getRoutingConfiguration();

        HashMap<String, RountingModel> routingTable = new HashMap<>();

        for (Map.Entry<String, ArrayList<String>> entry : routingConfiguration.entrySet()) {

            String key = entry.getKey();
            ArrayList<String> routingPathStrings = entry.getValue();

            ArrayList<PowerHost> routingPathHosts = new ArrayList<>();

            for (String type : routingPathStrings) {

                if (hostTypes.containsKey(type)) {
                    routingPathHosts.addAll(hostTypes.get(type));
                }
            }

            RountingModel rm = new RountingModel();
            rm.setHosts(routingPathHosts);
            rm.setNextHost(0);

            routingTable.put(key, rm);
        }

        return routingTable;
    }

    public static void createSimulationResults(List<Host> hosts) {

        try {
            PrintWriter pwC = new PrintWriter("D:\\thesis-results\\stats\\cpu.txt");
            PrintWriter pwR = new PrintWriter("D:\\thesis-results\\stats\\ram.txt");
            PrintWriter pwB = new PrintWriter("D:\\thesis-results\\stats\\bw.txt");
            List<Double> cpuUtilizations = new ArrayList<>();
            List<Double> ramUtilizations = new ArrayList<>();
            List<Double> bwUtilizations = new ArrayList<>();
            
            for (Host h : hosts) {
                PowerHost ph = (PowerHost) h;
                if (ph.getUtilizationOfCpu() != 0) {
                    
                    cpuUtilizations.add(ph.getUtilizationOfCpu());
                    ramUtilizations.add(ph.getUtilizationOfRam()/ph.getRam());
                    bwUtilizations.add(ph.getUtilizationOfBw()/ph.getBw());
                    
                    pwC.println("Host#" + ph.getId() + "\t\t" + ph.getUtilizationOfCpu());
                    pwR.println("Host#" + ph.getId() + "\t\t" + ph.getUtilizationOfRam()/ph.getRam());
                    pwB.println("Host#" + ph.getId() + "\t\t" + ph.getUtilizationOfBw()/ph.getBw());
                }
            }
            
            
            pwC.println(MathUtil.variance(cpuUtilizations));
            pwR.println(MathUtil.variance(ramUtilizations));
            pwB.println(MathUtil.variance(bwUtilizations));
            
            pwC.flush();
            pwR.flush();
            pwB.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
