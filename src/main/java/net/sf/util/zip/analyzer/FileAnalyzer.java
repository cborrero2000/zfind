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
import java.io.InputStream;
import java.util.List;

/**
 * Interface for all analyzers
 */
public interface FileAnalyzer {

    /**
     *
     *
     * @param f   The target file to look into
     * @return    List of file entries from the target
     * @throws Exception If any error happens while reading the file
     */
    List<String>  analyze(File f) throws Exception;

    /**
     * This method is usually called by a Analyzer on another analyzer. For example,
     * in case of nested archives, Outer archive analyzer will call inner archive
     * analyzer passing it's own InputStream as a parameter
     *
     * @param fin  InputStream for reading target file contents
     * @param path Actual path of the target file
     * @return   List of file entries from the target
     * @throws Exception If any error happens while reading the file
     */
    List<String>  analyze(InputStream fin, String path) throws Exception;

    void setAnalyzerFactory(FileAnalyzerFactory factory);
}
