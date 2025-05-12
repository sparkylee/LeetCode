# Write your MySQL query statement below

select distinct Num as ConsecutiveNums  from
    (select Num, count(*) as rankCnt from
                                         (select id,Num, CAST(@row:=IF(Num=NumPre,@row,@row+1) as UNSIGNED) as rank
                                          from (select l1.id, Num, (select Num from Logs l2 where l2.id < l1.id order by l2.id desc limit 1) as NumPre from Logs l1) LogsJoined,
                                         (SELECT @row := 0) r order by id ) T
group by rank having rankCnt>=3) T