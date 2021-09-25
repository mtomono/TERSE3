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

import iterator.TIterator;
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
public class HouseholderQR<K, T extends Context<K,T>&ContextOrdered<K,T>> {
    public static <K, T extends Context<K,T>&ContextOrdered<K,T>> QR<K,T> decompose(CMatrix<K,T> target) {
        return TIterator.range(0,target.columns-1).accum(new QR<>(target.i().sfix(), target.sfix()),
                (qr,i)->{var qn=columnEraser(qr.r(),i);return new QR<>(qn.mul(qr.qinv()).sfix(),qn.mul(qr.r()).sfix());}).last();
    }
}
