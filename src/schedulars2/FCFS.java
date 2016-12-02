/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schedulars2;

import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static schedulars2.Schedulars2.GantttChart;

/**
 *
 * @author YOMNA
 */
public class FCFS {

    //    public static void FCFS(int[] duration, int[] whencome, int n) {
//        LinkedList process = new LinkedList();
//        int[] index = new int[n];
//        int min;
//        int max = getMax(whencome, n);
//        for (int i = 0; i < n; i++) {
//            min = getMinIndex(whencome, n);
//            int ff = duration[min];
//            process.addLast(ff);
//            index[i] = min + 1;
//            whencome[min] += (max + 1);
//        }
//        GantttChart(process, index, n);
//    }
    // static resultWindow rf;
    public static int sum(List<process> pp, int n) {
        int summ = 0;
        for (int i = 0; i < n; i++) {
            summ += pp.get(i).duration;
        }
        return summ;
    }
    static Schedulars2 ss = new Schedulars2();
    
    static List<process> temp = new ArrayList<process>();
    public static void writeLabels(){
    for (int i = 0; i < temp.size(); i++) {
            ss.rw.lblNames.setText(ss.rw.lblNames.getText() + temp.get(i).name + "   ");
        }
        for (int i = 0; i < temp.size(); i++) {
            ss.rw.lblDurations.setText(ss.rw.lblDurations.getText() + temp.get(i).duration + "   ");
        }
        
    }

    public static int FCFS(List<process> pp, int n) {
        int counter1 = 0;
        int ssum = sum(pp, n);
        int j = 0;
        List<process> finished = new ArrayList();
        for (int i = 0; i < n; i++) {
            int d = pp.get(i).duration;
            int a = pp.get(i).whencome;
            int p = pp.get(i).priority;
            int in = pp.get(i).index;
            String na = pp.get(i).name;
            process o = new process();
            o.setDuration(d);
            o.setWhenCome(a);
            o.setPriotrity(p);
            o.setName(na);
            o.setInedex(in);

            temp.add(o);
        }
        Collections.sort(pp, process.getComparator(process.Parameter.whencome));
        while (counter1 < ssum && j < pp.size()) {

            if (pp.get(j).whencome > counter1) {
                process ppp = new process();
                ppp.setDuration(0);
                ppp.setName("NOP");
                ppp.setStartTime(counter1);
                ppp.setWhenCome(counter1);
                ssum++;
                counter1++;
                ppp.setendTime(counter1);
                finished.add(ppp);
            } else {
                finished.add(pp.get(j));
                counter1 += pp.get(j).duration;
                pp.get(j).endTime = counter1;
                j++;
            }
        }
//        int counter = 0;
//        for (int i = 0; i < pp.size(); i++) {
//            counter += pp.get(i).duration;
//            pp.get(i).endTime = counter;
//        }
        GantttChart(finished, finished.size(), 1);

        int sum = 0;
        pp.get(0).startTime = pp.get(0).whencome;
        for (int i = 1; i < pp.size(); i++) {
            pp.get(i).startTime = pp.get(i - 1).startTime + pp.get(i - 1).duration;
        }
        int processWaitingTime;
        for (int i = 0; i < pp.size(); i++) {
            processWaitingTime = abs(pp.get(i).startTime - pp.get(i).whencome);
            sum += processWaitingTime;
        }
        System.out.println("average waiting time" + (sum / pp.size()));
        ss.rw.lblWaiting.setText((sum / pp.size()) + "");
        writeLabels();
        return 1;
    }
}
