picFilters
==========

Extension of 61B project using Swing for GUI. Filters include Blur, Sobel (Edge Detection), Median (Nonlinear filter that beats the blur filter using quicksort), and Sepia filters for those with a penchant for the good old days.Some sample TIFF pictures taken from the wikimedia commons for you to play around with.

To compile, enter in terminal:
javac -cp ".:jai_core.jar:jai_codec.jar" *.java

To run, enter in terminal:
java -cp ".:jai_core.jar:jai_codec.jar" Gui

The files to filter must be TIFF images, support for JPEG compression and other images will be added later. Enjoy!

Contributors: Abhinava Singh and Jonathan Ting @ the UC Berkeley HackJam 2013. 
