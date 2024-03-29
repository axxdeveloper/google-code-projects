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
package canvas.view.filechooser;

import com.sun.java.swing.plaf.windows.WindowsBorders.DashedBorder;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JComponent;

/**
 *
 * @author Isaac
 */
public class ImageFileMicrocosm extends JComponent {
    
    private BufferedImage image;
    private double fixedHeight;
    private double fixedWidth;
    
    public ImageFileMicrocosm (BufferedImage image, int specWidth, int specHeight) {
        this.image = image;
        this.fixedHeight = specHeight;
        this.fixedWidth = specWidth;
        setBorder(new DashedBorder(Color.LIGHT_GRAY));
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension( (int) fixedWidth, (int) fixedHeight );
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        double imgWidth = image.getWidth();
        double imgHeight = image.getHeight();
        int centerX = 0;
        int centerY = 0;
        int paintWidth = 0;
        int paintHeight = 0;
        if ( fixedWidth > imgWidth && fixedHeight > imgHeight ) {
            paintWidth = (int) imgWidth;
            paintHeight = (int) imgHeight;
            centerX = (int) (fixedWidth - imgWidth) / 2;
            centerY = (int) (fixedHeight - imgHeight) / 2;
        } else {
            if ( imgWidth > imgHeight ) {
                paintWidth = (int) fixedWidth;
                paintHeight = (int) ( fixedWidth * imgHeight / imgWidth );
                centerX = 0;
                centerY = (int) (fixedHeight - paintHeight) / 2;
            } else {
                paintWidth = (int) (fixedHeight * imgWidth / imgHeight );
                paintHeight = (int) fixedHeight;
                centerX = (int) (fixedWidth - paintWidth) / 2;
                centerY = 0;
            }
        }
        g.drawImage(image, centerX, centerY, paintWidth, paintHeight, this);
    }

}
