# SQL Execution Order

Get the data → filter rows → form groups → filter groups → choose the output → remove duplicates → sort → paginate.

```text
1. FROM / JOIN
   → Which tables do I get data from?
   → How do I join different data based on a condition?

2. WHERE
   → Which individual rows should I keep?

3. GROUP BY
   → How should I group the remaining rows?
   → What operations should I perform on each group?
      e.g. SUM, COUNT, AVG, MAX, MIN

4. HAVING
   → Which groups should I keep after aggregation?

5. SELECT
   → What data or calculated results do I want to return?

6. DISTINCT
   → Remove duplicate results.

7. ORDER BY
   → How should I sort the results?

8. LIMIT / OFFSET
   → How many results should I return?
   → Where should I start from?
```

