zfind
=====

zfind is a fast file search utility/API for searching within archives(zip, jar etc). Supports nested archives (zip within zip)

Features
========

1. Fast file search utility
2. Can search within most common archive files (ZIP, JAR, TAR, WAR, EAR etc)
3. Can search within most common compressed files (GZ, TGZ, TAR.GZ, BZ2, XZ etc)
4. Nested archives/compressed files supported (i.e. ZIP within ZIP etc)
5. Normal directory search supported
6. File name search supports regular expression pattern


Usage
=====

To see a listing of all entries within a directory or archive/compressed file, use:
java -jar zfind.jar -t <target directory or archive file>

To search for a particular file name, use:
java -jar zfind.jar -t <target directory or archive file>  -f <file name to search)>

To use regular expression for file search, use
java -jar zfind.jar -t <target directory or archive file>  -f <file name pattern)> -r

To perform a case insensitive search, use:
java -jar zfind.jar -t <target directory or archive file>  -f <file name to search)>  -i
or
java -jar zfind.jar -t <target directory or archive file>  -f <file name pattern)> -r -i

Last but not least, to see help, use :
java -jar zfind.jar -h


Binary download
===============

Use maven to build it yourself, or download executable jar file from sourceforge :


License
=======

 zfind is covered by Apache 2.0 license.
 zfind internally uses Apache commons compression library and JOPT simple open source projects.