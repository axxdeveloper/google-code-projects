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

package canvas.view.persistence;

import canvas.view.widget.XObjectScene;
import canvas.view.widget.XWidget;
import java.awt.Color;
import java.util.UUID;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 *
 * @author Isaac
 */
public abstract class XWidgetPO<T extends XWidget> {

    private ColorPO widgetColor;
    private PointPO preferredLocation;
    private RectanglePO preferredBounds;
    private UUID widgetID;

    /**
     * Every widget can define what widget color meaning itself.<br/>
     * For example, a description widget's widget color may be font color.
     */
    public ColorPO getWidgetColor() {
        if ( widgetColor == null ) {
            widgetColor = new ColorPO(Color.GRAY);
        }
        return widgetColor;
    }

    /** @see #getWidgetColor()  */
    public void setWidgetColor(ColorPO widgetColor) {
        this.widgetColor = widgetColor;
    }
    
    /** Visual Library's location is where the widget locate. */
    public PointPO getPreferredLocation() {
        if ( preferredLocation == null ) {
            preferredLocation = new PointPO();
        }
        return preferredLocation;
    }

    /** @see #getPreferredLocation() */
    public void setPreferredLocation(PointPO preferredLocation) {
        this.preferredLocation = preferredLocation;
    }

    /** Visual Library's preferred bounds is base on location's calculation */
    public RectanglePO getPreferredBounds() {
        return preferredBounds;
    }

    /** @see #getPreferredBounds() */
    public void setPreferredBounds(RectanglePO preferredBounds) {
        this.preferredBounds = preferredBounds;
    }

    /** Used to judge unique widget */
    public UUID getWidgetID() {
        if ( widgetID == null ) {
            widgetID = UUID.randomUUID();
        }
        return widgetID;
    }

    /** @see #getWidgetID() */
    public void setWidgetID(UUID widgetID) {
        this.widgetID = widgetID;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof XWidgetPO == false) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        boolean isSuperEquals = super.equals(obj);
        boolean isEquals = isSuperEquals;
        if ( isSuperEquals ) {
            XWidgetPO po = (XWidgetPO) obj;
            isEquals = new EqualsBuilder()
                    .append(po.getPreferredBounds(), getPreferredBounds())
                    .append(po.getPreferredLocation(), getPreferredLocation())
                    .append(po.getWidgetID(), getWidgetID())
                    .isEquals();            
        } 
        return isEquals;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (this.preferredLocation != null ? this.preferredLocation.hashCode() : 0);
        hash = 97 * hash + (this.preferredBounds != null ? this.preferredBounds.hashCode() : 0);
        hash = 97 * hash + (this.widgetID != null ? this.widgetID.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("preferredBounds", preferredBounds)
                .append("preferredLocation", preferredLocation)
                .append("widgetID", widgetID)
                .append("widgetColor", widgetColor)
                .toString();
    }
    
    public abstract T createWidget(XObjectScene objectScene);
    public abstract XWidgetPO clonePO();

}
