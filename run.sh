#!/bin/bash

chmod +x ./engines/*
# Compile the Java files
javac -d bin src/main/*.java
java -cp bin main.Menu