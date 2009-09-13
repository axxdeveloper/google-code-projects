/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package bdc.model;

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
