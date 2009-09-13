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

import java.awt.Color;

/**
 *
 * @author Isaac
 */
public class ColorPO {

    private int red;
    private int green;
    private int blue;

    public ColorPO() {}
    public ColorPO(Color color) {
        setRed( color.getRed() );
        setGreen( color.getGreen() );
        setBlue( color.getBlue() );
    }
    
    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public int getGreen() {
        return green;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public int getBlue() {
        return blue;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }
    
    public Color toColor() {
        return new Color(red, green, blue);
    }

    @Override
    public String toString() {
        return toColor().toString();
    }
    
}
