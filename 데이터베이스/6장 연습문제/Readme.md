```
1. A, B, D

2. (A, C)

3. (A, C, D)

4. (B, C)
```

```
A. (shipname, date)

B. Ship(shipname, shiptype)

C. Voyage(voyageID, shipname, cargo)

D. Shipping(shipname, date, voyageID, port)
```

```
A. partnumber → description, supplier → suppaddress, (partnumber, supplier) → price

B. 1NF만 만족한다 (부분 함수 종속성 때문에 2NF는 불만족)

C. R1: 1NF, R2: BCNF

D. R3(partnumber, description) — BCNF, R4(partnumber, supplier, price) — BCNF, R2(supplier, suppaddress) — BCNF
```
