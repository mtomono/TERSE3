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

import function.NoReach;
import static java.lang.Math.max;
import java.util.Optional;
import static shape.ShapeUtil.err;
import string.Message;

/**
 *
 * @author masao
 */
public class Segment3d {
    public TPoint3d start;
    public TPoint3d end;
    
    public Segment3d(TPoint3d start, TPoint3d end) {
        assert !start.epsilonEquals(end, err);
        this.start = start;
        this.end = end;
    }
    
    public TVector3d normalize() {
        return asVector().normalizeS();
    }
    
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Segment3d))
            return false;
        Segment3d t = (Segment3d)o;
        return equals(t, err);
    }
    
    boolean equals(Segment3d t, double err) {
        return start.epsilonEquals(t.start, err)&&end.epsilonEquals(t.end, err);
    }
    
    boolean equivalent(Segment3d t, double err) {
        return equals(t,err)||reverse().equals(t, err);
    }
    
    public boolean equivalent(Segment3d t) {
        return equals(t, err);
    }
    
    public double length() {
        return start.distance(end);
    }
    
    public Line3d asLine() {
        return new Line3d(start, start.to(end));
    }
    
    /**
     * convert to Ray from start to end
     * @return 
     */
    public Ray3d asRay() {
        return new Ray3d(start, start.to(end));
    }
    
    public Segment3d reverse() {
        return new Segment3d(end, start);
    }
    
    public Segment3d move(TVector3d move) {
        return new Segment3d(start.addR(move), end.addR(move));
    }
    
    public TVector3d asVector() {
        return start.to(end);
    }
    
    public boolean otherGetsClosestOnSide(Line3d other) {
        if (parallel(other))
            return true;
        return otherGetsClosestOnSide(asLine().closestPoint(other));
    }
    
    public TPoint3d closestPoint(Line3d other) {
        assert !parallel(other);
        if (Segment3d.this.otherGetsClosestOnSide(other))
            return asLine().closestPoint(other);
        if (!asRay().otherGetsClosestOnSide(other))
            return start;
        return end;
    }
    
    public boolean otherGetsClosestOnSide(Ray3d other) {
        if (parallel(other))
            return other.otherGetsClosestOnSide(this);
        return otherGetsClosestOnSide(asLine().closestPoint(other));
    }
    
    public boolean hasParallelPartOnSide(Ray3d other) {
        return parallel(other)&&otherGetsClosestOnSide(other);
    }
    
    public TPoint3d closestPoint(Ray3d other) {
        assert !hasParallelPartOnSide(other);
        if (Segment3d.this.otherGetsClosestOnSide(other))
            return asLine().closestPoint(other);
        if (!asRay().otherGetsClosestOnSide(other))
            return start;
        return end;
    }
    
    public boolean otherGetsClosestOnSide(Segment3d other) {
        if (parallel(other))
            return asRay().otherGetsClosestOnSide(other)&&reverse().asRay().otherGetsClosestOnSide(other);
        return otherGetsClosestOnSide(asLine().closestPoint(other));
    }
    
    public boolean hasParallelPartOnSide(Segment3d other) {
        return parallel(other)&&otherGetsClosestOnSide(other);
    }
    
    public TPoint3d closestPoint(Segment3d other) {
        assert !hasParallelPartOnSide(other);
        if (otherGetsClosestOnSide(other))
            return asLine().closestPoint(other);
        if (!asRay().otherGetsClosestOnSide(other))
            return start;
        return end;
    }
    
    public boolean otherGetsClosestOnSide(TPoint3d other) {
        return asRay().otherGetsClosestOnSide(other)&&reverse().asRay().otherGetsClosestOnSide(other);
    }
    
    public TPoint3d closestPoint(TPoint3d other) {
        if (otherGetsClosestOnSide(other))
            return asLine().closestPoint(other);
        if (!asRay().otherGetsClosestOnSide(other))
            return start;
        return end;
    }
    
    public double distance(TPoint3d other) {
        return closestPoint(other).distance(other);
    }
    
    public double distance(Line3d other) {
        if (Segment3d.this.otherGetsClosestOnSide(other))
            return other.distance(asLine());
        if (!asRay().otherGetsClosestOnSide(other))
            return other.distance(start);
        return other.distance(end);
    }
    
    public double distance(Ray3d other) {
        if (parallel(other))
            return max(other.distance(asRay()), other.distance(reverse().asRay()));
        if (otherGetsClosestOnSide(other))
            return other.distance(asLine());
        if (asRay().otherGetsClosestOnSide(other)) //other is near end
            return other.distance(end);
        return other.distance(start);
    }
    
    public double distance(Segment3d other) {
        if (parallel(other))
            return max(distance(other.asRay()), distance(other.reverse().asRay()));
        if (otherGetsClosestOnSide(other))
            return other.distance(asLine());
        if (asRay().otherGetsClosestOnSide(other)) //other is near end
            return other.distance(end);
        return other.distance(start);
    }
    
    public boolean hit(Segment3d other) {
        return asRay().hit(other)&&reverse().asRay().hit(other);
    }
    
    public boolean hit(Ray3d other) {
        return other.hit(this);
    }
    
    public boolean hit(Line3d other) {
        return other.hit(this);
    }
    
    public boolean hit(TPoint3d other) {
        return asRay().hit(other)&&reverse().asRay().hit(other);
    }
    
    public boolean parallel(Segment3d other) {
        return other.asRay().parallel(this);
    }
    
    public boolean parallel(Ray3d other) {
        return other.parallel(this);
    }
    
    public boolean parallel(Line3d other) {
        return other.parallel(this);
    }
    
    public boolean doesSidedPartFormSegment(Ray3d other) {
        return asRay().doesSidedPartFormSegment(other, reverse().asRay())||
               reverse().asRay().doesSidedPartFormSegment(other, asRay());
    }
    
    public boolean doesSidedPartFormSegment(Segment3d other) {
        return doesSidedPartFormSegment(other.asRay(), other.reverse().asRay()) || 
               doesSidedPartFormSegment(other.reverse().asRay(), other.asRay());
    }
    
    public Optional<Segment3d> sidedPartSegment(Ray3d other) {
        if (asRay().doesSidedPartFormSegment(other, reverse().asRay()))
            return Optional.of(asRay().sidedPartSegment(other, reverse().asRay()));
        if (reverse().asRay().doesSidedPartFormSegment(other, asRay()))
            return Optional.of(reverse().asRay().sidedPartSegment(other, asRay()).reverse());
        return Optional.empty();
    }
    
    public Optional<Segment3d> sidedPartSegment(Segment3d other) {
        return sidedPartSegment(other.asRay()).flatMap(s->s.sidedPartSegment(other.reverse().asRay()));
    }
        
    private boolean doesSidedPartFormSegment(Ray3d forward, Ray3d backward) {
        return doesSidedPartFormSegment(forward)&&sidedPartSegment(forward)
               .orElseThrow(new NoReach()).doesSidedPartFormSegment(backward);
    }
    
    @Override
    public String toString() {
        return Message.nl("Segment3d[").c(start).c(",").c(end).c("]").toString();
    }
}
