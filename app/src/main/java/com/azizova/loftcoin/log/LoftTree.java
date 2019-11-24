package com.azizova.loftcoin.log;

import org.jetbrains.annotations.NotNull;

import java.util.Locale;

import timber.log.Timber;

public class LoftTree extends Timber.DebugTree {
    @Override
    protected void log(int priority, String tag, @NotNull String message, Throwable t) {
        final Thread thread = Thread.currentThread();
        super.log(priority, tag, String.format(Locale.US,
                "[%s] %s",
                thread.getName(),
                message
        ), t);
    }
}
