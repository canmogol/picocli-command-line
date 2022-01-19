package com.canmogol.picocli;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import picocli.jansi.graalvm.AnsiConsole;

import java.io.File;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.stream.Stream;

@Command(name = "list", mixinStandardHelpOptions = true,
        version = "list 1.0",
        description = "Lists the files in the current or the provided folder.")
class ListFiles implements Callable<Integer> {

    @Parameters(index = "0", description = "The directory to list all the files.")
    private File path;

    @Option(names = {"-f", "--files"}, description = "Show only files")
    private boolean onlyFiles = false;

    public static void main(String... args) {
        int exitCode;
        try (AnsiConsole ansi = AnsiConsole.windowsInstall()) {
            exitCode = new CommandLine(new ListFiles()).execute(args);
        }
        System.exit(exitCode);
    }

    @Override
    public Integer call() throws Exception {
        if (!path.isDirectory()) {
            log("Provided path is not a directory.");
            return 74;
        }
        final Stream<File> files = Stream.of(Objects.requireNonNull(path.listFiles()));
        if (onlyFiles) {
            files.filter(File::isFile).forEach(p -> log(p.getName()));
        } else {
            files.forEach(p -> log(p.getName()));
        }
        return 0;
    }

    private void log(String message) {
        System.out.println(message);
    }
}
