-- (1) Write MySQL query to find IPs that mode more than a certain number of requests for a given time period.
select distinct ip from log
where ((select count(*) from log where log_date between '2017-01-01 00:00:12' and '2017-01-01 00:01:59') > 200);

-- (2) Write MySQL query to find requests made by a given IP.
select * from log where ip = '192.168.72.173';