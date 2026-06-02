For Danny and the Wolfson Robotics Team:
An explanation of the structure of the codebase and the necessary skills and tools.

# Coder Dictionary:
* FtcRobotController - Name of the software FTC gives and that this file is in. Used for programming robot.
* Codebase - A unit of code that works for one purpose. In this case it's all of FtcRobotController.
* Root - The top of something like a filesystem. For the codebase it would be at the top of FtcRobotController.
* Commit - Term for Git software, Means to create a new version/state of your current code changes.


# Needed Skills and Tools:
## Java
You'll need to know the Java Programming Language to write code for the Robot.

Recommended tutorials:
- https://www.youtube.com/watch?v=xTtL8E4LzTQ&pp=ygUESmF2YQ%3D%3D (You don't have to watch all of it)
- https://www.youtube.com/watch?v=_ZIYtNadJBo&list=PLRHdgFNRLyaPiZ5rvINwMmGMHEIL9usla (Brogan Pratt)

## Android Studio
You should be reading this in Android Studio, but Android Studio is an IDE or Code Editor that allows
programmers to write code made for Android, which is what robot uses. Android Studio will also download
much of the stuff you'll need for coding like Java.

Download: https://developer.android.com/studio

## Git
Git is the tool that allows programmers to save versions of code, reverse to previous versions, or create
different "branches" that allow for continuing off a different version.

Download: https://git-scm.com/install

To use git you'll either need to use the User Interface built into Android Studio or, more commonly,
in the Command Line/Terminal.

Here's a tutorial for using it: https://www.youtube.com/watch?v=HkdAHXoRtos

Here's the commands you'll use a lot:
```
git pull - Retrieve code from the repository/cloud/GitHub
git add . - (Run this command at the root of the codebase) Select which code changes will be committed
git commit -m "Name of the commit" - Will create a commit of the added (^) code changes. Basically a new version.
git push - Will upload the new commits (commits only) to the repository/cloud/GitHub
```

## GitHub
GitHub is a cloud storage website built specifically for code and programmers. It's free to use and make
an account with. It works in tandem with Git, and requires it for uploading code.

Website: https://github.com/
Wolfson Robotics Organization Account: https://github.com/Wolfson-Robotics


# Codebase Structure:

Folders:
- autos - All the autonomous classes and OpModes
- components - Any Hardware or Physical Robot Parts that needed a new class not provided by FTC.
- debug - Elijah's debug system. Honestly just don't even touch it.
- pedropathing - All of pedropathing's required classes. Don't put auto/pedro code in here.
- testing - Any class made for the purposes of testing something. Not a real OpMode.
- util - Classes that have standalone utility.

Classes:
- RobotBase - The root class that all of our used OpModes inherit from.
- AutoBase - The root class that all our auto classes inherit from. Inherits RobotBase.
- PlayerDrive - The class that is used for player TeleOp. Inherits RobotBase.

