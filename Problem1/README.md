# Assignment 3 Problem 1 (COP4520)

Created by Sterling Downs for Concepts of Parallel and Distributed Processing

## Problem

The Minotaur’s birthday party was a success. The Minotaur received a lot of presents from his guests. The next day he decided to sort all of his presents and start writing “Thank you” cards. Every present had a tag with a unique number that was associated with the guest who gave it. Initially all of the presents were thrown into a large bag with no particular order. The Minotaur wanted to take the presents from this unordered bag and create a chain of presents hooked to each other with special links (similar to storing elements in a linked-list). In this chain (linked-list) all of the presents had to be ordered according to their tag numbers in increasing order. The Minotaur asked 4 of his servants to help him with creating the chain of presents and writing the cards to his guests. Each servant would do one of three actions in no particular order:

1. Take a present from the unordered bag and add it to the chain in the correct location by hooking it to the predecessor’s link. The servant also had to make sure that the newly added present is also linked with the next present in the chain.

2. Write a “Thank you” card to a guest and remove the present from the chain. To do so, a servant had to unlink the gift from its predecessor and make sure to connect the predecessor’s link with the next gift in the chain.
   Per the Minotaur’s request, check whether a gift with a particular tag was present in the chain or not; without adding or removing a new gift, a servant would scan through the chain and check whether a gift with a particular tag is already added to the ordered chain of gifts or not.

3. As the Minotaur was impatient to get this task done quickly, he instructed his servants not to wait until all of the presents from the unordered bag are placed in the chain of linked and ordered presents. Instead, every servant was asked to alternate adding gifts to the ordered chain and writing “Thank you” cards. The servants were asked not to stop or even take a break until the task of writing cards to all of the Minotaur’s guests was complete.

After spending an entire day on this task the bag of unordered presents and the chain of ordered presents were both finally empty!

Unfortunately, the servants realized at the end of the day that they had more presents than “Thank you” notes. What could have gone wrong?

Can we help the Minotaur and his servants improve their strategy for writing “Thank you” notes?

Design and implement a concurrent linked-list that can help the Minotaur’s 4 servants with this task. In your test, simulate this concurrent “Thank you” card writing scenario by dedicating 1 thread per servant and assuming that the Minotaur received 500,000 presents from his guests.

## Initial Question

Unfortunately, the servants realized at the end of the day that they had more presents than “Thank you” notes. What could have gone wrong?

**Answer:** Threads accessing the presents chain at the same time could have resulted in a some presents being unlinked from the list without a letter being written, interupting or causing an error in the removal process.

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
Generating Unordered List...
Shuffling Unordered List...
Starting servant threads...
Servant x finished with all work.
Servant x finished with all work.
Servant x finished with all work.
Servant x finished with all work.
```

## Proof of Correctness

This program creates 4 "servant" threads to perform tasks as specified for the problem. Tasks will be selected randomly by each servant. When they complete a task, they will randomly decide a new task to complete. For adding presents to the present chain, each servant will interact with the defined `ConcurrentLinkedList`, which locks only the nodes that are being accessed at each specific point in time.

## Efficiency

This program was tested on an i5-9600k CPU @4.60GHz with 6 cores and 6 threads.

Upon testing, the program finished writing all letters for all guests in approximately 0.6 seconds of runtime.

## Evaluation

This program was quite interesting to implement as it called for defining our own linked list that is compatibile with concurrency. Overall, the development process of this program went smoother than other assignments.
