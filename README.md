# Picocli Example

A Java Native command line tool example.

## Compile

Here are the steps to native compilation.

**How to Compile Native Executable**

You need GraalVM to compile a native application. One way to install GraalVM is to use SDKMan. 
You can install the SDK Man from its site https://sdkman.io/install
After that, you can install GraalVM using this command.

```bash
sdk use java 24.0.1-graal
```
You can compile the native executable by running the following command.
```bash
# this should create an executable called 'list' under the 'target' folder
mvn clean verify
```
