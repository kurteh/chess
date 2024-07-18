# ♕ BYU CS 240 Chess

This project demonstrates mastery of proper software design, client/server architecture, networking using HTTP and WebSocket, database persistence, unit testing, serialization, and security.

## 10k Architecture Overview

The application implements a multiplayer chess server and a command line chess client.

[![Sequence Diagram](10k-architecture.png)](https://sequencediagram.org/index.html#initialData=C4S2BsFMAIGEAtIGckCh0AcCGAnUBjEbAO2DnBElIEZVs8RCSzYKrgAmO3AorU6AGVIOAG4jUAEyzAsAIyxIYAERnzFkdKgrFIuaKlaUa0ALQA+ISPE4AXNABWAexDFoAcywBbTcLEizS1VZBSVbbVc9HGgnADNYiN19QzZSDkCrfztHFzdPH1Q-Gwzg9TDEqJj4iuSjdmoMopF7LywAaxgvJ3FC6wCLaFLQyHCdSriEseSm6NMBurT7AFcMaWAYOSdcSRTjTka+7NaO6C6emZK1YdHI-Qma6N6ss3nU4Gpl1ZkNrZwdhfeByy9hwyBA7mIT2KAyGGhuSWi9wuc0sAI49nyMG6ElQQA)

Link to updated sequence diagram:
https://sequencediagram.org/index.html?presentationMode=readOnly#initialData=IYYwLg9gTgBAwgGwJYFMB2YBQAHYUxIhK4YwDKKUAbpTngUSWDABLBoAmCtu+hx7ZhWqEUdPo0EwAIsDDAAgiBAoAzqswc5wAEbBVKGBx2ZM6MFACeq3ETQBzGAAYAdAE5M9qBACu2GADEaMBUljAASij2SKoWckgQaIEA7gAWSGBiiKikALQAfOSUNFAAXDAA2gAKAPJkACoAujAA9D4GUAA6aADeAETtlMEAtih9pX0wfQA0U7jqydAc45MzUyjDwEgIK1MAvpjCJTAFrOxclOX9g1AjYxNTs33zqotQyw9rfRtbO58HbE43FgpyOonKUCiMUyUAAFJForFKJEAI4+NRgACUh2KohOhVk8iUKnU5XsKDAAFUOrCbndsYTFMo1Kp8UYdKUAGJIJLUyiMmA6MJ04CjTCM4ksk6g3EqcpoHwIBA4kQqNkS5mkkCQuQoPlwm6MhnaSXqNnGUoKDgcGD6xkqqh404akmqUralC6hQ+MCpWHAH2pI3ik2a1mnC1Wm3e332sFq3KnQEXMoRKFIqCRVSKrDJ4HSwpHS4wa4dO6TcqrJ4B331CAAa3QFam+wdlAL8GQ5nKACYnE5uqWhqKxjBK48pjXUnXG2hm6sDugOKYvL5-AFoOxyTAADIQaJJAJpDJZLt5QvFYvVOpNVoGdQJNCDkWjWYvN4cA5FkGFPPFnpTC+9x9F875LCskwAuc+YyqqKDlAg+48rCe4Hqi6KxNi8aGM6oaumSFL6rSZYjsaRJhuaHLcpwtodAKQowEBIbka6HbYRCFI+LcjEdIKwokaMMBIAAZkJSRaPIegGMxTKsRGHIwB6XqBjJprhrBjpyjAkJgFxSRTgKwneMMRjaFJhgOk6SbQcWqE8lmOaYH+IIaf+AwCaO45fFOM5NmOLZ9F+l4uYU2TdjAfYDr07nDqM84Tn0PkNn5XmLpwK7eH4gReCg6C7vuvjMEe6SZJgYXnkU1BXtIACiO41fUNXNC096qI+3RJbObY-mcQL-pOga+XOEyBU5Nk9exMCIfYhUoQVProRiWGyjhBJ4SyBFgDGfqdegZGyVK8mbTx7bJBkqQwLtaCqRRiYXnBHG6dxBnaEJolXWJpmSfoYguodhQWkYKDcJkl2Dcl11-Wad2VZp8HaZx3FpBSqTttAMBoBAzDTYVMDJPoMDZmGwmKpZCbWX1qaoYVDkILm41scFVxBVVIWdjkYC9v2g5pcuniZeukI2ju0IwAA4iOrLFSeZVnswrmphUYsNc19gjh14Ndd+bLOVcA21hDEFQZTbKTcgsQS6MqiwstcHqutpIwOSW2Bv6mt7TdckAwpU7mIQupgwbXVQ+p91w49emB0Gr0iVHQ2fRJug-WTq0yA7bpTdCltqDbnv-eyx3AEqTuSzARkQCZifmXn0MaeCCNPfpxdq1bl2ssAmexCnHa653YDZ6otP0ybMPfnrfQt2o4yVP0k8AJLSNPACMPYAMwACxPMemT6uWI1PDoCCgPWu8jhBTyTwAciOI17DAjQs8cMPlZzEXc70UyT6o08VLPI4L8vNem8pjbz1B5c+UxD7H1PnFfen8RzX1GLfe+vMMprkCNgLi2BuDwB1KDbOKQSqng5qbJmlRagNFVurYI7sny9CviOR+7YKYpiuIlWhECJ4ILPrfMaI8w71yUpkbOsIhEoGzotTC3dcIsQ2k7Ck203ZBw9iHSi5RfYYH9qDKckBg7p0Zg9BukcXryDenHCGX0k7SWwvbWRjtnYiIYaMfaak1Flx5DaWOTjDCpAJjoXKSRBjLlUaPFa8pFTKhsTIg6Wo8HiJHLCbxLjbre0tNaEuoxE7SJYcCcoYiJFqEcs5AxJRx7z0XuUFeG8YBMLZi-LmUUAJcNGAAypQCalmHSvzdBARLDA0QskGAAApCAPJxYjkCFAkA9ZZYkIVuUaolJbwtEnhrZRdCejYOAH0qAcAICISgLMcptSdbjTYVdThWydl7IOZw8pRs+EplIYYgAVqMtAIi3k8gKWiJa0i1p2Izs7RRV1klewLlHP2IAA5XRrqHWG9cdLGMDAKWOH0xlV2TlEgFMSgUUkcf-aQYL84WmojaSePEeSOHKXCkpiLEZJFjlc9s0Kkj+MutaFANpIAZLENitOgLyh+AkvE0YiTCXEuhqkxi2ARW8rxudXlC9Lo0RuIg36+jQmGIVEqbJv4znDPeQU7MdNHkwXmSWE5z85YNJ5p0vmq4soBC8NszsnpYDAGwNgrRj5CEyxfs80plRar1Uas1Yw3VTmUzYRwYGFJgKjWKXXLSIBuB4FzvykOeS01wkleGaVsaQaGCLggcxs5WTl0rmZZOISC1xu0c3UulbLHV1rRCwt8bLrFxuBW4yLaa2auTfDJF3EdWltjqm91XKYB6t6qwxSOah5muYRanoVrTj1Lfo01BQA

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
