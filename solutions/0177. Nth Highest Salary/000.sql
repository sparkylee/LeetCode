CREATE FUNCTION getNthHighestSalary(N INT) RETURNS INT
BEGIN
  RETURN (
      # Write your MySQL query statement below.
      select IF(count(*)<N,null,min(Salary)) from (select distinct Salary from Employee order by Salary DESC limit N) B
  );
END