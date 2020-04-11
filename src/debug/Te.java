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

package debug;

import test.PTe;

/**
 * Tee.
 * every implementation is inherited from PTe.
 * Te is a class often times reference is prohibited.
 * thus, this way of inheritance.
 * and made PTe as home to their implementation.
 * because often times, reference to Te are clensed.
 * if Te has the implementation, PTe has to contain reference to Te.
 * and that reference would be on radar everytime we do the sanity check.
 * @author masao
 */
public class Te extends PTe{
}
