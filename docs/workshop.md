authors: Alexandre Touret 
summary: Code Quality Workshop
id: api-first-workshop
categories: api, java
environments: Web
status: Published
feedback link: https://github.com/alexandre-touret/api-first-workshop/issues

# API-First workshop

## Introduction

This workshop aims to

- Explore the API-First approach and its benefits
- Present the methodologies and tools which could be used
- Pinpoint common pitfalls

During this workshop we will use different tools and languages:

* Java & Quarkus as a platform and programming language
* Vacuum to validate the OPENAPI files
* OASDIFF to pinpoint breaking changes and generate changelogs
* Microcks for mocking our API

### Getting involved?

The source code is available on [GitHub](https://github.com/alexandre-touret/api-first-workshop/).

Feel free to raise any issues or participate!

## Overview

### High level design

TODO

### Main functionalities

#### Guitar management

#### Order management

#### Quote creation

### Icons & Conventions

We will the following icons during the workshop:

🛠️ An action to perform,  
📝 A file to modify,  
👀 Something to observe,  
✅ Validate something,  
ℹ️ Some information.

## Prerequisites

### Skills

| Skill                                     | Level      | 
|-------------------------------------------|------------|
| [Java](https://www.oracle.com/java/)      | novice     |   
| [Maven](https://www.maven.apache.org/)    | novice     |
| [Quarkus](https://quarkus.io)             | novice     |
| [REST API](https://www.maven.apache.org/) | proficient |

### Tools
#### If you want to execute this workshop locally
You **MUST** have set up these tools first:
* [Java 21+](https://adoptium.net/temurin/releases/?version=21)
* [Maven 3.9](https://www.maven.apache.org/)
* [Docker](https://docs.docker.com/)
* Any IDE ([IntelliJ IDEA](https://www.jetbrains.com/idea), [VSCode](https://code.visualstudio.com/), [Netbeans](https://netbeans.apache.org/),...) you want

🛠️ Here are commands to validate your environment:

**Java**

```jshelllanguage
$ java -version
openjdk version "21.0.1" 2023-10-17 LTS
OpenJDK Runtime Environment Temurin-21.0.1+12 (build 21.0.1+12-LTS)
OpenJDK 64-Bit Server VM Temurin-21.0.1+12 (build 21.0.1+12-LTS, mixed mode, sharing)
```

**Maven**

```jshelllanguage
$ mvn --version
    Apache Maven 3.9.9 (8e8579a9e76f7d015ee5ec7bfcdc97d260186937)
    Maven home:/home/alexandre/.sdkman/candidates/maven/current
    Java version:21.0.4,vendor:Eclipse Adoptium,runtime:/home/alexandre/.sdkman/candidates/java/21.0.4-tem
    Default locale:en,platform encoding:UTF-8
    OS name:"linux",version:"5.15.153.1-microsoft-standard-wsl2",arch:"amd64",family:"unix"
```

**Docker**

```jshelllanguage
$ docker version
    Client:
    Docker Engine -Community
    Version:
    27.4.1
    API version:1.47
    Go version:go1.22.10
    Git commit:b9d17ea
    Built:Tue Dec 17 15:45:46 2024
    OS/Arch:linux/amd64
    Context:default

```

**If you don't want to bother with a local setup**
It's strongly recommended to use [Github Codespaces](https://github.com/features/codespaces). You must create an account
first and fork this repository. You then can open this project in either your local VS Code or directly in your browser.

## Environment Setup

### Open Github Codespaces

* Log on [Github](https://github.com/) and
  fork [this repository](https://github.com/alexandre-touret/api-first-workshop).
* Click on Code>Codespaces>Create a codespace on main

When a messages invites you making a URL public, select and validate it.

Wait until the codespace is ready.
At the first startup, the maven build is automatically started. Please wait until it is finished.

### Start the app

🛠 In a terminal, run the Quarkus Dev environment:

```jshelllanguage
$. / mvnw quarkus:
dev
```

👀 Wait a while until you get the following output:

```jshelllanguage
2024 - 12 - 26 22:27:42,814INFO[io.quarkus](Quarkus Main Thread)guitar-heaven1.0.0-SNAPSHOT on JVM(powered by Quarkus3.17.4)started in27.006s.Listening on:http://localhost:8080
    2024-12-26 22:27:42,815INFO[io.quarkus](Quarkus Main Thread)Profile dev activated.Live Coding activated.
    2024-12-26 22:27:42,816INFO[io.quarkus](Quarkus Main Thread)Installed features:[agroal,cdi,hibernate-orm,hibernate-orm-panache,hibernate-validator,jdbc-postgresql,kafka-client,messaging,messaging-kafka,microcks,narayana-jta,rest,rest-client,rest-client-jackson,rest-jackson,resteasy-problem,smallrye-context-propagation,smallrye-openapi,swagger-ui,vertx]
```

ℹ️ All the stack is provided through the Quarkus Dev Services. You don't then have to bother about ramping it up.

✅ Now you validate your setup browsing the Quarkus DEV-UI. Go to the VS Code Port panel and click on the url which
exposes the port ``8080``.
Add then the ``/q/dev-ui`` suffix.

For instance: ``https://laughing-giggle-x5x4rqxpwfv5pj-8080.app.github.dev/q/dev-ui``

## Explore the current API

## Moving our app to API-First

## What about the clients?

## AsyncAPI

## API-Breaking changes

## How our customers may integrate our API?
