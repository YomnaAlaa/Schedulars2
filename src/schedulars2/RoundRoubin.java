package schedulars2;

import static java.lang.Math.abs;
import java.util.*;
import static schedulars2.Schedulars2.GantttChart;

public class RoundRoubin {

    static Schedulars2 ss = new Schedulars2();

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

    public static int getIndex(List<process> pp, process p) {
        for (int j = 0; j < pp.size(); j++) {
            if (pp.get(j) == p) {
                return j;
            }
        }
        return -1;
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
    static List<process> temp = new ArrayList<process>();
    
    static List<process> toBeShown = new ArrayList<process>();
    
    public static void writeLabels(int q){
    for (int i = 0; i < toBeShown.size(); i++) {
            ss.rw.lblNames.setText(ss.rw.lblNames.getText() + toBeShown.get(i).name + "   ");
        }
        for (int i = 0; i < toBeShown.size(); i++) {
            ss.rw.lblDurations.setText(ss.rw.lblDurations.getText() + toBeShown.get(i).duration + "      ");
        }
            ss.rw.lblQ.setText(q + "");
        
    }

    public static int RoundRobin(List<process> pp, int n, int q) {
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

            toBeShown.add(o);
        }
        int summ = sum(pp, n);
        int counter = 0;
        List<process> li = new ArrayList<process>();
        List<process> temp = new ArrayList<process>();
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
        double sum = 0;
        double averageWaitingTime = 0;
        double waitingTime = 0;

        while (summ > counter) {
            for (int i = 0; i < pp.size(); i++) {
                process p = new process();
                p.setDuration(pp.get(i).duration);
                p.setInedex(pp.get(i).index);
                p.setName(pp.get(i).name);
                p.setPriotrity(pp.get(i).priority);
                p.setStartTime(pp.get(i).startTime);
                p.setWhenCome(pp.get(i).whencome);
                
                int du;
                if (p.duration <= q) {
                    du = p.duration;
                    li.add(p);
                    counter += p.duration;
                    pp.get(i).duration = 0;
                    li.get(li.size() - 1).setDuration(du);
                    li.get(li.size() - 1).setendTime(counter);
                    temp.get(pp.get(i).index).endTime = counter;

                } else {
                    du=q;
                    pp.get(i).duration -= q;
                    li.add(p);
                    counter += q;
                    li.get(li.size() - 1).setDuration(du);
                    li.get(li.size() - 1).setendTime(counter);

                }
            }
            for (int i = 0; i < pp.size(); i++) {
                if (pp.get(i).duration == 0) {
                    pp.remove(pp.get(i));
                    i--;
                }
            }

        }
        for (int i = 0; i < n; i++) {
            int mm = temp.get(i).endTime;
            int nn = temp.get(i).duration;
            process p = temp.get(i);
            waitingTime = mm - nn;
            sum += waitingTime;
        }

        GantttChart(li, li.size(),6);
        System.out.println("Average Waiting Time= " + (sum / n));
        ss.rw.lblWaiting.setText((sum / n) + "");
        writeLabels(q);
        return 6;
    }
}
