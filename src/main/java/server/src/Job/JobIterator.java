package Job;

import java.util.Iterator;
import java.util.Set;

public class JobIterator implements Iterator {
    private Job[] jobs;
    private int index = 0;

    public JobIterator(Set<Job> jobs) {
        this.jobs = jobs.toArray(new Job[0]);
    }

    @Override
    public boolean hasNext() {
        if (index < jobs.length) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Object next() {
        Job job = jobs[index];
        index++;
        return job;
    }
}
