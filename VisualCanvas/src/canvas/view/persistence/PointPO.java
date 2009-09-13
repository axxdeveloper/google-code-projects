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

import java.awt.Point;

/**
 *
 * @author Isaac
 */
public class PointPO {

    private int x = 0;
    private int y = 0;

    public PointPO() {};
    
    public PointPO(Point p) {
        if ( p != null ) {
            setX( p.x );
            setY( p.y );
        }
    }

    public PointPO(int x, int y) {
        setX( x );
        setY( y );
    }
    
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    public Point toPoint() {
        return new Point( getX(), getY() );
    }

    @Override
    public String toString() {
        return toPoint().toString();
    }
    
}
