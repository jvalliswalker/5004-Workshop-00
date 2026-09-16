# LLM Code Evaluation Report — Assignment 0: Setup

## Student Information
- **Name**: Jamil Vallis-Walker
- **Date**: 2026-09-16
- **LLM Used**: Claude (Opus 5, via Claude Code in VS Code)

## Prompt Used

> Here is a Java class. What would you criticise about it, and what would you leave alone?

(`src/main/java/edu/northeastern/setup/Greeting.java` was open in the editor and supplied as
context. The model also read `Main.java`, `GreetingTest.java`, and `build.gradle` on its own, and
ran the project.)

## What the LLM said

Read the class, plus `Main`, `GreetingTest`, and `build.gradle` for context, and ran it.

### What I'd criticise

**One real thing: the em dash in the string literal.** `Greeting.java:25` uses U+2014 (`—`), and on
this machine it does not survive being printed:

```
source bytes:  342 200 224   → U+2014, correct UTF-8
output bytes:  357 277 275   → U+FFFD, the replacement character
```

`./gradlew run` prints `Hello, Java! ? Jamil`. The cause is the JVM's encoding split on Windows:
`file.encoding = UTF-8` (so javac reads the source correctly and the in-memory string is fine) but
`native.encoding` and `stdout.encoding` are both `Cp1252`. The character is destroyed on the way
out, not just misrendered by my terminal capture — I redirected to a file and inspected the bytes.

This is worth caring about for a reason bigger than cosmetics: the only thing this project does is
print one string, and that string is lossy on the platform you're working on. If a grader diffs
stdout, or CI logs it, or a later assignment asserts on exact output, you'll debug a phantom. A
plain `-` or `--` costs nothing here. The alternative fix — `compileJava.options.encoding = 'UTF-8'`
plus `run { jvmArgs '-Dstdout.encoding=UTF-8' }` in `build.gradle` — is the right habit for real
projects but is more machinery than a throwaway package deserves.

Related, and the reason the bug is invisible: `GreetingTest` asserts the prefix and the absence of
the placeholder, never the actual full output. That's a deliberate teaching choice (one test green,
one red on a fresh clone), so I wouldn't change it — just note that "tests pass" and "output is
correct" aren't the same claim here.

### What I'd leave alone

Nearly everything, and deliberately so. The class documents itself as scaffolding you can forget
after this week, and applying a general code-review checklist to it would be the actual mistake.
Specifically, I would *not* raise:

- **`private` constructor + `final` class for a two-line utility.** Ceremonious in the abstract, but
  this is a course setting the conventions you'll be graded against under Checkstyle. Correct as
  written.
- **`STUDENT_NAME` as a constant rather than a parameter.** `message(String name)` would be more
  testable and more "real," but the assignment is *edit one line*. Parameterising it defeats the
  exercise.
- **`message()` vs `getMessage()`.** A naming preference, not a defect.
- **The Javadoc example saying "Ada Lovelace" while the constant says "Jamil."** Technically drift,
  but it's an illustrative placeholder and reads as intended.
- **No `equals`/`hashCode`/interface/injection.** There's no design here to improve; there's no
  design here on purpose.

The short version: the only change I'd make is replacing `—` with `-`. The rest is well-formed for
what it is, and the instinct to polish it is the instinct to resist.

## Your checklist

- **`./gradlew run` prints the greeting with my name?**: Yes — prints `Hello, Java! ? Jamil`. The
  name is correct; the em dash separator is the encoding issue described above.
- **`./gradlew test` is green?**: Yes — 2 tests, 0 failures, 0 errors
  (`build/test-results/test/TEST-edu.northeastern.setup.GreetingTest.xml`).
- **Coverage + checkstyle reports generated?**: Yes — `build/reports/jacoco/test/html/index.html`,
  `build/reports/jacoco/test/jacocoTestReport.xml`, and `build/reports/checkstyle/main.html`
  (0 violations).
- **I can explain what `Greeting.message()` returns and why the test passes?**: _[confirm yourself]_

## Reflection

_[1–2 sentences in your own words: did anything in the setup trip you up (JDK, git, your editor,
Gradle)? How did you resolve it?]_
