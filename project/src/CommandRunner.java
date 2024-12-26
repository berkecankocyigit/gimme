package src;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class CommandRunner {
    private String commandPath;
    private String command;
    private String[] args;

    public CommandRunner(String commandPath, String command, String... args) {
        this.commandPath = commandPath;
        this.command = command;
        this.args = args;
    }

    public int run() {
        try {
            // Combine command and arguments into a single array
            String[] cmdArray = new String[args.length + 1];
            cmdArray[0] = command;
            System.arraycopy(args, 0, cmdArray, 1, args.length);

            // Validate if the path is valid
            File directory = new File(commandPath);
            if (!directory.exists() || !directory.isDirectory()) {
                System.err.println("Error: Path does not exist or is not a directory - " + commandPath);
                return -1; // Custom error code for invalid directory
            }

            // Start subprocess in the specified directory
            ProcessBuilder processBuilder = new ProcessBuilder(cmdArray);
            processBuilder.directory(directory); // Change to the specified directory
            processBuilder.redirectErrorStream(true); // Combine stdout and stderr

            System.out.println("Running command: " + command + " in directory: " + commandPath);
            Process process = processBuilder.start();

            // Read and print output
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            }

            // Wait for the process to finish
            int exitCode = process.waitFor();
            System.out.println("Command exited with code: " + exitCode);

            if (exitCode != 0) {
                System.err.println("Error: Command failed with exit code " + exitCode);
                return -1; // Map non-zero exit codes to -1 for consistent failure reporting
            }

            return exitCode; // Return the actual exit code on success
        } catch (Exception e) {
            System.err.println("Exception while running the command: " + e.getMessage());
            return -1; // Custom error code for exceptions
        }
    }
}