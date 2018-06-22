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
import static java.lang.Math.max;
import static shape.ShapeUtil.epsilonEquals;
import static shape.ShapeUtil.err;
import string.Message;

/**
 *
 * @author masao
 */
public class Line2d {
    public TVector2d direction;
    public TPoint2d start;
    
    public Line2d(TPoint2d start, TVector2d direction) {
        this.start = start;
        this.direction = direction;
    }
    
    public TVector2d normalize() {
        return direction.normalizeR();
    }
    
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Line2d))
            return false;
        Line2d t = (Line2d) o;
        return equals(t, err);
    }
    
    boolean equals(Line2d t, double err) {
        return start.epsilonEquals(t.start, err)&&direction.epsilonEquals(t.direction, err);
    }
    
    boolean equivalent(Line2d t, double err) {
        return parallel(t)&&epsilonEquals(distance(t.start), 0);
    }
    
    public boolean equivalent(Line2d t) {
        return equivalent(t, err);
    }
    
    public Ray2d asRay() {
        return new Ray2d(start, direction);
    }
    
    public Line2d reverse() {
        return new Line2d(start, direction.negateR());
    }
    
    public TVector2d perpendicular(TPoint2d other) {
        return closestPoint(other).from(other);
    }
    
    public TPoint2d closestPoint(Line2d other) {
        assert !parallel(other);
        TMatrix3d m = TMatrix3d.coordinateTransform(direction.expand(), other.direction.expand().negateS(), new TVector3d(0, 0, 1));
        TMatrix3d invm = m.invertR();
        TVector2d move = new TVector3d(start.to(other.start).expand().transformS(invm).x, 0, 0).transformS(m).shrink();
        return start.moveR(move);
    }
    
    public TPoint2d closestPoint(Ray2d other) {
        assert !parallel(other);
        return hit(other)?closestPoint(other.asLine()):closestPoint(other.start);
    }
    
    public TPoint2d closestPoint(Segment2d other) {
        assert !parallel(other);
        if (hit(other))
            return closestPoint(other.asLine());
        return distance(other.start)<distance(other.end)?closestPoint(other.start):closestPoint(other.end);
    }
    
    public TPoint2d closestPoint(TPoint2d other) {
        return start.epsilonEquals(other, err)?start:
               start.addR(direction.sizeR(start.to(other).dot(direction.normalizeR())));
    }
    
    public double distance(TPoint2d other) {
        if (start.epsilonEquals(other, err))
            return 0;
        return abs(start.to(other).det(direction))/direction.length();
    }
    
    public double distance(Line2d other) {
        if (!parallel(other))
            return 0;
        return distance(other.start);
    }
    
    public double distance(Ray2d other) {
        if (hit(other))
            return 0;
        return distance(other.start);
    }
    
    public double distance(Segment2d other) {
        return max(distance(other.asRay()), distance(other.reverse().asRay()));
    }
    
    public boolean hit(TPoint2d other) {
        return start.epsilonEquals(other, err)||epsilonEquals(start.to(other).det(direction), 0);
    }
    
    public boolean hit(Segment2d other) {
        return hit(other.asRay())&&hit(other.reverse().asRay());
    }

    public boolean hit(Ray2d other) {
        if (parallel(other))
            return epsilonEquals(distance(other.start), 0);
        TPoint2d intersectAsLine = closestPoint(other.asLine());
        if (intersectAsLine.epsilonEquals(start, err))
            return true;
        return other.otherGetsClosestOnSide(intersectAsLine)||intersectAsLine.epsilonEquals(other.start, err);
    }
    
    public boolean hit(Line2d other) {
        return !parallel(other) || epsilonEquals(direction.det(start.to(other.start)), 0);
    }
    
    public boolean parallel(Segment2d other) {
        return other.asRay().parallel(asRay());
    }
    
    public boolean parallel(Ray2d other) {
        return other.parallel(asRay());
    }
    
    public boolean parallel(Line2d other) {
        return other.asRay().parallel(asRay());
    }

    @Override
    public String toString() {
        return Message.nl("Line2d[").c(start).c(",").c(direction).c("]").toString();
    }
}
