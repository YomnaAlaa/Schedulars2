package schedulars2;

import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static schedulars2.Schedulars2.GantttChart;

public class SJF {

    static Schedulars2 ss = new Schedulars2();

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

    public static process getMinNumer(process x, process y) {
        if (x.duration < y.duration) {
            return x;
        } else {
            return y;
        }
    }

    public static process getMinDuration(List<process> pp, int n) {
        int min = pp.get(0).duration;
        process mm = new process();
        int i;
        for (i = 0; i < n; i++) {
            if (pp.get(i).duration <= min) {
                min = pp.get(i).duration;
                mm = pp.get(i);
            }
        }
        return mm;
    }

    public static int sum(List<process> pp, int n) {
        int summ = 0;
        for (int i = 0; i < n; i++) {
            summ += pp.get(i).duration;
        }
        return summ;
    }

    static List<process> temp = new ArrayList();

    static List<process> toBeShown = new ArrayList();

    public static void writeLabels() {
        for (int i = 0; i < toBeShown.size(); i++) {
            ss.rw.lblNames.setText(ss.rw.lblNames.getText() + toBeShown.get(i).name + "   ");
        }
        for (int i = 0; i < toBeShown.size(); i++) {
            ss.rw.lblDurations.setText(ss.rw.lblDurations.getText() + toBeShown.get(i).duration + "      ");
        }

    }

    public static int findIndexByDuration(List<process> pp, int m, int n) {
        for (int i = 0; i < n; i++) {
            if (m == pp.get(i).duration) {
                return i;
            }
        }
        return -1;
    }


    public static int NonPreemptiveSJF(List<process> pp, int n) {
        for (int i = 0; i < pp.size(); i++) {
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
        List<process> toBeSent = new ArrayList();
        Collections.sort(pp, process.getComparator(process.Parameter.duration));
        List<process> finished = new ArrayList();
        int counter = 0;
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
        int ssum = sum(pp, n);
        while (counter < ssum) {
            for (int i = 0; i < pp.size(); i++) {
                if (pp.get(i).whencome <= counter) {
                    finished.add(pp.get(i));
                    toBeSent.add(pp.get(i));
                    finished.get(finished.size() - 1).startTime = counter;
                    toBeSent.get(toBeSent.size() - 1).startTime = counter;
                    counter += pp.get(i).duration;
                    finished.get(finished.size() - 1).endTime = counter;
                    toBeSent.get(toBeSent.size() - 1).endTime = counter;
                    pp.remove(pp.get(i));
                    break;

                } else if (i == pp.size() - 1 && pp.get(i).whencome > counter) {
                    process ppp = new process();
                    ppp.setDuration(0);
                    ppp.setName("NOP");
                    ssum++;
                    counter++;
                    ppp.setendTime(counter);
                    toBeSent.add(ppp);
                }
            }
        }
        GantttChart(toBeSent, toBeSent.size(), 3);
        double sum = 0, processWaitingTime;
        for (int i = 0; i < finished.size(); i++) {
            processWaitingTime = abs(finished.get(i).startTime - finished.get(i).whencome);
            sum += processWaitingTime;
        }
        System.out.println("average waiting time" + (sum / finished.size()));
        ss.rw.lblWaiting.setText((sum / finished.size()) + "");
        writeLabels();
        return 3;
    }

    public static boolean swap(process x, process y) {
        process temp = x;
        x = y;
        y = temp;
        return true;
    }

    public static int getMaxDuration(List<process> arr, int n) {
        int max = arr.get(0).duration;
        int i;
        for (i = 0; i < n; i++) {
            if (arr.get(i).duration > max) {
                max = arr.get(i).duration;
            }
        }
        return max;
    }

    public static int PreemptiveSJF(List<process> pp, int n) {
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
        Collections.sort(pp, process.getComparator(process.Parameter.duration));
        List<process> finished = new ArrayList();
        int counter = 0;
        int ssum = sum(pp, n);
        int duration = getMaxDuration(pp, pp.size()) + 1;
        int index = -1;
        List<process> toBeSent = new ArrayList<process>();
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
        int cc = 0;
        while (counter < ssum) {
            cc = counter;
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

                if (pp.get(i).whencome <= counter && pp.get(i).duration <= duration) {
                    duration = pp.get(i).duration;
                    index = pp.get(i).index;

                    if (finished.size() == 0) {
                        finished.add(p);
                        p1.setDuration(1);
                        toBeSent.add(p1);
                    } else if (!finished.get(finished.size() - 1).name.equals(pp.get(i).name)) {
                        if (pp.get(i).duration == finished.get(finished.size() - 1).duration && finished.get(finished.size() - 1).duration == 0) {
                            finished.add(p);
                            p1.setDuration(1);
                            toBeSent.add(p1);
                        } else if (pp.get(i).duration < finished.get(finished.size() - 1).duration) {
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

                } else if (pp.get(i).whencome <= counter && pp.get(i).duration > duration && pp.get(0) == pp.get(i)) {
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
                }
//                 else if (toBeSent.size()>0 && toBeSent.get(toBeSent.size()-1).name.equals("NOP")) {
//                    finished.add(p);
//                    p1.setDuration(1);
//                    toBeSent.add(p1);
//                    counter++;
//                    pp.get(i).duration--;
//                    toBeSent.get(toBeSent.size() - 1).setendTime(counter);
//                }
//                else if (i == pp.size() - 1 && pp.get(i).whencome > counter) {
//                    if (toBeSent.size()>0 && (toBeSent.get(toBeSent.size() - 1).name.equals(pp.get(toBeSent.get(toBeSent.size() - 1).index).name) &&pp.get(toBeSent.get(toBeSent.size() - 1).index).duration > 0)) {
//                        break;
//                    } else {
//                        process ppp = new process();
//                        ppp.setDuration(0);
//                        ppp.setName("NOP");
//                        ppp.setStartTime(counter);
//                        ppp.setWhenCome(counter);
//                        ssum++;
//                        counter++;
//                        ppp.setendTime(counter);
//                        //finished.add(ppp);
//                        toBeSent.add(ppp);
//                    }
//                }


                if (pp.get(i).duration == 0) {
                    temp.get(pp.get(i).index).endTime = counter;
                    pp.remove(pp.get(i));
                }
            }
//            if (counter == cc) {
//                process ppp = new process();
//                ppp.setDuration(0);
//                ppp.setName("NOP");
//                ppp.setStartTime(counter);
//                ppp.setWhenCome(counter);
//                ssum++;
//                counter++;
//                ppp.setendTime(counter);
//                toBeSent.add(ppp);
//            }
        }

        double sum = 0;
        GantttChart(toBeSent, toBeSent.size(), 2);
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
        return 2;
    }


    int i = 0;
}