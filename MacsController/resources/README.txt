README MacsPlugin

Jar file and source code for the Macs Control plugin to ImageJ.

Installation

Basic:

to install plugin directly, download the .jar file and paste into your mmplugins directory
restart micromanager and it should appear in your plugins directory



Notes for Programmers

I have tried to stick to the following conventions:

1) Interfaces for the 'normal' setup start with Standard
2) Classes that implement standard interfaces start with Basic, i.e. BasicCleaningSettings implements StandardCleaningSettings
3) Note that PT - pressure tube and psi = pressure per square inch


The program is designed around a very flexible api containing: 

1) Interfaces for two low-level devices
	a) CoreMacsDevice: runs your macs electornics - normally an arduino
	b) CoreCleaningDevice: runs your cleaning system
	- in the standard setup both of these interfaces are implemented by a single class using a single arduino
	
2) Very general Interfaces for two types of classes:
	a) PressureTubeLiquid: with this interface you can define the 3 basic functions performed by the pressure tube on a liquid
		namely, injection, macsing and emptying
		Also using generics you can provide any set of parameters you want for performing these functions
		
	b) CleaningLiquid: this isa PressureTubeLiquid but also defines a cleaning and filling function
		along with this are the generic arguments to define the possible states for your cleaning device and the
		parameters needed to run your specific implementation for filling and cleaning
		
	-this is very abstract, so it is probably best to look at their standard implementations in the standardsetup subfolder
	-PressureTubeLiquid is implemented by BasicPTLiquid and CleaningLiquid is implemented by BasiCleaningLiquid

Where possible, Settings objects are created to store parameters for running a specific task,
for example FillPressureTubeSettings and RunMacsSettings.
- these settings objects are also where the rational default values are defined

The gui is comprised of seperate components each containing a controller, view interface and view as
part of the MVC design pattern:

The major gui components are found in top-level folders
 - cleaner, directcontrol and runmacs correspond to tabs in the final gui
 - halt and status are views present at all times
 
Most of these gui components are made up of seperate pieces each in their own subfolder
- these subfolders implement part of the view and have their own Controller, View interface and view
- these subfolders are meant to operate autonomously
