# Write your MySQL query statement below
select d2.Name as `Department`, eName as Employee, Salary  from (Select d1.Id as Id,  E.Name as eName,E.Salary from Employee E, (select max(Salary) as Salary, DepartmentId from Employee group by DepartmentId ) T,   Department d1
                                                                 where E.Salary = T.Salary and E.DepartmentId=T.DepartmentId
                                                                   and E.DepartmentId = d1.Id ) T2 join Department d2 on T2.Id = d2.Id

    #select max(Salary) as Salary, DepartmentId from Employee group by DepartmentId