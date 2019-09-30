#!/bin/bash

if [ -z "$1" ] || [ -z "$2" ] ;
then
	echo "invalid number of arguments"
	echo "error: usage: prog_name filename [-depth | -breadth]"
	exit 
elif [ "$2" != "-depth" ] && [ "$2" != "-breadth" ];
then
	echo "invalid argument"
	echo "error: usage: prog_name filename [-depth | -breadth]"
	exit 
else
	javac Driver.java
	java Driver "$1" "$2"
fi


