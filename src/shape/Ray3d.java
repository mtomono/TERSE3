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

import java.util.Optional;
import static shape.ShapeUtil.epsilonEquals;
import static shape.ShapeUtil.err;
import string.Message;

/**
 *
 * @author masao
 */
public class Ray3d {
    public TPoint3d start;
    public TVector3d direction;
    
    public Ray3d(TPoint3d start, TVector3d direction) {
        this.start = start;
        this.direction = direction;
    }
    
    public Ray3d(TPoint3d start, TPoint3d end) {
        this.start = start;
        this.direction = start.to(end);
    }
    
    public TVector3d normalize() {
        return direction.normalizeR();
    }
    
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Ray3d))
            return false;
        Ray3d t = (Ray3d)o;
        return equals(t, err);
    }
    
    boolean equals(Ray3d t, double err) {
        return start.epsilonEquals(t.start, err)&&direction.epsilonEquals(t.direction, err);
    }
    
    boolean equivalent(Ray3d t, double err) {
        return epsilonEquals(direction.dot(t.direction)/(direction.length()*t.direction.length()), 1)&&start.epsilonEquals(t.start, err);
    }
    
    public boolean equivalent(Ray3d t) {
        return equivalent(t, err);
    }
    
    public Line3d asLine() {
        return new Line3d(start, direction);
    }
    
    public Ray3d reverse() {
        return new Ray3d(start, direction.negateR());
    }
    
    public Ray3d move(TVector3d v) {
        return new Ray3d(start.moveR(v), direction);
    }
    
    /**
     * angle is narrower than PI/2
     * @param other
     * @return 
     */
    public boolean inSameSide(TVector3d other) {
        return direction.dot(other)>err;
    }
    
    public boolean otherGetsClosestOnSide(Line3d other) {
        if (parallel(other))
            return true;
        return otherGetsClosestOnSide(asLine().closestPoint(other));
    }
    
    public TPoint3d closestPoint(Line3d other) {
        assert !parallel(other);
        return otherGetsClosestOnSide(other)||other.hit(start)?asLine().closestPoint(other):start;
    }
    
    public boolean otherGetsClosestOnSide(Ray3d other) {
        if (parallel(other))
            return inSameSide(other.direction)||otherGetsClosestOnSide(other.start);
        return otherGetsClosestOnSide(asLine().closestPoint(other));
    }
    
    public boolean hasParallelPartOnSide(Ray3d other) {
        return parallel(other) && otherGetsClosestOnSide(other);
    }

    public TPoint3d closestPoint(Ray3d other) {
        assert !hasParallelPartOnSide(other);
        return otherGetsClosestOnSide(other)||other.hit(start)?asLine().closestPoint(other):start;
    }
    
    public boolean otherGetsClosestOnSide(Segment3d other) {
        if (parallel(other))
            return otherGetsClosestOnSide(other.start)||otherGetsClosestOnSide(other.end);
        return otherGetsClosestOnSide(asLine().closestPoint(other));
    }
    
    public boolean hasParallelPartOnSide(Segment3d other) {
        return parallel(other) && otherGetsClosestOnSide(other);
    }
    
    public TPoint3d closestPoint(Segment3d other) {
        assert !hasParallelPartOnSide(other);
        if (other.otherGetsClosestOnSide(this))
            return closestPoint(other.asLine());
        if (!other.asRay().otherGetsClosestOnSide(this))
            return closestPoint(other.start);
        return closestPoint(other.end);
    }
    
    public boolean otherGetsClosestOnSide(TPoint3d other) {
        return inSameSide(start.to(other));
    }
    
    public TPoint3d closestPoint(TPoint3d other) {
        return otherGetsClosestOnSide(other)?asLine().closestPoint(other):start;
    }
    
    public double distance(Line3d other) {
        return other.distance(this);
    }
    
    public double distance(TPoint3d other) {
        return otherGetsClosestOnSide(other)?asLine().distance(other):start.distance(other);
    }
    
    public double distance(Ray3d other) {
        if (parallel(other))
            return otherGetsClosestOnSide(other.start)||inSameSide(other.direction)?asLine().distance(other.start):start.distance(other.start);            
        return otherGetsClosestOnSide(other)?asLine().distance(other):other.distance(start);
    }
    
    public double distance(Segment3d other) {
        return other.distance(this);
    }
    
    public boolean hit(TPoint3d other) {
        return epsilonEquals(distance(other), 0);
    }

    /**
     * ray to segment hit.
     * segment is an intersection of two rays. thus this method is written in 
     * this way.
     * @param other
     * @return 
     */
    public boolean hit(Segment3d other) {
        return hit(other.asRay())&&hit(other.reverse().asRay());
    }
    
    /**
     * ray to ray hit.
     * if one of the complements (reverse()) of the two rays hits the opponent.asLine(),
     * those two rays do not hit. none of complements hits the opponent.asLine(), then 
     * those two rays hit somewhere. the complement doesn't hit the opponent.asLine() means 
     * the ray itself hits the opponent.asLine(). 
     * thus asLine.hit(other)&&hit(other.asLine()) means those two rays hit and otherwise
     * they do not hit, except when they are on one line. so in this method, situations 
     * when two rays are in one line differently. but fortunately, this case is easy to handle.
     * you can tell whether they hit or not only by checking the part of the opponent is on 
     * side of this ray and that logic is included in otherGetsClosestOnSide(ray).
     * 
     * @param other
     * @return 
     */
    public boolean hit(Ray3d other) {
        if (parallel(other)&&other.hit(asLine()))
            return otherGetsClosestOnSide(other);
        return other.hit(asLine())&&hit(other.asLine());
    }
    
    public boolean hit(Line3d other) {
        if (!epsilonEquals(other.distance(asLine()),0))
            return false;
        if (other.parallel(asLine()))
            return true;
        return otherGetsClosestOnSide(other)||other.hit(start);
    }
    
    public boolean parallel(Segment3d other) {
        return other.asRay().parallel(this);
    }
    
    public boolean parallel(Ray3d other) {
        return epsilonEquals(direction.cross(other.direction).length(), 0);
    }
    
    public boolean parallel(Line3d other) {
        return other.parallel(this);
    }

    public boolean doesSidedPartFormSegment(Ray3d other) {
        return otherGetsClosestOnSide(other.start)&&!inSameSide(other.direction);
    }
    
    /**
     * segment sided by other ray.
     * starts from this.start
     * @param other
     * @return 
     */
    Segment3d sidedPartSegmentBase(Ray3d other) {
        return new Segment3d(start, closestPoint(other.start));
    }
        
    public boolean doesSidedPartFormRay(Ray3d other) {
        return inSameSide(other.direction);
    }
    
    /**
     * ray sided by other ray.
     * @param other
     * @return 
     */
    Ray3d sidedPartRayBase(Ray3d other) {
        return new Ray3d(closestPoint(other.start), direction);
    }
    
    public Optional<Segment3d> sidedPartSegment(Ray3d other) {
        return doesSidedPartFormSegment(other)?Optional.of(sidedPartSegmentBase(other)):Optional.empty();
    }
    
    public Optional<Ray3d> sidedPartRay(Ray3d other) {
        return doesSidedPartFormRay(other)?Optional.of(sidedPartRayBase(other)):Optional.empty();
    }
    
    public boolean doesSidedPartFormSegment(Segment3d other) {
        return doesSidedPartFormSegment(other.asRay(), other.reverse().asRay()) ||
               doesSidedPartFormSegment(other.reverse().asRay(), other.asRay());
    }
    
    public Optional<Segment3d> sidedPartSegment(Segment3d other) {
        if (doesSidedPartFormSegment(other.asRay(), other.reverse().asRay()))
            return Optional.of(sidedPartSegment(other.asRay(), other.reverse().asRay()));
        if (doesSidedPartFormSegment(other.reverse().asRay(), other.asRay()))
            return Optional.of(sidedPartSegment(other.reverse().asRay(), other.asRay()));
        return Optional.empty();
    }
    
    boolean doesSidedPartFormSegment(Ray3d other1, Ray3d other2) {
        return doesSidedPartFormRay(other1)&&sidedPartRayBase(other1).doesSidedPartFormSegment(other2);
    }
    
    Segment3d sidedPartSegment(Ray3d other1, Ray3d other2) {
        return sidedPartRayBase(other1).sidedPartSegmentBase(other2);
    }
    @Override
    public String toString() {
        return Message.nl("Ray3d[").c(start).c(",").c(direction).c("]").toString();
    }
}
