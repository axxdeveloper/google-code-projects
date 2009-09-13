/*******************************************************************************
 *
 * blueMarine - open source photo workflow
 * =======================================
 *
 * Copyright (C) 2003-2007 by Fabrizio Giudici (Fabrizio.Giudici@tidalwave.it)
 * Project home page: http://bluemarine.tidalwave.it
 *
 *******************************************************************************
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0 
 * 
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and 
 * limitations under the License. 
 *
 *******************************************************************************
 *
 * $Id: PinConnectionWidget.java 86 2007-11-02 01:10:26Z fabriziogiudici $
 *
 ******************************************************************************/
package canvas.view.widget;

import java.util.logging.Logger;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import org.netbeans.api.visual.anchor.AnchorFactory;
import org.netbeans.api.visual.widget.ConnectionWidget;
import org.netbeans.api.visual.widget.Scene;
import org.netbeans.api.visual.widget.Widget;

/*******************************************************************************
 *
 * @author  Fabrizio Giudici
 * @version $Id: PinConnectionWidget.java 86 2007-11-02 01:10:26Z fabriziogiudici $
 *
 ******************************************************************************/
public class PinConnectionWidget extends ConnectionWidget
  {
    private static final String CLASS = PinConnectionWidget.class.getName();
    
    private static final Logger logger = Logger.getLogger(CLASS);
    
    private final Color selectedColor = new Color(255, 255, 255);
    
    private final Color color = new Color(200, 200, 200);
    
    private Point sourcePointCache;
    
    private Point targetPointCache;
    
    private final Widget sourceWidget;
    
    private final Widget targetWidget;
    
    /***************************************************************************
     *
     *
     **************************************************************************/
    public PinConnectionWidget (final Scene scene, final Widget sourceWidget, final Widget targetWidget) 
      {
        super(scene);
        this.sourceWidget = sourceWidget;
        this.targetWidget = targetWidget;
        // lazy anchor location computation
        setSourceAnchor(AnchorFactory.createFixedAnchor(new Point(0, 0)));
        setTargetAnchor(AnchorFactory.createFixedAnchor(new Point(0, 0)));
      }
    
    /***************************************************************************
     *
     *
     **************************************************************************/
    public void fixAnchors()
      {
        logger.fine("fixAnchors()");
        final Point targetPoint = AnchorFactory.createCenterAnchor(targetWidget).getRelatedSceneLocation();
        
        logger.finer(">>>> targetPoint: " + targetPoint + " vs " + targetPointCache);
        if (!targetPoint.equals(targetPointCache))
          {
            setTargetAnchor(AnchorFactory.createFixedAnchor(targetPointCache = targetPoint));
          }
        
        final Point sourcePoint = AnchorFactory.createRectangularAnchor(sourceWidget).getRelatedSceneLocation();
        
        logger.finer(">>>> sourcePoint: " + sourcePoint + " vs " + sourcePointCache);
        if (!sourcePoint.equals(sourcePointCache))
          {
            setSourceAnchor(AnchorFactory.createFixedAnchor(sourcePointCache = sourcePoint));
          }

        logger.finer(">>>> fixAnchors() done");
      }
    
    /***************************************************************************
     *
     *
     **************************************************************************/
    @Override
    protected void paintWidget()
      {
        final Graphics2D g = getGraphics();
        g.setColor(Color.WHITE);
//        g.setColor(getState().isSelected() ? selectedColor : color);
        final Point p1 = sourceWidget.getLocation();//getFirstControlPoint();
        final Point p2 = targetWidget.getLocation();//new Point(100, 100);//getLastControlPoint();
        int[] x = new int[3];
        int[] y = new int[3];
        x[0] = p2.x;
        y[0] = p2.y;
        float dx = p2.x - p1.x;
        float dy = p2.y - p1.y;
        final float l = (float)Math.sqrt(dx * dx + dy * dy);
        dx /= l;
        dy /= l;
        final int r = 6; // base size of the wedge
        final int e = -6; // how long it protrudes into the Widget
        x[1] = p1.x + Math.round(dx * e + dy * r);
        y[1] = p1.y + Math.round(dy * e - dx * r);
        x[2] = p1.x + Math.round(dx * e - dy * r);
        y[2] = p1.y + Math.round(dy * e + dx * r);
        final Polygon polygon = new Polygon(x, y, 3);
        g.fillPolygon(polygon);
      }
  }
