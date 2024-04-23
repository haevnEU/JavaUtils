package de.haevn.utils.logging;

import de.haevn.utils.exceptions.ExceptionUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

public record SanitizedLogEntry(String date, String level, String source, String method, String thread, Object object, String message, String throwable){
    public static SanitizedLogEntry getFromLogEntry(LogEntry entry){
        final SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm:ss");
        final Date resultdate = new Date(entry.getTimestamp());
        return new SanitizedLogEntry(sdf.format(resultdate), entry.getLevel().name(), entry.getHelper().getFileName() + ":" + entry.getHelper().getLineNumber(), entry.getHelper().getClassName() + "#" + entry.getHelper().getMethodName(), entry.getThreadName(), entry.getObj(), entry.getMessage(), ExceptionUtils.getStackTrace(entry.getThrowable()));
    }
}
