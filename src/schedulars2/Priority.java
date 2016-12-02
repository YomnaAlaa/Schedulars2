package schedulars2;

import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import static schedulars2.Schedulars2.GantttChart;

public class Priority {
//static resultWindow rf = new resultWindow();
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

    public static void NonPreemptivePriority(List<process> pp, int n) {
        Collections.sort(pp, process.getComparator(process.Parameter.priority));
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
                    pp.remove(pp.get(i));
                    break;
                }
            }
        }
        GantttChart(finished, finished.size());
        double sum = 0, processWaitingTime;
        for (int i = 0; i < finished.size(); i++) {
            processWaitingTime = abs(finished.get(i).startTime - finished.get(i).whencome);
            sum += processWaitingTime;
        }
        System.out.println("average waiting time" + (double) (sum / finished.size()));
        ss.rw.lblWaiting.setText((sum/n)+"");
    }

    public static void PreemptivePriority(List<process> pp, int n) {
        ss.rw.setVisible(true);
        Collections.sort(pp, process.getComparator(process.Parameter.priority));
        List<process> finished = new ArrayList();
        int counter = 0;
        //List<process> temp1 = new ArrayList();
        //process temp;
        int ssum = sum(pp, n);
        int priority = getMaxPriority(pp, pp.size()) + 1;
        int index = -1;
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
        while (counter < ssum) {
            for (int i = 0; i < pp.size(); i++) {
                if (pp.get(i).whencome <= counter && pp.get(i).priority <= priority) {
                    priority = pp.get(i).priority;
                    index = pp.get(i).index;
                    if (finished.size() == 0) {
                        finished.add(pp.get(i));
                    } else if (finished.get(finished.size() - 1) != pp.get(i)) {
                        if (pp.get(i).priority==finished.get(finished.size() - 1).priority && finished.get(finished.size() - 1).duration==0)
                            finished.add(pp.get(i));
                        else if (pp.get(i).priority<finished.get(finished.size() - 1).priority)
                            finished.add(pp.get(i));
                    }
                    counter++;
                    pp.get(i).duration--;

                } else if (pp.get(i).whencome <= counter && pp.get(i).priority > priority && pp.get(0)==pp.get(i)) {
                    if (finished.get(finished.size() - 1) != pp.get(i)) 
                        finished.add(pp.get(i));
                     counter++;
                    pp.get(i).duration--;
                }
                if (pp.get(i).duration == 0) {
                    temp.get(pp.get(i).index).endTime=counter;
                    pp.remove(pp.get(i));
                    
                }

            }
        }
        double sum = 0;
        GantttChart(finished, finished.size());
        for (int i = 0; i < n; i++) {
            int mm = temp.get(i).endTime;
            int nn = temp.get(i).duration;
            process p = temp.get(i);
            int waitingTime = mm - nn - temp.get(i).whencome;
            sum += (double)waitingTime;
        }
        System.out.println("Average Waiting Time= " + (double)(sum / n));
        ss.rw.lblWaiting.setText((sum/n)+"");
    }

//    public static void PreemptivePriority(List<process> pp, int n) {
//        Collections.sort(pp, process.getComparator(process.Parameter.priority));
//        List<process> finished = new ArrayList();
//        int counter = 0;
//        List<process> temp1 = new ArrayList();
//        int ssum = sum(pp, n);
//
//        while (counter < ssum) {
//            //for (int i = 0; i < pp.size(); i++) {
//                if (pp.get(0).whencome <= counter) {
//                    finished.add(pp.get(0));
//                    //finished.get(finished.size() - 1).startTime = counter;
//                    //counter += pp.get(i).duration;
//                    counter++;
//                    pp.get(0).duration--;
//                    if (pp.get(0).duration==0)
//                        pp.remove(pp.get(0));
//                    else if (pp.get(0).duration!=0 && pp.size()!=1 && pp.get(1).whencome<=counter){
//                        swap(pp.get(0), pp.get(1));
//                    }
//                    
//                //}
//            }
//        }
//        GantttChart(finished, finished.size());
//        double sum = 0, processWaitingTime;
//        for (int i = 0; i < finished.size(); i++) {
//            processWaitingTime = abs(finished.get(i).startTime - finished.get(i).whencome);
//            sum += processWaitingTime;
//        }
//        System.out.println("average waiting time" + (double) (sum / finished.size()));
//    }

    /*-----------------------------------------------------------------------------------------------------------------------------------------------------*/
//    public static void PreemptivePriority(List<process> pp, int n) {
//        Collections.sort(pp, process.getComparator(process.Parameter.whencome));
//        List<process> ppp = new ArrayList<process>();
//        boolean flag = false;
//        int counter = 0;
//        int summ = sum(pp, pp.size());
//        for (int i = 0; i < pp.size() - 1 || counter < summ;) {
//            if (counter == summ) {
//                break;
//            }
//            process ppq = getHighestPriority(pp, pp.size());
//            if (pp.size() == 1) {
//                ppp.add(pp.get(0));
//                counter += pp.get(0).duration;
//                pp.remove(pp.get(0));
//            } else if (pp.get(0).whencome == 0 && pp.get(i + 1).whencome != 0) {
//                ppp.add(pp.get(0));
//                for (int j = 0; j < pp.get(0).duration; j++) {
//                    if (counter == pp.get(1).whencome && pp.get(1).priority < pp.get(0).priority) {
//                        //waiting.add(pp.get(0));
//                        pp.get(0).duration = pp.get(0).duration - j;
//                        flag = swap(pp.get(0), pp.get(1));
//                        break;
//                    } else {
//                        counter++;
//                    }
//                }
//                //counter += pp.get(0).duration;
//                if (!flag) {
//                    pp.remove(pp.get(0));
//                    i++;
//                } else {
//                    flag = false;
//                }
//
//            } else if (pp.get(0).whencome == pp.get(i).whencome) {
//                int minn = getMinNumer(pp.get(0).priority, pp.get(i).priority);
//                int dd = findIndexByPriority(pp, minn, pp.size());
//                ppp.add(pp.get(dd));
//                for (int j = 0; j < pp.get(0).duration; j++) {
//                    if (counter == pp.get(1).whencome && pp.get(1).priority < pp.get(0).priority) {
//                        //waiting.add(pp.get(0));
//                        pp.get(0).duration = pp.get(0).duration - j;
//                        swap(pp.get(0), pp.get(1));
//                        break;
//                    } else {
//                        counter++;
//                    }
//                }
////                counter += pp.get(dd).duration;
//                if (!flag) {
//                    ppp.remove(minn);
//                    i++;
//                } else {
//                    flag = false;
//                }
//
//            } else if (ppq.whencome <= counter) {
//                ppp.add(ppq);
//                for (int j = 0; j < pp.get(0).duration; j++) {
//                    if (counter == pp.get(1).whencome && pp.get(1).priority < pp.get(0).priority) {
//                        //waiting.add(pp.get(0));
//                        pp.get(0).duration = pp.get(0).duration - j;
//                        swap(pp.get(0), pp.get(1));
//                        break;
//                    } else {
//                        counter++;
//                    }
//                }
//                //counter += ppq.duration;
//                if (!flag) {
//                    pp.remove(ppq);
//                } else {
//                    flag = false;
//                }
//
//            } else if (!(ppq.whencome <= counter)) {
//                process temp = ppq;
//                pp.remove(ppq);
//                for (int f = 0; f < pp.size(); f++) {
//                    process pps = getHighestPriority(pp, pp.size());
//                    if (pps.whencome <= counter) {
//                        ppp.add(pps);
//                        for (int j = 0; j < pps.duration; j++) {
//                            if (counter == pp.get(1).whencome && pp.get(1).priority < pps.priority) {
//                                //waiting.add(pp.get(0));
//                                pps.duration = pps.duration - j;
//                                swap(pps, pp.get(1));
//                                break;
//                            } else {
//                                counter++;
//                            }
//                        }
//                        // counter += pps.duration;
//                        if (!flag) {
//                            pp.remove(pps);
//                            break;
//                        } else {
//                            flag = false;
//                        }
//
//                    }
//                }
//                pp.add(temp);
//
//            } else {
//                //pp.remove(pp.get(i));
//                pp.add(pp.get(i));
//                i++;
//            }
//            System.out.print(ppp.get(i).name);
//        }
//        //GantttChart(ppp, n);
//    }
    //    public static void NonPreemptivePriority(List<process> pp, int n) {
//        Collections.sort(pp, process.getComparator(process.Parameter.whencome));
//        List<process> ppp = new ArrayList<process>();
//
//        int counter = 0;
//        int summ = sum(pp, pp.size());
//        for (int i = 0; i < pp.size() - 1 || counter < summ;) {
//            if (counter == summ) {
//                break;
//            }
//            process ppq = getHighestPriority(pp, pp.size());
//            if (pp.size() == 1) {
//                ppp.add(pp.get(0));
//                counter += pp.get(0).duration;
//                pp.remove(pp.get(0));
//            } else if (pp.get(0).whencome == 0 && pp.get(i + 1).whencome != 0) {
//                ppp.add(pp.get(0));
//                counter += pp.get(0).duration;
//                pp.remove(pp.get(0));
//                i++;
//
//            } else if (pp.get(0).whencome == pp.get(i).whencome) {
//                int minn = getMinNumer(pp.get(0).priority, pp.get(i).priority);
//                int dd = findIndexByPriority(pp, minn, pp.size());
//                ppp.add(pp.get(dd));
//                counter += pp.get(dd).duration;
//                pp.remove(minn);
//                i++;
//
//            } else if (ppq.whencome <= counter) {
//                ppp.add(ppq);
//                counter += ppq.duration;
//                pp.remove(ppq);
//            } else if (!(ppq.whencome <= counter)) {
//                process temp = ppq;
//                pp.remove(ppq);
//                for (int f = 0; f < pp.size(); f++) {
//                    process pps = getHighestPriority(pp, pp.size());
//                    if (pps.whencome <= counter) {
//                        ppp.add(pps);
//                        counter += pps.duration;
//                        pp.remove(pps);
//                        break;
//                    }
//                }
//                pp.add(temp);
//
//            } else {
//                //pp.remove(pp.get(i));
//                pp.add(pp.get(i));
//                i++;
//            }
//        }
//        GantttChart(ppp, n);
//    }
}
