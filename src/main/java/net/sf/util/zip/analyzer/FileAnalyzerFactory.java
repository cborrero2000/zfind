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

import net.sf.util.zip.FileNameUtil;

import java.io.File;

/**
 * Factory class which provides appropriate Analyzer instance based on file type
 */
public class FileAnalyzerFactory {

    private DirectoryAnalyzer directoryAnalyzer = new DirectoryAnalyzer();
    private ArchieveAnalyzer archieveAnalyzer = new ArchieveAnalyzer();
    private CompressorAnalyzer compressorAnalyzer = new CompressorAnalyzer();
    private SimpleFileAnalyzer simpleFileAnalyzer = new SimpleFileAnalyzer();

    public DirectoryAnalyzer getDirectoryAnalyzer() {
        return directoryAnalyzer;
    }

    public ArchieveAnalyzer getArchieveAnalyzer() {
        return archieveAnalyzer;
    }

    public CompressorAnalyzer getCompressorAnalyzer() {
        return compressorAnalyzer;
    }

    public SimpleFileAnalyzer getSimpleFileAnalyzer() {
        return simpleFileAnalyzer;
    }




    public FileAnalyzer getAnalyzer(File file) {
        if (file.isDirectory())
            return directoryAnalyzer;
        else if (FileNameUtil.getFileType(file.getName()) == FileNameUtil.TYPE_ARCHIVE)
            return archieveAnalyzer;
        else if (FileNameUtil.getFileType(file.getName()) == FileNameUtil.TYPE_COMPRESSED)
            return compressorAnalyzer;

        return simpleFileAnalyzer;
    }
}
