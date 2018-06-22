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

import static java.lang.Math.max;
import static shape.ShapeUtil.err;
import string.Message;

/**
 *
 * @author masao
 */
public class Segment2d {
    public TPoint2d start;
    public TPoint2d end;
    
    public Segment2d(TPoint2d start, TPoint2d end) {
        this.start = start;
        this.end = end;
    }
    
    public TVector2d normalize() {
        return asVector().normalizeS();
    }
        
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Segment2d))
            return false;
        Segment2d t = (Segment2d) o;
        return equals(t, err);
    }
    
    boolean equals(Segment2d t, double err) {
        return start.epsilonEquals(t.start, err)&&end.epsilonEquals(t.end, err);
    }
    
    boolean equivalent(Segment2d t, double err) {
        return equals(t, err)||reverse().equals(t, err);
    }
    
    public boolean equivalent(Segment2d t) {
        return equivalent(t, err);
    }
        
    public Line2d asLine() {
        return new Line2d(start, start.to(end));
    }
    
    /**
     * convert to Ray from start to end
     * @return 
     */
    public Ray2d asRay() {
        return new Ray2d(start, start.to(end));
    }
    
    public Segment2d reverse() {
        return new Segment2d(end, start);
    }
    
    public TVector2d asVector() {
        return start.to(end);
    }
    
    public double length() {
        return start.distance(end);
    }
    
    public boolean projectionOverlap(Ray2d other) {
        return other.projectionOverlap(asRay())&&other.projectionOverlap(reverse().asRay());
    }
    
    public boolean projectionOverlap(Segment2d other) {
        return projectionOverlap(other.asRay())&&projectionOverlap(other.reverse().asRay());
    }
    
    public boolean projectionContain(Segment2d other) {
        return asRay().projectionContain(other)&&reverse().asRay().projectionContain(other);
    }
    
    public TPoint2d closestPoint(Line2d other) {
        assert !parallel(other);
        if (hit(other))
            return asLine().closestPoint(other);
        return other.distance(start)<other.distance(end)?start:end;
    }
    
    public TPoint2d closestPoint(Ray2d other) {
        assert !parallel(other)||!projectionOverlap(other);
        return closestPoint(other.closestPoint(this));
    }
    
    /**
     * when segment is mutually overlapping, closest point can be calculated from rays that
     * is one side ray of segments respectively.
     * @param other
     * @return 
     */
    public Ray2d abstractAsRay(Segment2d other) {
        return asRay().projectionContain(other)?reverse().asRay():asRay();
    }
    
    public TPoint2d closestPoint(Segment2d other) {
        assert !parallel(other)||!projectionOverlap(other);
        if (hit(other))
            return asLine().closestPoint(other.asLine());
        if (!projectionOverlap(other))
            return asRay().projectionContain(other)?end:start;
        if (projectionContain(other))
            return asLine().closestPoint(other);
        return abstractAsRay(other).closestPoint(other.abstractAsRay(this));
    }
    
    public TPoint2d closestPoint(TPoint2d other) {
        if (asRay().otherGetsClosestOnSide(other)&&reverse().asRay().otherGetsClosestOnSide(other))
            return asLine().closestPoint(other);
        return asRay().otherGetsClosestOnSide(other)?end:start;
    }

    public double distance(TPoint2d other) {
        return max(asRay().distance(other), reverse().asRay().distance(other));
    }
    
    public double distance(Line2d other) {
        return max(asRay().distance(other), reverse().asRay().distance(other));
    }
    
    public double distance(Ray2d other) {
        return max(asRay().distance(other), reverse().asRay().distance(other));
    }
    
    public double distance(Segment2d other) {
        return max(asRay().distance(other), reverse().asRay().distance(other));
    }
    
    public boolean hit(Segment2d other) {
        return asRay().hit(other)&&reverse().asRay().hit(other);
    }
    
    public boolean hit(Ray2d other) {
        return other.hit(this);
    }
    
    public boolean hit(Line2d other) {
        return other.hit(this);
    }
    
    public boolean hit(TPoint2d other) {
        return asRay().hit(other)&&reverse().asRay().hit(other);
    }
    
    public boolean parallel(Segment2d other) {
        return other.asRay().parallel(this);
    }
    
    public boolean parallel(Ray2d other) {
        return other.parallel(this);
    }
    
    public boolean parallel(Line2d other) {
        return other.parallel(this);
    }
    
    @Override
    public String toString() {
        return Message.nl("Segment2d[").c(start).c(",").c(end).c("]").toString();
    }
}
