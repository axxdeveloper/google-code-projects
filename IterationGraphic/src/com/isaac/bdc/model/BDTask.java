
package com.isaac.bdc.model;

/**
 *
 * @author Isaac
 */
public class BDTask extends BDData {

    public static final String PROP_DAYS = "days";
    
    private Integer  days = new Integer(0);

    public Integer getDays() {
        if ( days == null ) {
            days = new Integer(0);
        }
        return days;
    }

    public void setDays(Integer days) {
        Integer oldDays = this.days;
        this.days = days;
        changeSupport.firePropertyChange(PROP_DAYS, oldDays, days);
    }

}
