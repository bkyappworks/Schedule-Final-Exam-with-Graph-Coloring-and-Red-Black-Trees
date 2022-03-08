# Schedule-Final-Exam-with-Graph-Coloring-and-Red-Black-Trees
An application of graph coloring
This program reads a data file containing a list of student class schedules. The name of the data file will be entered on the command line. The output of your program will be (a) a two dimensional matrix that represents the graph and (b) a final exam schedule. 
:
Each students name is in the form <last name>,<first name> with no intervening space characters . The course names are all 6 characters in length and are separated from each other by spaces. Each course name refers to a single section of the course and for each course, only one section is offered. (It’s a small school.) The number after the student’s name represents the number of courses the student is taking. There is no need to validate the file. We will assume that it has already been validated.
There is a maximum of 40 students on the file. No student may take more than 5 courses. There is a maximum of 20 different courses offered each term.
This program will build a graph from the data on the file. Process the graph with the greedy algorithm shown above and display a schedule of final exams. Final exams must be scheduled in such a way that no student has a conflict. For example, if Sue is taking both ENG040 and MAT100 then the final exam for each of these courses must be scheduled at a different time. Sue cannot be in two places at once.

Input File:
Jones,Andy 3 ENG100 PSY050 MAT220
Hein,Peter 4 MAT010 CHM230 CSC401 HST080 Miller,Kyle 5 MAT010 CHM230 HST080 ECN110 PHY100 Williams,Ann 5 MAT010 ENG100 PSY050 CSC401 HST080 Kim,Jenny 3 ENG100 CHM230 HST080
Carter,Herb 5 PSY050 CHM230 CSC401 ENG100 PHY100 Popov,Dimitri 2 PSY050 PHY100
Smith,Kellie 3 PSY050 HST080 ECN110