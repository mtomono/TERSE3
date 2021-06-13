/*
 Copyright 2021 Masao Tomono

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and limitations under the License.
 */
package math.matrix;

import collection.TList;
import iterator.TIterator;
import java.util.Optional;
import math.Context;
import math.ContextOrdered;
import static math.matrix.Householder.columnEraser;

/**
 * QR decomposition using Householder method.
 * I left several variation of decompose() in this class.
 * because all of them seemed to me similarly important.
 * i like decompose_with_accum() best.
 * @author masao
 * @param <K>
 * @param <T>
 */
public class HouseholderQrAlternatives {    
    public static <K, T extends Context<K,T>&ContextOrdered<K,T>> QR<K,T> decompose(CMatrix<K,T> target) {
        return decompose_with_accum(target);
    }
        
    public static <K, T extends Context<K,T>&ContextOrdered<K,T>> QR<K,T> decompose_with_accum(CMatrix<K,T> target) {
        return TIterator.range(0,target.y-1).accum(new QR<>(target.i().sfix(), target.sfix()),
                (qr,i)->{var qn=columnEraser(qr.r(),i);return new QR<>(qn.mul(qr.qinv()).sfix(),qn.mul(qr.r()).sfix());}).last();
    }
    
    public static <K, T extends Context<K,T>&ContextOrdered<K,T>> QR<K,T> decompose_with_accum_novar(CMatrix<K,T> target) {
        return TIterator.range(0,target.y-1).accum(new QR<>(target.i().sfix(), target.sfix()),
                (qr,i)->new QR<>(columnEraser(qr.r(),i).mul(qr.qinv()).sfix(),columnEraser(qr.r(),i).mul(qr.r()).sfix())).last();
    }

    public static <K, T extends Context<K,T>&ContextOrdered<K,T>> QR<K,T> decompose_with_accum_optional(CMatrix<K,T> target) {
        return TIterator.range(0,target.y-1).accum(new QR<>(target.i().sfix(), target.sfix()),
                (qr,i)->Optional.of(columnEraser(qr.r(),i)).map(qn->new QR<>(qn.mul(qr.qinv()).sfix(),qn.mul(qr.r()).sfix())).get()).last();
    }
    
    static <K, T extends Context<K,T>&ContextOrdered<K,T>> QR<K,T> next(QR<K,T> qr, int i) {
        var qn=columnEraser(qr.r(),i);
        return new QR<>(qn.mul(qr.qinv()).sfix(),qn.mul(qr.r()).sfix());
    }
    public static <K, T extends Context<K,T>&ContextOrdered<K,T>> QR<K,T> decompose_with_next(CMatrix<K,T> target) {
        return TIterator.range(0,target.y-1).accum(new QR<>(target.i().sfix(), target.sfix()),(a,b)->next(a,b)).last();
    }
    
    public static <K, T extends Context<K,T>&ContextOrdered<K,T>> QR<K,T> decompose_with_for(CMatrix<K,T> target) {
        CMatrix<K,T> rh=target.sfix();
        CMatrix<K,T> qh=target.i().sfix();
        for(int i=0;i<target.y-1;i++) {
            var lq=columnEraser(rh,i);
            rh=lq.mul(rh).sfix();
            qh=lq.mul(qh).sfix();
        }
        return new QR<>(TList.sof(qh,rh));
    }
}
