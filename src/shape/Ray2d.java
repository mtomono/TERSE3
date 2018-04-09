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
import static java.lang.Math.min;
import static shape.ShapeUtil.epsilonEquals;
import static shape.ShapeUtil.err;
import string.Message;

/**
 *
 * @author masao
 */
public class Ray2d {
    public TPoint2d start;
    public TVector2d direction;
    
    public Ray2d(TPoint2d start, TVector2d direction) {
        this.start = start;
        this.direction = direction;
    }
    
    public Ray2d(TPoint2d start, TPoint2d end) {
        this.start = start;
        this.direction = TVector2d.c(start, end);
    }
    
    public TVector2d normalize() {
        return direction.normalizeR();
    }
    
    public boolean equals(Object o) {
        if (!(o instanceof Ray2d))
            return false;
        Ray2d t = (Ray2d) o;
        return equals(t, err);
    }
    
    boolean equals(Ray2d t, double err) {
        return start.epsilonEquals(t.start, err)&&direction.epsilonEquals(t.direction, err);
    }
    
    boolean equivalent(Ray2d t, double err) {
        return epsilonEquals(direction.dot(t.direction)/(direction.length()*t.direction.length()), 1)&&start.epsilonEquals(t.start, err);
    }
    
    public boolean equivalent(Ray2d t) {
        return equivalent(t, err);
    }
    
    public Ray2d reverse() {
        return new Ray2d(start, direction.negateR());
    }
    
    public Line2d asLine() {
        return new Line2d(start, direction);
    }
    
    public boolean otherGetsClosestOnSide(TPoint2d other) {
        return start.to(other).dot(direction)>=err;
    }
    
    public boolean projectionOverlap(Ray2d other) {
        return projectionFallsOnStart(other.start)||otherGetsClosestOnSide(other.start)||other.otherGetsClosestOnSide(start);
    }
    
    public boolean inSameSide(TVector2d other) {
        return direction.dot(other)>=err;
    }
    
    public boolean projectionFallsOnStart(TPoint2d other) {
        return start.epsilonEquals(asLine().closestPoint(other), err);
    }
    
    public boolean projectionContain(Segment2d other) {
        return (otherGetsClosestOnSide(other.start)||projectionFallsOnStart(other.start))&&(otherGetsClosestOnSide(other.end)||projectionFallsOnStart(other.end));
    }
    
    public TPoint2d closestPoint(Line2d other) {
        assert !parallel(other);
        return hit(other)?asLine().closestPoint(other):start;
    }
    
    public TPoint2d closestPoint(Ray2d other) {
        assert !parallel(other)||!projectionOverlap(other);
        if (hit(other))
            return asLine().closestPoint(other.asLine());
        if (!otherGetsClosestOnSide(other.start)) // this contains 1) has no overlap each other 2) other contains all the projection of this.
            return start;
        if (inSameSide(other.direction)) // this contains all the projection of other
            return asLine().closestPoint(other.start);
        return asLine().distance(other.start)<other.asLine().distance(start)?asLine().closestPoint(other.start):start;
    }
        
    public TPoint2d closestPoint(Segment2d other) {
        assert !parallel(other)||!other.projectionOverlap(this);
        if (hit(other))
            return asLine().closestPoint(other.asLine());
        if (!other.projectionOverlap(this))
            return start;
        if (projectionContain(other))
            return asLine().closestPoint(other);
        if (!hit(other.asLine()))
            return start;
        return otherGetsClosestOnSide(other.start)?asLine().closestPoint(other.start):asLine().closestPoint(other.end);
    }
    
    public TPoint2d closestPoint(TPoint2d other) {
        return otherGetsClosestOnSide(other)?asLine().closestPoint(other):start;
    }
    
    public double distance(TPoint2d other) {
        if (start.epsilonEquals(other, err))
            return 0;
        return otherGetsClosestOnSide(other)?asLine().distance(other):start.distance(other);
    }
    
    public double distance(Line2d other) {
        return other.distance(this);
    }
    
    public double distance(Ray2d other) {
        return hit(other)?0:min(other.distance(start), distance(other.start));
    }
    
    public double distance(Segment2d other) {
        return max(distance(other.asRay()), distance(other.reverse().asRay()));
    }
    
    public boolean hit(TPoint2d other) {
        return epsilonEquals(distance(other), 0);
    }
            
    public boolean hit(Segment2d other) {
        return hit(other.asRay())&&hit(other.reverse().asRay());
    }
    
    public boolean hit(Ray2d other) {
        if (parallel(other)&&asLine().hit(other))
            return projectionOverlap(other);
        return asLine().hit(other)&&other.asLine().hit(this);
    }
    
    public boolean hit(Line2d other) {
        return other.hit(this);
    }
    
    public boolean parallel(Segment2d other) {
        return other.asRay().parallel(this);
    }
    
    public boolean parallel(Ray2d other) {
        return epsilonEquals(direction.det(other.direction), 0);
    }
    
    public boolean parallel(Line2d other) {
        return other.parallel(this);
    }

    @Override
    public String toString() {
        return Message.nl("Ray2d[").c(start).c(",").c(direction).c("]").toString();
    }
}
