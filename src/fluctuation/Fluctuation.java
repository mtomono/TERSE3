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

package fluctuation;

import collection.P;
import collection.TList;
import static function.ComparePolicy.inc;
import function.CompareUtil;
import function.Holder;
import java.util.Objects;
import java.util.function.Function;
import math.Decimal;
import math2.C;
import math2.CList;
import orderedSet.Range;
import static orderedSet.RangeUtil.widthL;

/**
 *
 * @author masao
 * @param <K>
 */
public class Fluctuation<K extends Comparable<K>> {
    final public Builder<K> builder;
    final public Tq tq;
    final public Entries entries;
    final public Accumulates accumulates;
    
    static public <K extends Comparable<K>> Builder<K> builder(C.Builder<K> context) {
        return new Builder<>(context);
    }
        
    protected Fluctuation(TList<Long> time, CList<K> q, TList<P<Long,C<K>>> entries, TList<P<Range<Long>,C<K>>> accumulates, Builder<K> builder) {
        this.builder=builder;
        this.tq=new Tq(time,q);
        this.entries=new Entries(entries);
        this.accumulates=new Accumulates(accumulates);
    }
    public C<K> zero() {
        return builder.context.zero();
    }
    public C.Builder<K> b() {
        return builder.context;
    }
    public CompareUtil.map<C<K>,K> c() {
        return builder.compare;
    }
    public Fluctuation<K> normalize() {
        return entries.ordered().entries.simplify();
    }
    public Fluctuation<K> scale(C<K> scale) {
        return tq.scale(scale);
    }
    public Fluctuation<K> negate() {
        return tq.negate();
    }
    public Fluctuation<K> add(Fluctuation<K> other) {
        return entries.add(other);
    }
    public Fluctuation<K> sub(Fluctuation<K> other) {
        return add(other.negate());
    }
    public Fluctuation<K> cut(Range<Long> range) {
        return accumulates.cut(range);
    }
    public Fluctuation<K> round(Function<Fluctuation<K>,C<K>> f) {
        return accumulates.round(f);
    }
    public boolean sufficient() {
        return accumulates.nonnegative();
    }
    public TList<Fluctuation<K>> unchattering(C<K> limit) {
        return accumulates.unchattering(tq.noticeableChanges(limit));
    }
    public Fluctuation<K> accumulate() {
        return accumulates.accumulate();
    }
    
    public class Tq {
        final public TList<Long> time;
        final public CList<K> q;
        public Tq(TList<Long> time, CList<K> q) {
            this.time=time;
            this.q=q;
        }
        public Fluctuation<K> scale(C<K> scale) {
            return builder.tq(time, q.scale(scale));
        }
        public Fluctuation<K> negate() {
            return builder.tq(time, q.negate());
        }
        public Range<Long> cover() {
            assert !time.isEmpty():"time is empty";
            return new Range<>(time.minval(t->t).get(),time.maxval(t->t).get());
        }
        public Fluctuation<K> diff() {
            return builder.tq(time.seek(1),q.m(l->l.diff((a,b)->b.sub(a))));
        }
        public P<Range<Long>,C<K>> enclosure() {
            return P.p(new Range<>(time.get(0),time.last()),q.get(0));
        }
        public boolean nonnegative() {
            return q.body().forAll(x->c().ge(x,zero()));
        }
        public TList<Boolean> noticeableChanges(C<K> limit) {
            Holder<C<K>> h=new Holder<>(zero());
            return q.body().iterator().map(x->c().gt(h.set(h.get().add(x)).abs(),limit)).tee(t->h.set(t?zero():h.get())).asList();
        }
    }

    public class Entries {
        final public TList<P<Long,C<K>>> body;
        public Entries(TList<P<Long,C<K>>> entries) {
            this.body=entries;
        }
        public Fluctuation<K> add(Fluctuation<K> other) {
            return builder.entries(body.append(other.entries.body));
        }
        public Fluctuation<K> ordered() {
            return builder.entries(body.sfix().sortTo(inc(p->p.l())));
        }
        public Fluctuation<K> simplify() {
            return unify().entries.nonzero();
        }
        public Fluctuation<K> unify() {
            return builder.entries(body.diffChunk((a,b)->!a.l().equals(b.l())).filter(l->!l.isEmpty())
                    .map(l->P.p(l.get(0).l(), l.map(p->p.r()).stream().reduce((a,b)->a.add(b)).get())));
        }
        public Fluctuation<K> nonzero() {
            return builder.entries(body.filter(p->c().ne(p.r(),zero())));
        }
        public Fluctuation<K> enclose(P<Range<Long>,C<K>> enclosure) {
            return enclose(enclosure.l(),enclosure.r());
        }
        public Fluctuation<K> enclose(Range<Long> range, C<K> opening) {
            return builder.entries(body.filter(p->range.contains(p.l())).startFrom(P.p(range.start,opening)).append(P.p(range.end,zero())));
        }
        public Fluctuation<K> unenclose() {
            return builder.entries(body.seek(1).seek(-1));
        }
    }
    
    public class Accumulates {
        TList<P<Range<Long>,C<K>>> body;
        public Accumulates(TList<P<Range<Long>,C<K>>> body) {
            this.body=body;
        }
        public C<K> dot(Fluctuation<K> target) {
            return body.cross(target.accumulates.body, 
                    (a,b)->a.l().intersect(b.l()).map(t->b().b(widthL(t)).mul(a.r()).mul(b.r())))
                        .optionalMap(o->o).normalize()
                        .stream().reduce(zero(),(a,b)->a.add(b));
        }
        public Fluctuation<K> accumulate() {
            return builder.entries(body.map(x->P.p(x.l().start, x.r())));
        }
        /**
         * amount.
         * this can be considered as l1-distance in this vector space.
         * and the one fall short of the other in aspect of the amount can never
         * contain the other. thus, this can be used as an index of Fluctuations
         * Meaning, by sorting Fluctuations in aspect of amount will make it easier to 
         * find an Fluctuations fits in the other Fluctuations.
         * @return 
         */
        public C<K> amount() {
            return amounts().stream().reduce((a,b)->a.add(b)).orElse(zero());
        }
        public TList<C<K>> amounts() {
            return body.map(p->p.r().mul(b().b(widthL(p.l()))));
        }
        public Fluctuation<K> cut(Range<Long> range) {
            return builder.accumulates(body.optionalMap(p->range.intersect(p.l()).map(i->P.p(i,p.r()))).normalize());
        }
        public boolean nonnegative() {
            return body.forAll(x->c().ge(x.r(),zero()));
        }
        public Fluctuation<K> nonzero() {
            return builder.accumulates(body.filter(p->c().ne(p.r(),zero())));
        }
        TList<Fluctuation<K>> unchattering(TList<Boolean> noticeable) {
            return body.pair(noticeable).reverseChunk(p->p.r()).map(l->builder.accumulates(l.map(p->p.l())));
        }
        public Fluctuation<K> round(Function<Fluctuation<K>,C<K>> f) {
            if (body.isEmpty()) return builder.empty();
            return builder.accumulates(TList.wrap(P.p(tq.cover(), f.apply(Fluctuation.this))));
        }
    }

    static public <K extends Comparable<K>> Function<Fluctuation<K>, C<K>> max() {
        return e->e.accumulates.body.map(p->p.r()).max(p->p.get()).get();
    }
    static public <K extends Comparable<K>> Function<Fluctuation<K>, C<K>> min() {
        return e->e.accumulates.body.map(p->p.r()).min(p->p.get()).get();
    }
    static public <K extends Decimal<K>> Function<Fluctuation<K>, C<K>> average() {
        return e->e.accumulates.amount().div(e.b().b(widthL(e.tq.cover())));
    }
    
    public boolean epsilonEquals(Fluctuation<K> other, C<K> e) {
        return tq.time.equals(other.tq.time)&&tq.q.body().pair(other.tq.q.body(),(a,b)->a.sub(b).abs()).forAll(x->c().lt(x,e));
    }
    
    @Override
    public boolean equals(Object e) {
        if (e == null) {
            return false;
        }
        if (!(e instanceof Fluctuation)) {
            return false;
        }
        Fluctuation<K> t = (Fluctuation<K>) e;
        return accumulates.body.equals(t.accumulates.body);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 13 * hash + Objects.hashCode(this.accumulates);
        return hash;
    }
    
    @Override
    public String toString() {
        return accumulates.body.toString();
    }
}
