# How to run this docker on your machine.

- First build the image docker with this command: `docker build -t  postegresql_brokermanagement`

- Second execute a command to get the container Id `docker ps` 

- Third execute the container `docker container run -it -p 5432:5432  ${containerId}`

- Fourth to get into the postegresql cli execute: ` psql -h localhost -p 5432 -d brokermanagement -U brokermanagement --password`

### Reference

- [Docker Documentation](https://docs.docker.com/engine/examples/postgresql_service/#connecting-from-your-host-system)
