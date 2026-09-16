# How to Submit — Assignment 0: Setup

A0 **counts as a full weekly assignment — weighted equally with A1–A9.** It's deliberately tiny, so
it's essentially free points for getting set up; the catch is you actually have to do it (including
the code-walk video). Do the whole loop once.

Everything you turn in goes in the **`submission/`** folder at the repo root.

## Deliverables

1. **Your name** in `Greeting.STUDENT_NAME` (the `messageNamesTheStudent` test passes).
2. **A copy of your edited `Greeting.java` in `submission/`** (`submission/Greeting.java`).
3. **`submission/LLM-Evaluation.md`** — run the prompt in `LLM-Evaluation-prompt.md` through an LLM
   and save its answer here (structure from the template). A first pass at the self-assessment habit.
4. **A `git push`** to your A0 repo.
5. **Code-walk video** — a ~1-minute recorded walkthrough, uploaded to Canvas by
   **Tue Sep 22, 5:00 PM PT**. **Worth 10 points**, and doing it earns all 10.
   **[code-walk.md](code-walk.md) says exactly what to show** — it is one page, read it.

## Steps

```bash
./gradlew run                                   # see the greeting
# ...edit Greeting.java: put your name in STUDENT_NAME...
./gradlew test jacocoTestReport checkstyleMain  # all green
```

Then put your deliverables in `submission/` and push:

```bash
# 1) copy your edited Greeting into submission/
cp src/main/java/edu/northeastern/sketchpad/Greeting.java submission/

# 2) give the prompt in LLM-Evaluation-prompt.md to your LLM (paste your code + output),
#    then save its answer as submission/LLM-Evaluation.md:
cp LLM-Evaluation-Template.md submission/LLM-Evaluation.md   # then fill in the LLM's assessment

git add -A
git commit -m "A0: setup"
git push
```

> **Due Sunday 5:00 PM PST (Week 1)**; the **code-walk video is due to Canvas by Sunday + 2 days**.
> A0 is graded and weighted like every other week — but it's small and easy, so treat it as free
> points for getting set up. Late work follows the standard **10%/day**. Get help early (office hours
> / recitation) if any step fails.

## Checklist

- [ ] Project opens in your editor (VS Code or IntelliJ) with **JDK 21**
- [ ] `./gradlew run` prints the greeting **with your name**
- [ ] `./gradlew test` is green
- [ ] Coverage + checkstyle reports generated
- [ ] `submission/Greeting.java` — a copy of your edited file
- [ ] `submission/LLM-Evaluation.md` — written
- [ ] Pushed to the repo
- [ ] Code-walk video uploaded to Canvas (by Tue Sep 22, 5:00 PM PT)

---

## Where the code walk is graded

Two Canvas entries, **10 points each** — the code and the code walk carry equal weight all term,
starting now. Recording the video earns all 10; there is no polish score.
[code-walk.md](code-walk.md) says what to show. The written feedback you get back is the point;
this week it is mostly about whether your recording setup works.
