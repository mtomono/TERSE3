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

import collection.TList;
import static collection.c.a2l;
import static java.lang.Math.abs;

/**
 *
 * @author masao
 */
public class ShapeUtil {
    public static double err=0.000000001;
    
    public static boolean epsilonEquals(double a, double b, double err) {
        return abs(a - b)<err;
    }
    
    public static boolean epsilonEquals(double a, double b) {
        return epsilonEquals(a, b, err);
    }
    public static Line2d line2(double x1, double y1, double x2, double y2) {
        return new Line2d(new TPoint2d(x1, y1), new TVector2d(x2, y2));
    }
    public static Ray2d ray2(double x1, double y1, double x2, double y2) {
        return new Ray2d(new TPoint2d(x1, y1), new TVector2d(x2, y2));
    }
    public static Segment2d segment2(double x1, double y1, double x2, double y2) {
        return new Segment2d(new TPoint2d(x1, y1), new TPoint2d(x2, y2));
    }
    public static TPoint2d point2(double x, double y) {
        return new TPoint2d(x, y);
    }
    public static TVector2d vector2(double x, double y) {
        return new TVector2d(x, y);
    }
    public static Line3d line3(double x1, double y1, double z1, double x2, double y2, double z2) {
        return new Line3d(new TPoint3d(x1, y1, z1), new TVector3d(x2, y2, z2));
    }
    public static Ray3d ray3(double x1, double y1, double z1, double x2, double y2, double z2) {
        return new Ray3d(new TPoint3d(x1, y1, z1), new TVector3d(x2, y2, z2));
    }
    public static Segment3d segment3(double x1, double y1, double z1, double x2, double y2, double z2) {
        return new Segment3d(new TPoint3d(x1, y1, z1), new TPoint3d(x2, y2, z2));
    }
    public static TPoint3d point3(double x, double y, double z) {
        return new TPoint3d(x, y, z);
    }
    public static TVector3d vector3(double x, double y, double z) {
        return new TVector3d(x, y, z);
    }
    public static TPoint2i p2i(int x, int y) {
        return new TPoint2i(x,y);
    }
    public static TList<TPoint2i> p2is(Integer... v) {
        return TList.set(a2l(v)).fold(2).map(l->p2i(l.get(0), l.get(1)));
    }
    public static double sin(TVector2d from, TVector2d to) {
        assert epsilonEquals(from.length(), 1, err);
        assert epsilonEquals(to.length(), 1, err);
        return from.det(to);
    }
    public static double cos(TVector2d from, TVector2d to) {
        assert epsilonEquals(from.length(), 1, err);
        assert epsilonEquals(to.length(), 1, err);
        return from.dot(to);
    }
    public static TVector3d sin(TVector3d from, TVector3d to) {
        assert epsilonEquals(from.length(), 1, err);
        assert epsilonEquals(to.length(), 1, err);
        return from.cross(to);
    }
    public static double cos(TVector3d from, TVector3d to) {
        assert epsilonEquals(from.length(), 1, err);
        assert epsilonEquals(to.length(), 1, err);
        return from.dot(to);
    }
}
