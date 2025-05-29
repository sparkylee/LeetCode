# Write your MySQL query statement below
select IFNULL( ( select Salary from Employee where Salary < (select max(Salary) from Employee) order by Salary desc limit 1 ) ,null ) as SecondHighestSalary;