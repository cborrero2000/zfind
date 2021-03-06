zfind
=====

zfind is a fast file search utility/API for searching within archives(zip, jar etc). Supports nested archives (zip within zip)


##Features##

* Fast file search utility
* Can search within most common archive files (ZIP, JAR, TAR, WAR, EAR etc)
* Can search within most common compressed files (GZ, TGZ, TAR.GZ, BZ2, XZ etc)
* Nested archives/compressed files supported (i.e. ZIP within ZIP etc)
* Normal directory search supported
* File name search supports regular expression pattern
* GUI and command line support


##GUI Usage##

Simply double click the executable jar or type:
		
		java -jar zfind.jar

##Command Line Usage##

1. To see a listing of all entries within a directory or archive/compressed file, use:
		
        java -jar zfind.jar -t [target directory or archive file]

2. To search for a particular file name, use:
		
        java -jar zfind.jar -t [target directory or archive file] -f [file name to search)]

3. To use regular expression for file search, use :
		
        java -jar zfind.jar -t [target directory or archive file]  -f [file name pattern)] -r

4. To perform a case insensitive search (works with both regular file name and regex pattern), use:
		
        java -jar zfind.jar -t [target directory or archive file]  -f [file name to search)]  -i
		or
        java -jar zfind.jar -t [target directory or archive file]  -f [file name pattern)] -r -i

5. Last but not least, to see help, use :
		
        java -jar zfind.jar -h


##API Use##

        //Normal file search
        ZFinder finder=new ZFinder(new File("/path/to/target.zip"));
        finder.setSearchFileName("foo.bar");
        finder.setIgnoreCase(true);
        List<String> allEntries=finder.getAllEntries();
        List<String> matchingEntries=finder.getMatchedEntries();

        //Regex file search
        finder.setSearchFileName("foo[0-9]*\\.bar");
        finder.setRegularExpression(true);
        matchingEntries=finder.getMatchedEntries();



##Binary build##

Use maven to create the binary (zfind-RELEASE.one-jar.jar). Also available in sourceforge : https://sourceforge.net/p/zfind



##License##

zfind is covered by Apache 2.0 license. zfind internally uses Apache commons compression library and JOPT simple open source projects.