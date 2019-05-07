SELECT *FROM <table>

SELECT password FROM patient WHERE pid=<number>

SELECT * FROM doctor WHERE docid=<number>

SELECT * FROM doctor WHERE docid=<number>

SELECT reg.reg_id,pat.name, reg.reg_datetime,cat.speciallist FROM (	SELECT reg_id,pid,reg_datetime,catid	FROM register	WHERE docid=<number> AND reg_datetime>=<start> AND reg_datetime<=<end>) as reg inner join (	SELECT pid,name	FROM patient) as pat on reg.pid=pat.pid inner join (	SELECT reg_id, specialist	FROM register_category) as cat on reg.catid=cat.catid

SELECT dep.name as depname, reg.docid, doc.name as docname, cat.specialist, reg.current_reg_fount, SUM(reg.fee) as sum FROM ( SELECT * FROM register WHERE reg_datetime>= <start> AND reg_datetime<= <end> ) as reg inner join (	SELECT docid,name,depid	FROM doctor) as doc on reg.docid=doc.docid inner join(	SELECT depid,name	FROM department) as dep on doc.depid=dep.depid inner join(	SELECT reg_id,specialist	FROM register_category) as cat on reg.catid=cat.catid GROUP BY reg.docid, cat.specialist

UPDATE patient SET last_login_datetime=<time> WHERE pid=<number>

UPDATE doctor SET last_login_datetime=<time> WHERE docid=<number>