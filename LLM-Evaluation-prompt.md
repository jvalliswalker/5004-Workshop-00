# LLM Evaluation Prompt — Assignment 0

**How to use.** Open your LLM (Claude, ChatGPT, Copilot Chat, …), paste **everything inside the box
below**, and replace each `<PASTE …>` marker with your actual files/output. Then copy the LLM's
answer into `LLM-Evaluation.md` (structure it with
[LLM-Evaluation-Template.md](LLM-Evaluation-Template.md)) and add your reflection.

> This week the code is trivial — the point is to **practice using an LLM as a reviewer** and to
> confirm your setup works. The LLM should *check* your work, not do it for you.

---

```text
You are a friendly but precise teaching assistant for a graduate Java course. This is a SETUP
assignment (Assignment 0) — the only code change is that the student put their name into a Greeting
class so a test passes. Help me confirm my environment and workflow are correct. Do not rewrite my
code; just verify and explain.

Check the following and answer each briefly:
1. In my Greeting class, does message() return a greeting that includes MY name (not the placeholder
   "YOUR NAME HERE")? Quote the exact string it would return.
2. Given that, will the JUnit test messageNamesTheStudent PASS (it asserts the message is not blank
   and no longer contains "YOUR NAME HERE")? Will messageHasGreetingPrefix still pass?
3. In one or two plain-English sentences, explain what STUDENT_NAME and message() do, so I can say it
   in my own words during my code walk.
4. Did my tool output below indicate a clean run (tests green, coverage + checkstyle reports
   generated)? If something looks wrong, tell me what to re-check (JDK version, ./gradlew, etc.).

Here is my Greeting.java:
<PASTE the contents of src/main/java/edu/northeastern/sketchpad/Greeting.java>

Here is the output of my commands (paste what you saw):
<PASTE the output of: ./gradlew run   and   ./gradlew test jacocoTestReport checkstyleMain>
```
