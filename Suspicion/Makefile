
JFLAGS = --module-path /usr/local/apps/javafx/lib/ --add-modules javafx.base
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	*.java

default: classes

classes: $(CLASSES:.java=.class)

