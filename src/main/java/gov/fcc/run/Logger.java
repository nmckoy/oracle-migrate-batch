package gov.fcc.run;


import java.util.Date;

// just to log
public class Logger {
    private static int rowsUpdated  = 0;
    private static int rowsFailed   = 0;
    private static int writtenFiles = 0;
    private static int failedWrites = 0;

    static final String date = new Date() + ": ";

    public void logWithDate(String text) {
        System.out.println(date + text);
    }

    public void log(String text) {
        System.out.println(text);
    }

    public int getRowsUpdated() {
        return rowsUpdated;
    }

    public void addRowsUpdated(int rowsUpdated) {
        Logger.rowsUpdated += rowsUpdated;
    }

    public int getRowsFailed() {
        return rowsFailed;
    }

    public void addRowsFailed(int rowsFailed) {
        Logger.rowsFailed += rowsFailed;
    }

    public int getWrittenFiles() {
        return writtenFiles;
    }

    public void addWrittenFiles(int writtenFiles) {
        Logger.writtenFiles += writtenFiles;
    }

    public int getFailedWrites() {
        return failedWrites;
    }

    public void addFailedWrites(int failedWrites) {
        Logger.failedWrites += failedWrites;
    }
}
