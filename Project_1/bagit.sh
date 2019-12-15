#!/bin/bash
if [ "$#" -eq 2 ]
then
	javac *.java
	java Driver "$1" "$2"
else
	javac *.java
	java Driver "$1"
fi