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

    public static int findIndexByDuration(List<process> pp, int m, int n) {
        for (int i = 0; i < n; i++) {
            if (m == pp.get(i).duration) {
                return i;
            }
        }
        return -1;
    }

//    public static void NonPreemptiveSJF(List<process> pp, int n) {
//        Collections.sort(pp, process.getComparator(process.Parameter.whencome));
//        List<process> ppp = new ArrayList<process>();
//
//        int counter = 0;
//        int summ = sum(pp, pp.size());
//        for (int i = 0; i < pp.size() - 1 || counter < summ;) {
//            if (counter == summ) {
//                break;
//            }
//            if (pp.get(0).whencome<=counter){
//            process ppq = getMinDuration(pp, pp.size());
//            if (pp.size() == 1) {
//                pp.get(0).startTime = counter;
//                ppp.add(pp.get(0));
//                counter += pp.get(0).duration;
//                pp.remove(pp.get(0));
//            } else if (pp.get(0).whencome == 0 && pp.get(1).whencome != 0) {
//                pp.get(0).startTime = counter;
//                ppp.add(pp.get(0));
//                counter += pp.get(0).duration;
//                pp.remove(pp.get(0));
//                i++;
//
//            } else if (pp.get(0).whencome == pp.get(1).whencome) {
//                
//                process minn = getMinNumer(pp.get(0), pp.get(1));
//                int dd = findIndexByDuration(pp, minn.duration, pp.size());
//                pp.get(dd).setStartTime(counter);
//                ppp.add(pp.get(dd));
//                counter += pp.get(dd).duration;
//                pp.remove(minn);
//                i++;
//
//            } else if (ppq.whencome <= counter) {
//                pp.get(0).startTime = counter;
//                ppp.add(ppq);
//                counter += ppq.duration;
//                pp.remove(ppq);
//            } else if (!(ppq.whencome <= counter)) {
//                process temp = ppq;
//                pp.remove(ppq);
//                for (int f = 0; f < pp.size(); f++) {
//                    process pps = getMinDuration(pp, pp.size());
//                    if (pps.whencome <= counter) {
//                        ppp.add(pps);
//                        counter += pps.duration;
//                        pp.remove(pps);
//                        break;
//                    }
//                }
//                pp.get(0).startTime = counter;
//                pp.add(temp);
//
//            } else {
//                //pp.remove(pp.get(i));
//                pp.get(i).startTime = counter;
//                pp.add(pp.get(i));
//                i++;
//            }
//            }
//        }
//        GantttChart(ppp, n);
//        int sum =0, processWaitingTime;
//        for (int i=0;i<pp.size();i++){
//           processWaitingTime = abs(pp.get(i).startTime - pp.get(i).whencome);
//           sum+=processWaitingTime;
//        }
//        System.out.println("average waiting time" + (sum/n));
//    }
    public static void NonPreemptiveSJF(List<process> pp, int n) {
        ss.rw.setVisible(true);
        Collections.sort(pp, process.getComparator(process.Parameter.duration));
        List<process> finished = new ArrayList();
        int counter = 0;
        List<process> temp1 = new ArrayList();
        int ssum = sum(pp, n);
        while (counter < ssum) {
            for (int i = 0; i < pp.size(); i++) {
                if (pp.get(i).whencome <= counter) {
                    finished.add(pp.get(i));
                    finished.get(finished.size() - 1).startTime = counter;
                    counter += pp.get(i).duration;
                    finished.get(finished.size() - 1).endTime = counter;
                    pp.remove(pp.get(i));
                    break;
                }
            }
        }
        GantttChart(finished, finished.size());
        int sum = 0, processWaitingTime;
        for (int i = 0; i < finished.size(); i++) {
            processWaitingTime = abs(finished.get(i).startTime - finished.get(i).whencome);
            sum += processWaitingTime;
        }
        System.out.println("average waiting time" + (sum / finished.size()));
        ss.rw.lblWaiting.setText((sum / finished.size()) + "");
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

    public static void PreemptiveSJF(List<process> pp, int n) {
        ss.rw.setVisible(true);
        Collections.sort(pp, process.getComparator(process.Parameter.duration));
        List<process> finished = new ArrayList();
        int counter = 0;
        //List<process> temp1 = new ArrayList();
        //process temp;
        int ssum = sum(pp, n);
        int duration = getMaxDuration(pp, pp.size()) + 1;
        int index = -1;
        List<process> temp = new ArrayList<process>();

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

                if (pp.get(i).whencome <= counter && pp.get(i).duration <= duration) {
                    duration = pp.get(i).duration;
                    index = pp.get(i).index;
                    if (finished.size() == 0) {
//                        int d = pp.get(i).duration;
//                            int a = pp.get(i).whencome;
//                            int p = pp.get(i).priority;
//                            int in = pp.get(i).index;
//                            String na = pp.get(i).name;
//                            process o = new process();
//                            o.setDuration(d);
//                            o.setWhenCome(a);
//                            o.setPriotrity(p);
//                            o.setName(na);
//                            o.setInedex(in);
//                            finished.add(o);
                        finished.add(p);
                        p1.setDuration(1);
                        //p1.setendTime(counter);
                        toBeSent.add(p1);
                    } else if (!finished.get(finished.size() - 1).name.equals(pp.get(i).name)) {
                        if (pp.get(i).duration == finished.get(finished.size() - 1).duration && finished.get(finished.size() - 1).duration == 0) {
//                            int d = pp.get(i).duration;
//                            int a = pp.get(i).whencome;
//                            int p = pp.get(i).priority;
//                            int in = pp.get(i).index;
//                            String na = pp.get(i).name;
//                            process o = new process();
//                            o.setDuration(d);
//                            o.setWhenCome(a);
//                            o.setPriotrity(p);
//                            o.setName(na);
//                            o.setInedex(in);
//                            finished.add(o);
                            finished.add(p);
                            p1.setDuration(1);
                            //p1.setendTime(counter);
                            toBeSent.add(p1);
                        } else if (pp.get(i).duration < finished.get(finished.size() - 1).duration) {
//                            int d = pp.get(i).duration;
//                            int a = pp.get(i).whencome;
//                            int p = pp.get(i).priority;
//                            int in = pp.get(i).index;
//                            String na = pp.get(i).name;
//                            process o = new process();
//                            o.setDuration(d);
//                            o.setWhenCome(a);
//                            o.setPriotrity(p);
//                            o.setName(na);
//                            o.setInedex(in);
//                            finished.add(o);
                            finished.add(p);
                            p1.setDuration(1);
                       // p1.setendTime(counter);
                        toBeSent.add(p1);
                        }
                    }else if (finished.get(finished.size() - 1).name.equals(pp.get(i).name)){
                        toBeSent.get(toBeSent.size()-1).duration++;
                        //toBeSent.get(toBeSent.size()-1).endTime = counter;
                    }
                    counter++;
                    pp.get(i).duration--;
                    toBeSent.get(toBeSent.size()-1).setendTime(counter);

                } else if (pp.get(i).whencome <= counter && pp.get(i).duration > duration && pp.get(0) == pp.get(i)) {
                    if (!finished.get(finished.size() - 1).name.equals(pp.get(i).name)) {
//                        int d = pp.get(i).duration;
//                        int a = pp.get(i).whencome;
//                        int p = pp.get(i).priority;
//                        int in = pp.get(i).index;
//                        String na = pp.get(i).name;
//                        process o = new process();
//                        o.setDuration(d);
//                        o.setWhenCome(a);
//                        o.setPriotrity(p);
//                        o.setName(na);
//                        o.setInedex(in);
//                        finished.add(o);
                        finished.add(p);
                        p1.setDuration(1);
                        //p1.setendTime(counter);
                        toBeSent.add(p1);
                    }else if (finished.get(finished.size() - 1).name.equals(pp.get(i).name)){
                        toBeSent.get(toBeSent.size()-1).duration++;
                        //toBeSent.get(toBeSent.size()-1).endTime = counter;
                    }
                    counter++;
                    pp.get(i).duration--;
                    toBeSent.get(toBeSent.size()-1).setendTime(counter);
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

//        List<process> LastOne = new ArrayList();
//        for (int i = 0; i < finished.size(); i++) {
//            if (i == 0) {
//                LastOne.add(finished.get(i));
//            } else if (i == finished.size() - 1) {
//                break;
//            } else if (LastOne.get(LastOne.size()-1).name.equals(finished.get(i))) {
//                LastOne.get(i-1).setDuration(LastOne.get(LastOne.size()-1).duration + finished.get(i).duration);
//            } else if (!(LastOne.get(LastOne.size()-1).name.equals(finished.get(i)))){
//                LastOne.add(finished.get(i));
//                break;
//            }else{
//                break;
//            }
//        }
        double sum = 0;
//        GantttChart(finished, finished.size());
        GantttChart(toBeSent, toBeSent.size());
        for (int i = 0; i < n; i++) {
            int mm = temp.get(i).endTime;
            int nn = temp.get(i).duration;
            process p = temp.get(i);
            int waitingTime = mm - nn - temp.get(i).whencome;
            sum += (double) waitingTime;
        }
        System.out.println("Average Waiting Time= " + (double) (sum / n));
        ss.rw.lblWaiting.setText((sum / n) + "");
    }

//    public static void PreemptiveSJF(List<process> pp, int n) {
//        Collections.sort(pp, process.getComparator(process.Parameter.duration));
//        List<process> finished = new ArrayList();
//        int counter = 0;
//        List<process> temp1 = new ArrayList();
//        int ssum = sum(pp, n);
//        while (counter < ssum){
//            
//        }
//        while (counter < ssum) {
//            for (int i = 0; i < pp.size(); i++) {
//                if (pp.get(i).whencome <= counter) {
//                    if (finished.size() == 0 || !(finished.get(finished.size() - 1).name.equals(pp.get(i).name))) {
//                        finished.add(pp.get(i));
//                    }
//                    //finished.get(finished.size() - 1).startTime = counter;
//                    while (pp.get(i).duration != 0) {
//
//                        if ((i + 1)%pp.size() < pp.size() && pp.get((i + 1)%pp.size()).duration < pp.get(i).duration) {
//                            Collections.sort(pp, process.getComparator(process.Parameter.duration));
//                            break;
//                        } else {
//                            counter++;
//                            pp.get(i).duration--;
//                        }
//                    }
//                    if (pp.get(i).duration == 0) {
//                        pp.remove(pp.get(i));
//                        break;
//                    }
//
//                }
//            }
//        }
    int i = 0;
//        while (counter < ssum && i < (i+1)%pp.size())
//                if (pp.get(i).whencome == counter) {
//                    process temp = pp.get(i);
//                    finished.add(pp.get(i));
//                    while (pp.get(i).duration > 0) {
//                        pp.get(i).duration--;
//                        counter++;
//                        Collections.sort(pp, process.getComparator(process.Parameter.duration));
//                        if (pp.get(i) != temp) {
//                            break;
//                        }
//                    }
//                }
//                if (pp.get(i).duration == 0) {
//                    pp.remove(pp.get(i));
//                    break;
//                }
//            
//        
//        if (counter == ssum) {
//            GantttChart(finished, finished.size());
//        }
//    }

//        int sum = 0, processWaitingTime;
//        for (int i = 0; i < finished.size(); i++) {
//            processWaitingTime = abs(finished.get(i).startTime - finished.get(i).whencome);
//            sum += processWaitingTime;
//        }
//        System.out.println("average waiting time" + (sum / finished.size()));
    // }}
//    public static void PreemptiveSJF(List<process> pp, int n) {
//        //Collections.sort(pp, process.getComparator(process.Parameter.duration));
//        List<process> finished = new ArrayList();
//        int counter = 0;
//        List<process> temp1 = new ArrayList();
//        int ssum = sum(pp, n);
//
//        while (counter < ssum) {
//            //Collections.sort(pp, process.getComparator(process.Parameter.duration));
//            for (int i = 0; i < pp.size(); i++) {
//                Collections.sort(pp, process.getComparator(process.Parameter.duration));
//                if (pp.get(i).whencome <= counter) {
//                    if (finished.size() == 0 || !(finished.get(finished.size() - 1).name.equals(pp.get(i).name))) {
//                        finished.add(pp.get(i));
//                        //finished.get(finished.size() - 1).startTime = counter;
//                        //counter += pp.get(i).duration;
//                        counter++;
//                        pp.get(i).duration--;
//                        break;
//                    }else{
//                        counter++;
//                        pp.get(i).duration--;
//                    }
//                    if (pp.get(i).duration == 0) {
//                        pp.remove(pp.get(i));
//                        break;
//                    }
//
//                }
//            }
//            
//        }
//        GantttChart(finished, finished.size());
//    }
}
