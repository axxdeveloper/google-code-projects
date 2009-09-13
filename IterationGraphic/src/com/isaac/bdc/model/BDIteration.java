
package com.isaac.bdc.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Isaac
 */
public class BDIteration extends BDData {

    public static final String PROP_REQUIREMENTS = "requirements";
    
    private List<BDRequirement> requirements = new ArrayList<BDRequirement>();

    public List<BDRequirement> getRequirements() {
        if ( requirements == null ) {
            requirements = new ArrayList<BDRequirement>();
        }
        return requirements;
    }

    public void setRequirements(List<BDRequirement> requirements) {
        List<BDRequirement> oldRequirements = this.requirements;
        this.requirements = requirements;
        changeSupport.firePropertyChange(PROP_REQUIREMENTS, oldRequirements, requirements);
    }
    
}
