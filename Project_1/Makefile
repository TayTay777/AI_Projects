
JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	Baggins.java \
	Cart.java \
	Driver.java \
	Item.java \
	Items.java \
	Sack.java

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class