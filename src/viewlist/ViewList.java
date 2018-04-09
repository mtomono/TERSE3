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

package viewlist;

import java.util.AbstractList;
import function.TIntSupplier;
import static java.lang.Math.max;

/**
 *
 * @author mtomono
 * @param <E>
 */
public class ViewList<E> extends AbstractList<View<E>> {
    TIntSupplier start;
    TIntSupplier end;
    View<E> view;
    
    /**
     * 0___4x                 0___4x
     * |                      x
     * @param <E>
     * @param view
     * @return 
     */
    public static <E> ViewList<E> natural(View<E> view) {
        return new ViewList<>(view, view.start(), view.end());
    }

    /**
     * 0___4x              0___4x
     *     |                   x
     * @param <E>
     * @param view
     * @return 
     */ 
    
    public static <E> ViewList<E> reverse(View<E> view) {
        return new ViewList<>(view, view.start().andThenInt(v->v+1-view.size()), view.end().andThenInt(v->v+1-view.size()));
    }

    /**
     * 0___4x                   0___4x
     *     |                    x
     * @param <E>
     * @param view
     * @return 
     */
    public static <E> ViewList<E> outer(View<E> view) {
        return new ViewList<>(view, view.start().andThenInt(v->v+1-view.size()), view.end());
    }
    
    /**
     * 0___4x            0___4x
     * |                     x
     * @param <E>
     * @param view
     * @return 
     */
    public static <E> ViewList<E> inner(View<E> view) {
        return new ViewList<>(view, view.start(), view.end().andThenInt(v->v+1-view.size()));
    }

    public ViewList(View<E> base, TIntSupplier start, TIntSupplier end) {
        this.view = base;
        this.start = start;
        this.end = end;
    }
    
    public View<E> getView() {
        return view;
    }
    
    @Override
    public View<E> get(int index) {
        return view.index(index + start.getAsInt());
    }

    @Override
    public int size() {
        return max(end.getAsInt() - start.getAsInt(), 0);
    }
    
}
