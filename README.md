# TERSE3

TERSE3 by Masao Tomono is licensed under the Apache License, Version2.0

## note on installation

This library depends on javax.vecmath which is included in Java 3D API.


## installation examples

### for [Apache Maven](https://maven.apache.org/)

``` xml
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.example</groupId>
    <artifactId>TERSE3-sample</artifactId>
    <version>1.0</version>

    <repositories>
        <repository>
            <id>TERSE3-repository</id>
            <url>https://raw.githubusercontent.com/mtomono/TERSE3/release/release</url>
        </repository>
    </repositories>

    <dependencies>
        <dependency>
            <groupId>mtomono</groupId>
            <artifactId>TERSE3</artifactId>
            <version>1.1</version>
        </dependency>
    </dependencies>
</project>
```


### for [Gradle](https://gradle.org/)

``` groovy
apply plugin: 'java'

repositories {
    mavenCentral()
    maven {
        url 'https://raw.githubusercontent.com/mtomono/TERSE3/release/release'
    }
}

dependencies {
    compile group: 'mtomono', name: 'TERSE3', version: '1.1'
}
```


### for [sbt](https://www.scala-sbt.org/index.html)

``` scala
resolvers += "TERSE3 Repository" at "https://raw.githubusercontent.com/mtomono/TERSE3/release/release"

libraryDependencies += "mtomono" % "TERSE3" % "1.1"
```
