# CalculatorApp

This project builds off of Postfix-InfixCalculator to add a GUI.
Instead of taking in an input file full of expressions and writing the result to an output file the user enters values using buttons or the keyboard.
When the user is done entering their expression they press the 'Return' button or key. The result is then calculated and returned.
This value can then immediately be used again as the first value in the next expression.

Currently known issues:
JTextField does not take up a full row at top.
Cannot handle negative number inputs, throws Error; does allow negative output though.
Keyboard input appears twice momentarily

Features to be added:
Ans button to recall whatever the last answer was so it can be used as more than just the first value
