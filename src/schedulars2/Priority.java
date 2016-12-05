package schedulars2;

import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import static schedulars2.Schedulars2.GantttChart;

public class Priority {

    static Schedulars2 ss = new Schedulars2();
    Timer TT = new Timer();

    public static process getHighestPriority(List<process> pp, int n) {
        int min = pp.get(0).priority;
        process mm = new process();
        int i;
        for (i = 0; i < n; i++) {
            if (pp.get(i).priority <= min) {
                min = pp.get(i).priority;
                mm = pp.get(i);
            }
        }
        return mm;
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

    public static int getMaxPriority(List<process> arr, int n) {
        int max = arr.get(0).priority;
        int i;
        for (i = 0; i < n; i++) {
            if (arr.get(i).priority > max) {
                max = arr.get(i).priority;
            }
        }
        return max;
    }

    public static boolean swap(process x, process y) {
        process temp = x;
        x = y;
        y = temp;
        return true;
    }

    public static int sum(List<process> pp, int n) {
        int summ = 0;
        for (int i = 0; i < n; i++) {
            summ += pp.get(i).duration;
        }
        return summ;
    }

    public static int findIndexByPriority(List<process> pp, int m, int n) {
        for (int i = 0; i < n; i++) {
            if (m == pp.get(i).priority) {
                return i;
            }
        }
        return -1;
    }

    static List<process> temp = new ArrayList<process>();
    static List<process> toBeShown = new ArrayList<process>();

    public static void writeLabels() {
        for (int i = 0; i < toBeShown.size(); i++) {
            ss.rw.lblNames.setText(ss.rw.lblNames.getText() + toBeShown.get(i).name + "   ");
        }
        for (int i = 0; i < toBeShown.size(); i++) {
            ss.rw.lblDurations.setText(ss.rw.lblDurations.getText() + toBeShown.get(i).duration + "      ");
        }
        for (int i = 0; i < toBeShown.size(); i++) {
            ss.rw.lblPriorities.setText(ss.rw.lblPriorities.getText() + toBeShown.get(i).priority + "      ");
        }

    }

    public static int NonPreemptivePriority(List<process> pp, int n) {
        toBeShown = new ArrayList();
        for (int i = 0; i < pp.size(); i++) {
//            toBeShown = new ArrayList();
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
        Collections.sort(pp, process.getComparator(process.Parameter.priority));
        List<process> finished = new ArrayList();
        int counter = 0;
        List<process> temp1 = new ArrayList();
        int ssum = sum(pp, n);
        temp = new ArrayList();
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
        while (counter < ssum) {
            for (int i = 0; i < pp.size(); i++) {
                if (pp.get(i).whencome <= counter) {
                    finished.add(pp.get(i));
                    finished.get(finished.size() - 1).startTime = counter;
                    counter += pp.get(i).duration;
                    finished.get(finished.size() - 1).endTime = counter;
                    pp.remove(pp.get(i));
                    break;
                } else if (i == pp.size() - 1 && pp.get(i).whencome > counter) {
                    int pr = getMaxPriority(pp, pp.size());
                    process ppp = new process();
                    ppp.setDuration(0);
                    ppp.setName("NOP");
                    ppp.setStartTime(counter);
                    ppp.setWhenCome(counter);
                    ppp.setPriotrity(pr + 1);
                    ssum++;
                    counter++;
                    ppp.setendTime(counter);
                    finished.add(ppp);
                }
            }
        }
        GantttChart(finished, finished.size(), 5);
        double sum = 0, processWaitingTime;
        for (int i = 0; i < finished.size(); i++) {
            processWaitingTime = abs(finished.get(i).startTime - finished.get(i).whencome);
            sum += processWaitingTime;
        }
        System.out.println("average waiting time" + (double) (sum / finished.size()));
        ss.rw.lblWaiting.setText((sum / n) + "");
        writeLabels();
        return 5;
    }
    int cc = 0;
    public static int PreemptivePriority(List<process> pp, int n) {
        
        toBeShown = new ArrayList();
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
        Collections.sort(pp, process.getComparator(process.Parameter.priority));
        List<process> finished = new ArrayList();
        int counter = 0;
        int ssum = sum(pp, n);
        int priority = getMaxPriority(pp, pp.size()) + 1;
        int index = -1;

        List<process> toBeSent = new ArrayList<process>();
        temp = new ArrayList();
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
        while (counter < ssum) {
            for (int i = 0; i < pp.size(); i++) {
                process p = new process();
                p.setDuration(pp.get(i).duration);
                p.setInedex(pp.get(i).index);
                p.setName(pp.get(i).name);
                p.setPriotrity(pp.get(i).priority);
                p.setStartTime(pp.get(i).startTime);
                p.setWhenCome(pp.get(i).whencome);

                process p1 = new process();
                p1.setDuration(pp.get(i).duration);
                p1.setInedex(pp.get(i).index);
                p1.setName(pp.get(i).name);
                p1.setPriotrity(pp.get(i).priority);
                p1.setStartTime(pp.get(i).startTime);
                p1.setWhenCome(pp.get(i).whencome);

                if (pp.get(i).whencome <= counter && pp.get(i).priority <= priority) {
                    priority = pp.get(i).priority;
                    index = pp.get(i).index;
                    if (finished.size() == 0) {
                        finished.add(p);
                        p1.setDuration(1);
                        toBeSent.add(p1);
                    } else if (!finished.get(finished.size() - 1).name.equals(pp.get(i).name)) {
                        if (pp.get(i).priority == finished.get(finished.size() - 1).priority && finished.get(finished.size() - 1).duration == 0) {
                            finished.add(p);
                            p1.setDuration(1);
                            toBeSent.add(p1);
                        } else if (pp.get(i).priority < finished.get(finished.size() - 1).priority) {
                            finished.add(p);
                            p1.setDuration(1);
                            toBeSent.add(p1);
                        }
                    } else if (finished.get(finished.size() - 1).name.equals(pp.get(i).name)) {
                        toBeSent.get(toBeSent.size() - 1).duration++;
                    }
                    counter++;
                    pp.get(i).duration--;
                    toBeSent.get(toBeSent.size() - 1).setendTime(counter);

                } else if (pp.get(i).whencome <= counter && pp.get(i).priority > priority && pp.get(0) == pp.get(i)) {
                    if (!finished.get(finished.size() - 1).name.equals(pp.get(i).name)) {
                        finished.add(p);
                        p1.setDuration(1);
                        toBeSent.add(p1);
                    } else if (finished.get(finished.size() - 1).name.equals(pp.get(i).name)) {
                        toBeSent.get(toBeSent.size() - 1).duration++;
                    }
                    counter++;
                    pp.get(i).duration--;
                    toBeSent.get(toBeSent.size() - 1).setendTime(counter);
                } else if (i == pp.size() - 1 && pp.get(i).whencome > counter) {
                    if (toBeSent.size() > 0 && (toBeSent.get(toBeSent.size() - 1).name.equals(pp.get(toBeSent.get(toBeSent.size() - 1).index).name) && pp.get(toBeSent.get(toBeSent.size() - 1).index).duration > 0)) {
                        break;
                    } else {
                        process ppp = new process();
                        ppp.setDuration(0);
                        ppp.setName("NOP");
                        ppp.setStartTime(counter);
                        ppp.setWhenCome(counter);
                        ssum++;
                        counter++;
                        ppp.setendTime(counter);
                        //finished.add(ppp);
                        toBeSent.add(ppp);
                    }
                }
//                }else if(i == pp.size()-1 && pp.get(i).whencome>counter){
//                        int d = 0;
//                        int a = counter;
//                        int p = 0;
//                        int in = -1;
//                        String na ="NOP";
//                        process o = new process();
//                        o.setDuration(d);
//                        o.setWhenCome(a);
//                        o.setPriotrity(p);
//                        o.setName(na);
//                        o.setInedex(in);
//                        finished.add(o);
//                        counter++;
//                }
                if (pp.get(i).duration == 0) {
                    temp.get(pp.get(i).index).endTime = counter;
                    pp.remove(pp.get(i));

                }
            }
        }
        double sum = 0;
        GantttChart(toBeSent, toBeSent.size(), 4);
        for (int i = 0; i < n; i++) {
            int mm = temp.get(i).endTime;
            int nn = temp.get(i).duration;
            process p = temp.get(i);
            double waitingTime = mm - nn - temp.get(i).whencome;
            sum += waitingTime;
        }
        System.out.println("Average Waiting Time= " + (sum / n));
        ss.rw.lblWaiting.setText((sum / n) + "");
        writeLabels();
        return 4;
    }
}
