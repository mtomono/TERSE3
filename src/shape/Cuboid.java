/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shape;

import collection.TList;

/**
 *
 * @author masao
 */
public class Cuboid {
    final public TPoint3d llb;//left-lower-bottom
    final public TList<TVector3d> basis;//xyz
    final public TMatrix4d orthodox;
    final public TMatrix4d unorthodox;
    
    public Cuboid(TPoint3d llb, TList<TVector3d> basis) {
        this.llb=llb;
        this.basis=basis;
        this.unorthodox=TMatrix4d.affineTransform(basis.get(0), basis.get(1), basis.get(2), new TVector3d(llb));
        this.orthodox=unorthodox.invertAffine();
    }
    
    public TPoint3d localize(TPoint3d p) {
        return orthodox.affine(p);
    }
    
    public TPoint3d globalize(TPoint3d p) {
        return unorthodox.affine(p);
    }
    
}
