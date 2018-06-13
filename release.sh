#!/bin/sh

DEBUG=1
VECMATH_VERSION=1.5.2
TESTNG_VERSION=6.14.3

version=$1

main() {
    if [ -z "$version" ]
    then
        echo "Usage: ./release.sh <version>"
        return 1
    fi

    download_release_target_jar
    if [ $? != 0 ]; then return 1; fi
    create_release_target_pom

    local original_branch=`get_current_branch`
    prepare_release_branch
    push_jar_to_release_branch
    git checkout $original_branch
}

download_release_target_jar() {
    mkdir -p release/mtomono/TERSE3/$version
    curl -L -o release/mtomono/TERSE3/$version/TERSE3-$version.jar https://github.com/mtomono/TERSE3/releases/download/v$version/TERSE3.jar
}

create_release_target_pom() {
    mkdir -p release/mtomono/TERSE3/$version
    cat <<EOF > release/mtomono/TERSE3/$version/TERSE3-$version.pom
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>mtomono</groupId>
    <artifactId>TERSE3</artifactId>
    <packaging>jar</packaging>
    <version>${version}</version>

    <name>TERSE3</name>
    <url>https://github.com/mtomono/TERSE3</url>
    <description>library for java to keep programs simple</description>

    <licenses>
        <license>
            <name>The Apache License, Version 2.0</name>
            <url>https://raw.githubusercontent.com/mtomono/TERSE3/master/LICENSE.txt</url>
        </license>
    </licenses>

    <developers>
        <developer>
            <name>Masao Tomono</name>
        </developer>
    </developers>

    <scm>
        <url>https://github.com/mtomono/TERSE3</url>
        <connection>scm:git:git@github.com:mtomono/TERSE3.git</connection>
        <developerConnection>scm:git:git@github.com:mtomono/TERSE3.git</developerConnection>
    </scm>

    <properties>
        <maven.compiler.target>1.8</maven.compiler.target>
        <maven.compiler.source>1.8</maven.compiler.source>
    </properties>

    <dependencies>
        <dependency>
            <groupId>javax.vecmath</groupId>
            <artifactId>vecmath</artifactId>
            <version>${VECMATH_VERSION}</version>
        </dependency>
        <dependency>
            <groupId>org.testng</groupId>
            <artifactId>testng</artifactId>
            <version>${TESTNG_VERSION}</version>
            <scope>test</scope>
        </dependency>
    </dependencies>
</project>
EOF
}

get_current_branch() {
    echo $(git symbolic-ref --short HEAD)
}

prepare_release_branch() {
    if [ -n "$(git branch | grep release)" ]
    then
        debug "Local release branch has aready created"
        git checkout release
        git pull origin release
    else
        git fetch origin --prune
        if [ -n "$(git branch -r | grep origin/release)" ]
        then
            debug "Remote release branch has aready created"
            git checkout -b release origin/release
        else
            debug "New local branch is created now"
            git checkout --orphan release
            git rm -rf .
        fi
    fi    
}

push_jar_to_release_branch() {
    git add -f release/mtomono/TERSE3/$version/TERSE3-$version.jar
    git add -f release/mtomono/TERSE3/$version/TERSE3-$version.pom

    git commit -m "released v${version}"

    git push origin release
}

debug() {
    if [ $DEBUG ]
    then
        echo $1
    fi
}


main
