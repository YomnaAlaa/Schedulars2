
package schedulars2;

import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static schedulars2.Schedulars2.GantttChart;

public class FCFS {

    public static int sum(List<process> pp, int n) {
        int summ = 0;
        for (int i = 0; i < n; i++) {
            summ += pp.get(i).duration;
        }
        return summ;
    }
    static Schedulars2 ss = new Schedulars2();
    
    static List<process> temp = new ArrayList<process>();
    static List<process> toBeShown = new ArrayList<process>();
    public static void writeLabels(){
    for (int i = 0; i < toBeShown.size(); i++) {
            ss.rw.lblNames.setText(ss.rw.lblNames.getText() + toBeShown.get(i).name + "   ");
        }
        for (int i = 0; i < toBeShown.size(); i++) {
            ss.rw.lblDurations.setText(ss.rw.lblDurations.getText() + toBeShown.get(i).duration + "      ");
        }
        
    }

    public static int FCFS(List<process> pp, int n) {
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
        GantttChart(finished, finished.size(), 1);

        double sum = 0;
        pp.get(0).startTime = pp.get(0).whencome;
        for (int i = 1; i < pp.size(); i++) {
            pp.get(i).startTime = pp.get(i - 1).startTime + pp.get(i - 1).duration;
        }
        double processWaitingTime;
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
