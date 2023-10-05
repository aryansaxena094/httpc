# HTTP Client Command Line Interface

This is a simple command-line tool that allows you to make HTTP GET and POST requests similar to cURL. It supports various options and can be used to interact with web services.

## Usage

To use the HTTP client, open your terminal and run the following command:

```
java -cp <classpath> Main
```

Replace `<classpath>` with the classpath where your compiled Java files are located.

## Supported Commands

### GET Request

You can make a GET request to a specified URL using the following command:

```
httpc get <URL>
```

Example:

```
httpc get http://httpbin.org/get
```

### POST Request

You can make a POST request to a specified URL with data using the following command:

```
httpc post <URL> -d '{"key": "value"}'
```

Example:

```
httpc post http://httpbin.org/post -d '{"key": "value"}'
```

### Verbose Mode

You can enable verbose mode to see additional information about the request and response by adding the `-v` option:

```
httpc get -v http://httpbin.org/get
```

### Headers

You can include custom headers in your request using the `-h` option. Headers should be specified as key-value pairs separated by a colon:

```
httpc get -h "HeaderName: HeaderValue" http://httpbin.org/get
```

Example:

```
httpc get -h "Content-Type: application/json" http://httpbin.org/get
```

### File Upload

You can upload a file in a POST request using the `-f` option followed by the file path:

```
httpc post -f <filename> <URL>
```

Example:

```
httpc post -f data.json http://httpbin.org/post
```

### Help

To see a list of available commands and their usage, you can run:

```
httpc help
```

## Dependencies

This tool requires Java to be installed on your system.

## Contributors

- Aryan Saxena - 40233170
- Muskan Lawania 

## License

This project is licensed under the [MIT License](LICENSE).