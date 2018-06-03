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

package shape;

import static java.lang.Math.sqrt;

/**
 *
 * @author masao
 */
public class RightTriangleBy2Sides extends Triangle {
    double a;
    double b;
    
    public RightTriangleBy2Sides(double a, double b) {
        this.a = a;
        this.b = b;
    }
    
    @Override
    public double area() {
        return a*b/2;
    }
    
    @Override
    public double circumference() {
        return a+b+sqrt(a*a+b*b);
    }
}
