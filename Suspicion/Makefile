
JFLAGS = --module-path /usr/local/apps/javafx/lib/ --add-modules javafx.base
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	TaylorPRoberts.java \
	Suspicion.java \
	ArgHandler.java \
	Bot.java \
	Card.java \
	Deck.java \
	Dice.java \
	Display.java \
	DummyBot.java \
	Location.java \
	Player.java \
	RBot.java \
	RBotDumb.java \
	TextDisplay.java \

default: classes

classes: $(CLASSES:.java=.class)