package luckytrial;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.List;

import luckytrial.events.EventBus;
import luckytrial.events.GameEventListener;

public final class TestSupport {
    private TestSupport() {
    }

    public static void assertTrue(boolean condition, String message) {
        if (!condition) {
            throw new AssertionError(message);
        }
    }

    public static void assertFalse(boolean condition, String message) {
        assertTrue(!condition, message);
    }

    public static void assertEquals(Object expected, Object actual, String message) {
        if (expected == null && actual == null) {
            return;
        }
        if (expected != null && expected.equals(actual)) {
            return;
        }
        throw new AssertionError(message + " expected=" + expected + " actual=" + actual);
    }

    public static void assertNotNull(Object value, String message) {
        assertTrue(value != null, message);
    }

    public static void assertNull(Object value, String message) {
        assertTrue(value == null, message);
    }

    public static void assertThrows(Class<? extends Throwable> expected, Runnable action, String message) {
        try {
            action.run();
            throw new AssertionError(message + " expected exception " + expected.getName());
        } catch (Throwable ex) {
            if (!expected.isInstance(ex)) {
                throw new AssertionError(message + " expected " + expected.getName() + " but got " + ex, ex);
            }
        }
    }

    public static ByteArrayOutputStream captureStdOut(Runnable action) {
        PrintStream original = System.out;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
        try {
            action.run();
        } finally {
            System.setOut(original);
        }
        return baos;
    }

    @SuppressWarnings("unchecked")
    public static void clearEventBus() {
        try {
            Field field = EventBus.class.getDeclaredField("LISTENERS");
            field.setAccessible(true);
            List<GameEventListener> listeners = (List<GameEventListener>) field.get(null);
            listeners.clear();
        } catch (Exception ex) {
            throw new IllegalStateException("Unable to reset EventBus", ex);
        }
    }
}
