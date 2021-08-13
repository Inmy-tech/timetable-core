# Timetable-Core

In some time I home this will be server of the project I want to create.

I felt pain when I hadn't found proper solution for non-paper time management.

So I decided to create such solution by myself.

## Tech stack I want to use:
* The latest JDK
* The latest Spring Framework Stack
* Kubernetes
* Reactive
* RSocket
* R2DBC
* PostgreSQL
* Docker
* Github Actions
* AWS
* ReactJs
* OAuth2

## Database:
run `docker run --name timetable-postgres -p 5433:5432 -v /timetable/postgres/core/data:/var/lib/postgresql/data -e POSTGRES_USER=timetable -e POSTGRES_DB=timetable -e POSTGRES_PASSWORD=timetable -d postgres:12.3-alpine`
