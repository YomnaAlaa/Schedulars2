/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schedulars2;

import static java.lang.Math.abs;
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
    static Schedulars2 ss = new Schedulars2();

    
    public static void FCFS(List<process> pp, int n) {
        Collections.sort(pp, process.getComparator(process.Parameter.whencome));
        int counter =0;
        for (int i=0;i<pp.size();i++){
            counter += pp.get(i).duration;
            pp.get(i).endTime = counter;
        }
        GantttChart(pp, pp.size());
        
        int sum = 0;
        pp.get(0).startTime = pp.get(0).whencome;
        for (int i=1;i<pp.size();i++){
            pp.get(i).startTime = pp.get(i-1).startTime + pp.get(i-1).duration;
        }
        int processWaitingTime;
        for (int i=0;i<pp.size();i++){
           processWaitingTime = abs(pp.get(i).startTime - pp.get(i).whencome);
           sum+=processWaitingTime;
        }
        System.out.println("average waiting time" + (sum/pp.size()));
        ss.rw.lblWaiting.setText((sum/pp.size())+"");
    }
}
