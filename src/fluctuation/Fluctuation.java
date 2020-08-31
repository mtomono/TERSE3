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
import function.Holder;
import java.util.Objects;
import java.util.function.Function;
import math.Decimal;
import math.MathContext;
import orderedSet.Range;
import static orderedSet.RangeUtil.widthL;

/**
 *
 * @author masao
 * @param <K>
 */
public class Fluctuation<K extends Decimal<K>> {
    final public Builder<K> builder;
    final public Tq tq;
    final public Entries entries;
    final public Accumulates accumulates;
    
    static public <K extends Decimal<K>> Builder<K> builder(Class<K> context) {
        return builder(new MathContext<>(context));
    }
    static public <K extends Decimal<K>> Builder<K> builder(MathContext<K> context) {
        return new Builder<>(context);
    }
        
    protected Fluctuation(TList<Long> time, TList<K> q, TList<P<Long,K>> entries, TList<P<Range<Long>,K>> accumulates, Builder<K> builder) {
        this.builder=builder;
        this.tq=new Tq(time,q);
        this.entries=new Entries(entries);
        this.accumulates=new Accumulates(accumulates);
    }
        public Fluctuation<K> normalize() {
        return entries.ordered().entries.simplify();
    }
    public Fluctuation<K> scale(K scale) {
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
    public Fluctuation<K> round(Function<Fluctuation<K>,K> f) {
        return accumulates.round(f);
    }
    public boolean sufficient() {
        return accumulates.nonnegative();
    }
    public TList<Fluctuation<K>> unchattering(K limit) {
        return accumulates.unchattering(tq.noticeableChanges(limit));
    }
    public Fluctuation<K> accumulate() {
        return accumulates.accumulate();
    }
    
    public class Tq {
        final public TList<Long> time;
        final public TList<K> q;
        public Tq(TList<Long> time, TList<K> q) {
            this.time=time;
            this.q=q;
        }
        public Fluctuation<K> scale(K scale) {
            return builder.tq(time, q.map(x->x.mul(scale)));
        }
        public Fluctuation<K> negate() {
            return builder.tq(time, q.map(x->x.negate()));
        }
        public Range<Long> cover() {
            assert !time.isEmpty():"time is empty";
            return new Range<>(time.minval(t->t).get(),time.maxval(t->t).get());
        }
        public Fluctuation<K> diff() {
            return builder.tq(time.seek(1),q.diff((a,b)->b.sub(a)));
        }
        public P<Range<Long>,K> enclosure() {
            return P.p(new Range<>(time.get(0),time.last()),q.get(0));
        }
        public boolean nonnegative() {
            return q.forAll(x->!x.belowZero());
        }
        public TList<Boolean> noticeableChanges(K limit) {
            Holder<K> h=new Holder<>(builder.context.zero());
            return q.iterator().map(x->h.set(h.get().add(x)).abs().gt(limit)).tee(t->h.set(t?builder.context.zero():h.get())).asList();
        }
    }

    public class Entries {
        final public TList<P<Long,K>> body;
        public Entries(TList<P<Long,K>> entries) {
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
                    .map(l->P.p(l.get(0).l(), l.toK(builder.context).sigma(p->p.r()))));
        }
        public Fluctuation<K> nonzero() {
            return builder.entries(body.filter(p->!p.r().isZero()));
        }
        public Fluctuation<K> enclose(P<Range<Long>,K> enclosure) {
            return enclose(enclosure.l(),enclosure.r());
        }
        public Fluctuation<K> enclose(Range<Long> range, K opening) {
            return builder.entries(body.filter(p->range.contains(p.l())).startFrom(P.p(range.start,opening)).append(P.p(range.end,builder.context.zero())));
        }
        public Fluctuation<K> unenclose() {
            return builder.entries(body.seek(1).seek(-1));
        }
    }
    
    public class Accumulates {
        TList<P<Range<Long>,K>> body;
        public Accumulates(TList<P<Range<Long>,K>> body) {
            this.body=body;
        }
        public K dot(Fluctuation<K> target) {
            return body.cross(target.accumulates.body, (a,b)->a.r().mul(b.r()).mul(a.l().intersect(b.l()).map(t->widthL(t)).orElse(0L))).stream().reduce(builder.context.zero(), (a,b)->a.add(b));
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
        public K amount() {
            return amounts().toK(builder.context).sigma(k->k);
        }
        public TList<K> amounts() {
            return body.map(p->p.r().mul(widthL(p.l())));
        }
        public Fluctuation<K> cut(Range<Long> range) {
            return builder.accumulates(body.optionalMap(p->range.intersect(p.l()).map(i->P.p(i,p.r()))).normalize());
        }
        public boolean nonnegative() {
            return body.forAll(x->!x.r().belowZero());
        }
        public Fluctuation<K> nonzero() {
            return builder.accumulates(body.filter(p->!p.r().isZero()));
        }
        TList<Fluctuation<K>> unchattering(TList<Boolean> noticeable) {
            return body.pair(noticeable).reverseChunk(p->p.r()).map(l->builder.accumulates(l.map(p->p.l())));
        }
        public Fluctuation<K> round(Function<Fluctuation<K>,K> f) {
            if (body.isEmpty()) return builder.empty();
            return builder.accumulates(TList.wrap(P.p(tq.cover(), f.apply(Fluctuation.this))));
        }
    }

    static public <K extends Decimal<K>> Function<Fluctuation<K>, K> max() {
        return e->e.accumulates.body.maxval(p->p.r()).get();
    }
    static public <K extends Decimal<K>> Function<Fluctuation<K>, K> min() {
        return e->e.accumulates.body.minval(p->p.r()).get();
    }
    static public <K extends Decimal<K>> Function<Fluctuation<K>, K> average() {
        return e->e.accumulates.amount().div(widthL(e.tq.cover()));
    }
    
    public boolean epsilonEquals(Fluctuation<K> other, K e) {
        return tq.time.equals(other.tq.time)&&tq.q.pair(other.tq.q,(a,b)->a.sub(b).abs().lt(e)).forAll(x->x);
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
