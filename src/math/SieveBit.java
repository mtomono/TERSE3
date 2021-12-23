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
package math;

import collection.TList;
import java.util.BitSet;

/**
 *
 * @author masao
 */
public class SieveBit {
    int max;
    int upto;
    BitSet flags;

    static BitSet init(int size) {
        BitSet retval = new BitSet(size);
        retval.clear();
        retval.set(0, size);
        return retval;
    }

    public static SieveBit patterned(int max, int preload) {
        int patternLength = SieveBit.simple(preload).exec().number().toC((i) -> i, C.i).pai().mul(C.i.b(Long.SIZE / 2)).body();
        int patternRepetition = max / patternLength + 1;
        long[] pattern = SieveBit.pattern(patternLength, preload).exec().flags.toLongArray();
        int patternLengthInLong = patternLength / Long.SIZE;
        long[] presetArray = new long[patternLength / Long.SIZE * patternRepetition];
        for (int i = 0; i < patternRepetition; i++) {
            for (int j = 0; j < patternLengthInLong; j++) {
                presetArray[j + patternLengthInLong * i] = pattern[j];
            }
        }
        BitSet preset = BitSet.valueOf(presetArray);
        preset.clear(1); // as a result, 1 is excluded.
        return new SieveBit(max, max, preset);
    }

    public static SieveBit simple(int max) {
        BitSet flags = init(max);
        flags.clear(0);
        flags.clear(1);
        return new SieveBit(max, max, flags);
    }

    public static SieveBit pattern(int max, int upto) {
        BitSet flags = init(max);
        flags.clear(0);
        return new SieveBit(max, upto, flags);
    }

    public SieveBit(int max, int upto, BitSet flags) {
        this.max = max;
        this.upto = (int) Math.ceil(Math.sqrt(upto));
        this.flags = flags;
    }

    public SieveBit exec() {
        for (int next=2;2<=next&&next<=upto;next=flags.nextSetBit(next)) {
            for (int i=next*next; i<max; i+=next) flags.clear(i);
            next++;
        }
        flags.clear(max, flags.size());
        return this;
    }

    public SieveBit set(int at, boolean state) {
        flags.set(at, state);
        return this;
    }

    public TList<Integer> number() {
        return flags.stream().mapToObj((i) -> i).collect(TList.toTList());
    }
    
}
