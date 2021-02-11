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
import math2.C;
import math2.CList;
import orderedSet.Range;

/**
 * Builder of Fluctuation.
 * @author masao
 * @param <K>
 */
public class Builder<K extends Comparable<K>> {
    C.Builder<K> context;
    protected Builder(C.Builder<K> context) {
        this.context=context;
    }
    public abstract class Building {
        TList<Long> time() {
            return entries().map(p->p.l());
        }
        CList<K> q() {
            return new CList<>(context,entries().map(p->p.r()));
        }
        TList<P<Long,C<K>>> entries() {
            return time().pair(q().body());
        }
        TList<P<Range<Long>,C<K>>> accumulates() {
            return time().diff((a,b)->new Range<>(a,b)).pair(q().body().accumFromStart(s->s,(a,b)->a.add(b)));
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
        CList<K> q() {
            return new CList<K>(context,accumulates().flatMapc(p->TList.sof(p.r(),p.r().negate())));
        }
    }
    public class TqBuilding extends Building {
        TList<Long> time;
        CList<K> q;
        TqBuilding(TList<Long> time, CList<K> q) {
            this.time=time;
            this.q=q;
        }
        @Override
        TList<Long> time() {
            return time;
        }
        @Override
        CList<K> q() {
            return q;
        }
    }
    public class EntriesBuilding extends Building {
        TList<P<Long,C<K>>> entries;
        EntriesBuilding(TList<P<Long,C<K>>> entries) {
            this.entries=entries;
        }
        @Override
        TList<P<Long,C<K>>> entries() {
            return entries;
        }
    }
    public class AccumulatesBuilding extends BuildingR {
        TList<P<Range<Long>,C<K>>> accumulates;
        AccumulatesBuilding(TList<P<Range<Long>,C<K>>> accumulates) {
            this.accumulates=accumulates;
        }
        @Override
        TList<P<Range<Long>,C<K>>> accumulates() {
            return accumulates;
        }
    }
    public Fluctuation<K> tq(TList<Long> time, CList<K> q) {
        return new TqBuilding(time,q).build();
    }
    public Fluctuation<K> entries(TList<P<Long,C<K>>> entries) {
        return new EntriesBuilding(entries).build();
    }
    public Fluctuation<K> accumulates(TList<P<Range<Long>,C<K>>> accumulates) {
        return new AccumulatesBuilding(accumulates).build();
    }
    public Fluctuation<K> empty() {
        return new Fluctuation<>(TList.empty(),CList.c(context,TList.empty()),TList.empty(),TList.empty(),this);
    }
    
    public EntryBuilder entries() {
        return new EntryBuilder();
    }
    public AccumulateBuilder accumulates() {
        return new AccumulateBuilder();
    }
    
    public class EntryBuilder {
        TList<P<Long,C<K>>> body;
        public EntryBuilder() {
            this.body=TList.c();
        }
        public EntryBuilder a(long at, C<K> q) {
            body.add(P.p(at, q));
            return this;
        }
        public Fluctuation<K> build() {
            return entries(body);
        }
    }
    
    public class AccumulateBuilder {
        TList<P<Range<Long>,C<K>>> body;
        public AccumulateBuilder() {
            this.body=TList.c();
        }
        public AccumulateBuilder a(long from, long to, C<K> q) {
            body.add(P.p(new Range<>(from,to),q));
            return this;
        }
        public Fluctuation<K> build() {
            return accumulates(body);
        }
    }
}
