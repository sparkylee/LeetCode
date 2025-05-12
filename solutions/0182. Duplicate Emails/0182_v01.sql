# Write your MySQL query statement below
select distinct P1.Email from Person P1 join Person P2 on P1.Email = P2.Email where P1.Id != P2.Id;