FROM postgres:14.2

USER root

COPY scripts/init_db.sh /docker-entrypoint-initdb.d/
RUN chmod +x /docker-entrypoint-initdb.d/*
RUN sed -i -e 's/\r$//' /docker-entrypoint-initdb.d/init_db.sh