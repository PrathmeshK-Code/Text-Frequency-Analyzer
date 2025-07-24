# Text-Frequency-Analyzer
Analyzes word and character frequencies in a text file using Java.


The text-frequency analyzer uses the following: 

Java Principles:
- OOP: Encapsulation, inheritance (ProbeHashMap extends AbstractHashMap), abstraction (Map interface), polymorphism (Comparator).
- Generics: Type-safe ProbeHashMap<K,V>, MergeSort<K>.
- Exception Handling: IOException for file reading, IndexOutOfBoundsException.
- Iterators: Custom iterators for keys/values in AbstractMap, ArrayList.
- File I/O: Scanner for text file processing.
- Static Methods: Utility methods for max/min frequency.

Data Structures:
- Hash Map: Custom ProbeHashMap with linear probing for frequency counting.
- ArrayList: Built-in and custom ArrayList for dynamic word/character storage.
- Array: Fixed-size storage in MergeSort, ProbeHashMap.
- Linked List: LinkedList for hash map resizing.
- Circular Doubly Linked List: Used in related "Double Trouble Dice" game.

Algorithms:
- Merge Sort: Sorts entries by frequency.
- Hashing: Custom hash function with scale/shift.
