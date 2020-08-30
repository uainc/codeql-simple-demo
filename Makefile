CC=gcc
CFLAGS=-I.
DEPS = 
OBJ = main.o 

%.o: %.c $(DEPS)
	$(CC) -c -o $@ $< $(CFLAGS)

main: $(OBJ)
	$(CC) -o $@ $^ $(CFLAGS)

scan: clean
	codeql database create scan-db --language=cpp
	codeql database upgrade scan-db
	codeql database analyze scan-db cpp-lgtm.qls --format=sarif-latest --output=cpp-results.sarif
	sed -i 's?%SRCROOT%?'`pwd`'?g' cpp-results.sarif 

clean:
	rm -rf *.o main scan-db *.sarif 
