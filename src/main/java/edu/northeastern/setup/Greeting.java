package edu.northeastern.setup;

/**
 * The one thing you edit this week: put your name in {@link #STUDENT_NAME} so the
 * app greets you by name. That is the whole assignment — the point is to get the
 * build, tests, LLM evaluation, and submission workflow working before Assignment 01.
 *
 * <p>This package is deliberately its own small world. It belongs to neither of the
 * two codebases you will actually build this term, and nothing here carries forward:
 * after this week you can forget it entirely. What carries forward is the layout it
 * sits in, and the commands you ran against it.
 */
public final class Greeting {

  private static final String STUDENT_NAME = "YOUR NAME HERE";

  private Greeting() {
    // utility class: no instances
  }

  /**
   * @return a greeting naming the student, e.g. {@code "Hello, Java! — Ada Lovelace"}.
   */
  public static String message() {
    return "Hello, Java! — " + STUDENT_NAME;
  }
}
