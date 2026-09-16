package edu.northeastern.setup;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("current")
class GreetingTest {

  @Test
  void messageHasGreetingPrefix() {
    // Provided: already green — the greeting always starts this way.
    assertTrue(Greeting.message().startsWith("Hello, Java!"),
        "message() should start with \"Hello, Java!\"");
  }

  @Test
  void messageNamesTheStudent() {
    // Fails on a fresh clone; passes once you replace the placeholder with your name.
    String message = Greeting.message();
    assertFalse(message.isBlank(), "message() must not be blank");
    assertFalse(message.contains("YOUR NAME HERE"),
        "Put your name in Greeting.STUDENT_NAME (replace the placeholder).");
  }
}
