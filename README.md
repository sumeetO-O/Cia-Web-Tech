Java program to solve word jumble.

Design Decision:
Decided to use iterative approach over recursion. No particular advantage of recursion and iterative approach was more intuitive in this specific case. The outer "for" loop - > "for (int i = 0; i < word.length(); i++) "  is equivalent to recursing over substrings of the words to be jumbled. 

Time Complexity -> (O(n!))


Assumptions made : 
1. Original word that is provided not returned as part of the results even if it is part of the dictionary.
2. One letter words like "a" and "I" are considered
3. If dictionary is empty or if file is not found, then the whole list of all unique permutations of the string is returned.
4.Only a single word is allowed at a time. (Space delimiter)

Solution Summary:

eg : dog 

split into 'd','o' and 'g' and inssert characters one by one to each location in the word.

Step 1 -> d

Step 2 -> d , o , o+d , d+o   Add "o" to all possible locations in and around existing words 

Step 3 -> d , o , od , do , g , g+d ,, d+g,  g+o , o+g , g+od, o+g+d , og+d ..... 

The words generated are checked with dictionary before they are inserted into main dictionary 
