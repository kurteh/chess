# ♕ BYU CS 240 Chess

This project demonstrates mastery of proper software design, client/server architecture, networking using HTTP and WebSocket, database persistence, unit testing, serialization, and security.

## 10k Architecture Overview

The application implements a multiplayer chess server and a command line chess client.

[![Sequence Diagram](10k-architecture.png)](https://sequencediagram.org/index.html#initialData=C4S2BsFMAIGEAtIGckCh0AcCGAnUBjEbAO2DnBElIEZVs8RCSzYKrgAmO3AorU6AGVIOAG4jUAEyzAsAIyxIYAERnzFkdKgrFIuaKlaUa0ALQA+ISPE4AXNABWAexDFoAcywBbTcLEizS1VZBSVbbVc9HGgnADNYiN19QzZSDkCrfztHFzdPH1Q-Gwzg9TDEqJj4iuSjdmoMopF7LywAaxgvJ3FC6wCLaFLQyHCdSriEseSm6NMBurT7AFcMaWAYOSdcSRTjTka+7NaO6C6emZK1YdHI-Qma6N6ss3nU4Gpl1ZkNrZwdhfeByy9hwyBA7mIT2KAyGGhuSWi9wuc0sAI49nyMG6ElQQA)

Link to updated sequences diagram:
https://sequencediagram.org/index.html?presentationMode=readOnly#initialData=IYYwLg9gTgBAwgGwJYFMB2YBQAHYUxIhK4YwDKKUAbpTngUSWDABLBoAmCtu+hx7ZhWqEUdPo0EwAIsDDAAgiBAoAzqswc5wAEbBVKGBx2ZM6MFACeq3ETQBzGAAYAdAE5M9qBACu2GADEaMBUljAASij2SKoWckgQaIEA7gAWSGBiiKikALQAfOSUNFAAXDAA2gAKAPJkACoAujAA9D4GUAA6aADeAETtlMEAtih9pX0wfQA0U7jqydAc45MzUyjDwEgIK1MAvpjCJTAFrOxclOX9g1AjYxNTs33zqotQyw9rfRtbO58HbE43FgpyOonKUCiMUyUAAFJForFKJEAI4+NRgACUh2KohOhVk8iUKnU5XsKDAAFUOrCbndsYTFMo1Kp8UYdKUAGJITgwamURkwHRhOnAUaYRnElknUG4lTlNA+BAIHEiFRsyXM0kgSFyFD8uE3RkM7RS9Rs4ylBQcDh8jqM1VUPGnTUk1SlHUoPUKHxgVKw4C+1LGiWmrWs06W622n1+h1g9W5U6Ai5lCJQpFQSKqJVYFPAmWFI6XGDXDp3SblVZPQN++oQADW6ErU32jsohfgyHM5QATE4nN0y0MxWMYFXHlNa6l6020C3Vgd0BxTF5fP4AtB2OSYAAZCDRJIBNIZLLdvJF4ol6p1JqtAzqBJoIei0azF5vDgHYsgwr5ks9FMr73H0XwfksKyTAC5wFrKaooOUCAHjysL7oeqLorE2IJoYLphm6ZIUgatLlqOJpEuGFoctyvIGoKwowMBoYUW6nY4RCFI+LcjEdDAABm3jDEY2h6AYMDJBkqQwGgKDJDA06zugjrOsmMElmhPLZrmmD-iCcElFcAykaMC6Tn0CmNs246tn035XnphTZD2MD9oOvRGSOJnWdWU5Bop87eW2y6rt4fiBF4KDoHuB6+Mwx7pJkmBOReRTUNe0gAKK7hl9QZc0LQPqoT7dBZc7tr+ZxAgBvl1pZ84TLZOlqRV7EwEh9ixahMW+hhGLYXKuEEvhLKEWAsb+qV6DkUyrGRhyMDkswNziZJ8l+XV8m8hwKDcJka21WVrrSkml7wRxYBcUkaQUqkHbQNJEDMO1sXifoMA5uGfFKspiaqVVaZobFWkIHmzVsfZVx2WlDldjkYB9gOQ5LpwIXroEkK2ru0IwAA4qOrLxaeSXnsw+nXjjOX5fYo4letZU-myulXDVM51ZB0H-WyrXILEeOjKosL9fBGrDaSC0UuNAZ01NzEzcdhSWvtt0YIQepK-5P2DTIovum10J82oguy2aEYK-Ni3ycqC34-xgnCfIoliEd5onalTryjAkIXdxwBW9T-PyaywB67EmudkzIdgAbqjA6DnOuz+zN9P7ajjJU-QpwAktIacAIy9gAzAALE8J6ZAaFYNU8OgIKADYV6OkFPCnAByo4NXsMCNFDxyu8l8MuYjvRTCnqhpxUGejtneeFyXUxl-qxkgV8Nd1w3JlVyPo5t6MHdd8jK6eKFG7YFx2DcPAup7QbKQJWecNcxDlS1A0VM08E0vPr0rejj3HZ-amQyk16qgWbtvRuHcmrx1Ou7BCMBPR6gNrCBBmQDa9SwmHPCLERrizGkGKWB0ZbO1Nuyco05zCqz2tOSA9MBoi2wWLRaSCf6jGmibKi5Q+I8ltEgPi1tRg70MKkN6OhIpJEGCuYh4MzrSSVCqHC9C5baivigZh4DWHG0onNK0Np+EoC0PITBADgTlBQao0cscoGpmkQZUsW9RjT3KPnYuMA-4w37gjNygFk5Txzk42erizAoyPmjAIlgdpITkgAKQgDyXGo5AirxAA2YmD8yZpmqJSO8LQU600IV-Ho59gDhKgHACASEoCzCztINxjNmpAM-k3OYtcSllIqU0nxDi-GgQ5tY-S4IYAACtYloCQcMnkaCUBoj6pgoaDDdaLUlsAthWizZkKDBQkAatgGzO1vM0aaiukrNmmsmitoU48R5I4apmjZr9I9l7S6MBeEwCKSU+B7AhSGGADafRMBIB6N2cQ8ofgDHmNGLCapxz5akPkromSck3kdgBRc9oVy9HZ02raJFpTynQFucde5cDFTKiMX+epMAYkTIsWobSukbEAVqX3EmnikZBMPmuMKAQvDFK7F6WAwBsDn0oU+W+RN+6P2huUComVsq5XysYcqdT-qGW2rtZevTYIwIGSAbgeAjYKKwUo3Wur+VGykdoowO0KTfKtsA1QVT8azHODxSgrIBIQCEgY3Q+gnY6xsQMx53ESUIGeXw01eA-lksqoA+Beqsy0pBlY2C6TIZKuZXDVlvQD6YCAA

## Modules

The application has three modules.

- **Client**: The command line program used to play a game of chess over the network.
- **Server**: The command line program that listens for network requests from the client and manages users and games.
- **Shared**: Code that is used by both the client and the server. This includes the rules of chess and tracking the state of a game.

## Starter Code

As you create your chess application you will move through specific phases of development. This starts with implementing the moves of chess and finishes with sending game moves over the network between your client and server. You will start each phase by copying course provided [starter-code](starter-code/) for that phase into the source code of the project. Do not copy a phases' starter code before you are ready to begin work on that phase.

## IntelliJ Support

Open the project directory in IntelliJ in order to develop, run, and debug your code using an IDE.

## Maven Support

You can use the following commands to build, test, package, and run your code.

| Command                    | Description                                     |
| -------------------------- | ----------------------------------------------- |
| `mvn compile`              | Builds the code                                 |
| `mvn package`              | Run the tests and build an Uber jar file        |
| `mvn package -DskipTests`  | Build an Uber jar file                          |
| `mvn install`              | Installs the packages into the local repository |
| `mvn test`                 | Run all the tests                               |
| `mvn -pl shared test`      | Run all the shared tests                        |
| `mvn -pl client exec:java` | Build and run the client `Main`                 |
| `mvn -pl server exec:java` | Build and run the server `Main`                 |

These commands are configured by the `pom.xml` (Project Object Model) files. There is a POM file in the root of the project, and one in each of the modules. The root POM defines any global dependencies and references the module POM files.

## Running the program using Java

Once you have compiled your project into an uber jar, you can execute it with the following command.

```sh
java -jar client/target/client-jar-with-dependencies.jar

♕ 240 Chess Client: chess.ChessPiece@7852e922
```
