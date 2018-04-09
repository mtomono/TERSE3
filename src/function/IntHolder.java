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

package function;

/**
 *
 * @author mtgetmgetnget
 */
public class IntHolder {
    int value;
    public IntHolder(int init) {
        this.value = init;
    }
    public int get() {
        return value;
    }
    public int set(int value) {
        this.value = value;
        return this.value;
    }
    public int push(int value) {
        int retval = this.value;
        this.value = value;
        return retval;
    }
}
