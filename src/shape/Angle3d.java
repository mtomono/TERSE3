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

import static java.lang.Math.PI;
import static java.lang.Math.atan2;
import javax.vecmath.Vector3d;

/**
 *
 * @author masao
 */
public class Angle3d {
    public static double angleBase(double start, double end, double cross, double dot) {
        assert start > 0;
        assert end > 0;
        double startxend = start * end;
        double sinx = cross / startxend;
        double cosx = dot / startxend;
        return atan2(sinx, cosx);
    }
    TVector3d start;
    Vector3d end;
    
    public Angle3d(TVector3d from, Vector3d to) {
        this.start = from;
        this.end = to;
    }
    
    public TVector3d axis() {
        return start.cross(end);
    }
    
    /**
     * counterclockwise angle around axis.
     * ranges from 0 to PI.
     * @return 
     */
    public double angle() {
        return angleBase(start.length(), end.length(), axis().length(), start.dot(end));
    }
    
    public double complement() {
        return 2*PI-angle();
    }
}
