package edu.northeastern.setup;

/** Prints the greeting. Run with {@code ./gradlew run}. */
public final class Main {

  private Main() {
    // entry point only: no instances
  }

  /**
   * Prints {@link Greeting#message()} to standard output.
   *
   * @param args ignored
   */
  public static void main(String[] args) {
    System.out.println(Greeting.message());
  }
}
