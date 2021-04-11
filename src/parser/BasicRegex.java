/*
 Copyright 2017, 2018, 2019, 2020 Masao Tomono

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and limitations under the License.
 */
package parser;

import collection.TList;

/**
 *
 * @author masao
 */
public class BasicRegex {
    public static String or(TList<String> patterns) {
        return patterns.toDelimitedString("|");
    }
    public static String delimited(String pattern) {
        return "\\b"+pattern+"\\b";
    }
    public static String spaces="[ \t\f\r\n]*";
    public static String doubleQuoteStr="\"(?:[^\"]*)\"";
    public static String singleQuoteStr="\'(?:[^\']*)\'";
    public static String integer="[+-]?\\d+";
    public static String decimal="[+-]?(?:\\d+\\.?\\d*|\\.\\d+)";
    public static String number="[+-]?(?:\\d+\\.?\\d*|\\.\\d+)(?:[eE][+-]?\\d+)?";
    public static String camelHead="[^A-Z0-9]*";
    public static String camelBump="[A-Z][^A-Z0-9]*";
    public static String ipv4="([0-9x]{1,3}\\.){3}[0-9x]{1,3}";
    public static String ipv6full="([a-fA-F0-9x]{1,4}:){7}[a-fA-F0-9x]{1,4}";
    public static String ipv6m="([a-fA-F0-9x]{1,4}:){1,6}(:[a-fA-F0-9x]{1,4})";
    public static String ipv6h="::([a-fA-F0-9x]{1,4}:){0,6}([a-fA-F0-9x]{1,4})";
    public static String ipv6t="([a-fA-F0-9x]{1,4}:){1,7}:";
    public static String ipv6s="::";
    public static TList<String> ipv6patterns=TList.sof(ipv6full,ipv6m,ipv6h,ipv6t,ipv6s);
    public static String ipv6=or(ipv6patterns);
    public static String ipv6d=or(ipv6patterns.map(s->delimited(s)));
}
