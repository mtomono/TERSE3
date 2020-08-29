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
import math.Decimal;
import math.MathContext;
import orderedSet.Range;

/**
 * Builder of Fluctuation.
 * @author masao
 * @param <K>
 */
public class Builder<K extends Decimal<K>> {
    MathContext<K> base;
    protected Builder(MathContext<K> context) {
        this.base=context;
    }
    public abstract class Building {
        TList<Long> time() {
            return entries().map(p->p.l());
        }
        TList<K> q() {
            return entries().map(p->p.r());
        }
        TList<P<Long,K>> entries() {
            return time().pair(q());
        }
        TList<P<Range<Long>,K>> accumulates() {
            return time().diff((a,b)->new Range<>(a,b)).pair(q().accumFromStart(s->s,(a,b)->a.add(b)));
        }
        Fluctuation<K> build() {
            return new Fluctuation<>(time(),q(),entries(),accumulates(),Builder.this);
        }
    }
    public abstract class BuildingR extends Building {
        @Override
        TList<Long> time() {
            return accumulates().flatMapc(p->TList.sof(p.l().start,p.l().end));
        }
        @Override
        TList<K> q() {
            return accumulates().flatMapc(p->TList.sof(p.r(),p.r().negate()));
        }
    }
    public class TqBuilding extends Building {
        TList<Long> time;
        TList<K> q;
        TqBuilding(TList<Long> time, TList<K> q) {
            this.time=time;
            this.q=q;
        }
        @Override
        TList<Long> time() {
            return time;
        }
        @Override
        TList<K> q() {
            return q;
        }
    }
    public class EntriesBuilding extends Building {
        TList<P<Long,K>> entries;
        EntriesBuilding(TList<P<Long,K>> entries) {
            this.entries=entries;
        }
        @Override
        TList<P<Long,K>> entries() {
            return entries;
        }
    }
    public class AccumulatesBuilding extends BuildingR {
        TList<P<Range<Long>,K>> accumulates;
        AccumulatesBuilding(TList<P<Range<Long>,K>> accumulates) {
            this.accumulates=accumulates;
        }
        @Override
        TList<P<Range<Long>,K>> accumulates() {
            return accumulates;
        }
    }
    public Fluctuation<K> tq(TList<Long> time, TList<K> q) {
        return new TqBuilding(time,q).build();
    }
    public Fluctuation<K> entries(TList<P<Long,K>> entries) {
        return new EntriesBuilding(entries).build();
    }
    public Fluctuation<K> accumulates(TList<P<Range<Long>,K>> accumulates) {
        return new AccumulatesBuilding(accumulates).build();
    }
    public Fluctuation<K> empty() {
        return new Fluctuation<>(TList.empty(),TList.empty(),TList.empty(),TList.empty(),this);
    }
    
    public EntryBuilder entries() {
        return new EntryBuilder();
    }
    public ExtentBuilder accumulates() {
        return new ExtentBuilder();
    }
    
    public class EntryBuilder {
        TList<P<Long,K>> body;
        public EntryBuilder() {
            this.body=TList.c();
        }
        public EntryBuilder a(long at, K q) {
            body.add(P.p(at, q));
            return this;
        }
        public Fluctuation<K> build() {
            return entries(body);
        }
    }
    
    public class ExtentBuilder {
        TList<P<Range<Long>,K>> body;
        public ExtentBuilder() {
            this.body=TList.c();
        }
        public ExtentBuilder a(long from, long to, K q) {
            body.add(P.p(new Range<>(from,to),q));
            return this;
        }
        public Fluctuation<K> build() {
            return accumulates(body);
        }
    }
}
