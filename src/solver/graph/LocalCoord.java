/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solver.graph;

import collection.TList;
import shape.TMatrix3d;
import shape.TPoint3d;
import shape.TVector3d;

/**
 *
 * @author masao
 */
public class LocalCoord {
    final public TList<TVector3d> bases;
    final public TPoint3d origin;
    final public TMatrix3d globalize;
    final public TMatrix3d localize;
    public LocalCoord(TList<TVector3d> bases, TPoint3d origin) {
        this.bases=bases;
        this.origin=origin;
        this.globalize=TMatrix3d.coordinateTransform(bases.get(0), bases.get(1), bases.get(2));
        this.localize=globalize.invertR();
    }
    
    public TVector3d localize(TVector3d v) {
        return localize.transformToVector(v);
    }

    public TPoint3d localize(TPoint3d p) {
        return localize.transformToPoint(p.subR(origin));
    }
    
    public TVector3d globalize(TVector3d v) {
        return globalize.transformToVector(v);
    }
    
    public TPoint3d globalize(TPoint3d p) {
        return globalize.transformToPoint(p).addR(origin);
    }
}
