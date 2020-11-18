JAVAC=javac
sources = $(wildcard *.java)
classes = $(sources:.java=.class)

all: program

program: $(classes)
	java Main html < ../input.txt
	java Main mediawiki < ../input.txt
	java Main markdown < ../input.txt

scan: $(classes)
	docker run --rm -e SONAR_HOST_URL="https://sonarqube.sidus.io" -v $(shell pwd):/usr/src sonarsource/sonar-scanner-cli

clean:
	rm -f *.class

%.class: %.java
	$(JAVAC) $<

.PHONY: all program clean scan