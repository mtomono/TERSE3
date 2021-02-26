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

import collection.c;
import java.util.Optional;
import static orderedSet.Builder.doubleRange;
import orderedSet.Range;
import static shape.ShapeUtil.err;
import static shape.ShapeUtil.point2;
import string.Message;

/**
 *
 * @author masao
 */
public class Rect2d {
    TPoint2d sw;
    TPoint2d ne;

    static public Rect2d c(double x0, double y0, double x1, double y1) {
        return c(new TPoint2d(x0, y0), new TPoint2d(x1, y1));
    }
    
    static public Rect2d c(TPoint2d x, TPoint2d y) {
        TPoint2d ne = newNe(x, y);
        TPoint2d sw = newSw(x, y);
        return new Rect2d(sw, ne);
    }
    
    static public Rect2d c(Segment2d s) {
        return c(s.start, s.end);
    }
    
    static public TPoint2d newNe(TPoint2d x, TPoint2d y) {
        return new TPoint2d(x.x>y.x?x.x:y.x, x.y>y.y?x.y:y.y);
    }
    
    static public TPoint2d newSw(TPoint2d x, TPoint2d y) {
        return new TPoint2d(x.x<y.x?x.x:y.x, x.y<y.y?x.y:y.y);
    }
    
    static public TPoint2d newSe(TPoint2d x, TPoint2d y) {
        return new TPoint2d(x.x>y.x?x.x:y.x, x.y<y.y?x.y:y.y);
    }
    
    static public TPoint2d newNw(TPoint2d x, TPoint2d y) {
        return new TPoint2d(x.x<y.x?x.x:y.x, x.y>y.y?x.y:y.y);
    }
    
    public Rect2d(TPoint2d sw, TPoint2d ne) {
        assert sw.x<=ne.x&&sw.y<=ne.y;
        this.sw = sw;
        this.ne = ne;
    }
    
    @Override
    public boolean equals(Object e) {
        if (!(e instanceof Rect2d))
            return false;
        Rect2d t = (Rect2d) e;
        return sw.epsilonEquals(t.sw, err)&&ne.epsilonEquals(ne, err);
    }
    
    public TPoint2d sw() {
        return sw;
    }
    
    public TPoint2d ne() {
        return ne;
    }
    
    public TPoint2d nw() {
        return new TPoint2d(sw.x, ne.y);
    }
    
    public TPoint2d se() {
        return new TPoint2d(ne.x, sw.y);
    }
    
    public TVector2d size() {
        return sw.to(ne);
    }
    
    public boolean inX(TPoint2d p) {
        return sw.x<=p.x&&p.x<=ne.x;
    }
    
    public boolean inY(TPoint2d p) {
        return sw.y<=p.y&&p.y<=ne.y;
    }
    
    public boolean in1quad(TPoint2d p) {
        return p.x>ne.x&&p.y>ne.y;
    }
    
    public boolean in2quad(TPoint2d p) {
        return p.x<sw.x&&p.y>ne.y;
    }
    
    public boolean in3quad(TPoint2d p) {
        return p.x<sw.x&&p.y<sw.y;
    }
    
    public boolean in4quad(TPoint2d p) {
        return p.x>ne.x&&p.y<sw.y;
    }
    
    public boolean contains(TPoint2d p) {
        return inX(p)&&inY(p);
    }
    
    public boolean contains(Rect2d r) {
        return contains(r.sw)&&contains(r.ne);
    }
    
    public boolean overlaps(Rect2d r) {
        return contains(r.sw)||contains(r.ne)||contains(r.se())||contains(r.nw());
    }
    
    public Rect2d cover(Rect2d r) {
        return new Rect2d(newSw(this.sw, r.sw), newNe(this.ne, r.ne));
    }
    
    public Optional<Rect2d> intersect(Rect2d r) {
        Optional<Rect2d> retval = intersectWith(r);
        return retval.isPresent()?retval:r.intersectWith(this);
    }
        
    private Optional<Rect2d> intersectWith(Rect2d r) {
        if (contains(r))
            return Optional.of(r);
        if (contains(r.ne)&&contains(r.nw()))
            return Optional.of(new Rect2d(newNe(sw, r.sw), r.ne));
        if (contains(r.ne)&&contains(r.se()))
            return Optional.of(new Rect2d(newNe(sw, r.sw), r.ne));
        if (contains(r.sw)&&contains(r.se()))
            return Optional.of(new Rect2d(r.sw, newSw(ne, r.ne)));
        if (contains(r.sw)&&contains(r.nw()))
            return Optional.of(new Rect2d(r.sw, newSw(ne, r.ne)));
        if (contains(r.ne))
            return Optional.of(new Rect2d(sw, r.ne));
        if (contains(r.sw))
            return Optional.of(new Rect2d(r.sw, ne));
        if (contains(r.nw()))
            return Optional.of(Rect2d.c(r.nw(), se()));
        if (contains(r.se()))
            return Optional.of(Rect2d.c(r.se(), nw()));
        return Optional.empty();
    }
    
    public double distance(TPoint2d p) {
        if (contains(p))
            return 0;
        if (in1quad(p))
            return p.distance(ne);
        if (in2quad(p))
            return p.distance(nw());
        if (in3quad(p))
            return p.distance(sw);
        if (in4quad(p))
            return p.distance(se());
        if (p.x<sw.x)
            return sw.x-p.x;
        if (p.x>ne.x)
            return p.x-ne.x;
        if (p.y<sw.y)
            return sw.y-p.y;
        if (p.y>ne.y)
            return p.y-ne.y;
        throw new RuntimeException("no reach");
    }
    
    public double farthest(TPoint2d p) {
        return c.a2l(ne, nw(), sw, se()).stream().mapToDouble(c->c.distance(p)).max().getAsDouble();
    }
    
    public Range<Double> range(TPoint2d p) {
        return doubleRange.r(distance(p), farthest(p));
    }
    
    public Optional<Dir> touchesEdge(TPoint2d p) {
        if (p.y >= ne.y)
            return Optional.of(Dir.n);
        if (p.x >= ne.x)
            return Optional.of(Dir.e);
        if (p.y <= sw.y)
            return Optional.of(Dir.s);
        if (p.x <= sw.x)
            return Optional.of(Dir.w);
        return Optional.empty();
    }
    
    public Rect2d margin(double m) {
        return new Rect2d(point2(sw.x-m, sw.y-m), point2(ne.x+m, ne.y+m));
    }
    
    public TPoint2d center() {
        return ne.subR(sw).scaleS(0.5).addS(sw);
    }
    
    public Rect2d scale(TPoint2d center, double ratio) {
        return new Rect2d(center.addR(center.to(sw).scaleS(ratio)), center.addR(center.to(ne).scaleS(ratio)));
    }
        
    public Rect2d move(TVector2d dir) {
        return new Rect2d(sw.addR(dir), ne.addR(dir));
    }
    
    /**
     * clip the move so that this rect would not go outside container.
     * @param container
     * @param move
     * @return 
     */
    public TVector2d enclose(Rect2d container, TVector2d move) {
        double x = move.x;
        double y = move.y;
        if (ne.addR(move).y >=container.ne.y)
            y=container.ne.y-ne.y;
        if (ne.addR(move).x >=container.ne.x)
            x=container.ne.x-ne.x;
        if (sw.addR(move).y <=container.sw.y)
            y=container.sw.y-sw.y;
        if (sw.addR(move).x <=container.sw.x)
            x=container.sw.x-sw.x;
        return new TVector2d(x, y);
    }
    
    public TVector2d enclose(Rect2d container) {
        return enclose(container, new TVector2d(0, 0));
    }
    
    @Override
    public String toString() {
        return Message.nl("[rect:").c(sw).c(ne).c(']').toString();
    }
}
