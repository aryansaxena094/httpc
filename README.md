# HTTP-Client-Java

This project provides a simplified HTTP client, `httpc`, written in Java. It supports basic HTTP GET and POST requests, and includes various options such as verbose output, custom headers, file or inline data for POST body, and output redirection to a file.

## Table of Contents

- [Features](#features)
- [Installation](#installation)
- [Usage](#usage)
  - [GET Requests](#get-requests)
  - [POST Requests](#post-requests)
  - [Help Commands](#help-commands)
- [Architecture](#architecture)
- [Contributing](#contributing)
- [License](#license)

## Features

- GET and POST HTTP requests
- HTTP Redirection Handling
- Custom Headers
- Inline Data or File as POST body
- Verbose Mode for Debugging
- Save Output to File

## Installation

To install the project, clone the repository and navigate to the project directory:

```bash
git clone https://github.com/yourusername/http-client-java.git
cd http-client-java
```

Then compile the Java files:

```bash
javac *.java
```

## Usage

### GET Requests

For sending GET requests:

```bash
httpc get 'http://httpbin.org/get?course=networking&assignment=1'
```

To get verbose output:

```bash
httpc get -v 'http://httpbin.org/get?course=networking&assignment=1'
```

### POST Requests

For POST requests:

```bash
httpc post -d '{"Assignment": 1}' 'http://httpbin.org/post'
```

Or, send data from a file:

```bash
httpc post -f data.txt 'http://httpbin.org/post'
```

### Help Commands

To view help:

```bash
httpc help
httpc help get
httpc help post
```

## Architecture

The project is divided into several classes:

- `httpc`: The main class that initiates HTTP requests.
- `HttpRequest`: Class to format the HTTP request.
- `HttpResponse`: Class to handle HTTP response.
- `RequestFormatter`: Class to parse input data.
- `ResponseParser`: Class to parse HTTP responses.

## Contributors

- Aryan Saxena - 40233170
- Muskan Lawania - 40239133
