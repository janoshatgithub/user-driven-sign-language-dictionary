-- Extra constrains
alter table ApplicationUser
add constraint valid_user_roles
check (userrole in ('ADMIN', 'NORMAL'));

alter table ApplicationUser
add constraint password_length_ge_3
check (length(password) >= 3);

-- Test data
---------------------------------------
-- Users
insert into applicationuser 
  (login, password, userrole, fullname, email, emailverificationsent,
   emailverified, version) 
values 
  ('jsh', 'jsh', 'NORMAL', 'Jan Schrøder Hansen', 'jsh@jsh.dk', 
   '2011-10-29 10:10:10', '2011-10-29 10:10:12', 1);

insert into applicationuser 
  (login, password, userrole, fullname, email, emailverificationsent,
   emailverified, version) 
values 
  ('tki', 'tki', 'NORMAL', 'Tanja Kikkenborg', 'tki@tki.dk', 
   '2011-10-29 10:10:10', '2011-10-29 10:10:12', 1);
