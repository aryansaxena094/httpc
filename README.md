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

## Usage
Ensure that you're using the Java command with --enable-preview flag for proper execution.

### GET Requests

For sending GET requests:

```bash
java --enable-preview -jar httpc.jar get 'http://httpbin.org/get?course=networking&assignment=1'
```

To get verbose output:

```bash
java --enable-preview -jar httpc.jar get -v 'http://httpbin.org/get?course=networking&assignment=1'
```

### POST Requests

For POST requests:

```bash
java --enable-preview -jar httpc.jar post -d '{"Assignment": 1}' 'http://httpbin.org/post'
```

Or, send data from a file:

```bash
java --enable-preview -jar httpc.jar post -f data.json 'http://httpbin.org/post'
```

### Help Commands

To view help:

```bash
java --enable-preview -jar httpc.jar help
java --enable-preview -jar httpc.jar help get
java --enable-preview -jar httpc.jar help post
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
