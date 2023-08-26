JAVAC=javac
JFLAGS=

.SUFFIXES: .java .class
.java.class:
	$(JAVAC) $(JFLAGS) $*.java

CLASSES= \
	Prog04.class \
	JDBC.class \
	Record.class \


default: $(CLASSES)

# only on non Linux env (idk if it works on mac)
clean:
	del $(CLASSES)


# Only for Linux
# clean:
# 	rm -f $(CLASSES)
