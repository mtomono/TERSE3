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

package collection;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;

/**
 * wrapping and unwrapping for arrays of primitive types like double, int, or long.
 * @author masao
 */
public class PrimitiveArrayWrap {
    public static List<java.lang.Double> wrap(double[] body) {
        return new Double(body);
    }
    public static List<java.lang.Integer> wrap(int... body) {
        return new Integer(body);
    }
    public static List<java.lang.Byte> wrap(byte... body) {
        return new Byte(body);
    }
    public static List<java.lang.Long> wrap(long... body) {
        return new Long(body);
    }
    public static double[] unwrapD(List<java.lang.Double> body) {
        double[] retval = new double[body.size()];
        Iterator<java.lang.Double> iter = body.iterator();
        int i = 0;
        while (iter.hasNext())
            retval[i++]=iter.next();
        return retval;
    }
    public static int[] unwrapI(List<java.lang.Integer> body) {
        int[] retval = new int[body.size()];
        Iterator<java.lang.Integer> iter = body.iterator();
        int i = 0;
        while (iter.hasNext())
            retval[i++]=iter.next();
        return retval;
    }
    public static byte[] unwrapB(List<java.lang.Byte> body) {
        byte[] retval = new byte[body.size()];
        Iterator<java.lang.Byte> iter = body.iterator();
        int i = 0;
        while (iter.hasNext())
            retval[i++]=iter.next();
        return retval;
    }
    public static long[] unwrapL(List<java.lang.Long> body) {
        long[] retval = new long[body.size()];
        Iterator<java.lang.Long> iter = body.iterator();
        int i = 0;
        while (iter.hasNext())
            retval[i++]=iter.next();
        return retval;
    }
    public static class Double extends AbstractList<java.lang.Double> {
        double[] body;
        public Double(double[] body) {
            this.body = body;
        }

        @Override
        public java.lang.Double get(int index) {
            return body[index];
        }

        @Override
        public int size() {
            return body.length;
        }
        
        @Override
        public java.lang.Double set(int index, java.lang.Double v) {
            java.lang.Double retval = body[index];
            body[index] = v;
            return retval;
        }
    }

    public static class Byte extends AbstractList<java.lang.Byte> {
        byte[] body;
        public Byte(byte[] body) {
            this.body = body;
        }

        @Override
        public java.lang.Byte get(int index) {
            return body[index];
        }

        @Override
        public int size() {
            return body.length;
        }
        
        @Override
        public java.lang.Byte set(int index, java.lang.Byte v) {
            java.lang.Byte retval = body[index];
            body[index] = v;
            return retval;
        }
    }

    public static class Integer extends AbstractList<java.lang.Integer> {
        int[] body;
        public Integer(int[] body) {
            this.body = body;
        }

        @Override
        public java.lang.Integer get(int index) {
            return body[index];
        }

        @Override
        public int size() {
            return body.length;
        }
        
        @Override
        public java.lang.Integer set(int index, java.lang.Integer v) {
            java.lang.Integer retval = body[index];
            body[index] = v;
            return retval;
        }
    }

    public static class Long extends AbstractList<java.lang.Long> {
        long[] body;
        public Long(long[] body) {
            this.body = body;
        }

        @Override
        public java.lang.Long get(int index) {
            return body[index];
        }

        @Override
        public int size() {
            return body.length;
        }
        
        @Override
        public java.lang.Long set(int index, java.lang.Long v) {
            java.lang.Long retval = body[index];
            body[index] = v;
            return retval;
        }
    }
}
