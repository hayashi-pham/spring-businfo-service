cd ..
set JAVA_HOME=C:\Program Files\Java\jdk1.8.0_112
mvnw.cmd install:install-file -Dfile=sqljdbc4.jar -DgroupId=com.microsoft.sqlserver -DartifactId=sqljdbc4 -Dversion=4.0 -Dpackaging=jar -DgeneratePom=true

