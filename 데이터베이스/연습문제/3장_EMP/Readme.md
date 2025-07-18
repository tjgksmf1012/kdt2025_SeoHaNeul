```
[db 만들기]
create database exam;
use exam;
```

```
[1번]
create table Dept (
    deptno INT PRIMARY KEY,
    dname  VARCHAR(20) NOT NULL,
    loc    VARCHAR(20)
);
```

```
[2번]
create table Emp (
    empno    INT PRIMARY KEY,
    ename    VARCHAR(20) NOT NULL,
    job      VARCHAR(20),
    mgr      INT,
    hiredate DATETIME,
    sal      INT,
    comm     INT,
    deptno   INT,
);
```

```
[3번]
insert into Dept (deptno, dname, loc) values
    (10, 'ACCOUNTING', 'NEW YORK'),
    (20, 'RESEARCH', 'DALLAS'),
    (30, 'SALES', 'CHICAGO'),
    (40, 'OPERATIONS', 'BOSTON');
```

```
[4번]
insert into Emp (empno, ename, job, mgr, hiredate, sal, comm, deptno) values
    (7369, 'SMITH', 'CLERK',    7902, '1920-12-17', 800,  NULL, 20),
    (7499, 'ALLEN', 'SALESMAN', 7698, '1981-02-20', 1600, 300,  30),
    (7521, 'WARD',  'SALESMAN', 7698, '1981-02-22', 1250, 500,  30),
    (7566, 'JONES', 'MANAGER',  7839, '1981-04-02', 2975, NULL, 20);
```

```
[5번]
부서번호가 50인 사원이 없어서 외래키 제약조건 오류 발생
```

```
[6번]
select e.ename, d.loc
from Emp e
join Dept d on e.deptno = d.deptno;
```

```
[7번]
alter table Dept ADD managername varchar(20);
update Dept set managername = 'CLARK' where deptno = 10;
update Dept set managername = 'JONES' where deptno = 20;
update Dept set managername = 'BLAKE' where deptno = 30;
update Dept set managername = NULL   where deptno = 40;
