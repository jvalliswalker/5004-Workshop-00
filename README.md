# Assignment 00 — Setup: Your Environment & the Weekly Loop

Welcome! This is the **onboarding** week. There's almost no code to write — the goal is to get your
environment and the weekly workflow working **before** anything counts, and to understand *why* a
real Java project is laid out the way this one is. Read the second half of this file carefully; it's
the actual lesson.

This project is **standalone**. You will build two independent codebases this term — an animal
shelter and a task board — and this is neither of them. Nothing you write here carries into either.
What carries over is the *shape*: every assignment you receive is laid out exactly like this one and
answers to exactly the same commands. That is the whole point of doing it once, now, with nothing at
stake.

> **Module 0** — *Getting Started / Course Setup & Java Environment*.
> **Week 1 · graded, weighted equally with every other assignment** — but it's tiny, so it's
> essentially free points for getting set up. If this all works this week, every real assignment is
> just more of the same.
>
> Out **Mon Sep 14**, due **Fri Sep 18, 5:00 PM**. The window is short on purpose: Assignment 01 —
> the first real one — arrives the following Monday, and you want this working before it does.

---

## Part 1 — Do the task

### The one change you make

Open [`Greeting.java`](src/main/java/edu/northeastern/setup/Greeting.java) and replace the
placeholder with **your name**:

```java
private static final String STUDENT_NAME = "YOUR NAME HERE";   // <-- put your name
```

That's the whole code change. Everything else is running the tools and understanding the layout.

### Do the whole loop

1. **Open** this project in your editor with **JDK 21** — VS Code is the supported one, IntelliJ
   works too. *(No Java or editor yet? [installing-java.md](installing-java.md) sets up both.)*
2. **Run it:** `./gradlew run` — you'll see the greeting (with the placeholder at first).
3. **Add your name** in `Greeting` (above).
4. **Test:** `./gradlew test` — the `messageNamesTheStudent` test is red until your name is in.
5. **Reports:** `./gradlew jacocoTestReport checkstyleMain` — open the HTML reports under
   `build/reports/`.
6. **LLM self-eval:** give the ready-made prompt in
   [LLM-Evaluation-prompt.md](LLM-Evaluation-prompt.md) to your LLM (paste in your code + tool
   output), then save its answer as **`submission/LLM-Evaluation.md`** (structure from
   [LLM-Evaluation-Template.md](LLM-Evaluation-Template.md)).
7. **Collect & submit:** copy your edited `Greeting.java` into **`submission/`**, then
   `git add -A && git commit -m "Assignment 00: setup" && git push`.
8. **Code walk:** record a ~1-minute video tour of your change + passing test, and **upload it to
   Canvas by Tue Sep 22, 5:00 PM PT**. It is worth **10 points** and doing it earns all 10 —
   see [code-walk.md](code-walk.md).

See [how-to-submit.md](how-to-submit.md) for the checklist.

### Command reference

```bash
./gradlew run               # compile and run Main (prints the greeting)
./gradlew test              # compile and run the JUnit tests
./gradlew jacocoTestReport  # coverage  -> build/reports/jacoco/test/html/index.html
./gradlew checkstyleMain    # style     -> build/reports/checkstyle/main.html
./gradlew build             # do essentially all of the above at once
```

Requires JDK 21 (see [installing-java.md](installing-java.md)); the Gradle wrapper (`./gradlew`)
fetches everything else.

---

## Part 2 — Why the project looks like this (the real lesson)

You will inherit and extend this exact structure every week. Ten minutes understanding it now saves
you hours later. If you already know Java, skim; if you're new to it, this is your foundation.

### 2.1 What a Java package *is*

A **package** is a named group of related classes — Java's way of organizing code and keeping names
from colliding. Look at the very first line of `Greeting.java`:

```java
package edu.northeastern.setup;
```

That declares: "the `Greeting` class lives in the package `edu.northeastern.setup`." The class's
real, **fully-qualified name** is therefore `edu.northeastern.setup.Greeting` — the package name
plus the class name. Packages exist for three concrete reasons:

1. **No name clashes.** Java's standard library already has a `java.util.List` and a `java.awt.List`.
   Both can exist because their packages differ. A class you write in a later week won't collide with a
   same-named class in the JDK for the same reason — they're fully-qualified differently.
2. **Organization.** Related classes sit together — next week the shelter's model classes share one
   package — so a reader knows where to look.
3. **Access control.** A member with no `public`/`private`/`protected` keyword is *package-private*:
   visible only to other classes **in the same package**. Packages are the boundary that makes that
   meaningful.

**The naming convention** is *reverse domain name*. Northeastern's domain is `northeastern.edu`, so
Java code starts with `edu.northeastern`, then a project name: `edu.northeastern.setup`. Reversing
the domain guarantees globally-unique package names (nobody else owns `edu.northeastern`), which
matters the moment you use third-party libraries. Package names are lowercase by convention.

To use a class from *another* package, you **import** it:

```java
import java.util.Objects;      // now you can write Objects.hash(...) instead of java.util.Objects.hash(...)
```

Classes in the **same** package need no import. `java.lang` (things like `String`, `System`) is
imported automatically — that's why `System.out.println` just works.

### 2.2 Packages are folders

Here's the rule that trips up newcomers: **a class's package must match its folder path.** Because
`Greeting` is in `edu.northeastern.setup`, its file must live at:

```
src/main/java/edu/northeastern/setup/Greeting.java
                └──────────┬──────────┘
                 one folder per package segment
```

Each dot in the package name is a directory level. The compiler enforces this — put the file in the
wrong folder and the build fails. So the deep, repetitive folder chain you see isn't bureaucracy;
it's the package name written out as directories.

### 2.3 `src/main/java` vs `src/test/java` — source sets

Two parallel trees, same package structure:

```
src/
├── main/java/edu/northeastern/setup/   ← the program: Greeting, Main
└── test/java/edu/northeastern/setup/   ← the tests:   GreetingTest
```

This is a **Gradle convention** (shared with Maven). Gradle calls each tree a *source set*:

- **`main`** is your actual application code. It's what ships / runs.
- **`test`** is your JUnit tests. They are compiled with extra libraries (JUnit) that the main code
  never sees, and they are **not** part of the shipped program.

Keeping the same package (`edu.northeastern.setup`) in both trees is deliberate: because
`GreetingTest` is in the *same package* as `Greeting`, it can reach package-private members for
testing without you having to make everything `public`.

### 2.4 Gradle — the build tool

Compiling, running, testing, checking style, and downloading libraries by hand gets old fast.
**Gradle** automates all of it. You describe *what* the project needs in
[`build.gradle`](build.gradle), and Gradle figures out *how*. A few pieces to recognize there:

- **`plugins { … }`** — turns on capabilities: `java` (compile Java), `application` (adds the `run`
  task), `jacoco` (coverage), `checkstyle` (style checks).
- **`repositories { mavenCentral() }`** — where to download libraries from.
- **`dependencies { … }`** — the libraries this project uses. Here, JUnit 5 for tests
  (`testImplementation` means "only the test source set needs it").
- **`application { mainClass = 'edu.northeastern.setup.Main' }`** — which class `./gradlew run`
  should launch (note: the *fully-qualified* name again).

You interact with Gradle through **tasks** — `run`, `test`, `build`, `checkstyleMain`. Each task
depends on earlier ones (running `test` compiles the code first), so you rarely think about ordering.

### 2.5 The Gradle *wrapper* (`gradlew`)

You did not install Gradle, yet `./gradlew` works. That's the **wrapper**: the `gradlew` /
`gradlew.bat` scripts plus `gradle/wrapper/gradle-wrapper.properties`, which pins an **exact Gradle
version** (8.14). The first run downloads that version; everyone on the course — and the
autograder — then builds with the *same* Gradle. This is how we avoid "works on my machine": always
run `./gradlew`, never a `gradle` you installed yourself.

### 2.6 The quality tools: JUnit, JaCoCo, Checkstyle

Real software is judged on more than "it runs." Three tools make those other qualities visible, and
they show up in every assignment:

- **JUnit 5** — the testing framework. A test is a method annotated `@Test` that asserts your code
  does what it should (`assertTrue`, `assertEquals`, …). Tests are how you *prove* correctness and
  how the graders check it. TDD (test-driven development) — writing tests alongside or before the
  code — is a course theme.
- **JaCoCo** — measures **test coverage**: which lines of your code the tests actually exercised.
  `./gradlew jacocoTestReport` writes an HTML report; green lines are covered, red are not. High
  coverage means your tests actually look at your code.
- **Checkstyle** — enforces a shared **style** (naming, spacing, Javadoc) from the rules in
  [`config/checkstyle/checkstyle.xml`](config/checkstyle/checkstyle.xml). Consistent style is how a
  team reads each other's code quickly — a real professional skill, not busywork.

### 2.7 The whole file tour

```
Setup00/
├── build.gradle          what to build + which libraries/tools (Part 2.4)
├── settings.gradle       the project's name
├── gradlew, gradlew.bat  the Gradle wrapper scripts (Part 2.5)
├── gradle/wrapper/       pins the exact Gradle version
├── config/checkstyle/    the style rules Checkstyle enforces
├── src/main/java/…       the program  (Greeting, Main)
├── src/test/java/…       the JUnit tests (GreetingTest)
├── README.md             this file
├── how-to-submit.md      the deliverables + checklist
└── LLM-Evaluation-Template.md   the self-assessment you copy to LLM-Evaluation.md
```

Everything you build this term slots into exactly this shape. That's the point of Assignment 00: next
week you add `Species`, `AgeMonths`, and `Animal` to `src/main/java/edu/northeastern/shelter/`, write
their tests in the matching `test` folder, and run the very same commands — no setup friction, just
design.

---

## Next week

**Assignment 01** (Module 1) starts the real work: the animal-shelter value objects — `Species`,
`AgeMonths`, `Animal`. It arrives Monday Sep 21 in its own repository, with this same layout and the
same commands. By then this build-test-submit loop should be second nature.

Note that the package changes — `edu.northeastern.shelter`, not `edu.northeastern.setup`. Each
assignment is a self-contained project with its own package; there is no single repository that
accumulates all term. The task board you meet in Assignment 02 is a third, separate one again.
