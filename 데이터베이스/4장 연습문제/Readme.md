```
1번
SELECT ename FROM emp WHERE mgr IS NULL;
```

```
2번
[JOIN]
SELECT e.ename, d.dname FROM emp e JOIN dept d ON e.deptno = d.deptno;

[스칼라 부속질]
SELECT ename, (SELECT dname FROM dept d WHERE d.deptno = e.deptno) AS dname FROM emp e;
```

```
3번
[JOIN]
SELECT e.ename FROM emp e JOIN dept d ON e.deptno = d.deptno WHERE d.loc = 'CHICAGO';

[중첩질의]
SELECT ename FROM emp WHERE deptno = (SELECT deptno FROM dept WHERE loc = 'CHICAGO');

[EXISTS]
SELECT e.ename FROM emp e
WHERE EXISTS (
    SELECT 1
    FROM dept d
    WHERE d.deptno = e.deptno AND d.loc = 'CHICAGO'
);
```

```
4번
SELECT ename FROM emp WHERE sal > (SELECT AVG(sal) FROM emp);
```

```
5번
SELECT e.ename FROM emp e
WHERE sal > (
    SELECT AVG(sal)
    FROM emp
    WHERE deptno = e.deptno
);
```
