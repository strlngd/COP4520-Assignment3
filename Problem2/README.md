# Assignment 3 Problem 2 (COP4520)

Created by Sterling Downs for Concepts of Parallel and Distributed Processing

## Problem

You are tasked with the design of the module responsible for measuring the atmospheric temperature of the next generation Mars Rover, equipped with a multi-core CPU and 8 temperature sensors. The sensors are responsible for collecting temperature readings at regular intervals and storing them in shared memory space. The atmospheric temperature module has to compile a report at the end of every hour, comprising the top 5 highest temperatures recorded for that hour, the top 5 lowest temperatures recorded for that hour, and the 10-minute interval of time when the largest temperature difference was observed. The data storage and retrieval of the shared memory region must be carefully handled, as we do not want to delay a sensor and miss the interval of time when it is supposed to conduct temperature reading.

Design and implement a solution using 8 threads that will offer a solution for this task. Assume that the temperature readings are taken every 1 minute. In your solution, simulate the operation of the temperature reading sensor by generating a random number from -100F to 70F at every reading. In your report, discuss the efficiency, correctness, and progress guarantee of your program.

## Installation & Usage

To compile and run this program, navigate to the `src` directory in the project you want to run.

Then use following commands:

```
javac Program.java
java Program
```

## Output

The program will print all output to the console.
The output is in the following format:

```
Report generates every 6 seconds to simulate 1 hour interval.

===== Report For Hour x =====
Highest temperatures recorded: x, x, x, x, x
Lowest temperatures recorded: x, x, x, x, x
Largest difference in 10 minute interval: x
...
```

## Proof of Correctness

This program creates a 2-dimensional array for shared storage. Each thread has it's own row in the array for storing data up to 60 values. Upon each interval, these values are then copied over to form a report of the highest and lowest temperatures as well as the largest interval between temperatures in a 10 minute (6 second simulated) span of time.

## Efficiency

This program was tested on an i5-9600k CPU @4.60GHz with 6 cores and 6 threads.

Due to the intervals of this program, efficiency was harder to test. However, setting breakpoints allowed me to ensure that data is being collected and reported on in a timely manner.

## Evaluation

This program was interesting to work on and a fun problem to solve. Although I had some initial confusion regarding what this was actually asking for, I think it turned out as desired.
