package com.azizova.loftcoin.log;

import org.jetbrains.annotations.NotNull;

import java.util.Locale;

import timber.log.Timber;

public class LoftTree extends Timber.DebugTree {
    @Override
    protected void log(int priority, String tag, @NotNull String message, Throwable t) {
        final Thread thread = Thread.currentThread();
        final StackTraceElement ste = new Throwable().fillInStackTrace().getStackTrace()[5];
        super.log(priority, tag, String.format(Locale.US,
                "[%s] (%s:%d) %s: %s",
                thread.getName(),
                ste.getFileName(),
                ste.getLineNumber(),
                ste.getMethodName(),
                message
        ), t);
    }
}
