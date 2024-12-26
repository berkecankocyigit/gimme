import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CommandRunner {
    private String command;
    private String[] args;

    public CommandRunner(String command, String... args) {
        this.command = command;
        this.args = args;
    }

    public int run() {
        try {
            // Combine command and arguments into a single array
            String[] cmdArray = new String[args.length + 1];
            cmdArray[0] = command;
            System.arraycopy(args, 0, cmdArray, 1, args.length);

            // Validate if the command is available
            if (!isCommandAvailable(command)) {
                System.err.println("Error: Command not found - " + command);
                return -1; // Custom error code for command not found
            }

            // Start subprocess
            ProcessBuilder processBuilder = new ProcessBuilder(cmdArray);
            processBuilder.redirectErrorStream(true); // Combine stdout and stderr

            System.out.println("Running command: " + command);
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

    private boolean isCommandAvailable(String command) {
        String os = System.getProperty("os.name").toLowerCase();
        String[] checkCommand;
        if (os.contains("win")) {
            checkCommand = new String[]{"cmd", "/c", "where", command};
        } else {
            checkCommand = new String[]{"which", command};
        }
        try {
            Process checkProcess = new ProcessBuilder(checkCommand).start();
            return checkProcess.waitFor() == 0;
        } catch (Exception e) {
            return false;
        }
    }
}