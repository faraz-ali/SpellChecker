# SpellChecker
Suggests spelling corrections, uses a Trie 

# Overview

A program that reads a large list of English words (e.g. from /usr/share/dict/words on a unix system) into memory, and then reads words from stdin, and prints either the best spelling suggestion, or "NO SUGGESTION" if no suggestion can be found. The program prints ">" as a prompt before reading each word, loops until killed.

# Examples

For example:

\> sheeeeep
>sheep

\>peepple
>people

\> sheeple
>NO SUGGESTION

# Specifications
The class of spelling mistakes to be corrected is as follows:

Case (upper/lower) errors: "inSIDE" => "inside" Repeated letters: "jjoobbb" => "job" Incorrect vowels: "weke" => "wake" Any combination of the above types of error in a single word should be corrected (e.g. "CUNsperrICY" => "conspiracy").

If there are many possible corrections of an input word, program chooses any valid english word.

Tests:
Generates words with spelling mistakes of the above form, starting with correctly spelled English words. Pipes its output into the first program and verifies that there are no occurrences of "NO SUGGESTION" in the output.
