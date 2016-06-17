1.
select sum(tmp.cnt) as SUM from(
select COUNT(*) cnt from news
union 
select COUNT(*) from reviews) tmp

2.
select nc_name, count(n_id)
from news
right join news_categories
on (news.n_category = news_categories.nc_id)
group by nc_name;

3.
select rc_name, count(r_id) cnt
from reviews_categories
left join reviews
on (reviews.r_category = reviews_categories.rc_id)
group by rc_name;

4.
select MAX(tmp.n_dt) as last_date, tmp.category_name from
(
select n_dt, nc_name as category_name
from news 
join news_categories
on (news.n_category = news_categories.nc_id)

union all

select r_dt, rc_name
from reviews
join reviews_categories
on (reviews.r_category = reviews_categories.rc_id)) tmp
Group by tmp.category_name;

5.
select p.p_name, bp.b_id, b_url
from pages p
join m2m_banners_pages bp
on (p.p_id = bp.p_id)
join banners b
on (bp.b_id = b.b_id)
where p.p_parent is null;

6.
select distinct p.p_name from pages p 
join m2m_banners_pages bp
on (p.p_id = bp.p_id)

7.
select distinct p.p_name from pages p 
left join m2m_banners_pages bp
on (p.p_id = bp.p_id)
where bp.p_id is null;

8.
select distinct bp.b_id, b.b_url 
from m2m_banners_pages bp
join banners b
on (bp.b_id = b.b_id)

9.
select distinct b.b_id, b.b_url 
from m2m_banners_pages bp
right join banners b
on (bp.b_id = b.b_id)
where bp.b_id is null;

10.
select * from
(select b_id, b_url, b_click*100/b_show as rate
from banners
where b_show > 0) tmp
where rate >= 80;

11.
select distinct p.p_name
from pages p 
join m2m_banners_pages bp
on (p.p_id = bp.p_id)
join banners b 
on (b.b_id = bp.b_id)
where b.b_text is not null AND NOT(b.b_text LIKE ('NULL'));

12.
select distinct p.p_name
from pages p 
join m2m_banners_pages bp
on (p.p_id = bp.p_id)
join banners b 
on (b.b_id = bp.b_id)
where b.b_pic is not null AND NOT(b.b_text LIKE ('NULL'));

13.
select * from(
select n_header header, n_dt date
from news 
union 
select r_header, r_dt
from reviews) tmp
where tmp.date between to_date(2011) and to_date(2012); //TODO!!!!!! тут ошибка

14.
select nc.nc_name, n.n_id
from news n
right join news_categories nc 
on (n.n_category = nc.nc_id)
where n.n_id is null

union all

select rc.rc_name, r.r_id
from reviews r
right join reviews_categories rc
on(r.r_category = rc.rc_id)
where r.r_id is null;

15.
select nc.nc_name, n.n_id
from news n
right join news_categories nc 
on (n.n_category = nc.nc_id)
where n.n_id is null

union all

select rc.rc_name, r.r_id
from reviews r
right join reviews_categories rc
on(r.r_category = rc.rc_id)
where r.r_id is null;

16.
select YEAR(n_dt) year, COUNT(n_id) cnt 
from news 
group by year;

17.
select tmp.b_url, bn.b_id from(
select b.b_url, count(b.b_id) as cnt
from banners b
group by b.b_url
having cnt > 1) tmp, banners bn
where bn.b_url = tmp.b_url;

18.
select p.p_name, b.b_id, b.b_url
from pages p
join m2m_banners_pages bp on (p.p_id = bp.p_id)
join banners b on (bp.b_id = b.b_id)
where p.p_parent = 
	(select p_id 
	from pages 
	where p_name = 'Юридическим лицам');

19.
select b_id, b_url, b_click/b_show as rate
from banners
where b_pic is not null AND NOT(b_pic LIKE 'NULL')
order by rate DESC;

20.
select tmp.header, tmp.date from(
select n_header header, n_dt as date from news
union all
select r_header, r_dt from reviews) tmp
where tmp.date = (select min(tmp.date) from 
(select n_header header, n_dt as date from news
union all
select r_header, r_dt from reviews) tmp);

21.
select url, id from(
select b.b_url url, b.b_id id, Count(b.b_id) cnt
from banners b
group by b.b_url) tmp
where cnt = 1;

22.
select p.p_name name, count from(
select p_id id, count(b_id) as count
from m2m_banners_pages
group by p_id) tmp
join pages p
on (tmp.id = p.p_id)
order by count desc, name asc;

23.
select n.n_header header, n.n_dt date
from news n
where n.n_dt = (select max(n_dt) from news)

union all

select r.r_header, r.r_dt
from reviews r
where r.r_dt = (select max(r_dt) from reviews)

24.
select b_id, b_url, b_text, SUBSTRING(b_url,8) as pattern from banners
where b_text LIKE (CONCAT('%',SUBSTRING(b_url,8),'%'));

25.
select p.p_name from 
(select b.b_id from banners b 
where b.b_click/b.b_show = (select max(b_click/b_show) from banners)) b

join m2m_banners_pages bp on (b.b_id = bp.b_id)
join pages p on (bp.p_id = p.p_id);

26.
select avg(b_click/b_show) from banners
where b_show >=1;

27.
select avg(b_click/b_show) from banners
where b_pic is null OR b_pic LIKE ('NULL');

28.
select sum(tmp.cnt) as COUNT
from(
	select bp.p_id, COUNT(bp.p_id) cnt
	from m2m_banners_pages bp
	group by bp.b_id) tmp, pages p
where tmp.p_id = p.p_id AND p.p_parent is NULL;

29.
select tmp1.b_id, tmp1.cnt as count, b.b_url 
from
	(select bp.b_id, COUNT(bp.p_id) as cnt
	from m2m_banners_pages bp
	group by bp.b_id) tmp1
join banners b
on(tmp1.b_id = b.b_id)

where tmp1.cnt = (
	select MAX(tmp2.cnt) 
	from
		(select bp.b_id, COUNT(bp.p_id) as cnt
		from m2m_banners_pages bp
		group by bp.b_id) tmp2)

30.
select p.p_name, tmp1.cnt as COUNT from(
select p_id, COUNT(b_id) cnt
from m2m_banners_pages
GROUP BY(p_id)) tmp1
join pages p on (tmp1.p_id = p.p_id)
where cnt = 
	(select max(tmp2.cnt) 
	 from (select COUNT(b_id) cnt from m2m_banners_pages GROUP BY(p_id))tmp2)