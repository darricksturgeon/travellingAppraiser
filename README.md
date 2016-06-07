# travellingAppraiser

THIS APPLICATION REQUIRES A GOOGLE MAPS API KEY, which can be
acquired through Google or by my permission I can lend mine for
use.

General Purpose:
Solves a particular variant of mTSP using a genetic algorithm.
Uses Google Maps API to get traffic times and display routes.

Background:
Built for the purpose of helping a local housing appraiser
optimize their daily schedule.  It is difficult to parameterize
human preferences (a person might want a light friday schedule, or
maybe they can only work a half day at some point) so I have left
a couple of user inputs in the program with more to come.

How to Run:
The Main.java class in the GUI package runs the program, wherein
you may enter a home as well as several addresses to visit.  The
algorithm by default will attempt to split the loops into sets of
5 houses each, but a custom set of loops may be entered.