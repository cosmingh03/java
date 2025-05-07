-- Create tables
create table if not exists continents (
   id   serial primary key,
   name varchar(255) not null unique
);

create table if not exists countries (
   id        serial primary key,
   name      varchar(255) not null,
   code      varchar(2),
   continent int,
   foreign key ( continent )
      references continents ( id )
);

-- Add cities table
create table if not exists cities (
   id        serial primary key,
   name      varchar(255) not null,
   country   int not null,
   capital   boolean default false,
   latitude  double precision,
   longitude double precision,
   foreign key ( country )
      references countries ( id )
);

-- Insert continents if they don't exist
insert into continents ( name )
   select 'Europe'
    where not exists (
      select 1
        from continents
       where name = 'Europe'
   );

insert into continents ( name )
   select 'Asia'
    where not exists (
      select 1
        from continents
       where name = 'Asia'
   );

insert into continents ( name )
   select 'Africa'
    where not exists (
      select 1
        from continents
       where name = 'Africa'
   );

insert into continents ( name )
   select 'North America'
    where not exists (
      select 1
        from continents
       where name = 'North America'
   );

insert into continents ( name )
   select 'South America'
    where not exists (
      select 1
        from continents
       where name = 'South America'
   );

insert into continents ( name )
   select 'Australia'
    where not exists (
      select 1
        from continents
       where name = 'Australia'
   );

insert into continents ( name )
   select 'Antarctica'
    where not exists (
      select 1
        from continents
       where name = 'Antarctica'
   );