@echo off
@del testresults.txt
@java DieAndDiceSetTestHarness > testresults.txt
@java HighRoll 5 6 < letInput1.txt >> testresults.txt
@java HighRoll 11 17 < letInput2.txt >> testresults.txt
@java HighRoll 29 4 < letInput3.txt >> testresults.txt
@java HighRoll 3 3 >> testresults.txt

