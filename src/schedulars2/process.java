package schedulars2;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class process {

    public String name;
    public int duration;
    public int whencome;
    public int priority;
    public int startTime;
    public int endTime = 0;
    public int waitingTime;
    public int index;

    public static Comparator<process> getComparator(Parameter... parameters) {
        return new ProcessComparator(parameters);

    }

    public static void swap(int x, int y) {
        int temp = x;
        x = y;
        y = temp;
    }

    enum Parameter {

        duration, whencome, priority, name
    };

    static class ProcessComparator implements Comparator<process> {

        private Parameter[] parameters;

        private ProcessComparator(Parameter[] parameters) {
            this.parameters = parameters;
        }

        public int compare(process o1, process o2) {
            int comparison;
            for (Parameter parameter : parameters) {
                switch (parameter) {
                    case duration:
                        comparison = o1.duration - o2.duration;
                        if (comparison != 0) {
                            return comparison;
                        }
                        break;
                    case whencome:
                        comparison = o1.whencome - o2.whencome;
                        if (comparison != 0) {
                            return comparison;
                        }
                        break;
                    case priority:
                        comparison = o1.priority - (o2.priority);
                        if (comparison != 0) {
                            return comparison;
                        }
                        break;
                    case name:
                        comparison = o1.name.compareTo(o2.name);
                        if (comparison != 0) {
                            return comparison;
                        }
                        break;
                }
            }
            return 0;
        }

    }

    public void setWhenCome(int nn) {
        this.whencome = nn;
    }

    public void setDuration(int nn) {
        this.duration = nn;
    }

    public void setName(String nn) {
        this.name = nn;
    }

    public void setPriotrity(int nn) {
        this.priority = nn;
    }

    public void setStartTime(int nn) {
        this.startTime = nn;
    }
    
    public void setInedex(int nn) {
        this.index = nn;
    }
    
    public void setendTime(int nn) {
        this.endTime = nn;
    }

}
