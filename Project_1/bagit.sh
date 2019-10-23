#!/bin/bash

if [ -z "$1" ] ;
then
	echo "failure"
	exit 
elif [ "$2" != "-depth" ] && [ "$2" != "-breadth" ] && [ "$2" != "-local" ] && [ ! -z "$2" ];
then
	echo "failure"
	exit 
else
	javac Driver.java
	java Driver "$1" "$2"
fi


