#!/bin/bash
shellDir=$(cd "$(dirname "$0")"; pwd)

shopt -s expand_aliases
if [ ! -n "$1" ] ;then
	echo "Please enter a version"
 	exit 1	
else
  	echo "The version is $1 !"
fi

if [ `uname` == "Darwin" ] ;then
 	echo "This is OS X"
 	alias sed='sed -i ""'
else
 	echo "This is Linux"
 	alias sed='sed -i'
fi

echo "Change SOFABoot version in subproject pom ===>"
sed "s/<sofaboot.version>.*<\/sofaboot.version>/<sofaboot.version>$1<\/sofaboot.version>/" $shellDir/../sofa-boot-samples/pom.xml


cd $shellDir/..
echo "Change SOFABoot version in subproject pom ===>"
for filename in `find . -name "pom.xml" -mindepth 2`;do

    if [ $filename == "./sofa-boot-samples/pom.xml" ]; then
       echo "Skip with $filename"
       continue
    fi

	echo "Deal with $filename"
	sed "/<parent>/,/<dependencies>/ s/<version>[^\$].*<\/version>/<version>$1<\/version>/" $filename
done