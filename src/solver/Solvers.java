/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solver;

import collection.TList;
import java.util.function.ToIntFunction;
import static shape.ShapeUtil.p2i;
import shape.TPoint2i;

/**
 *
 * @author masao
 */
public class Solvers {
    public static <T> TList<TPoint2i> extract2i(TList<T> body, ToIntFunction<T> volume, ToIntFunction<T> value) {
        return body.map((T x)->p2i(volume.applyAsInt(x), value.applyAsInt(x))).fix();
    }
    
}
