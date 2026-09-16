# Installing Java (JDK 21) on your machine

This course uses **Java 21** and **JUnit 5**. You run the tests yourself with `./gradlew test`, so
the JDK on your machine is the one that matters — please use **21 exactly**, because some assignments
use language features that older JDKs reject outright. You only need a **JDK 21**: the Gradle wrapper
(`./gradlew`) downloads Gradle and every library for you, so there is nothing else to install.

> **Already have it?** Run `java -version`. If it prints `21.x` (e.g. `openjdk version "21.0.5"`),
> you're set — skip to [§3 Your editor](#3-your-editor). Otherwise install a JDK 21 below.
>
> Any JDK 21 works; we recommend **Eclipse Temurin**, a free, widely-used OpenJDK build from the
> Adoptium project.

## 1. Install a JDK 21

### macOS
- **Homebrew (recommended):**
  ```bash
  brew install --cask temurin@21
  ```
  (No Homebrew? Install it first: <https://brew.sh>.)
- **Installer:** download the macOS `.pkg` from Adoptium and run it —
  <https://adoptium.net/temurin/releases/?version=21> (OS: **macOS**; Architecture: **aarch64** for
  Apple Silicon / M-series, **x64** for older Intel Macs).
- **SDKMAN** (nice if you juggle multiple Java versions): install from <https://sdkman.io>, then run
  `sdk list java` and install a `21...-tem` build, e.g. `sdk install java 21.0.5-tem`.

### Windows
- **winget:**
  ```powershell
  winget install EclipseAdoptium.Temurin.21.JDK
  ```
- **Installer:** download the Windows `.msi` from
  <https://adoptium.net/temurin/releases/?version=21> (OS: **Windows**; Architecture: **x64**). In
  the installer, enable **"Set JAVA_HOME"** and **"Add to PATH"**.

### Linux
- **Debian/Ubuntu:**
  ```bash
  sudo apt update && sudo apt install openjdk-21-jdk
  ```
- **Fedora:** `sudo dnf install java-21-openjdk-devel`
- **SDKMAN (any distro):** <https://sdkman.io>, then `sdk install java 21.0.5-tem`.

## 2. Verify the install

Open a **new** terminal and run:

```bash
java -version
javac -version
```

Both should report **21** (for example `openjdk version "21.0.5"`). If they don't, see
[Troubleshooting](#troubleshooting).

## 3. Your editor

**VS Code is the supported editor for this course** — it is what you will see in workshops and what
we can most reliably help you fix. **IntelliJ IDEA Community Edition also works**; these are plain
Gradle projects and open in either. Pick one and set it up before week 1.

Whichever you choose, use one with an **LLM assistant** available in it. This course is designed to be
worked through alongside one.

### VS Code (supported)

1. **Install VS Code:** <https://code.visualstudio.com>.
2. **Install two extensions** from the Extensions panel (`Ctrl/Cmd + Shift + X`):
   - **Extension Pack for Java** (Microsoft) — language support, debugger, and the test runner.
   - **Gradle for Java** (Microsoft) — adds the Gradle panel that lists this project's tasks.
3. **Open the project:** `File ▸ Open Folder…` and select the assignment folder — **the folder that
   contains `build.gradle`**, not its parent and not a subfolder. This is the single most common
   setup mistake; if Java support looks broken, check this first. Give it a minute to import.
4. **Point it at JDK 21:** open the Command Palette (`Ctrl/Cmd + Shift + P`) → **"Java: Configure
   Java Runtime"** → set the project's JDK to your **21** install. That screen will also download a
   JDK 21 for you if you skipped §1.
5. **Check it works:** open the built-in terminal (`` Ctrl/Cmd + ` ``) and run `./gradlew test`. You
   can also use the **Testing** sidebar or the **Gradle** panel once the project has imported.

### IntelliJ IDEA (alternative)

1. Download and install: <https://www.jetbrains.com/idea/download/> (scroll to *Community Edition*).
2. **Open the project:** `File ▸ Open…` and select the assignment folder. IntelliJ will detect the
   Gradle project and import it (give it a minute the first time).
3. **Point it at JDK 21:** `File ▸ Project Structure ▸ Project ▸ SDK` → pick your **21** JDK.
   *No JDK yet?* This same menu has **"Download JDK…"** — choose vendor **Eclipse Temurin**, version
   **21**, and IntelliJ installs it for you (an easy alternative to §1).
4. **Check it works:** open the built-in terminal and run `./gradlew test`, or use the Gradle tool
   window.

### What a correct first run looks like

`./gradlew test` is the check that matters, in either editor. Most projects in this course are
libraries exercised entirely through JUnit — there is no `main` to run and no `./gradlew run` task.

**Two exceptions**, and both say so in their own README: the **week-1 setup project**, and
**Assignment 06**, whose provided Swing view has a `BoardApp` you are welcome — but not required —
to run.

**A run that reports failing tests is the correct starting state for a new assignment** — those are
the stubs you have not written yet. A run that reports a *build* error is not, and is worth asking
about.

## Troubleshooting

- **`java -version` shows the wrong version, or "command not found":** your `PATH` points at a
  different (or no) JDK. Close and reopen the terminal after installing. On macOS, list all installed
  JDKs with `/usr/libexec/java_home -V` and set `JAVA_HOME` to the 21 one. On Windows, re-run the
  installer and make sure **"Add to PATH"** is checked.
- **Gradle says it needs a different Java version:** confirm your JDK is **21** (not 8, 11, or 17).
  In VS Code, re-check *Command Palette ▸ "Java: Configure Java Runtime"*; in IntelliJ, *Project
  Structure ▸ Project ▸ SDK*.
- **VS Code shows no Java features, or "classpath is incomplete":** you almost certainly opened the
  wrong folder. It must be the one containing `build.gradle`. Reopen with `File ▸ Open Folder…`, then
  *Command Palette ▸ "Java: Clean Java Language Server Workspace"* and reload.
- **Do NOT install Gradle yourself.** Always use the wrapper, `./gradlew` — it pins the exact Gradle
  version everyone (and the autograder) uses.

## Getting help

Setup snags are normal in Week 1 and quick to fix. Bring the exact error message to **office hours**
or **Wednesday recitation** early — don't lose days to a `PATH` issue.
