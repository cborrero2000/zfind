/*
 * Dipankar Datta (dipdatta@user.sourceforge.net)
 * https://github.com/dipdatta/zfind
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package net.sf.util.zip.analyzer;

import java.io.FilterInputStream;
import java.io.InputStream;

/**
 * Just a dummy InputStream implementation which does not close underlying stream. Useful when passing the stream across
 * multiple analyzers.
 */
class ProxyInputStream extends FilterInputStream {

    public ProxyInputStream(InputStream in){
        super(in);
    }

    @Override
        public void close() {
            //Do nothing
        }
}
