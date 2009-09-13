
package com.isaac.bdc.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Isaac
 */
public class BDRequirement extends BDData {
    
    public static final String PROP_DAYS = "days";
    public static final String PROP_TASKS = "tasks";

    private Integer  days = new Integer(0);
    private List<BDTask> tasks = new ArrayList<BDTask>();

    public BDRequirement() {}
    
    public BDRequirement(String name, String description, Integer days) {
        setName( name );
        setDescription( description );
        setDays( days );
    }

    public Integer getDays() {
        if ( days == null ) {
            days = new Integer(0);
        }
        int totalTaskDays = 0;
        if ( getTasks() != null ) {
            for (BDTask task : getTasks()) {
                totalTaskDays += task.getDays().intValue();
            }
        }
        if ( totalTaskDays > days.intValue() ) {
            return new Integer( totalTaskDays );
        } 
        return days;
    }

    public void setDays(Integer days) {
        Integer oldDays = this.days;
        this.days = days;
        changeSupport.firePropertyChange(PROP_DAYS, oldDays, days);
    }

    public List<BDTask> getTasks() {
        if ( tasks == null ) {
            tasks = new ArrayList<BDTask>();
        }
        return tasks;
    }

    public void setTasks(List<BDTask> tasks) {
        List<BDTask> oldTasks = this.tasks;
        this.tasks = tasks;
        changeSupport.firePropertyChange(PROP_TASKS, oldTasks, tasks);
    }

}
