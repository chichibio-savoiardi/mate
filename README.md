# Math as programming homework

Done as i could not bring myself to exercise on math stuff consistently.

## Libraries

Necessary libraries for compiling the project, to be put under a `lib/` directory in the root of the project.
* [lombok](https://projectlombok.org/)

## Running

This project uses `make` to run and compile itself, use with `make [cmd]`, the commands are as follows:
* `run`: the default target, will compile classes and run the project in the JVM.
* `runjar`: run the `jar` job and execute the generated .jar file.
* `classes`: compile the source files to class files in a to be generated `bin/` directory.
* `jar`: pack the class files inside a `.jar` file with main class.
* `jarlib`: same as previous, but with no main class.
* `docs`: make the documentation for the project.
* `clean`: delete the `bin/` directory, where all the class files and artifacts are generated.
* `cleandoc`: delete the `doc/` directory, home of the generated documentation. 
* `cleanall`: run both the `clean` and `cleandoc` jobs. 

The `make` command is available only on unix-like systems, for users of inferior systems one may use [WSL](https://learn.microsoft.com/en-us/windows/wsl/) or [MSYS2](https://www.msys2.org/).