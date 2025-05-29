# Write your MySQL query statement below
select * from cinema where MOD(id,2)=1 and LOWER(description) 
not like '%boring%' order by rating DESC