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

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Analyzer for simple file (i.e not a directory or some Archive/compressed file)
 */
class SimpleFileAnalyzer extends AbstractFileAnalyzer {
    @Override
    public List<String> analyze(File f) throws Exception {
        List<String> entries=new ArrayList<String>();
        addEntry(f.getAbsolutePath(), entries);
        return entries;
    }
}
