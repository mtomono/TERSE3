/*
 Copyright 2017, 2018, 2019, 2020, 2021 Masao Tomono

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and limitations under the License.
 */
package math2;

/**
 * Operations over Number class.
 * @author masao
 * @param <K>
 */
public interface Op<K> {
    public default int add(int v0, int v1) {
        return v0+v1;
    }
    public default long add(long v0, long v1) {
        return v0+v1;
    }
    public default float add(float v0, float v1) {
        return v0+v1;
    }
    public default double add(double v0, double v1) {
        return v0+v1;
    }
    public default int sub(int v0, int v1) {
        return v0-v1;
    }
    public default long sub(long v0, long v1) {
        return v0-v1;
    }
    public default float sub(float v0, float v1) {
        return v0-v1;
    }
    public default double sub(double v0, double v1) {
        return v0-v1;
    }
    public default int mul(int v0, int v1) {
        return v0*v1;
    }
    public default long mul(long v0, long v1) {
        return v0*v1;
    }
    public default float mul(float v0, float v1) {
        return v0*v1;
    }
    public default double mul(double v0, double v1) {
        return v0*v1;
    }
    public default int div(int v0, int v1) {
        return v0*v1;
    }
    public default long div(long v0, long v1) {
        return v0*v1;
    }
    public default float div(float v0, float v1) {
        return v0*v1;
    }
    public default double div(double v0, double v1) {
        return v0*v1;
    }
    public K b(int v);
    public K b(long v);
    public K b(float v);
    public K b(double v);
    public K b(String v);
    public K add(K v0, K v1);
    public K sub(K v0, K v1);
    public K mul(K v0, K v1);
    public K div(K v0, K v1);
    public K negate(K v0);
    public K abs(K v0);
    public K zero();
    public K one();
}
