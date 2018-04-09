/*
 Copyright 2017, 2018 Masao Tomono

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and limitations under the License.
 */

package shape;

import static java.lang.Math.abs;
import javax.vecmath.Vector2d;
import static shape.ShapeUtil.err;

/**
 *
 * @author masao
 */
public class Angle2d {
    TVector2d start;
    Vector2d end;
    
    public Angle2d(TVector2d start, Vector2d end) {
        this.start = start;
        this.end = end;
    }
    
    public double angle() {
        return angleFromStart(end);
    }
    
    public double angleFromStart(Vector2d end) {
        return Angle3d.angleBase(start.length(), end.length(), start.det(end), start.dot(end));
    }

    public boolean fallBetween(Vector2d target) {
        double toEnd = angle();
        double toTarget = angleFromStart(target);
        return toEnd*toTarget>err&&err<abs(toTarget)&&abs(toTarget)<abs(toEnd);        
    }
}
