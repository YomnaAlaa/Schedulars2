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

    public static FCFS fs = new FCFS();
    public static Priority pr = new Priority();
    public static SJF sj = new SJF();
    public static RoundRoubin rr = new RoundRoubin();
    static resultWindow rw;

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
    static StartWindow sw = new StartWindow();
    static Scanner sc = new Scanner(System.in);
    static int type;
    static int index;
    /*====================================================================GLOBAL VARIABLES==================================================================*/

    public static void main(String[] args) {

        sw.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        sw.setVisible(true);

        System.out.println("Enter the schedular Number: 1-FCFS \n 2-Nonpreemptive SJF \n 3-Preemptive SJF \n 4-Nonpreemptive Priority \n 5- Preemptive Pririty \n 6-Round Robin");
        System.out.println("Enter the number of processes");

    }

}
