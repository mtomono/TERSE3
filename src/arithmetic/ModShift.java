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

package arithmetic;

/**
 * mod which maps 1,2,3,4,5,6 -> 1,2,3,4,5,1
 * @author mtomono
 */
public class ModShift extends Mod {
    @Override
    public int o(int a, int b) { return super.o(a-1,b)+1; }
    @Override
    public long o(long a, long b) { return super.o(a-1,b)+1; }
    @Override
    public long o(int a, long b) { return super.o(a-1,b)+1; }
    @Override
    public long o(long a, int b) { return super.o(a-1,b)+1; }
    @Override
    public double o(double a, double b) { return super.o(a-1,b)+1; }
    @Override
    public double o(int a, double b) { return super.o(a-1,b)+1; }
    @Override
    public double o(long a, double b) { return super.o(a-1,b)+1; }
    @Override
    public double o(double a, int b) { return super.o(a-1,b)+1; }
    @Override
    public double o(double a, long b) { return super.o(a-1,b)+1; }
}
