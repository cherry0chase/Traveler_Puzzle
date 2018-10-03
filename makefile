JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	Grid.java \
	Solution.java \
	SolutionSet.java \
	Traveler_Ray.java \
	Vertex.java

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
