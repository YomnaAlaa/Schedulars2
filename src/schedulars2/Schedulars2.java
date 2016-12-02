package schedulars2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import javax.swing.*;

public class Schedulars2 {

//    public static void GantttChart(LinkedList processDuration, int[] index, int n) {
//
//        for (int i = 0; i < n; i++) {
//            System.out.print("P".concat((index[i]) + ""));
//            for (int j = 0; j < (int) processDuration.getFirst(); j++) {
//                System.out.print(" ");
//            }
//            processDuration.pop();
//            System.out.print("|");
//        }
//
//    }
    public static FCFS fs = new FCFS();
    public static Priority pr = new Priority();
    public static SJF sj = new SJF();
    public static RoundRoubin rr = new RoundRoubin();
    static resultWindow rw;

//    public static void GantttChart(List<process> pp, int n) {
//        
//        rw.setVisible(true);
//        rw.lblGantt.setText("");
//        for (int i = 0; i < n; i++) {
//            System.out.print(pp.get(i).name + "");
//            rw.lblGantt.setText(rw.lblGantt.getText() + pp.get(i).name + "");
//            for (int j = 0; j < (int) pp.get(i).duration; j++) {
//                System.out.print(" ");
//                rw.lblGantt.setText(rw.lblGantt.getText() + " ");
//            }
//            System.out.print("|");
//            rw.lblGantt.setText(rw.lblGantt.getText() + "|");
//        }
//
//    }
    public static void GantttChart(List<process> pp, int n, int index) {
        List<String> processes = new ArrayList();
        List<String> timer = new ArrayList();
        rw = new resultWindow(index);
        rw.setVisible(true);
        processes.add("|");
        for (int i = 0; i < n; i++) {
            processes.add(rw.lblGantt.getText() + pp.get(i).name);
            for (int j = 0; j < (int) pp.get(i).duration; j++) {
                processes.add(" ");
            }
            processes.add("|");
        }
        int counter = 0;
        timer.add("0");
        for (int i = 1; i < processes.size(); i++) {
            if (processes.get(i).equals("|")) {
                timer.add(pp.get(counter).endTime + "");
                counter++;
            } else if (processes.get(i).startsWith("P") || processes.get(i).startsWith("p")) {
                timer.add(" " + " ");
            }else if (processes.get(i).contains("NOP")){
                timer.add(" " + " "+" "+" "+" ");
            } 
            else {
                timer.add(" ");
            }
        }
        rw.lblGantt.setText("");
        rw.lblTimer.setText("");
        for (int i = 0; i < processes.size(); i++) {
            rw.lblGantt.setText(rw.lblGantt.getText() + processes.get(i));
            rw.lblTimer.setText(rw.lblTimer.getText() + timer.get(i));
        }

    }
    
    

    public static int getMinNum(int[] arr, int n) {
        int min = arr[0];
        int i;
        for (i = 0; i < n; i++) {
            if (arr[i] < min) {
                min = arr[i];
            }
        }
        return min;
    }

    public static int getMinNumer(int x, int y) {
        if (x < y) {
            return x;
        } else {
            return y;
        }
    }

    public static int getMax(List<process> arr, int n) {
        int max = arr.get(0).duration;
        int i;
        for (i = 0; i < n; i++) {
            if (arr.get(i).duration > max) {
                max = arr.get(i).duration;
            }
        }
        return max;
    }

    public static int sum(List<process> pp, int n) {
        int summ = 0;
        for (int i = 0; i < n; i++) {
            summ += pp.get(i).duration;
        }
        return summ;
    }

    /*====================================================================GLOBAL VARIABLES==================================================================*/
    static int q = 0;
    static int i = 0;
    static process ppp = new process();
    static List<process> p = new ArrayList();
    //static MyFrame ff = new MyFrame();
    static StartWindow sw = new StartWindow();
    static Scanner sc = new Scanner(System.in);
    static int type;
    static int index;
    /*====================================================================GLOBAL VARIABLES==================================================================*/

    public static void main(String[] args) {

        sw.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        sw.setVisible(true);

        System.out.println("Enter the schedular Number: 1-FCFS \n 2-Nonpreemptive SJF \n 3-Preemptive SJF \n 4-Nonpreemptive Priority \n 5- Preemptive Pririty \n 6-Round Robin");
        //type = sc.nextInt();
        System.out.println("Enter the number of processes");
        //int n = sc.nextInt();
        /*-----------------------------------------------------------------------------------------------------------------------------------------------------*/
        /*not used for now*/
//        

        /*-----------------------------------------------------------------------------------------------------------------------------------------------------*/
//        int [] Duration = new int [n];
//        int [] priority = new int[n];
//        int [] whenCome = new int [n];
//        System.out.println("Enter the Duration of each process");
//        for (int i=0;i<n;i++){
//            Duration[i] = sc.nextInt();
//        }
//        System.out.println("Enter the time this process come");
//        for (int i=0;i<n;i++){
//            whenCome[i] = sc.nextInt();
//        }
//        if (type.startsWith("Priority")){
//        System.out.println("Enter the priority of each process");
//        for (int i=0;i<n;i++){
//            priority[i] = sc.nextInt();
//        }
//        }
        int Duration[] = {8, 3, 2, 5};
        int[] whenCome = {0, 4, 3, 2};
        int[] priority = {4, 3, 2, 1};

        // FCFS(Duration, whenCome, n);
        //NonpreemptiveSJF(Duration, whenCome, n);
        List<process> processes = new ArrayList<process>();
        process pp = new process();
        process pp1 = new process();
        process pp2 = new process();
        process pp3 = new process();
        process pp4 = new process();
        process pp5 = new process();
        process pp6 = new process();
        pp.setName("P1");
        pp.setDuration(8);
        pp.setWhenCome(0);
        pp.setPriotrity(4);
        processes.add(pp);

        pp1.setName("P2");
        pp1.setDuration(3);
        pp1.setWhenCome(4);
        pp1.setPriotrity(7);
        processes.add(pp1);

        pp2.setName("P3");
        pp2.setDuration(2);
        pp2.setWhenCome(3);
        pp2.setPriotrity(5);
        processes.add(pp2);

        pp3.setName("P4");
        pp3.setDuration(5);
        pp3.setWhenCome(2);
        pp3.setPriotrity(3);
        processes.add(pp3);
//        pp4.setName("P5");
//        pp4.setDuration(2);
//        pp4.setWhenCome(2);
//        pp4.setPriotrity(1);
//        processes.add(pp4);
//
//        pp5.setName("P6");
//        pp5.setDuration(10);
//        pp5.setWhenCome(1);
//        pp5.setPriotrity(3);
//        processes.add(pp5);
//
//        pp6.setName("P7");
//        pp6.setDuration(1);
//        pp6.setWhenCome(9);
//        pp6.setPriotrity(6);
//        processes.add(pp6);
//        int q = 2;

        process p0 = new process();
        //p0.SortArrivalTime(processes);
//        for (int i=0;i<processes.size();i++){
//            System.out.print(processes.get(i).name+" ");
//        }
    }

}
