# Math as programming homework

Done as i could not bring myself to exercise on math stuff consistently.

## Libraries

Necessary libraries for compiling the project.
* [lombok](https://projectlombok.org/)

## Running

This project uses `make` to run and compile itself, use with `make [cmd]`, the commands are as follows:
* `run`: the default target, will compile classes and run the project in the JVM.
* `classes`: compile the source files to class files in a to be generated `bin/` directory.
* `jar`: pack tthe class files inside a `.jar` file with main class.
* `jarlib`: same as previous, but with no main class.
* `docs`: make the documentation for the project.

The `make` command is available only on unix-like systems, for users of inferior systems one may use [WSL](https://learn.microsoft.com/en-us/windows/wsl/) or [MSYS2](https://www.msys2.org/).