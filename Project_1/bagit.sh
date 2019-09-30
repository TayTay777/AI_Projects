#!/bin/bash

if [ -z "$1" ] || [ -z "$2" ] ;
then
	echo "failure"
	exit 
elif [ "$2" != "-depth" ] && [ "$2" != "-breadth" ];
then
	echo "failure"
	exit 
else
	javac Driver.java
	java Driver "$1" "$2"
fi


