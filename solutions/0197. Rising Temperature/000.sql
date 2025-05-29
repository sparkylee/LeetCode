# Write your MySQL query statement below
select W1.Id from Weather W1 join Weather W2 on 
DATEDIFF(W1.RecordDate,W2.RecordDate)=1
where W1.Temperature>W2.Temperature;