
# Zordo
___
This game is a first full-fledged project for me. This game will not be sold or distributed.
___

## Table of Contents
- [Pre-requisites](#pre-requisites)
- [Installation](#installation)
  - [Forking the repository](#forking-the-repository)
  - [Cloning your forked repository](#cloning-your-forked-repository)
  - [Setting up Java](#setting-up-java)
  - [Setting up Gradle](#setting-up-gradle)
- [Running the game](#running-the-game)
- [Contributing](#contributing)
- [License](#license)
___

## Pre-requisites
Before installing, download and install the following tools on your local machine:
- [Git](https://git-scm.com/downloads) or ```brew install git```
- [Java 1.8](https://www.oracle.com/java/technologies/javase-jdk8-downloads.html) or ```brew install java@1.8```
- [Gradle 8.4](https://gradle.org/releases/) or ```brew install gradle@8.4```
___

## Installation
### Forking the repository
- Fork the repository by clicking [here](https://github.com/jesusch4vez/zordo-game/fork).

### Cloning your forked repository
- Open your terminal and navigate to the directory where you want to clone the repository.
- Example: `cd ~/Projects`
- Clone the repository to your local machine:
  - `git clone https://github.com/your-username/zordo-game.git`
- Navigate into the cloned repository: 
  - `cd zordo-game`

### Setting up Java
- Set the `JAVA_HOME` and `PATH` environment variables:
  - `export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_301.jdk/Contents/Home`
  - `export PATH=$JAVA_HOME/bin:$PATH`
- Verify the Java installation is set to 1.8 by running `java -version`

### Setting up Gradle
- Set the `GRADLE_HOME` environment variable:
    - `export GRADLE_HOME=/usr/local/Cellar/gradle/8.4/libexec`
- Verify the Gradle installation is set to 8.4 by running: `gradle -v`
>**NOTE**: If you are using IntelliJ IDEA, ensure to select Generate *iml files under:
IntelliJ IDEA -> Settings -> Build, Execution, Deployment -> Build Tools -> Gradle
___

## Running the game
- Build the project: 
  - `gradle build`
- Run the game:
  - `gradle run`
___

## Contributing
- Fork the repository
- Create a new branch
- Make your changes
- Push your changes to your forked repository
- Create a pull request
- Wait for the pull request to be reviewed
- Once approved, the pull request will be merged
- Thank you for contributing!

## License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
___


[//]: # (TODO: Work on an interactive title screen)

[//]: # (- Start New Game -> leads to actual game)

[//]: # (- Load Game -> leads to placeholder)

[//]: # (- Settings -> leads to placeholder)

[//]: # (- Exit -> closes the game)