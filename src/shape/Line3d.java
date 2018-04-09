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
import string.Message;
import static shape.ShapeUtil.epsilonEquals;
import static shape.ShapeUtil.err;

/**
 *
 * @author masao
 */
public class Line3d {
    public TVector3d direction;
    public TPoint3d start;
    
    public Line3d(TPoint3d start, TVector3d direction) {
        this.start = start;
        this.direction = direction;
    }
    
    public TVector3d normalize() {
        return direction.normalizeR();
    }
    
    /**
     * equals.
     * IMPORTANT DESIGN DECISION
     * considering that the eplilon accordance will be the ordinary usage for this class, 
     * the main equality should follow it. for a very special case which needs exact accordance, 
     * use equals(Line3d t, double err). it may sounds contrary, but you can give 0 to err and 
     * do the exact matching. as a consequence of this decision, this class abandoned hash-ability, 
     * meaning, cannot be used as a key of a hashmap. but who cares? from the beginning, this kind
     * of class is not suitable for hashmap key.
     * @param o
     * @return 
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Line3d))
            return false;
        Line3d t = (Line3d)o;
        return equals(t, err);
    }
    
    boolean equals(Line3d t, double err) {
        return start.epsilonEquals(t.start, err)&&direction.epsilonEquals(t.direction, err);
    }
    
    boolean equivalent(Line3d t, double err) {
        return parallel(t)&&epsilonEquals(distance(t.start), 0);
    }
    
    public boolean equivalent(Line3d t) {
        return equivalent(t, err);
    }
    
    public Ray3d asRay() {
        return new Ray3d(start, direction);
    }
    
    public Line3d reverse() {
        return new Line3d(start, direction.negateR());
    }
    
    public Line3d move(TVector3d v) {
        return new Line3d(start.moveR(v), direction);
    }
    
    public TVector3d perpendicular(TPoint3d other) {
        return closestPoint(other).from(other);
    }
    
    /**
     * Segment corresponds to the distance between two lines.
     * when two lines were described like below:
     * this : l1 = t*d1+s1
     * other : l2 = s*d2+s2
     * let's call vector that moves l1 to have intersect with l2 as gap and call the l1 moved by gap
     * as l1' = u*d1+s1+gap. gap is vertical to d1 and d2. 
     * calculate the intersect i between l1' and l2 and i-gap->i is the result
     * @param other
     * @return a segment connects between this line and the other line. start is on this line and end is on the other line.
     */
    public Segment3d closestSegment(Line3d other) {
        assert !parallel(other);
        assert !epsilonEquals(distance(other), 0);
        TVector3d gapBase = direction.cross(other.direction).sizeS(distance(other));
        TVector3d gap = epsilonEquals(move(gapBase).distance(other), 0)?gapBase:gapBase.negateR();
        assert epsilonEquals(move(gap).distance(other), 0);
        TPoint3d i = move(gap).intersect(other, gap);
        return new Segment3d(i.moveR(gap.negateR()), i);
    }
    
    public TPoint3d intersect(Line3d other) {
        assert !parallel(other);
        return intersect(other, direction.cross(other.direction));
    }
    
    /**
     * calculate intersect for two lines whose distance is 0.
     * consider two lines were described like below:
     * this : l1 = t*d1+s1
     * other : l2 = s*d2+s2
     * let's call something vertical to d1 and d2 as z-axis. l1 and l2 has intersect, thus they constitute
     * a plane P. 
     * consider coodinate transformation W=(d1, d2, gap)
     * inv W will transform plane P into xy plane. s1 and s2 is a point on l1 and l2. 
     * let's call the intersect between l1 and l2 as i, s1-i as q1, and s2-i as q2. q1 is on l1 and q2 is
     * on l2, so they will be transformed onto x axis and y axis respectively. q2-q1 = s2-s1. 
     * so when s2-s1 is transformed into t by inv W, then q1=(-t.x, 0, 0) q2=(0, t.y, 0).
     * if q2 is transformed into r2 by W, then i = s2-r2.
     * @param other
     * @param zaxis
     * @return 
     */
    public TPoint3d intersect(Line3d other, TVector3d zaxis) {
        assert epsilonEquals(this.distance(other), 0);
        TMatrix3d w = TMatrix3d.coordinateTransform(direction, other.direction, zaxis);
        TMatrix3d invw = w.invertR();
        TVector3d t = start.to(other.start).transformS(invw);
        return other.start.moveR(new TVector3d(0, t.y, 0).transformS(w).negateS());
    }
    
    /**
     * closest point on this line to other
     * @param other
     * @return 
     */
    public TPoint3d closestPoint(Line3d other) {
        assert !parallel(other);
        return distance(other)==0?intersect(other):closestSegment(other).start;
    }
    
    public TPoint3d closestPoint(Ray3d other) {
        assert !parallel(other);
        if (other.otherGetsClosestOnSide(this))
            return closestPoint(other.asLine());
        return closestPoint(other.start);
    }
    
    public TPoint3d closestPoint(Segment3d other) {
        assert !parallel(other);
        if (other.otherGetsClosestOnSide(this)) 
            return closestPoint(other.asLine());
        if (!other.asRay().otherGetsClosestOnSide(this))
            return closestPoint(other.start);
        return closestPoint(other.end);
    }
    
    public TPoint3d closestPoint(TPoint3d other) {
        return start.epsilonEquals(other, err)?start:
               start.moveR(direction.sizeR(start.to(other).dot(direction.normalizeR())));
    }
    

    public double distance(Line3d other) {
        if (parallel(other))
            return distance(other.start);
        return abs(start.to(other.start).dot(direction.cross(other.direction).normalizeS()));
    }
    
    public double distance(Ray3d other) {
        if (parallel(other))
            return distance(other.start);
        return other.otherGetsClosestOnSide(other.asLine().closestPoint(this))?distance(other.asLine()):distance(other.start);
    }
    
    /**
     * distance to a segment.
     * when other is considered as a line and distance was 0.
     * if intersect is on segment then rays of both sides of segment should have
     * distance 0. otherwise, one of the rays should have distance more than 0.
     * thus, in this case, distance should be max of distance of rays.
     * 
     * when other is considered as a line and distance was not 0.
     * but the similar thing is true when you consider the relative position with
     * closestSegment's end of the other's side instead of intersect.
     * thus, all the cases are calculated by max of distance of rays.
     * @param other
     * @return 
     */
    public double distance(Segment3d other) {
        return max(distance(other.asRay()), distance(other.reverse().asRay()));
    }
    
    public double distance(TPoint3d other) {
        if (start.epsilonEquals(other, err))
            return 0;
        return start.to(other).cross(direction).length()/direction.length();
    }
    
    public boolean hit(TPoint3d other) {
        return epsilonEquals(distance(other), 0);
    }
    
    public boolean hit(Segment3d other) {
        return other.asRay().hit(this)&&other.reverse().asRay().hit(this);
    }

    public boolean hit(Ray3d other) {
        return other.hit(this);
    }
    
    public boolean hit(Line3d other) {
        return epsilonEquals(distance(other), 0);
    }
    
    public boolean parallel(Segment3d other) {
        return other.asRay().parallel(asRay());
    }
    
    public boolean parallel(Ray3d other) {
        return other.parallel(asRay());
    }
    
    public boolean parallel(Line3d other) {
        return other.asRay().parallel(asRay());
    }
    
    @Override
    public String toString() {
        return Message.nl("Line3d[").c(start).c(",").c(direction).c("]").toString();
    }
}
