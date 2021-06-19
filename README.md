# Flightgear simulator android application

## Discription
Creating an android app for controling flights in FlightGear Simulator. The user can decide on the plane behavior.

## Prerequisites
* Download FlightGear Simulator [here](http://home.flightgear.org/)
* Run FG
* Go to Setting and add the following lines:

```--generic=socket,in,10,127.0.0.1,6400,tcp```
* Press Fly!

## about the application
The app will look like this:

You can control the flight:
1. In the IP box enter your computer IP adress
2. In the port box enter 6400
3. Press the "connect" button
4. The left seek bar is for controlling the throttle values 
5. The down seek bar is for controlling the throttle values 
6. The joystick is to control the movement of the plane: aileron(up and down) and elevator (right and left)

## software architecture
There are 4 main files:
1. activity_main.xml - View (visuality)- contain all the visual components and containers
2. MainActivity.kt - View (functionallity) - contain all the logic and code of the visual components
3. Joystick.kt - View - contains the components of the joystick. 
3. Model.kt - Model - data, and data manipulation of the flight's behavior
4. MainViewModel - View-Model - connect the view and the model

## basic uml


## Tutorial
you can watch [here]( https://youtu.be/-K7770-DwB4) example of basic use of this app
